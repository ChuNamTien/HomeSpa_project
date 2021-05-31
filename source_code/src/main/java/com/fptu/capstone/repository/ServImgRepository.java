package com.fptu.capstone.repository;

import com.fptu.capstone.domain.ServImg;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ServImg entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServImgRepository extends JpaRepository<ServImg, Long> {

}
