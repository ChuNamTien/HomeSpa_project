package com.fptu.capstone.repository;

import com.fptu.capstone.domain.Staff;
import com.fptu.capstone.service.dto.StaffDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Staff entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    @Query(value = "select distinct staff from Staff staff left join fetch staff.categories left join fetch staff.treatments",
        countQuery = "select count(distinct staff) from Staff staff")
    Page<Staff> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct staff from Staff staff left join fetch staff.categories left join fetch staff.treatments")
    List<Staff> findAllWithEagerRelationships();

    @Query("select staff from Staff staff left join fetch staff.categories left join fetch staff.treatments where staff.id =:id")
    Optional<Staff> findOneWithEagerRelationships(@Param("id") Long id);

    @Query(name = "getAllStaffByPartnerId", nativeQuery = true)
	List<StaffDTO> getAllStaffByPartnerId(@Param("partnerId")Long partnerId);

}
