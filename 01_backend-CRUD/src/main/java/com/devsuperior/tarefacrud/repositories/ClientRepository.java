package com.devsuperior.tarefacrud.repositories;

import com.devsuperior.tarefacrud.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
