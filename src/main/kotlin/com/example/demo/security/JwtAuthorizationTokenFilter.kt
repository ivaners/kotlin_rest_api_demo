package com.example.demo.security

import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import io.jsonwebtoken.ExpiredJwtException
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthorizationTokenFilter(private val userDetailsService: UserDetailsService, private val jwtTokenUtil: JwtTokenUtil, private val tokenHeader: String) : OncePerRequestFilter() {

    private val loggers = LoggerFactory.getLogger(this.javaClass)

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        loggers.info("processing authentication for '{}'", request.requestURL)

        val requestHeader = request.getHeader(this.tokenHeader)

        var username: String? = null
        var authToken: String? = null
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7)
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken)
            } catch (e: IllegalArgumentException) {
                loggers.error("an error occured during getting username from token", e)
            } catch (e: ExpiredJwtException) {
                loggers.warn("the token is expired and not valid anymore", e)
            }

        } else {
            loggers.warn("couldn't find bearer string, will ignore the header")
        }

        loggers.info("checking authentication for user '{}'", username)
        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            loggers.debug("security context was null, so authorizating user")

            // It is not compelling necessary to load the use details from the database. You could also store the information
            // in the token and read it from it. It's up to you ;)
            val userDetails = this.userDetailsService.loadUserByUsername(username)

            // For simple validation it is completely sufficient to just check the token integrity. You don't have to call
            // the database compellingly. Again it's up to you ;)
            if (jwtTokenUtil.validateToken(authToken!!, userDetails)!!) {
                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                loggers.info("authorizated user '{}', setting security context", username)
                SecurityContextHolder.getContext().authentication = authentication
            }
        }

        chain.doFilter(request, response)
    }
}