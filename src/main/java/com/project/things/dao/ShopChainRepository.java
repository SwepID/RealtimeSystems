package com.project.things.dao;

import com.project.things.entities.ShopChain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopChainRepository extends JpaRepository<ShopChain, Integer> {

}
