package com.project.things.dao;

import com.project.things.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface ShopRepository extends JpaRepository<Shop, Integer> {

}
