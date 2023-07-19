package com.example.apply.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apply.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long>, SubjectRepositoryCustom {

}
