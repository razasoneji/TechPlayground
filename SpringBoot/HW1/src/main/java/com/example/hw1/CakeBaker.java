package com.example.hw1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CakeBaker {
    private final Frosting frosting;
    private final Syrup syrup;

    //Constructor DI
    @Autowired
    public CakeBaker(Frosting frosting , Syrup syrup){
        this.frosting = frosting;
        this.syrup = syrup;
    }

    public void bake(){
        System.out.println(" Baking Cake : The type of frosting used is - " + frosting.getFrostingType() + " and the type of syrup is - " + syrup.getSyrupType());
    }

    @PostConstruct
    public void showAfterBeanConstructAndInitialize(){
        System.out.println(" After Bean construct and initialize CakeBaker method postconstruct");
    }

    @PreDestroy
    public void showAfterBeanDestroy(){
        System.out.println(" After Bean destroy and initialize CakeBaker method postdestroy");
    }


}
