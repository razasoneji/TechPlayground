package com.example.hw1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
Alice and her Bakery
• Create a class called CakeBaker, that is dependent on two other
classes called Frosting and Syrup. This class has a function
called bakeCake().
• Create two interfaces of type Frosting and Syrup with a
function called getFrostingType and getSyrupType respectively.
• Create two implementations of these two interfaces (so total 4
classes) for Chocolate and Strawberry flavors.
• Use Dependency injection to inject the Frosting and Syrup
dependencies into CakeBaker and also to call the bakeCake
function of the CakeBaker class.
*/

@SpringBootApplication
public class Hw1Application implements CommandLineRunner {

    private final CakeBaker cakeBaker;
    //constructor DI
    public Hw1Application(CakeBaker cakeBaker) {
        this.cakeBaker = cakeBaker;
    }

    public static void main(String[] args) {
        SpringApplication.run(Hw1Application.class, args);
        // can create a variable here, but it will not be a bean,
        // beans are instance variable and not local variable.
        // the appconfig or appdbconfig has methods that return a variable.not a variable that is local and used as bean and hence is a different thing.
        //hence create here so it will be a variable/object and not a bean
        //hence we need to implement the
        //command line runner and use its run method.
        // we can create instance variable here and use its method,but main is a static method and hence cannot address /use
        //non static methods and non static variables here.
    }



    @Override
    public void run(String... args) throws Exception {
        cakeBaker.bake();
    }
}
