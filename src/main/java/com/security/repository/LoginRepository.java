package com.security.repository;

import com.security.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by app on 2016/9/1.
 */
@Repository
public interface LoginRepository extends JpaRepository<Admin,Integer> {

    @Query("select COUNT(a) from Admin a where a.uName=:qName and a.pwd=:qPwd")
    public int login(@Param("qName") String uName, @Param("qPwd") String pwd);

    @Query("select a from Admin a where a.uName=:qName")
    public Admin findAdmin(@Param("qName") String uName);
}

