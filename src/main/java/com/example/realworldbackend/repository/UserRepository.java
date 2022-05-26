package com.example.realworldbackend.repository;

import com.example.realworldbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.crypto.spec.OAEPParameterSpec;
import javax.persistence.Id;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    void deleteUserById(Long id);
    Optional<User> findUserById(Long id);

}

