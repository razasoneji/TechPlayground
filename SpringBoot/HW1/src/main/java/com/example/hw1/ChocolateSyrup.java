package com.example.hw1;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "syrup.mode",havingValue = "chocolate")
public class ChocolateSyrup implements Syrup{
   @Override
    public String getSyrupType(){
        return "Chocolate Syrup";
    }
}
