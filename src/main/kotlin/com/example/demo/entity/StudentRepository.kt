package com.example.demo.entity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface StudentRepository:JpaRepository<Student,Long> {
    fun findByName(name: String): List<Student>

    fun findByNameLike(name: String, pageable: Pageable): Page<Student>
}