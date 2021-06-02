package com.fptu.capstone.service;

import com.fptu.capstone.domain.Staff;
import com.fptu.capstone.service.dto.StaffDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Staff.
 */
public interface StaffService {

    /**
     * Save a staff.
     *
     * @param staff the entity to save
     * @return the persisted entity
     */
    Staff save(Staff staff);

    /**
     * Get all the staff.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Staff> findAll(Pageable pageable);

    /**
     * Get all the Staff with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<Staff> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" staff.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Staff> findOne(Long id);

    /**
     * Delete the "id" staff.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

	List<StaffDTO> getAllStaffByPartnerId(Long partnerId);
}
