package com.fptu.capstone.service.impl;

import com.fptu.capstone.service.BookingStaffService;
import com.fptu.capstone.domain.BookingStaff;
import com.fptu.capstone.repository.BookingStaffRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing BookingStaff.
 */
@Service
@Transactional
public class BookingStaffServiceImpl implements BookingStaffService {

    private final Logger log = LoggerFactory.getLogger(BookingStaffServiceImpl.class);

    private BookingStaffRepository bookingStaffRepository;

    public BookingStaffServiceImpl(BookingStaffRepository bookingStaffRepository) {
        this.bookingStaffRepository = bookingStaffRepository;
    }

    /**
     * Save a bookingStaff.
     *
     * @param bookingStaff the entity to save
     * @return the persisted entity
     */
    @Override
    public BookingStaff save(BookingStaff bookingStaff) {
        log.debug("Request to save BookingStaff : {}", bookingStaff);
        return bookingStaffRepository.save(bookingStaff);
    }

    /**
     * Get all the bookingStaffs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BookingStaff> findAll(Pageable pageable) {
        log.debug("Request to get all BookingStaffs");
        return bookingStaffRepository.findAll(pageable);
    }


    /**
     * Get one bookingStaff by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BookingStaff> findOne(Long id) {
        log.debug("Request to get BookingStaff : {}", id);
        return bookingStaffRepository.findById(id);
    }

    /**
     * Delete the bookingStaff by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BookingStaff : {}", id);
        bookingStaffRepository.deleteById(id);
    }
}
