package com.fptu.capstone.service;

import com.fptu.capstone.domain.PartnerImg;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PartnerImg.
 */
public interface PartnerImgService {

    /**
     * Save a partnerImg.
     *
     * @param partnerImg the entity to save
     * @return the persisted entity
     */
    PartnerImg save(PartnerImg partnerImg);

    /**
     * Get all the partnerImgs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PartnerImg> findAll(Pageable pageable);


    /**
     * Get the "id" partnerImg.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PartnerImg> findOne(Long id);

    /**
     * Delete the "id" partnerImg.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
