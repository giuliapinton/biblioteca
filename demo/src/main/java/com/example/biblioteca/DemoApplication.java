package com.example.biblioteca;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final BibliotecaController bibliotecaController;

    public DemoApplication(BibliotecaController bibliotecaController) {
        this.bibliotecaController = bibliotecaController;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Avvia il menu della biblioteca
        bibliotecaController.avvia();
    }
}
