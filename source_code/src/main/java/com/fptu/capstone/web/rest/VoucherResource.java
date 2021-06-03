package com.fptu.capstone.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fptu.capstone.domain.Voucher;
import com.fptu.capstone.service.VoucherService;
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
 * REST controller for managing Voucher.
 */
@RestController
@RequestMapping("/api")
public class VoucherResource {

    private final Logger log = LoggerFactory.getLogger(VoucherResource.class);

    private static final String ENTITY_NAME = "voucher";

    private VoucherService voucherService;

    public VoucherResource(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    /**
     * POST  /vouchers : Create a new voucher.
     *
     * @param voucher the voucher to create
     * @return the ResponseEntity with status 201 (Created) and with body the new voucher, or with status 400 (Bad Request) if the voucher has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vouchers")
    @Timed
    public ResponseEntity<Voucher> createVoucher(@RequestBody Voucher voucher) throws URISyntaxException {
        log.debug("REST request to save Voucher : {}", voucher);
        if (voucher.getId() != null) {
            throw new BadRequestAlertException("A new voucher cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Voucher result = voucherService.save(voucher);
        return ResponseEntity.created(new URI("/api/vouchers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vouchers : Updates an existing voucher.
     *
     * @param voucher the voucher to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated voucher,
     * or with status 400 (Bad Request) if the voucher is not valid,
     * or with status 500 (Internal Server Error) if the voucher couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vouchers")
    @Timed
    public ResponseEntity<Voucher> updateVoucher(@RequestBody Voucher voucher) throws URISyntaxException {
        log.debug("REST request to update Voucher : {}", voucher);
        if (voucher.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Voucher result = voucherService.save(voucher);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, voucher.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vouchers : get all the vouchers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of vouchers in body
     */
    @GetMapping("/vouchers")
    @Timed
    public ResponseEntity<List<Voucher>> getAllVouchers(Pageable pageable) {
        log.debug("REST request to get a page of Vouchers");
        Page<Voucher> page = voucherService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vouchers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /vouchers/:id : get the "id" voucher.
     *
     * @param id the id of the voucher to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the voucher, or with status 404 (Not Found)
     */
    @GetMapping("/vouchers/{id}")
    @Timed
    public ResponseEntity<Voucher> getVoucher(@PathVariable Long id) {
        log.debug("REST request to get Voucher : {}", id);
        Optional<Voucher> voucher = voucherService.findOne(id);
        return ResponseUtil.wrapOrNotFound(voucher);
    }

    /**
     * DELETE  /vouchers/:id : delete the "id" voucher.
     *
     * @param id the id of the voucher to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vouchers/{id}")
    @Timed
    public ResponseEntity<Void> deleteVoucher(@PathVariable Long id) {
        log.debug("REST request to delete Voucher : {}", id);
        voucherService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
