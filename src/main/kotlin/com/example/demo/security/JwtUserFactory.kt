package com.example.demo.security

import java.util.stream.Collectors

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import com.example.demo.model.security.Authority
import com.example.demo.model.security.User

object JwtUserFactory {

    fun create(user: User): JwtUser {
        return JwtUser(
                user.id,
                user.username!!,
                user.firstname!!,
                user.lastname!!,
                user.email!!,
                user.password!!,
                mapToGrantedAuthorities(user.authorities!!),
                user.enabled!!,
                user.lastPasswordResetDate!!
        )
    }

    private fun mapToGrantedAuthorities(authorities: List<Authority>): List<GrantedAuthority> {
        return authorities.stream()
                .map { authority -> SimpleGrantedAuthority(authority.name.toString()) }
                .collect(Collectors.toList())
    }
}