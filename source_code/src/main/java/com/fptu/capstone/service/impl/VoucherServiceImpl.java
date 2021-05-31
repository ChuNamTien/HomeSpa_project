package com.fptu.capstone.service.impl;

import com.fptu.capstone.service.VoucherService;
import com.fptu.capstone.domain.Voucher;
import com.fptu.capstone.repository.VoucherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Voucher.
 */
@Service
@Transactional
public class VoucherServiceImpl implements VoucherService {

    private final Logger log = LoggerFactory.getLogger(VoucherServiceImpl.class);

    private VoucherRepository voucherRepository;

    public VoucherServiceImpl(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    /**
     * Save a voucher.
     *
     * @param voucher the entity to save
     * @return the persisted entity
     */
    @Override
    public Voucher save(Voucher voucher) {
        log.debug("Request to save Voucher : {}", voucher);
        return voucherRepository.save(voucher);
    }

    /**
     * Get all the vouchers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Voucher> findAll(Pageable pageable) {
        log.debug("Request to get all Vouchers");
        return voucherRepository.findAll(pageable);
    }

    /**
     * Get all the Voucher with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Voucher> findAllWithEagerRelationships(Pageable pageable) {
        return voucherRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one voucher by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Voucher> findOne(Long id) {
        log.debug("Request to get Voucher : {}", id);
        return voucherRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the voucher by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Voucher : {}", id);
        voucherRepository.deleteById(id);
    }
}
