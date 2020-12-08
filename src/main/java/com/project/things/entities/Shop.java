package com.project.things.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Fetch;
import org.springframework.stereotype.Repository;

import javax.persistence.*;


@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer shop_id;
    @ManyToOne
    ShopChain shopChain;
    String contacts;

    public Integer getShop_id() {
        return shop_id;
    }

    public void setShop_id(Integer shop_id) {
        this.shop_id = shop_id;
    }

    public ShopChain getShopChain() {
        return shopChain;
    }

    public void setShopChain(ShopChain shopChain) {
        this.shopChain = shopChain;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public Shop() {
    }

    @Override
    public String toString() {
        return "" + this.shop_id + " " + this.contacts + " " + shopChain.getChain_id();
    }
}
