package com.veterinaria.back.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
/**
 * Entidad que representa una cita o consulta médica dentro de la agenda veterinaria.
 * Mantiene la vinculación entre la mascota atendida y el veterinario responsable.
 * 
 * @author Roberto Barrero de la Rosa
 * @version 1.0
 */
@Entity
@Data
@Table(name = "consultas")
public class Consulta {

    /**
     * Identificador único de la consulta/cita en la base de datos (Clave Primaria).
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    
    @Column(nullable = false, length = 255)
    private String motivo;

    /**
     * Estado de la cita (ej. Pendiente, Completada, Cancelada)
     */
    @Column(nullable = false, length =30)
    private Sstring estado;

    /**
     * Observaciones adicionales realizadas durante o tras la consulta.
     */
    @Column(columnDefinition = "TEXT")
    private String observaciones;

    /**
     * Muchas consultas pueden pertenecer a una mascota.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    /**
     * Veterinario asignado para atender la cita.
     * Relación Muchas Consultas pueden ser atendidas por un mismo Veterinario.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;


    
    //Costructores

    public Consulta() {
    }

    public Consulta(LocalDateTime fechaHora, String motivo, String estado, String observaciones, Mascota mascota, Veterinario veterinario) {
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.estado = estado;
        this.observaciones = observaciones;
        this.mascota = mascota;
        this.veterinario = veterinario;
    }



    //Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }
}
