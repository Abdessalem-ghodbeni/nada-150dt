package com.example.biatapplication_backend.Repository;

import com.example.biatapplication_backend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Long> {

  //  User findByMatricule(Long matricule);

}
