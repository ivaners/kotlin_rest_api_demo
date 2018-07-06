package com.example.demo.Security.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import com.example.demo.Security.JwtUserFactory
import com.example.demo.Security.repository.UserRepository

@Service
class JwtUserDetailsService : UserDetailsService {
    @Autowired
    private val userRepository: UserRepository? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository!!.findByUsername(username)
        return if (user == null) {
            throw UsernameNotFoundException(String.format("No user found with username '%s'.", username))
        } else {
            JwtUserFactory.create(user)
        }
    }
}
