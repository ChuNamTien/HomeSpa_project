package com.fptu.capstone.service;

import com.fptu.capstone.domain.ServImg;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ServImg.
 */
public interface ServImgService {

    /**
     * Save a servImg.
     *
     * @param servImg the entity to save
     * @return the persisted entity
     */
    ServImg save(ServImg servImg);

    /**
     * Get all the servImgs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ServImg> findAll(Pageable pageable);


    /**
     * Get the "id" servImg.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ServImg> findOne(Long id);

    /**
     * Delete the "id" servImg.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
