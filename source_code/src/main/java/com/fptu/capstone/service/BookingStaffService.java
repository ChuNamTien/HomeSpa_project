package com.fptu.capstone.service;

import com.fptu.capstone.domain.BookingStaff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing BookingStaff.
 */
public interface BookingStaffService {

    /**
     * Save a bookingStaff.
     *
     * @param bookingStaff the entity to save
     * @return the persisted entity
     */
    BookingStaff save(BookingStaff bookingStaff);

    /**
     * Get all the bookingStaffs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BookingStaff> findAll(Pageable pageable);


    /**
     * Get the "id" bookingStaff.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BookingStaff> findOne(Long id);

    /**
     * Delete the "id" bookingStaff.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
