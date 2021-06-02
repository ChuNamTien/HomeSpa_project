package com.fptu.capstone.repository;

import com.fptu.capstone.domain.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Voucher entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {

    @Query(value = "select distinct voucher from Voucher voucher left join fetch voucher.customers left join fetch voucher.servs left join fetch voucher.bookings",
        countQuery = "select count(distinct voucher) from Voucher voucher")
    Page<Voucher> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct voucher from Voucher voucher left join fetch voucher.customers left join fetch voucher.servs left join fetch voucher.bookings")
    List<Voucher> findAllWithEagerRelationships();

    @Query("select voucher from Voucher voucher left join fetch voucher.customers left join fetch voucher.servs left join fetch voucher.bookings where voucher.id =:id")
    Optional<Voucher> findOneWithEagerRelationships(@Param("id") Long id);

}
