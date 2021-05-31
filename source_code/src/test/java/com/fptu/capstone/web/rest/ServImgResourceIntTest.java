package com.fptu.capstone.web.rest;

import com.fptu.capstone.HomespaApp;

import com.fptu.capstone.domain.ServImg;
import com.fptu.capstone.repository.ServImgRepository;
import com.fptu.capstone.service.ServImgService;
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
 * Test class for the ServImgResource REST controller.
 *
 * @see ServImgResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HomespaApp.class)
public class ServImgResourceIntTest {

    private static final String DEFAULT_IMG_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMG_URL = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_INDEX = 1L;
    private static final Long UPDATED_INDEX = 2L;

    @Autowired
    private ServImgRepository servImgRepository;
    
    @Autowired
    private ServImgService servImgService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restServImgMockMvc;

    private ServImg servImg;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ServImgResource servImgResource = new ServImgResource(servImgService);
        this.restServImgMockMvc = MockMvcBuilders.standaloneSetup(servImgResource)
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
    public static ServImg createEntity(EntityManager em) {
        ServImg servImg = new ServImg()
            .imgUrl(DEFAULT_IMG_URL)
            .status(DEFAULT_STATUS)
            .index(DEFAULT_INDEX);
        return servImg;
    }

    @Before
    public void initTest() {
        servImg = createEntity(em);
    }

    @Test
    @Transactional
    public void createServImg() throws Exception {
        int databaseSizeBeforeCreate = servImgRepository.findAll().size();

        // Create the ServImg
        restServImgMockMvc.perform(post("/api/serv-imgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(servImg)))
            .andExpect(status().isCreated());

        // Validate the ServImg in the database
        List<ServImg> servImgList = servImgRepository.findAll();
        assertThat(servImgList).hasSize(databaseSizeBeforeCreate + 1);
        ServImg testServImg = servImgList.get(servImgList.size() - 1);
        assertThat(testServImg.getImgUrl()).isEqualTo(DEFAULT_IMG_URL);
        assertThat(testServImg.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testServImg.getIndex()).isEqualTo(DEFAULT_INDEX);
    }

    @Test
    @Transactional
    public void createServImgWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = servImgRepository.findAll().size();

        // Create the ServImg with an existing ID
        servImg.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restServImgMockMvc.perform(post("/api/serv-imgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(servImg)))
            .andExpect(status().isBadRequest());

        // Validate the ServImg in the database
        List<ServImg> servImgList = servImgRepository.findAll();
        assertThat(servImgList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllServImgs() throws Exception {
        // Initialize the database
        servImgRepository.saveAndFlush(servImg);

        // Get all the servImgList
        restServImgMockMvc.perform(get("/api/serv-imgs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(servImg.getId().intValue())))
            .andExpect(jsonPath("$.[*].imgUrl").value(hasItem(DEFAULT_IMG_URL.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].index").value(hasItem(DEFAULT_INDEX.intValue())));
    }
    
    @Test
    @Transactional
    public void getServImg() throws Exception {
        // Initialize the database
        servImgRepository.saveAndFlush(servImg);

        // Get the servImg
        restServImgMockMvc.perform(get("/api/serv-imgs/{id}", servImg.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(servImg.getId().intValue()))
            .andExpect(jsonPath("$.imgUrl").value(DEFAULT_IMG_URL.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.index").value(DEFAULT_INDEX.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingServImg() throws Exception {
        // Get the servImg
        restServImgMockMvc.perform(get("/api/serv-imgs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateServImg() throws Exception {
        // Initialize the database
        servImgService.save(servImg);

        int databaseSizeBeforeUpdate = servImgRepository.findAll().size();

        // Update the servImg
        ServImg updatedServImg = servImgRepository.findById(servImg.getId()).get();
        // Disconnect from session so that the updates on updatedServImg are not directly saved in db
        em.detach(updatedServImg);
        updatedServImg
            .imgUrl(UPDATED_IMG_URL)
            .status(UPDATED_STATUS)
            .index(UPDATED_INDEX);

        restServImgMockMvc.perform(put("/api/serv-imgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedServImg)))
            .andExpect(status().isOk());

        // Validate the ServImg in the database
        List<ServImg> servImgList = servImgRepository.findAll();
        assertThat(servImgList).hasSize(databaseSizeBeforeUpdate);
        ServImg testServImg = servImgList.get(servImgList.size() - 1);
        assertThat(testServImg.getImgUrl()).isEqualTo(UPDATED_IMG_URL);
        assertThat(testServImg.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testServImg.getIndex()).isEqualTo(UPDATED_INDEX);
    }

    @Test
    @Transactional
    public void updateNonExistingServImg() throws Exception {
        int databaseSizeBeforeUpdate = servImgRepository.findAll().size();

        // Create the ServImg

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServImgMockMvc.perform(put("/api/serv-imgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(servImg)))
            .andExpect(status().isBadRequest());

        // Validate the ServImg in the database
        List<ServImg> servImgList = servImgRepository.findAll();
        assertThat(servImgList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteServImg() throws Exception {
        // Initialize the database
        servImgService.save(servImg);

        int databaseSizeBeforeDelete = servImgRepository.findAll().size();

        // Get the servImg
        restServImgMockMvc.perform(delete("/api/serv-imgs/{id}", servImg.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ServImg> servImgList = servImgRepository.findAll();
        assertThat(servImgList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServImg.class);
        ServImg servImg1 = new ServImg();
        servImg1.setId(1L);
        ServImg servImg2 = new ServImg();
        servImg2.setId(servImg1.getId());
        assertThat(servImg1).isEqualTo(servImg2);
        servImg2.setId(2L);
        assertThat(servImg1).isNotEqualTo(servImg2);
        servImg1.setId(null);
        assertThat(servImg1).isNotEqualTo(servImg2);
    }
}
