package com.fptu.capstone.web.rest;

import com.fptu.capstone.HomespaApp;

import com.fptu.capstone.domain.Voucher;
import com.fptu.capstone.repository.VoucherRepository;
import com.fptu.capstone.service.VoucherService;
import com.fptu.capstone.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static com.fptu.capstone.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VoucherResource REST controller.
 *
 * @see VoucherResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HomespaApp.class)
public class VoucherResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DISCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DISCRIPTION = "BBBBBBBBBB";

    private static final Float DEFAULT_DISCOUNT = 1F;
    private static final Float UPDATED_DISCOUNT = 2F;

    @Autowired
    private VoucherRepository voucherRepository;

    @Mock
    private VoucherRepository voucherRepositoryMock;
    

    @Mock
    private VoucherService voucherServiceMock;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVoucherMockMvc;

    private Voucher voucher;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VoucherResource voucherResource = new VoucherResource(voucherService);
        this.restVoucherMockMvc = MockMvcBuilders.standaloneSetup(voucherResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Voucher createEntity(EntityManager em) {
        Voucher voucher = new Voucher()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .discription(DEFAULT_DISCRIPTION)
            .discount(DEFAULT_DISCOUNT);
        return voucher;
    }

    @Before
    public void initTest() {
        voucher = createEntity(em);
    }

    @Test
    @Transactional
    public void createVoucher() throws Exception {
        int databaseSizeBeforeCreate = voucherRepository.findAll().size();

        // Create the Voucher
        restVoucherMockMvc.perform(post("/api/vouchers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voucher)))
            .andExpect(status().isCreated());

        // Validate the Voucher in the database
        List<Voucher> voucherList = voucherRepository.findAll();
        assertThat(voucherList).hasSize(databaseSizeBeforeCreate + 1);
        Voucher testVoucher = voucherList.get(voucherList.size() - 1);
        assertThat(testVoucher.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testVoucher.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testVoucher.getDiscription()).isEqualTo(DEFAULT_DISCRIPTION);
        assertThat(testVoucher.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
    }

    @Test
    @Transactional
    public void createVoucherWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = voucherRepository.findAll().size();

        // Create the Voucher with an existing ID
        voucher.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVoucherMockMvc.perform(post("/api/vouchers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voucher)))
            .andExpect(status().isBadRequest());

        // Validate the Voucher in the database
        List<Voucher> voucherList = voucherRepository.findAll();
        assertThat(voucherList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllVouchers() throws Exception {
        // Initialize the database
        voucherRepository.saveAndFlush(voucher);

        // Get all the voucherList
        restVoucherMockMvc.perform(get("/api/vouchers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(voucher.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].discription").value(hasItem(DEFAULT_DISCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.doubleValue())));
    }
    
    public void getAllVouchersWithEagerRelationshipsIsEnabled() throws Exception {
        VoucherResource voucherResource = new VoucherResource(voucherServiceMock);
        when(voucherServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restVoucherMockMvc = MockMvcBuilders.standaloneSetup(voucherResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restVoucherMockMvc.perform(get("/api/vouchers?eagerload=true"))
        .andExpect(status().isOk());

        verify(voucherServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllVouchersWithEagerRelationshipsIsNotEnabled() throws Exception {
        VoucherResource voucherResource = new VoucherResource(voucherServiceMock);
            when(voucherServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restVoucherMockMvc = MockMvcBuilders.standaloneSetup(voucherResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restVoucherMockMvc.perform(get("/api/vouchers?eagerload=true"))
        .andExpect(status().isOk());

            verify(voucherServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getVoucher() throws Exception {
        // Initialize the database
        voucherRepository.saveAndFlush(voucher);

        // Get the voucher
        restVoucherMockMvc.perform(get("/api/vouchers/{id}", voucher.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(voucher.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.discription").value(DEFAULT_DISCRIPTION.toString()))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingVoucher() throws Exception {
        // Get the voucher
        restVoucherMockMvc.perform(get("/api/vouchers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVoucher() throws Exception {
        // Initialize the database
        voucherService.save(voucher);

        int databaseSizeBeforeUpdate = voucherRepository.findAll().size();

        // Update the voucher
        Voucher updatedVoucher = voucherRepository.findById(voucher.getId()).get();
        // Disconnect from session so that the updates on updatedVoucher are not directly saved in db
        em.detach(updatedVoucher);
        updatedVoucher
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .discription(UPDATED_DISCRIPTION)
            .discount(UPDATED_DISCOUNT);

        restVoucherMockMvc.perform(put("/api/vouchers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedVoucher)))
            .andExpect(status().isOk());

        // Validate the Voucher in the database
        List<Voucher> voucherList = voucherRepository.findAll();
        assertThat(voucherList).hasSize(databaseSizeBeforeUpdate);
        Voucher testVoucher = voucherList.get(voucherList.size() - 1);
        assertThat(testVoucher.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testVoucher.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testVoucher.getDiscription()).isEqualTo(UPDATED_DISCRIPTION);
        assertThat(testVoucher.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingVoucher() throws Exception {
        int databaseSizeBeforeUpdate = voucherRepository.findAll().size();

        // Create the Voucher

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVoucherMockMvc.perform(put("/api/vouchers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voucher)))
            .andExpect(status().isBadRequest());

        // Validate the Voucher in the database
        List<Voucher> voucherList = voucherRepository.findAll();
        assertThat(voucherList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVoucher() throws Exception {
        // Initialize the database
        voucherService.save(voucher);

        int databaseSizeBeforeDelete = voucherRepository.findAll().size();

        // Get the voucher
        restVoucherMockMvc.perform(delete("/api/vouchers/{id}", voucher.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Voucher> voucherList = voucherRepository.findAll();
        assertThat(voucherList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Voucher.class);
        Voucher voucher1 = new Voucher();
        voucher1.setId(1L);
        Voucher voucher2 = new Voucher();
        voucher2.setId(voucher1.getId());
        assertThat(voucher1).isEqualTo(voucher2);
        voucher2.setId(2L);
        assertThat(voucher1).isNotEqualTo(voucher2);
        voucher1.setId(null);
        assertThat(voucher1).isNotEqualTo(voucher2);
    }
}
