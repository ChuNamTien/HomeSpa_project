package com.fptu.capstone.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fptu.capstone.domain.BookingActivity;
import com.fptu.capstone.service.BookingActivityService;
import com.fptu.capstone.web.rest.errors.BadRequestAlertException;
import com.fptu.capstone.web.rest.util.HeaderUtil;
import com.fptu.capstone.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing BookingActivity.
 */
@RestController
@RequestMapping("/api")
public class BookingActivityResource {

    private final Logger log = LoggerFactory.getLogger(BookingActivityResource.class);

    private static final String ENTITY_NAME = "bookingActivity";

    private BookingActivityService bookingActivityService;

    public BookingActivityResource(BookingActivityService bookingActivityService) {
        this.bookingActivityService = bookingActivityService;
    }

    /**
     * POST  /booking-activities : Create a new bookingActivity.
     *
     * @param bookingActivity the bookingActivity to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bookingActivity, or with status 400 (Bad Request) if the bookingActivity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/booking-activities")
    @Timed
    public ResponseEntity<BookingActivity> createBookingActivity(@RequestBody BookingActivity bookingActivity) throws URISyntaxException {
        log.debug("REST request to save BookingActivity : {}", bookingActivity);
        if (bookingActivity.getId() != null) {
            throw new BadRequestAlertException("A new bookingActivity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BookingActivity result = bookingActivityService.save(bookingActivity);
        return ResponseEntity.created(new URI("/api/booking-activities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /booking-activities : Updates an existing bookingActivity.
     *
     * @param bookingActivity the bookingActivity to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bookingActivity,
     * or with status 400 (Bad Request) if the bookingActivity is not valid,
     * or with status 500 (Internal Server Error) if the bookingActivity couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/booking-activities")
    @Timed
    public ResponseEntity<BookingActivity> updateBookingActivity(@RequestBody BookingActivity bookingActivity) throws URISyntaxException {
        log.debug("REST request to update BookingActivity : {}", bookingActivity);
        if (bookingActivity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BookingActivity result = bookingActivityService.save(bookingActivity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bookingActivity.getId().toString()))
            .body(result);
    }

    /**
     * GET  /booking-activities : get all the bookingActivities.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of bookingActivities in body
     */
    @GetMapping("/booking-activities")
    @Timed
    public ResponseEntity<List<BookingActivity>> getAllBookingActivities(Pageable pageable) {
        log.debug("REST request to get a page of BookingActivities");
        Page<BookingActivity> page = bookingActivityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/booking-activities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /booking-activities/:id : get the "id" bookingActivity.
     *
     * @param id the id of the bookingActivity to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bookingActivity, or with status 404 (Not Found)
     */
    @GetMapping("/booking-activities/{id}")
    @Timed
    public ResponseEntity<BookingActivity> getBookingActivity(@PathVariable Long id) {
        log.debug("REST request to get BookingActivity : {}", id);
        Optional<BookingActivity> bookingActivity = bookingActivityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bookingActivity);
    }

    /**
     * DELETE  /booking-activities/:id : delete the "id" bookingActivity.
     *
     * @param id the id of the bookingActivity to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/booking-activities/{id}")
    @Timed
    public ResponseEntity<Void> deleteBookingActivity(@PathVariable Long id) {
        log.debug("REST request to delete BookingActivity : {}", id);
        bookingActivityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
