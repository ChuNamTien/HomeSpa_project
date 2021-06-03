package com.fptu.capstone.repository;

import com.fptu.capstone.domain.BookingActivity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BookingActivity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookingActivityRepository extends JpaRepository<BookingActivity, Long> {

}
