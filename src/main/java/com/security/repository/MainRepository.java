package com.security.repository;

import com.security.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by app on 2016/9/14.
 */
@Repository
public interface MainRepository extends JpaRepository<Admin,Integer> {

    @Query("select a from Admin a where a.uName=:qName")
    public Admin findAdmin(@Param("qName") String uName);
}
