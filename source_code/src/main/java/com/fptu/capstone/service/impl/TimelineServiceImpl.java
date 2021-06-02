package com.fptu.capstone.service.impl;

import com.fptu.capstone.service.TimelineService;
import com.fptu.capstone.domain.Timeline;
import com.fptu.capstone.repository.TimelineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Timeline.
 */
@Service
@Transactional
public class TimelineServiceImpl implements TimelineService {

    private final Logger log = LoggerFactory.getLogger(TimelineServiceImpl.class);

    private TimelineRepository timelineRepository;

    public TimelineServiceImpl(TimelineRepository timelineRepository) {
        this.timelineRepository = timelineRepository;
    }

    /**
     * Save a timeline.
     *
     * @param timeline the entity to save
     * @return the persisted entity
     */
    @Override
    public Timeline save(Timeline timeline) {
        log.debug("Request to save Timeline : {}", timeline);
        return timelineRepository.save(timeline);
    }

    /**
     * Get all the timelines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Timeline> findAll(Pageable pageable) {
        log.debug("Request to get all Timelines");
        return timelineRepository.findAll(pageable);
    }


    /**
     * Get one timeline by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Timeline> findOne(Long id) {
        log.debug("Request to get Timeline : {}", id);
        return timelineRepository.findById(id);
    }

    /**
     * Delete the timeline by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Timeline : {}", id);
        timelineRepository.deleteById(id);
    }
}
