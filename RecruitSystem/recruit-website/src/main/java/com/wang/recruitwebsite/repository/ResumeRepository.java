package com.wang.recruitwebsite.repository;
import com.wang.recruitwebsite.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume,String> {
}
