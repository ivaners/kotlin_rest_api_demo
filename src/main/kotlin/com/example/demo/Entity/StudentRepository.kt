package com.example.demo.Entity

import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository:JpaRepository<Student,Long> {
    fun findByName(name: String): List<Student>
}