package com.veterinaria.back.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad que representa auna mascota en el sistema.
 * Relaciona con un dueño.
 * 
 * @author Roberto Barrero de la Rosa
 * @version 1.0
 */

@Data
@Table(name = "mascota")
@Entity


public class Mascota {
	/**
	 * Identificador único de la mascota en la base de datos (Clave Primaria).
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String especie;

    @Column(length = 50)
    private String raza;

    @Column(length = 20)
    private String edad;

    @Column(length = 20)
    private String peso;
	
	@Column(name = "foto_url")
    private String fotoUrl;

    /**
	 * Relaciona muchas mascotas con un solo dueño.
	 * 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dueno_dni", nullable = false)
	private Dueno dueno;

	/**
     * Relación Una Mascota tiene varias Consultas/Citas asociadas.
     */
    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consulta> consultas = new ArrayList<>();


	
	// Costructores

	public Mascota(){

	 };

	public Mascota(String nombre, String especie, String raza, String edad, String peso, String fotoUrl, Dueno dueno){
		this.nombre = nombre;
		this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.peso = peso;
        this.fotoUrl = fotoUrl;
        this.dueno = dueno;
	};

	

	//Getters y Setters

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public Dueno getDueno() {
        return dueno;
    }

    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}
