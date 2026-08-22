package com.veterinaria.back.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


/**
 * Entidad que representa a un dueño de mascota dentro del sistema.
 * contiene su información mas la relación con la mascota.
 * 
 * @author Roberto Barrero de la Rosa.
 * @version 1.0
 */

@Entity
@Data
@Table(name = "dueno")

public class Dueno {
	
	/**
     * Identificador único del dueño en la base de datos (Clave Primaria).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
	 * Documento nacional de identidad del diueño. Solo puede ser único en el sistema.
	 */
	@Column(nullable = false, unique = true, length = 20)
	private String dni; 
	
	@Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(length = 20)
    private String telefono;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "foto_url")
    private String fotoUrl;

    /**
	 * Lista de mascotas que tiene el dueño.
	 */
	@OneToMany(mappedBy = "dueno", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Mascota> mascotas = new ArrayList<>();


	// Costructores
	public Dueno(){

	}

	public Dueno(String dni, String nombre, String apellidos, String telefono, String email, LocalDate fechaNacimiento, String fotoUrl) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.fotoUrl = fotoUrl;
    }

	


	//Getters y Setters


	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

	public void addMascota(Mascota mascota) {
        mascotas.add(mascota);
        mascota.setDueno(this);
    }

    public void removeMascota(Mascota mascota) {
        mascotas.remove(mascota);
        mascota.setDueno(null);
    }

}
