package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController
{
    @Autowired
    lateinit var studentRepository:StudentRepository
    @RequestMapping("/")
    fun index(name: String):List<Student>{
        return studentRepository.findByName(name)
    }

    @GetMapping("create")
    fun create(name:String): String {
        val student=Student(id=null,name = name)
        studentRepository.save(student)
        return "success"
    }
}