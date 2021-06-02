package com.fptu.capstone.web.rest;

import com.fptu.capstone.HomespaApp;

import com.fptu.capstone.domain.Partner;
import com.fptu.capstone.repository.PartnerRepository;
import com.fptu.capstone.service.PartnerService;
import com.fptu.capstone.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.fptu.capstone.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PartnerResource REST controller.
 *
 * @see PartnerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HomespaApp.class)
public class PartnerResourceIntTest {

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PARTNER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final Float DEFAULT_LONG_COORD = 1F;
    private static final Float UPDATED_LONG_COORD = 2F;

    private static final Float DEFAULT_LAT_COORD = 1F;
    private static final Float UPDATED_LAT_COORD = 2F;

    private static final Float DEFAULT_OPEN_TIME = 1F;
    private static final Float UPDATED_OPEN_TIME = 2F;

    private static final Float DEFAULT_CLOSE_TIME = 1F;
    private static final Float UPDATED_CLOSE_TIME = 2F;

    private static final Boolean DEFAULT_IS_WEEKEND_OPEN = false;
    private static final Boolean UPDATED_IS_WEEKEND_OPEN = true;

    private static final Boolean DEFAULT_STATUS = false;
    private static final Boolean UPDATED_STATUS = true;

    private static final Float DEFAULT_CONFIRM_AFTER = 1F;
    private static final Float UPDATED_CONFIRM_AFTER = 2F;

    private static final String DEFAULT_BUSSINESS_LICENSE_URL = "AAAAAAAAAA";
    private static final String UPDATED_BUSSINESS_LICENSE_URL = "BBBBBBBBBB";

    @Autowired
    private PartnerRepository partnerRepository;
    
    @Autowired
    private PartnerService partnerService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPartnerMockMvc;

    private Partner partner;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PartnerResource partnerResource = new PartnerResource(partnerService);
        this.restPartnerMockMvc = MockMvcBuilders.standaloneSetup(partnerResource)
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
    public static Partner createEntity(EntityManager em) {
        Partner partner = new Partner()
            .userId(DEFAULT_USER_ID)
            .name(DEFAULT_NAME)
            .partnerType(DEFAULT_PARTNER_TYPE)
            .customerType(DEFAULT_CUSTOMER_TYPE)
            .description(DEFAULT_DESCRIPTION)
            .city(DEFAULT_CITY)
            .address(DEFAULT_ADDRESS)
            .phone(DEFAULT_PHONE)
            .longCoord(DEFAULT_LONG_COORD)
            .latCoord(DEFAULT_LAT_COORD)
            .openTime(DEFAULT_OPEN_TIME)
            .closeTime(DEFAULT_CLOSE_TIME)
            .isWeekendOpen(DEFAULT_IS_WEEKEND_OPEN)
            .status(DEFAULT_STATUS)
            .confirmAfter(DEFAULT_CONFIRM_AFTER)
            .bussinessLicenseUrl(DEFAULT_BUSSINESS_LICENSE_URL);
        return partner;
    }

    @Before
    public void initTest() {
        partner = createEntity(em);
    }

    @Test
    @Transactional
    public void createPartner() throws Exception {
        int databaseSizeBeforeCreate = partnerRepository.findAll().size();

        // Create the Partner
        restPartnerMockMvc.perform(post("/api/partners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partner)))
            .andExpect(status().isCreated());

        // Validate the Partner in the database
        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeCreate + 1);
        Partner testPartner = partnerList.get(partnerList.size() - 1);
        assertThat(testPartner.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testPartner.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPartner.getPartnerType()).isEqualTo(DEFAULT_PARTNER_TYPE);
        assertThat(testPartner.getCustomerType()).isEqualTo(DEFAULT_CUSTOMER_TYPE);
        assertThat(testPartner.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPartner.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testPartner.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testPartner.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testPartner.getLongCoord()).isEqualTo(DEFAULT_LONG_COORD);
        assertThat(testPartner.getLatCoord()).isEqualTo(DEFAULT_LAT_COORD);
        assertThat(testPartner.getOpenTime()).isEqualTo(DEFAULT_OPEN_TIME);
        assertThat(testPartner.getCloseTime()).isEqualTo(DEFAULT_CLOSE_TIME);
        assertThat(testPartner.isIsWeekendOpen()).isEqualTo(DEFAULT_IS_WEEKEND_OPEN);
        assertThat(testPartner.isStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPartner.getConfirmAfter()).isEqualTo(DEFAULT_CONFIRM_AFTER);
        assertThat(testPartner.getBussinessLicenseUrl()).isEqualTo(DEFAULT_BUSSINESS_LICENSE_URL);
    }

    @Test
    @Transactional
    public void createPartnerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = partnerRepository.findAll().size();

        // Create the Partner with an existing ID
        partner.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPartnerMockMvc.perform(post("/api/partners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partner)))
            .andExpect(status().isBadRequest());

        // Validate the Partner in the database
        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPartners() throws Exception {
        // Initialize the database
        partnerRepository.saveAndFlush(partner);

        // Get all the partnerList
        restPartnerMockMvc.perform(get("/api/partners?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(partner.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].partnerType").value(hasItem(DEFAULT_PARTNER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].customerType").value(hasItem(DEFAULT_CUSTOMER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].longCoord").value(hasItem(DEFAULT_LONG_COORD.doubleValue())))
            .andExpect(jsonPath("$.[*].latCoord").value(hasItem(DEFAULT_LAT_COORD.doubleValue())))
            .andExpect(jsonPath("$.[*].openTime").value(hasItem(DEFAULT_OPEN_TIME.doubleValue())))
            .andExpect(jsonPath("$.[*].closeTime").value(hasItem(DEFAULT_CLOSE_TIME.doubleValue())))
            .andExpect(jsonPath("$.[*].isWeekendOpen").value(hasItem(DEFAULT_IS_WEEKEND_OPEN.booleanValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.booleanValue())))
            .andExpect(jsonPath("$.[*].confirmAfter").value(hasItem(DEFAULT_CONFIRM_AFTER.doubleValue())))
            .andExpect(jsonPath("$.[*].bussinessLicenseUrl").value(hasItem(DEFAULT_BUSSINESS_LICENSE_URL.toString())));
    }
    
    @Test
    @Transactional
    public void getPartner() throws Exception {
        // Initialize the database
        partnerRepository.saveAndFlush(partner);

        // Get the partner
        restPartnerMockMvc.perform(get("/api/partners/{id}", partner.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(partner.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.partnerType").value(DEFAULT_PARTNER_TYPE.toString()))
            .andExpect(jsonPath("$.customerType").value(DEFAULT_CUSTOMER_TYPE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.longCoord").value(DEFAULT_LONG_COORD.doubleValue()))
            .andExpect(jsonPath("$.latCoord").value(DEFAULT_LAT_COORD.doubleValue()))
            .andExpect(jsonPath("$.openTime").value(DEFAULT_OPEN_TIME.doubleValue()))
            .andExpect(jsonPath("$.closeTime").value(DEFAULT_CLOSE_TIME.doubleValue()))
            .andExpect(jsonPath("$.isWeekendOpen").value(DEFAULT_IS_WEEKEND_OPEN.booleanValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.booleanValue()))
            .andExpect(jsonPath("$.confirmAfter").value(DEFAULT_CONFIRM_AFTER.doubleValue()))
            .andExpect(jsonPath("$.bussinessLicenseUrl").value(DEFAULT_BUSSINESS_LICENSE_URL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPartner() throws Exception {
        // Get the partner
        restPartnerMockMvc.perform(get("/api/partners/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePartner() throws Exception {
        // Initialize the database
        partnerService.save(partner);

        int databaseSizeBeforeUpdate = partnerRepository.findAll().size();

        // Update the partner
        Partner updatedPartner = partnerRepository.findById(partner.getId()).get();
        // Disconnect from session so that the updates on updatedPartner are not directly saved in db
        em.detach(updatedPartner);
        updatedPartner
            .userId(UPDATED_USER_ID)
            .name(UPDATED_NAME)
            .partnerType(UPDATED_PARTNER_TYPE)
            .customerType(UPDATED_CUSTOMER_TYPE)
            .description(UPDATED_DESCRIPTION)
            .city(UPDATED_CITY)
            .address(UPDATED_ADDRESS)
            .phone(UPDATED_PHONE)
            .longCoord(UPDATED_LONG_COORD)
            .latCoord(UPDATED_LAT_COORD)
            .openTime(UPDATED_OPEN_TIME)
            .closeTime(UPDATED_CLOSE_TIME)
            .isWeekendOpen(UPDATED_IS_WEEKEND_OPEN)
            .status(UPDATED_STATUS)
            .confirmAfter(UPDATED_CONFIRM_AFTER)
            .bussinessLicenseUrl(UPDATED_BUSSINESS_LICENSE_URL);

        restPartnerMockMvc.perform(put("/api/partners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPartner)))
            .andExpect(status().isOk());

        // Validate the Partner in the database
        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeUpdate);
        Partner testPartner = partnerList.get(partnerList.size() - 1);
        assertThat(testPartner.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testPartner.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPartner.getPartnerType()).isEqualTo(UPDATED_PARTNER_TYPE);
        assertThat(testPartner.getCustomerType()).isEqualTo(UPDATED_CUSTOMER_TYPE);
        assertThat(testPartner.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPartner.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testPartner.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testPartner.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testPartner.getLongCoord()).isEqualTo(UPDATED_LONG_COORD);
        assertThat(testPartner.getLatCoord()).isEqualTo(UPDATED_LAT_COORD);
        assertThat(testPartner.getOpenTime()).isEqualTo(UPDATED_OPEN_TIME);
        assertThat(testPartner.getCloseTime()).isEqualTo(UPDATED_CLOSE_TIME);
        assertThat(testPartner.isIsWeekendOpen()).isEqualTo(UPDATED_IS_WEEKEND_OPEN);
        assertThat(testPartner.isStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPartner.getConfirmAfter()).isEqualTo(UPDATED_CONFIRM_AFTER);
        assertThat(testPartner.getBussinessLicenseUrl()).isEqualTo(UPDATED_BUSSINESS_LICENSE_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingPartner() throws Exception {
        int databaseSizeBeforeUpdate = partnerRepository.findAll().size();

        // Create the Partner

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPartnerMockMvc.perform(put("/api/partners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partner)))
            .andExpect(status().isBadRequest());

        // Validate the Partner in the database
        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePartner() throws Exception {
        // Initialize the database
        partnerService.save(partner);

        int databaseSizeBeforeDelete = partnerRepository.findAll().size();

        // Get the partner
        restPartnerMockMvc.perform(delete("/api/partners/{id}", partner.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Partner> partnerList = partnerRepository.findAll();
        assertThat(partnerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Partner.class);
        Partner partner1 = new Partner();
        partner1.setId(1L);
        Partner partner2 = new Partner();
        partner2.setId(partner1.getId());
        assertThat(partner1).isEqualTo(partner2);
        partner2.setId(2L);
        assertThat(partner1).isNotEqualTo(partner2);
        partner1.setId(null);
        assertThat(partner1).isNotEqualTo(partner2);
    }
}
