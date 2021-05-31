package com.fptu.capstone.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fptu.capstone.domain.PartnerImg;
import com.fptu.capstone.service.PartnerImgService;
import com.fptu.capstone.web.rest.errors.BadRequestAlertException;
import com.fptu.capstone.web.rest.util.HeaderUtil;
import com.fptu.capstone.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PartnerImg.
 */
@RestController
@RequestMapping("/api")
public class PartnerImgResource {

    private final Logger log = LoggerFactory.getLogger(PartnerImgResource.class);

    private static final String ENTITY_NAME = "partnerImg";

    private PartnerImgService partnerImgService;

    public PartnerImgResource(PartnerImgService partnerImgService) {
        this.partnerImgService = partnerImgService;
    }

    /**
     * POST  /partner-imgs : Create a new partnerImg.
     *
     * @param partnerImg the partnerImg to create
     * @return the ResponseEntity with status 201 (Created) and with body the new partnerImg, or with status 400 (Bad Request) if the partnerImg has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/partner-imgs")
    @Timed
    public ResponseEntity<PartnerImg> createPartnerImg(@RequestBody PartnerImg partnerImg) throws URISyntaxException {
        log.debug("REST request to save PartnerImg : {}", partnerImg);
        if (partnerImg.getId() != null) {
            throw new BadRequestAlertException("A new partnerImg cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PartnerImg result = partnerImgService.save(partnerImg);
        return ResponseEntity.created(new URI("/api/partner-imgs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /partner-imgs : Updates an existing partnerImg.
     *
     * @param partnerImg the partnerImg to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated partnerImg,
     * or with status 400 (Bad Request) if the partnerImg is not valid,
     * or with status 500 (Internal Server Error) if the partnerImg couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/partner-imgs")
    @Timed
    public ResponseEntity<PartnerImg> updatePartnerImg(@RequestBody PartnerImg partnerImg) throws URISyntaxException {
        log.debug("REST request to update PartnerImg : {}", partnerImg);
        if (partnerImg.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PartnerImg result = partnerImgService.save(partnerImg);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, partnerImg.getId().toString()))
            .body(result);
    }

    /**
     * GET  /partner-imgs : get all the partnerImgs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of partnerImgs in body
     */
    @GetMapping("/partner-imgs")
    @Timed
    public ResponseEntity<List<PartnerImg>> getAllPartnerImgs(Pageable pageable) {
        log.debug("REST request to get a page of PartnerImgs");
        Page<PartnerImg> page = partnerImgService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/partner-imgs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /partner-imgs/:id : get the "id" partnerImg.
     *
     * @param id the id of the partnerImg to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the partnerImg, or with status 404 (Not Found)
     */
    @GetMapping("/partner-imgs/{id}")
    @Timed
    public ResponseEntity<PartnerImg> getPartnerImg(@PathVariable Long id) {
        log.debug("REST request to get PartnerImg : {}", id);
        Optional<PartnerImg> partnerImg = partnerImgService.findOne(id);
        return ResponseUtil.wrapOrNotFound(partnerImg);
    }

    /**
     * DELETE  /partner-imgs/:id : delete the "id" partnerImg.
     *
     * @param id the id of the partnerImg to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/partner-imgs/{id}")
    @Timed
    public ResponseEntity<Void> deletePartnerImg(@PathVariable Long id) {
        log.debug("REST request to delete PartnerImg : {}", id);
        partnerImgService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
