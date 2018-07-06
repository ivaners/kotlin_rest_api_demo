package com.example.demo.Security

import java.util.Date

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import com.fasterxml.jackson.annotation.JsonIgnore

/**
 * Created by stephan on 20.03.16.
 */
class JwtUser(
        @get:JsonIgnore
        val id: Int?,
        private val username: String,
        val firstname: String,
        val lastname: String,
        val email: String,
        private val password: String, private val authorities: Collection<GrantedAuthority>,
        private val enabled: Boolean,
        @get:JsonIgnore
        val lastPasswordResetDate: Date
) : UserDetails {

    override fun getUsername(): String {
        return username
    }

    @JsonIgnore
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    @JsonIgnore
    override fun isAccountNonLocked(): Boolean {
        return true
    }

    @JsonIgnore
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    @JsonIgnore
    override fun getPassword(): String {
        return password
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun isEnabled(): Boolean {
        return enabled
    }
}