package com.fptu.capstone.web.rest;

import com.fptu.capstone.HomespaApp;

import com.fptu.capstone.domain.PartnerImg;
import com.fptu.capstone.repository.PartnerImgRepository;
import com.fptu.capstone.service.PartnerImgService;
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
 * Test class for the PartnerImgResource REST controller.
 *
 * @see PartnerImgResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HomespaApp.class)
public class PartnerImgResourceIntTest {

    private static final Long DEFAULT_PARTNER_ID = 1L;
    private static final Long UPDATED_PARTNER_ID = 2L;

    private static final String DEFAULT_IMG_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMG_URL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_HIDDENT = false;
    private static final Boolean UPDATED_IS_HIDDENT = true;

    private static final Long DEFAULT_INDEX = 1L;
    private static final Long UPDATED_INDEX = 2L;

    @Autowired
    private PartnerImgRepository partnerImgRepository;
    
    @Autowired
    private PartnerImgService partnerImgService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPartnerImgMockMvc;

    private PartnerImg partnerImg;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PartnerImgResource partnerImgResource = new PartnerImgResource(partnerImgService);
        this.restPartnerImgMockMvc = MockMvcBuilders.standaloneSetup(partnerImgResource)
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
    public static PartnerImg createEntity(EntityManager em) {
        PartnerImg partnerImg = new PartnerImg()
            .partnerId(DEFAULT_PARTNER_ID)
            .imgUrl(DEFAULT_IMG_URL)
            .isHiddent(DEFAULT_IS_HIDDENT)
            .index(DEFAULT_INDEX);
        return partnerImg;
    }

    @Before
    public void initTest() {
        partnerImg = createEntity(em);
    }

    @Test
    @Transactional
    public void createPartnerImg() throws Exception {
        int databaseSizeBeforeCreate = partnerImgRepository.findAll().size();

        // Create the PartnerImg
        restPartnerImgMockMvc.perform(post("/api/partner-imgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partnerImg)))
            .andExpect(status().isCreated());

        // Validate the PartnerImg in the database
        List<PartnerImg> partnerImgList = partnerImgRepository.findAll();
        assertThat(partnerImgList).hasSize(databaseSizeBeforeCreate + 1);
        PartnerImg testPartnerImg = partnerImgList.get(partnerImgList.size() - 1);
        assertThat(testPartnerImg.getPartnerId()).isEqualTo(DEFAULT_PARTNER_ID);
        assertThat(testPartnerImg.getImgUrl()).isEqualTo(DEFAULT_IMG_URL);
        assertThat(testPartnerImg.isIsHiddent()).isEqualTo(DEFAULT_IS_HIDDENT);
        assertThat(testPartnerImg.getIndex()).isEqualTo(DEFAULT_INDEX);
    }

    @Test
    @Transactional
    public void createPartnerImgWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = partnerImgRepository.findAll().size();

        // Create the PartnerImg with an existing ID
        partnerImg.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPartnerImgMockMvc.perform(post("/api/partner-imgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partnerImg)))
            .andExpect(status().isBadRequest());

        // Validate the PartnerImg in the database
        List<PartnerImg> partnerImgList = partnerImgRepository.findAll();
        assertThat(partnerImgList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPartnerImgs() throws Exception {
        // Initialize the database
        partnerImgRepository.saveAndFlush(partnerImg);

        // Get all the partnerImgList
        restPartnerImgMockMvc.perform(get("/api/partner-imgs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(partnerImg.getId().intValue())))
            .andExpect(jsonPath("$.[*].partnerId").value(hasItem(DEFAULT_PARTNER_ID.intValue())))
            .andExpect(jsonPath("$.[*].imgUrl").value(hasItem(DEFAULT_IMG_URL.toString())))
            .andExpect(jsonPath("$.[*].isHiddent").value(hasItem(DEFAULT_IS_HIDDENT.booleanValue())))
            .andExpect(jsonPath("$.[*].index").value(hasItem(DEFAULT_INDEX.intValue())));
    }
    
    @Test
    @Transactional
    public void getPartnerImg() throws Exception {
        // Initialize the database
        partnerImgRepository.saveAndFlush(partnerImg);

        // Get the partnerImg
        restPartnerImgMockMvc.perform(get("/api/partner-imgs/{id}", partnerImg.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(partnerImg.getId().intValue()))
            .andExpect(jsonPath("$.partnerId").value(DEFAULT_PARTNER_ID.intValue()))
            .andExpect(jsonPath("$.imgUrl").value(DEFAULT_IMG_URL.toString()))
            .andExpect(jsonPath("$.isHiddent").value(DEFAULT_IS_HIDDENT.booleanValue()))
            .andExpect(jsonPath("$.index").value(DEFAULT_INDEX.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPartnerImg() throws Exception {
        // Get the partnerImg
        restPartnerImgMockMvc.perform(get("/api/partner-imgs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePartnerImg() throws Exception {
        // Initialize the database
        partnerImgService.save(partnerImg);

        int databaseSizeBeforeUpdate = partnerImgRepository.findAll().size();

        // Update the partnerImg
        PartnerImg updatedPartnerImg = partnerImgRepository.findById(partnerImg.getId()).get();
        // Disconnect from session so that the updates on updatedPartnerImg are not directly saved in db
        em.detach(updatedPartnerImg);
        updatedPartnerImg
            .partnerId(UPDATED_PARTNER_ID)
            .imgUrl(UPDATED_IMG_URL)
            .isHiddent(UPDATED_IS_HIDDENT)
            .index(UPDATED_INDEX);

        restPartnerImgMockMvc.perform(put("/api/partner-imgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPartnerImg)))
            .andExpect(status().isOk());

        // Validate the PartnerImg in the database
        List<PartnerImg> partnerImgList = partnerImgRepository.findAll();
        assertThat(partnerImgList).hasSize(databaseSizeBeforeUpdate);
        PartnerImg testPartnerImg = partnerImgList.get(partnerImgList.size() - 1);
        assertThat(testPartnerImg.getPartnerId()).isEqualTo(UPDATED_PARTNER_ID);
        assertThat(testPartnerImg.getImgUrl()).isEqualTo(UPDATED_IMG_URL);
        assertThat(testPartnerImg.isIsHiddent()).isEqualTo(UPDATED_IS_HIDDENT);
        assertThat(testPartnerImg.getIndex()).isEqualTo(UPDATED_INDEX);
    }

    @Test
    @Transactional
    public void updateNonExistingPartnerImg() throws Exception {
        int databaseSizeBeforeUpdate = partnerImgRepository.findAll().size();

        // Create the PartnerImg

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPartnerImgMockMvc.perform(put("/api/partner-imgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partnerImg)))
            .andExpect(status().isBadRequest());

        // Validate the PartnerImg in the database
        List<PartnerImg> partnerImgList = partnerImgRepository.findAll();
        assertThat(partnerImgList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePartnerImg() throws Exception {
        // Initialize the database
        partnerImgService.save(partnerImg);

        int databaseSizeBeforeDelete = partnerImgRepository.findAll().size();

        // Get the partnerImg
        restPartnerImgMockMvc.perform(delete("/api/partner-imgs/{id}", partnerImg.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PartnerImg> partnerImgList = partnerImgRepository.findAll();
        assertThat(partnerImgList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PartnerImg.class);
        PartnerImg partnerImg1 = new PartnerImg();
        partnerImg1.setId(1L);
        PartnerImg partnerImg2 = new PartnerImg();
        partnerImg2.setId(partnerImg1.getId());
        assertThat(partnerImg1).isEqualTo(partnerImg2);
        partnerImg2.setId(2L);
        assertThat(partnerImg1).isNotEqualTo(partnerImg2);
        partnerImg1.setId(null);
        assertThat(partnerImg1).isNotEqualTo(partnerImg2);
    }
}
