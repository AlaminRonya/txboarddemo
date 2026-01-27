package com.sdlcpro.txdemo.data.repository;

import com.sdlcpro.txdemo.data.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}