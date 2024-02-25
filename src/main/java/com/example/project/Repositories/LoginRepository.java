package com.example.project.Repositories;

import com.example.project.Entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

    Login findByEmailAndPassword(String email, String password);

    Login findByEmail(String email);

}
