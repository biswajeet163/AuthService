package com.jwtauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwtauth.model.UserCredential;

@Repository
public interface UserRepository extends JpaRepository<UserCredential, String> {

}
