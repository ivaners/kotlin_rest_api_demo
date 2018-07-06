package com.example.demo.Security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import com.example.demo.Security.service.JwtUserDetailsService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
open class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private val jwtTokenUtil: JwtTokenUtil = JwtTokenUtil()

    @Autowired
    private val jwtUserDetailsService: JwtUserDetailsService? = null

    @Value("\${jwt.header}")
    private val tokenHeader: String = ""

    @Value("\${jwt.route.authentication.path}")
    private val authenticationPath: String? = null


    override fun configure(http: HttpSecurity) {
//		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic()
//        http.addFilterBefore(AuthFilter(authenticationManager()), BasicAuthenticationFilter::class.java)

        // 设置不拦截规则
        http.csrf().disable().authorizeRequests().antMatchers("/api/v1/custom/create").permitAll()

        val authenticationTokenFilter = JwtAuthorizationTokenFilter(userDetailsService(), jwtTokenUtil, tokenHeader)
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Autowired
    open fun configureGlobal(auth: AuthenticationManagerBuilder) {
        /*auth.inMemoryAuthentication().withUser("user").password("password").roles("USER")*/
//        auth.authenticationProvider(DomainUPAuthProvider(tokenService(), getConfig()))
//                .authenticationProvider(TokenAuthenticationProvider(tokenService()))
        auth.userDetailsService(jwtUserDetailsService)
                .passwordEncoder(passwordEncoderBean())
    }

    @Bean
    fun passwordEncoderBean(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    /*
    @Bean
    fun tokenService(): TokenService {
        return TokenService()
    }

    @Bean
    fun getConfig(): ConfigInfo {
        return ConfigInfo()
    }
    */

    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        // AuthenticationTokenFilter will ignore the below paths
        web.ignoring().antMatchers(HttpMethod.POST, authenticationPath)
                .and().ignoring().antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).and().ignoring()
        // 设置不拦截规则
//        web.ignoring().antMatchers("/api/v1/custom/get")
    }
}