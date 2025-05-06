/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.biblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Controller
public class BibliotecaController {

    private final BibliotecaService bibliotecaService;

    @Autowired
    public BibliotecaController(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    public void avvia() throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Menu Biblioteca ===");
            System.out.println("1. Visualizza libri");
            System.out.println("2. Aggiungi un libro");
            System.out.println("3. Modifica un libro");
            System.out.println("4. Elimina un libro");
            System.out.println("5. Esci");
            System.out.print("Scegli un'opzione: ");
            int scelta = scanner.nextInt();
            scanner.nextLine(); // Consuma la newline

            switch (scelta) {
                case 1:
                    bibliotecaService.visualizzaLibri();
                    break;
                case 2:
                    System.out.print("Titolo: ");
                    String titolo = scanner.nextLine();
                    System.out.print("Autore: ");
                    String autore = scanner.nextLine();
                    System.out.print("Genere: ");
                    String genere = scanner.nextLine();
                    System.out.print("Anno: ");
                    int anno = scanner.nextInt();
                    scanner.nextLine(); // Consuma la newline
                    Libro libro = new Libro(titolo, autore, genere, anno);
                    bibliotecaService.aggiungiLibro(libro);
                    System.out.println("Libro aggiunto con successo!");
                    break;
                case 3:
                    System.out.print("Inserisci il titolo del libro da modificare: ");
                    String titoloModifica = scanner.nextLine();
                    System.out.print("Nuovo autore (lascia vuoto per non modificare): ");
                    String nuovoAutore = scanner.nextLine();
                    System.out.print("Nuovo genere (lascia vuoto per non modificare): ");
                    String nuovoGenere = scanner.nextLine();
                    System.out.print("Nuovo anno (0 per non modificare): ");
                    int nuovoAnno = scanner.nextInt();
                    scanner.nextLine(); // Consuma la newline

                    if (bibliotecaService.modificaLibro(titoloModifica, nuovoAutore, nuovoGenere, nuovoAnno)) {
                        System.out.println("Libro modificato con successo!");
                    } else {
                        System.out.println("Libro non trovato.");
                    }
                    break;
                case 4:
                    System.out.print("Inserisci il titolo del libro da eliminare: ");
                    String titoloElimina = scanner.nextLine();
                    if (bibliotecaService.eliminaLibro(titoloElimina)) {
                        System.out.println("Libro eliminato con successo!");
                    } else {
                        System.out.println("Libro non trovato.");
                    }
                    break;
                case 5:
                    System.out.println("Uscita...");
                    return;
                default:
                    System.out.println("Opzione non valida!");
            }
        }
    }
}
