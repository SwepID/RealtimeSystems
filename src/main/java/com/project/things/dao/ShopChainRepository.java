package com.project.things.dao;

import com.project.things.entities.ShopChain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopChainRepository extends JpaRepository<ShopChain, Integer> {
    @Query(value = "select shop_chain.chain_id, shop_chain.shop_chain_name, shop_chain.website, shop.shop_id, shop.contacts from shop_chain inner join shop on shop_chain.chain_id = shop.shop_chain_chain_id", nativeQuery = true)
    public List<String> getAllShopsAndShopChains();

}
