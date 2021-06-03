package com.fptu.capstone.service.impl;

import com.fptu.capstone.service.BookingActivityService;
import com.fptu.capstone.domain.BookingActivity;
import com.fptu.capstone.repository.BookingActivityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing BookingActivity.
 */
@Service
@Transactional
public class BookingActivityServiceImpl implements BookingActivityService {

    private final Logger log = LoggerFactory.getLogger(BookingActivityServiceImpl.class);

    private BookingActivityRepository bookingActivityRepository;

    public BookingActivityServiceImpl(BookingActivityRepository bookingActivityRepository) {
        this.bookingActivityRepository = bookingActivityRepository;
    }

    /**
     * Save a bookingActivity.
     *
     * @param bookingActivity the entity to save
     * @return the persisted entity
     */
    @Override
    public BookingActivity save(BookingActivity bookingActivity) {
        log.debug("Request to save BookingActivity : {}", bookingActivity);
        return bookingActivityRepository.save(bookingActivity);
    }

    /**
     * Get all the bookingActivities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BookingActivity> findAll(Pageable pageable) {
        log.debug("Request to get all BookingActivities");
        return bookingActivityRepository.findAll(pageable);
    }


    /**
     * Get one bookingActivity by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BookingActivity> findOne(Long id) {
        log.debug("Request to get BookingActivity : {}", id);
        return bookingActivityRepository.findById(id);
    }

    /**
     * Delete the bookingActivity by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BookingActivity : {}", id);
        bookingActivityRepository.deleteById(id);
    }
}
