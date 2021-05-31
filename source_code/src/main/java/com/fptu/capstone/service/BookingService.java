package com.fptu.capstone.service;

import com.fptu.capstone.domain.Booking;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Booking.
 */
public interface BookingService {

    /**
     * Save a booking.
     *
     * @param booking the entity to save
     * @return the persisted entity
     */
    Booking save(Booking booking);

    /**
     * Get all the bookings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Booking> findAll(Pageable pageable);

    /**
     * Get all the Booking with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<Booking> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" booking.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Booking> findOne(Long id);

    /**
     * Delete the "id" booking.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
