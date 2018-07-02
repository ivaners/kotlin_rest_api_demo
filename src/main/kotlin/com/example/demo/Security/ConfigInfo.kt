package com.example.demo.Security

import org.springframework.beans.factory.annotation.*
import org.springframework.core.env.Environment
import org.springframework.context.annotation.*

@Configuration
@PropertySource("classpath:application.properties")
public class ConfigInfo {
    @Autowired
    lateinit var environment :Environment

    @Bean
    @Primary
    fun getEnv():Environment {
        return environment
    }


}