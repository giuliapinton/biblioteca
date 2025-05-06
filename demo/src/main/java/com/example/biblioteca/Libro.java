/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.biblioteca;
import com.opencsv.bean.CsvBindByName;

public class Libro {

    @CsvBindByName
    private String titolo;

    @CsvBindByName
    private String autore;

    @CsvBindByName
    private String genere;

    @CsvBindByName
    private int anno;

    // Costruttore
    public Libro() {}

    public Libro(String titolo, String autore, String genere, int anno) {
        this.titolo = titolo;
        this.autore = autore;
        this.genere = genere;
        this.anno = anno;
    }

    // Getter e setter
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    @Override
    public String toString() {
        return "Titolo: " + titolo + ", Autore: " + autore + ", Genere: " + genere + ", Anno: " + anno;
    }
}
