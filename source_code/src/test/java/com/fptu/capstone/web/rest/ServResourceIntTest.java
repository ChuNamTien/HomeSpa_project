package com.fptu.capstone.web.rest;

import com.fptu.capstone.HomespaApp;

import com.fptu.capstone.domain.Serv;
import com.fptu.capstone.repository.ServRepository;
import com.fptu.capstone.service.ServService;
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
 * Test class for the ServResource REST controller.
 *
 * @see ServResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HomespaApp.class)
public class ServResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ServRepository servRepository;
    
    @Autowired
    private ServService servService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restServMockMvc;

    private Serv serv;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ServResource servResource = new ServResource(servService);
        this.restServMockMvc = MockMvcBuilders.standaloneSetup(servResource)
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
    public static Serv createEntity(EntityManager em) {
        Serv serv = new Serv()
            .name(DEFAULT_NAME)
            .customerType(DEFAULT_CUSTOMER_TYPE)
            .description(DEFAULT_DESCRIPTION);
        return serv;
    }

    @Before
    public void initTest() {
        serv = createEntity(em);
    }

    @Test
    @Transactional
    public void createServ() throws Exception {
        int databaseSizeBeforeCreate = servRepository.findAll().size();

        // Create the Serv
        restServMockMvc.perform(post("/api/servs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serv)))
            .andExpect(status().isCreated());

        // Validate the Serv in the database
        List<Serv> servList = servRepository.findAll();
        assertThat(servList).hasSize(databaseSizeBeforeCreate + 1);
        Serv testServ = servList.get(servList.size() - 1);
        assertThat(testServ.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testServ.getCustomerType()).isEqualTo(DEFAULT_CUSTOMER_TYPE);
        assertThat(testServ.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createServWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = servRepository.findAll().size();

        // Create the Serv with an existing ID
        serv.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restServMockMvc.perform(post("/api/servs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serv)))
            .andExpect(status().isBadRequest());

        // Validate the Serv in the database
        List<Serv> servList = servRepository.findAll();
        assertThat(servList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllServs() throws Exception {
        // Initialize the database
        servRepository.saveAndFlush(serv);

        // Get all the servList
        restServMockMvc.perform(get("/api/servs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serv.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].customerType").value(hasItem(DEFAULT_CUSTOMER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getServ() throws Exception {
        // Initialize the database
        servRepository.saveAndFlush(serv);

        // Get the serv
        restServMockMvc.perform(get("/api/servs/{id}", serv.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(serv.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.customerType").value(DEFAULT_CUSTOMER_TYPE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingServ() throws Exception {
        // Get the serv
        restServMockMvc.perform(get("/api/servs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateServ() throws Exception {
        // Initialize the database
        servService.save(serv);

        int databaseSizeBeforeUpdate = servRepository.findAll().size();

        // Update the serv
        Serv updatedServ = servRepository.findById(serv.getId()).get();
        // Disconnect from session so that the updates on updatedServ are not directly saved in db
        em.detach(updatedServ);
        updatedServ
            .name(UPDATED_NAME)
            .customerType(UPDATED_CUSTOMER_TYPE)
            .description(UPDATED_DESCRIPTION);

        restServMockMvc.perform(put("/api/servs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedServ)))
            .andExpect(status().isOk());

        // Validate the Serv in the database
        List<Serv> servList = servRepository.findAll();
        assertThat(servList).hasSize(databaseSizeBeforeUpdate);
        Serv testServ = servList.get(servList.size() - 1);
        assertThat(testServ.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testServ.getCustomerType()).isEqualTo(UPDATED_CUSTOMER_TYPE);
        assertThat(testServ.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingServ() throws Exception {
        int databaseSizeBeforeUpdate = servRepository.findAll().size();

        // Create the Serv

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServMockMvc.perform(put("/api/servs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serv)))
            .andExpect(status().isBadRequest());

        // Validate the Serv in the database
        List<Serv> servList = servRepository.findAll();
        assertThat(servList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteServ() throws Exception {
        // Initialize the database
        servService.save(serv);

        int databaseSizeBeforeDelete = servRepository.findAll().size();

        // Get the serv
        restServMockMvc.perform(delete("/api/servs/{id}", serv.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Serv> servList = servRepository.findAll();
        assertThat(servList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Serv.class);
        Serv serv1 = new Serv();
        serv1.setId(1L);
        Serv serv2 = new Serv();
        serv2.setId(serv1.getId());
        assertThat(serv1).isEqualTo(serv2);
        serv2.setId(2L);
        assertThat(serv1).isNotEqualTo(serv2);
        serv1.setId(null);
        assertThat(serv1).isNotEqualTo(serv2);
    }
}
