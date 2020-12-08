package com.project.things.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ShopChain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer chain_id;
    String shopChainName;
    String website;

    public Integer getChain_id() {
        return chain_id;
    }

    public void setChain_id(Integer chain_id) {
        this.chain_id = chain_id;
    }

    public String getShopChainName() {
        return shopChainName;
    }

    public void setShopChainName(String shopChainName) {
        this.shopChainName = shopChainName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public ShopChain() {
    }

    @Override
    public String toString() {
        return "" + this.getChain_id() + " " + this.getShopChainName() + " " + this.getWebsite();
    }
}
