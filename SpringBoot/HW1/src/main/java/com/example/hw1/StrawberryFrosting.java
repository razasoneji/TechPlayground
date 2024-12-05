package com.example.hw1;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name="frosting.mode",havingValue = "strawberry")
public class StrawberryFrosting implements Frosting{

    @Override
    public String getFrostingType(){
        return "Strawberry Frosting";
    }
}
