package com.fptu.capstone.service;

import com.fptu.capstone.domain.Voucher;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Voucher.
 */
public interface VoucherService {

    /**
     * Save a voucher.
     *
     * @param voucher the entity to save
     * @return the persisted entity
     */
    Voucher save(Voucher voucher);

    /**
     * Get all the vouchers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Voucher> findAll(Pageable pageable);

    /**
     * Get all the Voucher with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<Voucher> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" voucher.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Voucher> findOne(Long id);

    /**
     * Delete the "id" voucher.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
