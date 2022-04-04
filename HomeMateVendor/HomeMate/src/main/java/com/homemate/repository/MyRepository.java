package com.homemate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.homemate.entities.CustomerTbl;

@Repository
public interface MyRepository extends JpaRepository<CustomerTbl, Integer> {

}