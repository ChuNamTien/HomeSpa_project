package com.fptu.capstone.service.impl;

import com.fptu.capstone.service.ServImgService;
import com.fptu.capstone.domain.ServImg;
import com.fptu.capstone.repository.ServImgRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ServImg.
 */
@Service
@Transactional
public class ServImgServiceImpl implements ServImgService {

    private final Logger log = LoggerFactory.getLogger(ServImgServiceImpl.class);

    private ServImgRepository servImgRepository;

    public ServImgServiceImpl(ServImgRepository servImgRepository) {
        this.servImgRepository = servImgRepository;
    }

    /**
     * Save a servImg.
     *
     * @param servImg the entity to save
     * @return the persisted entity
     */
    @Override
    public ServImg save(ServImg servImg) {
        log.debug("Request to save ServImg : {}", servImg);
        return servImgRepository.save(servImg);
    }

    /**
     * Get all the servImgs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ServImg> findAll(Pageable pageable) {
        log.debug("Request to get all ServImgs");
        return servImgRepository.findAll(pageable);
    }


    /**
     * Get one servImg by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ServImg> findOne(Long id) {
        log.debug("Request to get ServImg : {}", id);
        return servImgRepository.findById(id);
    }

    /**
     * Delete the servImg by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ServImg : {}", id);
        servImgRepository.deleteById(id);
    }
}
