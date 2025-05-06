/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.biblioteca;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class BibliotecaService {

    private final Biblioteca biblioteca;

    public BibliotecaService() {
        this.biblioteca = new Biblioteca("libri.csv"); // Passa il percorso del file CSV
    }

    public List<Libro> getTuttiLibri() throws IOException {
        return biblioteca.caricaLibri();
    }

    public void aggiungiLibro(Libro libro) throws IOException {
        biblioteca.aggiungiLibro(libro);
    }

    public boolean modificaLibro(String titolo, String autore, String genere, int anno) throws IOException {
        return biblioteca.modificaLibro(titolo, autore, genere, anno);
    }

    public boolean eliminaLibro(String titolo) throws IOException {
        return biblioteca.eliminaLibro(titolo);
    }

    public void visualizzaLibri() throws IOException {
        List<Libro> libri = biblioteca.caricaLibri();
        biblioteca.visualizzaLibri(libri);
    }
}
