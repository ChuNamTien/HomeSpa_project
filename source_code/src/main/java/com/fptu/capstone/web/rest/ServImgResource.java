package com.fptu.capstone.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fptu.capstone.domain.ServImg;
import com.fptu.capstone.service.ServImgService;
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
 * REST controller for managing ServImg.
 */
@RestController
@RequestMapping("/api")
public class ServImgResource {

    private final Logger log = LoggerFactory.getLogger(ServImgResource.class);

    private static final String ENTITY_NAME = "servImg";

    private ServImgService servImgService;

    public ServImgResource(ServImgService servImgService) {
        this.servImgService = servImgService;
    }

    /**
     * POST  /serv-imgs : Create a new servImg.
     *
     * @param servImg the servImg to create
     * @return the ResponseEntity with status 201 (Created) and with body the new servImg, or with status 400 (Bad Request) if the servImg has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/serv-imgs")
    @Timed
    public ResponseEntity<ServImg> createServImg(@RequestBody ServImg servImg) throws URISyntaxException {
        log.debug("REST request to save ServImg : {}", servImg);
        if (servImg.getId() != null) {
            throw new BadRequestAlertException("A new servImg cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServImg result = servImgService.save(servImg);
        return ResponseEntity.created(new URI("/api/serv-imgs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /serv-imgs : Updates an existing servImg.
     *
     * @param servImg the servImg to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated servImg,
     * or with status 400 (Bad Request) if the servImg is not valid,
     * or with status 500 (Internal Server Error) if the servImg couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/serv-imgs")
    @Timed
    public ResponseEntity<ServImg> updateServImg(@RequestBody ServImg servImg) throws URISyntaxException {
        log.debug("REST request to update ServImg : {}", servImg);
        if (servImg.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ServImg result = servImgService.save(servImg);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, servImg.getId().toString()))
            .body(result);
    }

    /**
     * GET  /serv-imgs : get all the servImgs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of servImgs in body
     */
    @GetMapping("/serv-imgs")
    @Timed
    public ResponseEntity<List<ServImg>> getAllServImgs(Pageable pageable) {
        log.debug("REST request to get a page of ServImgs");
        Page<ServImg> page = servImgService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/serv-imgs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /serv-imgs/:id : get the "id" servImg.
     *
     * @param id the id of the servImg to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the servImg, or with status 404 (Not Found)
     */
    @GetMapping("/serv-imgs/{id}")
    @Timed
    public ResponseEntity<ServImg> getServImg(@PathVariable Long id) {
        log.debug("REST request to get ServImg : {}", id);
        Optional<ServImg> servImg = servImgService.findOne(id);
        return ResponseUtil.wrapOrNotFound(servImg);
    }

    /**
     * DELETE  /serv-imgs/:id : delete the "id" servImg.
     *
     * @param id the id of the servImg to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/serv-imgs/{id}")
    @Timed
    public ResponseEntity<Void> deleteServImg(@PathVariable Long id) {
        log.debug("REST request to delete ServImg : {}", id);
        servImgService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
