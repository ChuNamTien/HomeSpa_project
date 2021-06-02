package com.fptu.capstone.web.rest;

import com.fptu.capstone.HomespaApp;

import com.fptu.capstone.domain.BookingStaff;
import com.fptu.capstone.repository.BookingStaffRepository;
import com.fptu.capstone.service.BookingStaffService;
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
 * Test class for the BookingStaffResource REST controller.
 *
 * @see BookingStaffResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HomespaApp.class)
public class BookingStaffResourceIntTest {

    private static final Long DEFAULT_BOOKING_ID = 1L;
    private static final Long UPDATED_BOOKING_ID = 2L;

    private static final Long DEFAULT_TREATMENT_ID = 1L;
    private static final Long UPDATED_TREATMENT_ID = 2L;

    private static final Long DEFAULT_STATUS = 1L;
    private static final Long UPDATED_STATUS = 2L;

    @Autowired
    private BookingStaffRepository bookingStaffRepository;
    
    @Autowired
    private BookingStaffService bookingStaffService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBookingStaffMockMvc;

    private BookingStaff bookingStaff;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BookingStaffResource bookingStaffResource = new BookingStaffResource(bookingStaffService);
        this.restBookingStaffMockMvc = MockMvcBuilders.standaloneSetup(bookingStaffResource)
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
    public static BookingStaff createEntity(EntityManager em) {
        BookingStaff bookingStaff = new BookingStaff()
            .bookingId(DEFAULT_BOOKING_ID)
            .treatmentId(DEFAULT_TREATMENT_ID)
            .status(DEFAULT_STATUS);
        return bookingStaff;
    }

    @Before
    public void initTest() {
        bookingStaff = createEntity(em);
    }

    @Test
    @Transactional
    public void createBookingStaff() throws Exception {
        int databaseSizeBeforeCreate = bookingStaffRepository.findAll().size();

        // Create the BookingStaff
        restBookingStaffMockMvc.perform(post("/api/booking-staffs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookingStaff)))
            .andExpect(status().isCreated());

        // Validate the BookingStaff in the database
        List<BookingStaff> bookingStaffList = bookingStaffRepository.findAll();
        assertThat(bookingStaffList).hasSize(databaseSizeBeforeCreate + 1);
        BookingStaff testBookingStaff = bookingStaffList.get(bookingStaffList.size() - 1);
        assertThat(testBookingStaff.getBookingId()).isEqualTo(DEFAULT_BOOKING_ID);
        assertThat(testBookingStaff.getTreatmentId()).isEqualTo(DEFAULT_TREATMENT_ID);
        assertThat(testBookingStaff.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createBookingStaffWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bookingStaffRepository.findAll().size();

        // Create the BookingStaff with an existing ID
        bookingStaff.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBookingStaffMockMvc.perform(post("/api/booking-staffs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookingStaff)))
            .andExpect(status().isBadRequest());

        // Validate the BookingStaff in the database
        List<BookingStaff> bookingStaffList = bookingStaffRepository.findAll();
        assertThat(bookingStaffList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBookingStaffs() throws Exception {
        // Initialize the database
        bookingStaffRepository.saveAndFlush(bookingStaff);

        // Get all the bookingStaffList
        restBookingStaffMockMvc.perform(get("/api/booking-staffs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bookingStaff.getId().intValue())))
            .andExpect(jsonPath("$.[*].bookingId").value(hasItem(DEFAULT_BOOKING_ID.intValue())))
            .andExpect(jsonPath("$.[*].treatmentId").value(hasItem(DEFAULT_TREATMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.intValue())));
    }
    
    @Test
    @Transactional
    public void getBookingStaff() throws Exception {
        // Initialize the database
        bookingStaffRepository.saveAndFlush(bookingStaff);

        // Get the bookingStaff
        restBookingStaffMockMvc.perform(get("/api/booking-staffs/{id}", bookingStaff.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bookingStaff.getId().intValue()))
            .andExpect(jsonPath("$.bookingId").value(DEFAULT_BOOKING_ID.intValue()))
            .andExpect(jsonPath("$.treatmentId").value(DEFAULT_TREATMENT_ID.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingBookingStaff() throws Exception {
        // Get the bookingStaff
        restBookingStaffMockMvc.perform(get("/api/booking-staffs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBookingStaff() throws Exception {
        // Initialize the database
        bookingStaffService.save(bookingStaff);

        int databaseSizeBeforeUpdate = bookingStaffRepository.findAll().size();

        // Update the bookingStaff
        BookingStaff updatedBookingStaff = bookingStaffRepository.findById(bookingStaff.getId()).get();
        // Disconnect from session so that the updates on updatedBookingStaff are not directly saved in db
        em.detach(updatedBookingStaff);
        updatedBookingStaff
            .bookingId(UPDATED_BOOKING_ID)
            .treatmentId(UPDATED_TREATMENT_ID)
            .status(UPDATED_STATUS);

        restBookingStaffMockMvc.perform(put("/api/booking-staffs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBookingStaff)))
            .andExpect(status().isOk());

        // Validate the BookingStaff in the database
        List<BookingStaff> bookingStaffList = bookingStaffRepository.findAll();
        assertThat(bookingStaffList).hasSize(databaseSizeBeforeUpdate);
        BookingStaff testBookingStaff = bookingStaffList.get(bookingStaffList.size() - 1);
        assertThat(testBookingStaff.getBookingId()).isEqualTo(UPDATED_BOOKING_ID);
        assertThat(testBookingStaff.getTreatmentId()).isEqualTo(UPDATED_TREATMENT_ID);
        assertThat(testBookingStaff.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingBookingStaff() throws Exception {
        int databaseSizeBeforeUpdate = bookingStaffRepository.findAll().size();

        // Create the BookingStaff

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBookingStaffMockMvc.perform(put("/api/booking-staffs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookingStaff)))
            .andExpect(status().isBadRequest());

        // Validate the BookingStaff in the database
        List<BookingStaff> bookingStaffList = bookingStaffRepository.findAll();
        assertThat(bookingStaffList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBookingStaff() throws Exception {
        // Initialize the database
        bookingStaffService.save(bookingStaff);

        int databaseSizeBeforeDelete = bookingStaffRepository.findAll().size();

        // Get the bookingStaff
        restBookingStaffMockMvc.perform(delete("/api/booking-staffs/{id}", bookingStaff.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BookingStaff> bookingStaffList = bookingStaffRepository.findAll();
        assertThat(bookingStaffList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BookingStaff.class);
        BookingStaff bookingStaff1 = new BookingStaff();
        bookingStaff1.setId(1L);
        BookingStaff bookingStaff2 = new BookingStaff();
        bookingStaff2.setId(bookingStaff1.getId());
        assertThat(bookingStaff1).isEqualTo(bookingStaff2);
        bookingStaff2.setId(2L);
        assertThat(bookingStaff1).isNotEqualTo(bookingStaff2);
        bookingStaff1.setId(null);
        assertThat(bookingStaff1).isNotEqualTo(bookingStaff2);
    }
}
