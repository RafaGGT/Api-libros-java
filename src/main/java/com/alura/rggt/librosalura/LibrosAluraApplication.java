package com.alura.rggt.librosalura;

import com.alura.rggt.librosalura.template.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibrosAluraApplication {


    public static void main(String[] args) {
        SpringApplication.run(LibrosAluraApplication.class, args);
        Menu on = new Menu();
        on.iniciarMenu();
    }

}
