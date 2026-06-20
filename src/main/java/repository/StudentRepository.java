package com.madhavi.placementportal.repository;

import com.madhavi.placementportal.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByEmailAndPassword(String email, String password);
    Student findByEmail(String email);
}