package com.fptu.capstone.service;

import com.fptu.capstone.domain.BookingActivity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing BookingActivity.
 */
public interface BookingActivityService {

    /**
     * Save a bookingActivity.
     *
     * @param bookingActivity the entity to save
     * @return the persisted entity
     */
    BookingActivity save(BookingActivity bookingActivity);

    /**
     * Get all the bookingActivities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BookingActivity> findAll(Pageable pageable);


    /**
     * Get the "id" bookingActivity.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BookingActivity> findOne(Long id);

    /**
     * Delete the "id" bookingActivity.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
