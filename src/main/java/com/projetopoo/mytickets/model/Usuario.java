package com.projetopoo.mytickets.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 50)
    @Column(name = "nome", nullable = false)
    private String nome;

    @Email(message = "E-mail inválido")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "isAdmin", nullable = false)
    private Boolean isAdmin;

    @Size(min = 5, max = 50)
    @Column(name = "username")
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
    
    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }
    public String getNome() {
        return nome;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

}
