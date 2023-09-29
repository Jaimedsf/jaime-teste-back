package com.pefoce.jaime.jaime.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "clients" ,
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "nome"),
            @UniqueConstraint(columnNames = "cpf")
    })

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String nome;

    @Column(length = 255)
    private String email;

    @Column(length = 11)
    private String cpf;

    @Column(precision = 7, scale = 2)
    private BigDecimal renda;

    @Column(length = 20)
    private String telefone;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    public Client() {
        // Construtor vazio necessário para JPA
    }

    public Client(String nome, String cpf, String email, BigDecimal renda, String telefone, LocalDateTime dataCriacao) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.renda = renda;
        this.telefone = telefone;
        this.dataCriacao = dataCriacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getRenda() {
        return renda;
    }

    public void setRenda(BigDecimal renda) {
        this.renda = renda;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

}
