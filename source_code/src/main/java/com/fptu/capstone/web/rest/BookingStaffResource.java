package com.fptu.capstone.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fptu.capstone.domain.BookingStaff;
import com.fptu.capstone.service.BookingStaffService;
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
 * REST controller for managing BookingStaff.
 */
@RestController
@RequestMapping("/api")
public class BookingStaffResource {

    private final Logger log = LoggerFactory.getLogger(BookingStaffResource.class);

    private static final String ENTITY_NAME = "bookingStaff";

    private BookingStaffService bookingStaffService;

    public BookingStaffResource(BookingStaffService bookingStaffService) {
        this.bookingStaffService = bookingStaffService;
    }

    /**
     * POST  /booking-staffs : Create a new bookingStaff.
     *
     * @param bookingStaff the bookingStaff to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bookingStaff, or with status 400 (Bad Request) if the bookingStaff has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/booking-staffs")
    @Timed
    public ResponseEntity<BookingStaff> createBookingStaff(@RequestBody BookingStaff bookingStaff) throws URISyntaxException {
        log.debug("REST request to save BookingStaff : {}", bookingStaff);
        if (bookingStaff.getId() != null) {
            throw new BadRequestAlertException("A new bookingStaff cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BookingStaff result = bookingStaffService.save(bookingStaff);
        return ResponseEntity.created(new URI("/api/booking-staffs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /booking-staffs : Updates an existing bookingStaff.
     *
     * @param bookingStaff the bookingStaff to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bookingStaff,
     * or with status 400 (Bad Request) if the bookingStaff is not valid,
     * or with status 500 (Internal Server Error) if the bookingStaff couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/booking-staffs")
    @Timed
    public ResponseEntity<BookingStaff> updateBookingStaff(@RequestBody BookingStaff bookingStaff) throws URISyntaxException {
        log.debug("REST request to update BookingStaff : {}", bookingStaff);
        if (bookingStaff.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BookingStaff result = bookingStaffService.save(bookingStaff);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bookingStaff.getId().toString()))
            .body(result);
    }

    /**
     * GET  /booking-staffs : get all the bookingStaffs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of bookingStaffs in body
     */
    @GetMapping("/booking-staffs")
    @Timed
    public ResponseEntity<List<BookingStaff>> getAllBookingStaffs(Pageable pageable) {
        log.debug("REST request to get a page of BookingStaffs");
        Page<BookingStaff> page = bookingStaffService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/booking-staffs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /booking-staffs/:id : get the "id" bookingStaff.
     *
     * @param id the id of the bookingStaff to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bookingStaff, or with status 404 (Not Found)
     */
    @GetMapping("/booking-staffs/{id}")
    @Timed
    public ResponseEntity<BookingStaff> getBookingStaff(@PathVariable Long id) {
        log.debug("REST request to get BookingStaff : {}", id);
        Optional<BookingStaff> bookingStaff = bookingStaffService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bookingStaff);
    }

    /**
     * DELETE  /booking-staffs/:id : delete the "id" bookingStaff.
     *
     * @param id the id of the bookingStaff to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/booking-staffs/{id}")
    @Timed
    public ResponseEntity<Void> deleteBookingStaff(@PathVariable Long id) {
        log.debug("REST request to delete BookingStaff : {}", id);
        bookingStaffService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
