package com.fptu.capstone.repository;

import com.fptu.capstone.domain.Serv;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Serv entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServRepository extends JpaRepository<Serv, Long> {

}
