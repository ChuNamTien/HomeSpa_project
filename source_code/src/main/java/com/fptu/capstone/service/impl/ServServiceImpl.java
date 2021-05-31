package com.fptu.capstone.service.impl;

import com.fptu.capstone.service.ServService;
import com.fptu.capstone.domain.Serv;
import com.fptu.capstone.repository.ServRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Serv.
 */
@Service
@Transactional
public class ServServiceImpl implements ServService {

    private final Logger log = LoggerFactory.getLogger(ServServiceImpl.class);

    private ServRepository servRepository;

    public ServServiceImpl(ServRepository servRepository) {
        this.servRepository = servRepository;
    }

    /**
     * Save a serv.
     *
     * @param serv the entity to save
     * @return the persisted entity
     */
    @Override
    public Serv save(Serv serv) {
        log.debug("Request to save Serv : {}", serv);
        return servRepository.save(serv);
    }

    /**
     * Get all the servs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Serv> findAll(Pageable pageable) {
        log.debug("Request to get all Servs");
        return servRepository.findAll(pageable);
    }


    /**
     * Get one serv by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Serv> findOne(Long id) {
        log.debug("Request to get Serv : {}", id);
        return servRepository.findById(id);
    }

    /**
     * Delete the serv by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Serv : {}", id);
        servRepository.deleteById(id);
    }
}
