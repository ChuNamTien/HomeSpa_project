package com.fptu.capstone.web.rest;

import com.fptu.capstone.HomespaApp;

import com.fptu.capstone.domain.BookingActivity;
import com.fptu.capstone.repository.BookingActivityRepository;
import com.fptu.capstone.service.BookingActivityService;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.fptu.capstone.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BookingActivityResource REST controller.
 *
 * @see BookingActivityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HomespaApp.class)
public class BookingActivityResourceIntTest {

    private static final Long DEFAULT_BOOKING_ID = 1L;
    private static final Long UPDATED_BOOKING_ID = 2L;

    private static final Long DEFAULT_STAFF_ID = 1L;
    private static final Long UPDATED_STAFF_ID = 2L;

    private static final Long DEFAULT_TREATMENT_ID = 1L;
    private static final Long UPDATED_TREATMENT_ID = 2L;

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_FINISH_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FINISH_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_STATUS = 1L;
    private static final Long UPDATED_STATUS = 2L;

    @Autowired
    private BookingActivityRepository bookingActivityRepository;
    
    @Autowired
    private BookingActivityService bookingActivityService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBookingActivityMockMvc;

    private BookingActivity bookingActivity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BookingActivityResource bookingActivityResource = new BookingActivityResource(bookingActivityService);
        this.restBookingActivityMockMvc = MockMvcBuilders.standaloneSetup(bookingActivityResource)
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
    public static BookingActivity createEntity(EntityManager em) {
        BookingActivity bookingActivity = new BookingActivity()
            .bookingId(DEFAULT_BOOKING_ID)
            .staffId(DEFAULT_STAFF_ID)
            .treatmentId(DEFAULT_TREATMENT_ID)
            .startDate(DEFAULT_START_DATE)
            .finishDate(DEFAULT_FINISH_DATE)
            .status(DEFAULT_STATUS);
        return bookingActivity;
    }

    @Before
    public void initTest() {
        bookingActivity = createEntity(em);
    }

    @Test
    @Transactional
    public void createBookingActivity() throws Exception {
        int databaseSizeBeforeCreate = bookingActivityRepository.findAll().size();

        // Create the BookingActivity
        restBookingActivityMockMvc.perform(post("/api/booking-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookingActivity)))
            .andExpect(status().isCreated());

        // Validate the BookingActivity in the database
        List<BookingActivity> bookingActivityList = bookingActivityRepository.findAll();
        assertThat(bookingActivityList).hasSize(databaseSizeBeforeCreate + 1);
        BookingActivity testBookingActivity = bookingActivityList.get(bookingActivityList.size() - 1);
        assertThat(testBookingActivity.getBookingId()).isEqualTo(DEFAULT_BOOKING_ID);
        assertThat(testBookingActivity.getStaffId()).isEqualTo(DEFAULT_STAFF_ID);
        assertThat(testBookingActivity.getTreatmentId()).isEqualTo(DEFAULT_TREATMENT_ID);
        assertThat(testBookingActivity.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testBookingActivity.getFinishDate()).isEqualTo(DEFAULT_FINISH_DATE);
        assertThat(testBookingActivity.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createBookingActivityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bookingActivityRepository.findAll().size();

        // Create the BookingActivity with an existing ID
        bookingActivity.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBookingActivityMockMvc.perform(post("/api/booking-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookingActivity)))
            .andExpect(status().isBadRequest());

        // Validate the BookingActivity in the database
        List<BookingActivity> bookingActivityList = bookingActivityRepository.findAll();
        assertThat(bookingActivityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBookingActivities() throws Exception {
        // Initialize the database
        bookingActivityRepository.saveAndFlush(bookingActivity);

        // Get all the bookingActivityList
        restBookingActivityMockMvc.perform(get("/api/booking-activities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bookingActivity.getId().intValue())))
            .andExpect(jsonPath("$.[*].bookingId").value(hasItem(DEFAULT_BOOKING_ID.intValue())))
            .andExpect(jsonPath("$.[*].staffId").value(hasItem(DEFAULT_STAFF_ID.intValue())))
            .andExpect(jsonPath("$.[*].treatmentId").value(hasItem(DEFAULT_TREATMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].finishDate").value(hasItem(DEFAULT_FINISH_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.intValue())));
    }
    
    @Test
    @Transactional
    public void getBookingActivity() throws Exception {
        // Initialize the database
        bookingActivityRepository.saveAndFlush(bookingActivity);

        // Get the bookingActivity
        restBookingActivityMockMvc.perform(get("/api/booking-activities/{id}", bookingActivity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bookingActivity.getId().intValue()))
            .andExpect(jsonPath("$.bookingId").value(DEFAULT_BOOKING_ID.intValue()))
            .andExpect(jsonPath("$.staffId").value(DEFAULT_STAFF_ID.intValue()))
            .andExpect(jsonPath("$.treatmentId").value(DEFAULT_TREATMENT_ID.intValue()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.finishDate").value(DEFAULT_FINISH_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingBookingActivity() throws Exception {
        // Get the bookingActivity
        restBookingActivityMockMvc.perform(get("/api/booking-activities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBookingActivity() throws Exception {
        // Initialize the database
        bookingActivityService.save(bookingActivity);

        int databaseSizeBeforeUpdate = bookingActivityRepository.findAll().size();

        // Update the bookingActivity
        BookingActivity updatedBookingActivity = bookingActivityRepository.findById(bookingActivity.getId()).get();
        // Disconnect from session so that the updates on updatedBookingActivity are not directly saved in db
        em.detach(updatedBookingActivity);
        updatedBookingActivity
            .bookingId(UPDATED_BOOKING_ID)
            .staffId(UPDATED_STAFF_ID)
            .treatmentId(UPDATED_TREATMENT_ID)
            .startDate(UPDATED_START_DATE)
            .finishDate(UPDATED_FINISH_DATE)
            .status(UPDATED_STATUS);

        restBookingActivityMockMvc.perform(put("/api/booking-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBookingActivity)))
            .andExpect(status().isOk());

        // Validate the BookingActivity in the database
        List<BookingActivity> bookingActivityList = bookingActivityRepository.findAll();
        assertThat(bookingActivityList).hasSize(databaseSizeBeforeUpdate);
        BookingActivity testBookingActivity = bookingActivityList.get(bookingActivityList.size() - 1);
        assertThat(testBookingActivity.getBookingId()).isEqualTo(UPDATED_BOOKING_ID);
        assertThat(testBookingActivity.getStaffId()).isEqualTo(UPDATED_STAFF_ID);
        assertThat(testBookingActivity.getTreatmentId()).isEqualTo(UPDATED_TREATMENT_ID);
        assertThat(testBookingActivity.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testBookingActivity.getFinishDate()).isEqualTo(UPDATED_FINISH_DATE);
        assertThat(testBookingActivity.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingBookingActivity() throws Exception {
        int databaseSizeBeforeUpdate = bookingActivityRepository.findAll().size();

        // Create the BookingActivity

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBookingActivityMockMvc.perform(put("/api/booking-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookingActivity)))
            .andExpect(status().isBadRequest());

        // Validate the BookingActivity in the database
        List<BookingActivity> bookingActivityList = bookingActivityRepository.findAll();
        assertThat(bookingActivityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBookingActivity() throws Exception {
        // Initialize the database
        bookingActivityService.save(bookingActivity);

        int databaseSizeBeforeDelete = bookingActivityRepository.findAll().size();

        // Get the bookingActivity
        restBookingActivityMockMvc.perform(delete("/api/booking-activities/{id}", bookingActivity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BookingActivity> bookingActivityList = bookingActivityRepository.findAll();
        assertThat(bookingActivityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BookingActivity.class);
        BookingActivity bookingActivity1 = new BookingActivity();
        bookingActivity1.setId(1L);
        BookingActivity bookingActivity2 = new BookingActivity();
        bookingActivity2.setId(bookingActivity1.getId());
        assertThat(bookingActivity1).isEqualTo(bookingActivity2);
        bookingActivity2.setId(2L);
        assertThat(bookingActivity1).isNotEqualTo(bookingActivity2);
        bookingActivity1.setId(null);
        assertThat(bookingActivity1).isNotEqualTo(bookingActivity2);
    }
}
