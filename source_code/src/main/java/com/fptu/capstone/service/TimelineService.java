package com.fptu.capstone.service;

import com.fptu.capstone.domain.Timeline;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Timeline.
 */
public interface TimelineService {

    /**
     * Save a timeline.
     *
     * @param timeline the entity to save
     * @return the persisted entity
     */
    Timeline save(Timeline timeline);

    /**
     * Get all the timelines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Timeline> findAll(Pageable pageable);


    /**
     * Get the "id" timeline.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Timeline> findOne(Long id);

    /**
     * Delete the "id" timeline.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
