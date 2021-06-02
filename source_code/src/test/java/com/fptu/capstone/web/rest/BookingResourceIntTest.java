package com.fptu.capstone.web.rest;

import com.fptu.capstone.HomespaApp;

import com.fptu.capstone.domain.Booking;
import com.fptu.capstone.repository.BookingRepository;
import com.fptu.capstone.service.BookingService;
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
 * Test class for the BookingResource REST controller.
 *
 * @see BookingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HomespaApp.class)
public class BookingResourceIntTest {

    private static final Long DEFAULT_CUSTOMER_ID = 1L;
    private static final Long UPDATED_CUSTOMER_ID = 2L;

    private static final Long DEFAULT_PARTNER_ID = 1L;
    private static final Long UPDATED_PARTNER_ID = 2L;

    private static final Instant DEFAULT_START_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_FINISH_TIME = "AAAAAAAAAA";
    private static final String UPDATED_FINISH_TIME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_FINISHED = false;
    private static final Boolean UPDATED_IS_FINISHED = true;

    private static final Boolean DEFAULT_IS_CONFIRMED = false;
    private static final Boolean UPDATED_IS_CONFIRMED = true;

    private static final String DEFAULT_PAYMENT_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_METHOD = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIRM_TIME = "AAAAAAAAAA";
    private static final String UPDATED_CONFIRM_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFIED_BY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_BY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private BookingService bookingService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBookingMockMvc;

    private Booking booking;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BookingResource bookingResource = new BookingResource(bookingService);
        this.restBookingMockMvc = MockMvcBuilders.standaloneSetup(bookingResource)
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
    public static Booking createEntity(EntityManager em) {
        Booking booking = new Booking()
            .customerId(DEFAULT_CUSTOMER_ID)
            .partnerId(DEFAULT_PARTNER_ID)
            .startTime(DEFAULT_START_TIME)
            .finishTime(DEFAULT_FINISH_TIME)
            .isFinished(DEFAULT_IS_FINISHED)
            .isConfirmed(DEFAULT_IS_CONFIRMED)
            .paymentMethod(DEFAULT_PAYMENT_METHOD)
            .confirmTime(DEFAULT_CONFIRM_TIME)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return booking;
    }

    @Before
    public void initTest() {
        booking = createEntity(em);
    }

    @Test
    @Transactional
    public void createBooking() throws Exception {
        int databaseSizeBeforeCreate = bookingRepository.findAll().size();

        // Create the Booking
        restBookingMockMvc.perform(post("/api/bookings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(booking)))
            .andExpect(status().isCreated());

        // Validate the Booking in the database
        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeCreate + 1);
        Booking testBooking = bookingList.get(bookingList.size() - 1);
        assertThat(testBooking.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testBooking.getPartnerId()).isEqualTo(DEFAULT_PARTNER_ID);
        assertThat(testBooking.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testBooking.getFinishTime()).isEqualTo(DEFAULT_FINISH_TIME);
        assertThat(testBooking.isIsFinished()).isEqualTo(DEFAULT_IS_FINISHED);
        assertThat(testBooking.isIsConfirmed()).isEqualTo(DEFAULT_IS_CONFIRMED);
        assertThat(testBooking.getPaymentMethod()).isEqualTo(DEFAULT_PAYMENT_METHOD);
        assertThat(testBooking.getConfirmTime()).isEqualTo(DEFAULT_CONFIRM_TIME);
        assertThat(testBooking.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBooking.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBooking.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testBooking.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createBookingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bookingRepository.findAll().size();

        // Create the Booking with an existing ID
        booking.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBookingMockMvc.perform(post("/api/bookings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(booking)))
            .andExpect(status().isBadRequest());

        // Validate the Booking in the database
        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBookings() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get all the bookingList
        restBookingMockMvc.perform(get("/api/bookings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(booking.getId().intValue())))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID.intValue())))
            .andExpect(jsonPath("$.[*].partnerId").value(hasItem(DEFAULT_PARTNER_ID.intValue())))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].finishTime").value(hasItem(DEFAULT_FINISH_TIME.toString())))
            .andExpect(jsonPath("$.[*].isFinished").value(hasItem(DEFAULT_IS_FINISHED.booleanValue())))
            .andExpect(jsonPath("$.[*].isConfirmed").value(hasItem(DEFAULT_IS_CONFIRMED.booleanValue())))
            .andExpect(jsonPath("$.[*].paymentMethod").value(hasItem(DEFAULT_PAYMENT_METHOD.toString())))
            .andExpect(jsonPath("$.[*].confirmTime").value(hasItem(DEFAULT_CONFIRM_TIME.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getBooking() throws Exception {
        // Initialize the database
        bookingRepository.saveAndFlush(booking);

        // Get the booking
        restBookingMockMvc.perform(get("/api/bookings/{id}", booking.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(booking.getId().intValue()))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID.intValue()))
            .andExpect(jsonPath("$.partnerId").value(DEFAULT_PARTNER_ID.intValue()))
            .andExpect(jsonPath("$.startTime").value(DEFAULT_START_TIME.toString()))
            .andExpect(jsonPath("$.finishTime").value(DEFAULT_FINISH_TIME.toString()))
            .andExpect(jsonPath("$.isFinished").value(DEFAULT_IS_FINISHED.booleanValue()))
            .andExpect(jsonPath("$.isConfirmed").value(DEFAULT_IS_CONFIRMED.booleanValue()))
            .andExpect(jsonPath("$.paymentMethod").value(DEFAULT_PAYMENT_METHOD.toString()))
            .andExpect(jsonPath("$.confirmTime").value(DEFAULT_CONFIRM_TIME.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY.toString()))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBooking() throws Exception {
        // Get the booking
        restBookingMockMvc.perform(get("/api/bookings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBooking() throws Exception {
        // Initialize the database
        bookingService.save(booking);

        int databaseSizeBeforeUpdate = bookingRepository.findAll().size();

        // Update the booking
        Booking updatedBooking = bookingRepository.findById(booking.getId()).get();
        // Disconnect from session so that the updates on updatedBooking are not directly saved in db
        em.detach(updatedBooking);
        updatedBooking
            .customerId(UPDATED_CUSTOMER_ID)
            .partnerId(UPDATED_PARTNER_ID)
            .startTime(UPDATED_START_TIME)
            .finishTime(UPDATED_FINISH_TIME)
            .isFinished(UPDATED_IS_FINISHED)
            .isConfirmed(UPDATED_IS_CONFIRMED)
            .paymentMethod(UPDATED_PAYMENT_METHOD)
            .confirmTime(UPDATED_CONFIRM_TIME)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restBookingMockMvc.perform(put("/api/bookings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBooking)))
            .andExpect(status().isOk());

        // Validate the Booking in the database
        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeUpdate);
        Booking testBooking = bookingList.get(bookingList.size() - 1);
        assertThat(testBooking.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testBooking.getPartnerId()).isEqualTo(UPDATED_PARTNER_ID);
        assertThat(testBooking.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testBooking.getFinishTime()).isEqualTo(UPDATED_FINISH_TIME);
        assertThat(testBooking.isIsFinished()).isEqualTo(UPDATED_IS_FINISHED);
        assertThat(testBooking.isIsConfirmed()).isEqualTo(UPDATED_IS_CONFIRMED);
        assertThat(testBooking.getPaymentMethod()).isEqualTo(UPDATED_PAYMENT_METHOD);
        assertThat(testBooking.getConfirmTime()).isEqualTo(UPDATED_CONFIRM_TIME);
        assertThat(testBooking.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBooking.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBooking.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testBooking.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingBooking() throws Exception {
        int databaseSizeBeforeUpdate = bookingRepository.findAll().size();

        // Create the Booking

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBookingMockMvc.perform(put("/api/bookings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(booking)))
            .andExpect(status().isBadRequest());

        // Validate the Booking in the database
        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBooking() throws Exception {
        // Initialize the database
        bookingService.save(booking);

        int databaseSizeBeforeDelete = bookingRepository.findAll().size();

        // Get the booking
        restBookingMockMvc.perform(delete("/api/bookings/{id}", booking.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Booking.class);
        Booking booking1 = new Booking();
        booking1.setId(1L);
        Booking booking2 = new Booking();
        booking2.setId(booking1.getId());
        assertThat(booking1).isEqualTo(booking2);
        booking2.setId(2L);
        assertThat(booking1).isNotEqualTo(booking2);
        booking1.setId(null);
        assertThat(booking1).isNotEqualTo(booking2);
    }
}
