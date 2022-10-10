package com.example.gerenciadorDeSenhas.repository;

import com.example.gerenciadorDeSenhas.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends JpaRepository<Password,Long> {

}
