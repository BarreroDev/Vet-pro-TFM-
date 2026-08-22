package com.veterinaria.back.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


/**
 * Entidad que representa a un veterinario.
 * Gestiona tanto los datos profesionales de colegiado como las credenciales de acceso 
 * y roles de seguridad para la autenticación (Spring Security).
 * 
 * @author Roberto Barrero de la Rosa
 * @version 1.0
 */

@Data
@Entity
@Table(name = "veterinarios")
public class Veterinario {
	
    /**
     * Identificador único del veterinario en la base de datos (Clave primaria).
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    /**
     * Número del prfesional. Debe ser unico en el sistema.
     */
    @Column(name = "numero_colegiado", nullable = false, unique = true, length = 40)
    private String numeroColegiado;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(nullable = false, length = 20)
    private String telefono;

    /**
     * El email tiene quie ser único como identificador para logearse.
     */
    @Column(nullable = false, unique = true, length = 100)
    private String email;
	
    /**
     * La contraseña sera escriptada paar el acceso al sistema.
     * @JsonIgnore evita que se envie la contaseña en las respuestas JSON.
     */
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    /**
     * Rol asignado para el control de permisos (ej. "ROLE_ADMIN" o "ROLE_USER")
     */
    @Column(nullable = false, length = 20)
    private String rol;

    @Column(name = "foto_url")
    private String fotoUrl;



    
   // Costructores

    public Veterinario() {
    }

    public Veterinario(String numeroColegiado, String nombre, String apellidos, String telefono, 
                       String email, String password, String rol, String fotoUrl) {
        this.numeroColegiado = numeroColegiado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.fotoUrl = fotoUrl;
    }
	

    //Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(String numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

}
