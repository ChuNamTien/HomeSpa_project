package com.fptu.capstone.repository;

import com.fptu.capstone.domain.PartnerImg;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PartnerImg entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PartnerImgRepository extends JpaRepository<PartnerImg, Long> {

}
