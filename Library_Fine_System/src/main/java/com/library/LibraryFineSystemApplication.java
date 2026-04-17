package com.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryFineSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryFineSystemApplication.class, args);
        System.out.println("===============");
        System.out.println("Library Fine System Backend is running!");
        System.out.println("===============");
    }

}
