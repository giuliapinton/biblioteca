package com.example.biblioteca;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Biblioteca {

    private final String filePath;

    public Biblioteca(String filePath) {
        this.filePath = filePath;
    }

    // Carica i libri dal file CSV
    public List<Libro> caricaLibri() throws IOException {
        try (Reader reader = new FileReader(filePath)) {
            CsvToBean<Libro> csvToBean = new CsvToBeanBuilder<Libro>(reader)
                    .withType(Libro.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        }
    }

    // Salva i libri su file CSV
    public void salvaLibri(List<Libro> libri) throws IOException {
        try (Writer writer = new FileWriter(filePath)) {
            StatefulBeanToCsv<Libro> beanToCsv = new StatefulBeanToCsvBuilder<Libro>(writer).build();
            beanToCsv.write(libri);
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    // Aggiungi un nuovo libro
    public void aggiungiLibro(Libro libro) throws IOException {
        List<Libro> libri = caricaLibri();
        libri.add(libro);
        salvaLibri(libri);
    }

    public List<Libro> cercaPerAutore(String autore) throws IOException {
        return caricaLibri().stream()
                .filter(libro -> libro.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }

    public List<Libro> cercaPerGenere(String genere) throws IOException {
        return caricaLibri().stream()
                .filter(libro -> libro.getGenere().equalsIgnoreCase(genere))
                .collect(Collectors.toList());
    }

    public List<Libro> cercaPerAnno(int anno) throws IOException {
        return caricaLibri().stream()
                .filter(libro -> libro.getAnno() == anno)
                .collect(Collectors.toList());
    }

    public boolean modificaLibro(String titolo, String nuovoAutore, String nuovoGenere, int nuovoAnno) throws IOException {
        List<Libro> libri = caricaLibri();
        for (int i = 0; i < libri.size(); i++) {
            Libro l = libri.get(i);
            if (l.getTitolo().equalsIgnoreCase(titolo)) {
                l.setAutore(nuovoAutore.isEmpty() ? l.getAutore() : nuovoAutore);
                l.setGenere(nuovoGenere.isEmpty() ? l.getGenere() : nuovoGenere);
                l.setAnno(nuovoAnno <= 0 ? l.getAnno() : nuovoAnno);
                salvaLibri(libri);
                return true;
            }
        }
        return false;
    }

    public boolean eliminaLibro(String titolo) throws IOException {
        List<Libro> libri = caricaLibri();
        boolean removed = libri.removeIf(l -> l.getTitolo().equalsIgnoreCase(titolo));
        if (removed) {
            salvaLibri(libri);
        }
        return removed;
    }

    // Metodo per visualizzare i libri
    public void visualizzaLibri(List<Libro> libri) {
        if (libri.isEmpty()) {
            System.out.println("Nessun libro trovato.");
        } else {
            libri.forEach(libro -> System.out.println(libro));
        }
    }
}
