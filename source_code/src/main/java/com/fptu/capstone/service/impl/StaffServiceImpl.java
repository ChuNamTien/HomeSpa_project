package com.fptu.capstone.service.impl;

import com.fptu.capstone.service.StaffService;
import com.fptu.capstone.domain.Staff;
import com.fptu.capstone.repository.StaffRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Staff.
 */
@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    private final Logger log = LoggerFactory.getLogger(StaffServiceImpl.class);

    private StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    /**
     * Save a staff.
     *
     * @param staff the entity to save
     * @return the persisted entity
     */
    @Override
    public Staff save(Staff staff) {
        log.debug("Request to save Staff : {}", staff);
        return staffRepository.save(staff);
    }

    /**
     * Get all the staff.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Staff> findAll(Pageable pageable) {
        log.debug("Request to get all Staff");
        return staffRepository.findAll(pageable);
    }

    /**
     * Get all the Staff with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Staff> findAllWithEagerRelationships(Pageable pageable) {
        return staffRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one staff by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Staff> findOne(Long id) {
        log.debug("Request to get Staff : {}", id);
        return staffRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the staff by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Staff : {}", id);
        staffRepository.deleteById(id);
    }
}
