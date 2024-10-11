package com.example.essai;

public class Dates {
    private String begin;
    private String end;

    // Constructeur
    public Dates(String begin, String end) {
        this.begin = begin;
        this.end = end;
    }

    // Getters et setters
    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
