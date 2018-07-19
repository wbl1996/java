package com.wang.recruitwebsite.repository;
import com.wang.recruitwebsite.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {
}
