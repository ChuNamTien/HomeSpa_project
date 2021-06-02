package com.fptu.capstone.service.impl;

import com.fptu.capstone.service.PartnerImgService;
import com.fptu.capstone.domain.PartnerImg;
import com.fptu.capstone.repository.PartnerImgRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing PartnerImg.
 */
@Service
@Transactional
public class PartnerImgServiceImpl implements PartnerImgService {

    private final Logger log = LoggerFactory.getLogger(PartnerImgServiceImpl.class);

    private PartnerImgRepository partnerImgRepository;

    public PartnerImgServiceImpl(PartnerImgRepository partnerImgRepository) {
        this.partnerImgRepository = partnerImgRepository;
    }

    /**
     * Save a partnerImg.
     *
     * @param partnerImg the entity to save
     * @return the persisted entity
     */
    @Override
    public PartnerImg save(PartnerImg partnerImg) {
        log.debug("Request to save PartnerImg : {}", partnerImg);
        return partnerImgRepository.save(partnerImg);
    }

    /**
     * Get all the partnerImgs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PartnerImg> findAll(Pageable pageable) {
        log.debug("Request to get all PartnerImgs");
        return partnerImgRepository.findAll(pageable);
    }


    /**
     * Get one partnerImg by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PartnerImg> findOne(Long id) {
        log.debug("Request to get PartnerImg : {}", id);
        return partnerImgRepository.findById(id);
    }

    /**
     * Delete the partnerImg by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PartnerImg : {}", id);
        partnerImgRepository.deleteById(id);
    }
}
