package com.fptu.capstone.repository;

import com.fptu.capstone.domain.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Partner entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    @Query(value = "select distinct partner from Partner partner left join fetch partner.categories",
        countQuery = "select count(distinct partner) from Partner partner")
    Page<Partner> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct partner from Partner partner left join fetch partner.categories")
    List<Partner> findAllWithEagerRelationships();

    @Query("select partner from Partner partner left join fetch partner.categories where partner.id =:id")
    Optional<Partner> findOneWithEagerRelationships(@Param("id") Long id);

}
