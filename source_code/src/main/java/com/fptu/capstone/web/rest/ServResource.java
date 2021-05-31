package com.fptu.capstone.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fptu.capstone.domain.Serv;
import com.fptu.capstone.service.ServService;
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
 * REST controller for managing Serv.
 */
@RestController
@RequestMapping("/api")
public class ServResource {

    private final Logger log = LoggerFactory.getLogger(ServResource.class);

    private static final String ENTITY_NAME = "serv";

    private ServService servService;

    public ServResource(ServService servService) {
        this.servService = servService;
    }

    /**
     * POST  /servs : Create a new serv.
     *
     * @param serv the serv to create
     * @return the ResponseEntity with status 201 (Created) and with body the new serv, or with status 400 (Bad Request) if the serv has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/servs")
    @Timed
    public ResponseEntity<Serv> createServ(@RequestBody Serv serv) throws URISyntaxException {
        log.debug("REST request to save Serv : {}", serv);
        if (serv.getId() != null) {
            throw new BadRequestAlertException("A new serv cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Serv result = servService.save(serv);
        return ResponseEntity.created(new URI("/api/servs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /servs : Updates an existing serv.
     *
     * @param serv the serv to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated serv,
     * or with status 400 (Bad Request) if the serv is not valid,
     * or with status 500 (Internal Server Error) if the serv couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/servs")
    @Timed
    public ResponseEntity<Serv> updateServ(@RequestBody Serv serv) throws URISyntaxException {
        log.debug("REST request to update Serv : {}", serv);
        if (serv.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Serv result = servService.save(serv);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, serv.getId().toString()))
            .body(result);
    }

    /**
     * GET  /servs : get all the servs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of servs in body
     */
    @GetMapping("/servs")
    @Timed
    public ResponseEntity<List<Serv>> getAllServs(Pageable pageable) {
        log.debug("REST request to get a page of Servs");
        Page<Serv> page = servService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/servs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /servs/:id : get the "id" serv.
     *
     * @param id the id of the serv to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the serv, or with status 404 (Not Found)
     */
    @GetMapping("/servs/{id}")
    @Timed
    public ResponseEntity<Serv> getServ(@PathVariable Long id) {
        log.debug("REST request to get Serv : {}", id);
        Optional<Serv> serv = servService.findOne(id);
        return ResponseUtil.wrapOrNotFound(serv);
    }

    /**
     * DELETE  /servs/:id : delete the "id" serv.
     *
     * @param id the id of the serv to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/servs/{id}")
    @Timed
    public ResponseEntity<Void> deleteServ(@PathVariable Long id) {
        log.debug("REST request to delete Serv : {}", id);
        servService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
