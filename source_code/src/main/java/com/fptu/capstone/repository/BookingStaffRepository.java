package com.fptu.capstone.repository;

import com.fptu.capstone.domain.BookingStaff;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BookingStaff entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookingStaffRepository extends JpaRepository<BookingStaff, Long> {

}
