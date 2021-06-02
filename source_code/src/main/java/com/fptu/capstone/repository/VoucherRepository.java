package com.fptu.capstone.repository;

import com.fptu.capstone.domain.Voucher;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Voucher entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {

}
