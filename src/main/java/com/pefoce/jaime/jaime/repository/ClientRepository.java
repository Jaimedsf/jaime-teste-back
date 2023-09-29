package com.pefoce.jaime.jaime.repository;

import com.pefoce.jaime.jaime.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    boolean existsByCpf(String cpf);

    boolean existsByNome(String nome);

    boolean existsByEmail(String email);
}

