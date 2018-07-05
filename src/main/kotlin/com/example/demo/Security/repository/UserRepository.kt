package com.example.demo.Security.repository

import com.example.demo.model.security.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by stephan on 20.03.16.
 */
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User
}