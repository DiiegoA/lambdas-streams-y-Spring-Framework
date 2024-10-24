package com.aluracursos.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {

    private final Integer temporada;
    private final String titulo;
    private final Integer numeroEpisodio;
    private final Double evaluacion;
    private final LocalDate fechaDelanzamiento;

    public Episodio(Integer numero, DatosEpisodio d) {
        this.temporada = numero;
        this.titulo = d.titulo();
        this.numeroEpisodio = d.numeroEpisodio();

        Double evaluacionTemp;
        try {
            evaluacionTemp = Double.valueOf(d.evaluacion());
        } catch (NumberFormatException e) {
            evaluacionTemp = 0.0;  // Valor predeterminado si hay error o "N/A"
        }
        this.evaluacion = evaluacionTemp;  // Asignación final

        LocalDate fechaTemp;
        try {
            fechaTemp = LocalDate.parse(d.fechaDeLanzamiento());
        } catch (DateTimeParseException e) {
            fechaTemp = null;  // Valor predeterminado si hay error o "N/A"
        }
        this.fechaDelanzamiento = fechaTemp;  // Asignación final
    }

    // getters

    public Integer getTemporada() {
        return temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public LocalDate getFechaDelanzamiento() {
        return fechaDelanzamiento;
    }

    @Override
    public String toString() {
        return "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", evaluacion=" + evaluacion +
                ", fechaDelanzamiento=" + (fechaDelanzamiento != null ? fechaDelanzamiento : "Fecha no disponible");
    }
}