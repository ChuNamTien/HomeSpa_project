package com.fptu.capstone.service.impl;

import com.fptu.capstone.service.PartnerService;
import com.fptu.capstone.domain.Partner;
import com.fptu.capstone.repository.PartnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Partner.
 */
@Service
@Transactional
public class PartnerServiceImpl implements PartnerService {

    private final Logger log = LoggerFactory.getLogger(PartnerServiceImpl.class);

    private PartnerRepository partnerRepository;

    public PartnerServiceImpl(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    /**
     * Save a partner.
     *
     * @param partner the entity to save
     * @return the persisted entity
     */
    @Override
    public Partner save(Partner partner) {
        log.debug("Request to save Partner : {}", partner);
        return partnerRepository.save(partner);
    }

    /**
     * Get all the partners.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Partner> findAll(Pageable pageable) {
        log.debug("Request to get all Partners");
        return partnerRepository.findAll(pageable);
    }


    /**
     * Get one partner by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Partner> findOne(Long id) {
        log.debug("Request to get Partner : {}", id);
        return partnerRepository.findById(id);
    }

    /**
     * Delete the partner by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Partner : {}", id);
        partnerRepository.deleteById(id);
    }
}
