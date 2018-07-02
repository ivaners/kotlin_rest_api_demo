package com.example.demo.Entity

import com.example.demo.Entity.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository:JpaRepository<Student,Long> {
    fun findByName(name: String): List<Student>
}