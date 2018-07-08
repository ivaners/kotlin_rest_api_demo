package com.example.demo.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import com.example.demo.entity.Student
import com.example.demo.entity.StudentRepository

@RestController
class CustomerController : BaseController()
{
    @Autowired
    lateinit var studentRepository: StudentRepository
    @RequestMapping("get")
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