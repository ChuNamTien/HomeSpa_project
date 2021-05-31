package com.fptu.capstone.service;

import com.fptu.capstone.domain.Serv;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Serv.
 */
public interface ServService {

    /**
     * Save a serv.
     *
     * @param serv the entity to save
     * @return the persisted entity
     */
    Serv save(Serv serv);

    /**
     * Get all the servs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Serv> findAll(Pageable pageable);


    /**
     * Get the "id" serv.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Serv> findOne(Long id);

    /**
     * Delete the "id" serv.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
