package com.example.demo.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import com.example.demo.entity.Student
import com.example.demo.entity.StudentRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page


@RestController
class CustomerController : BaseController()
{
    @Autowired
    lateinit var studentRepository: StudentRepository
    @RequestMapping("get")
    fun index(): List<Student> {
        return studentRepository.findAll()
    }

    @PostMapping("create")
    fun create(name:String): String {
        val student=Student(id=null,name = name)
        studentRepository.save(student)
        return "success"
    }

    @RequestMapping("all")
    fun getAll(@RequestParam(defaultValue = "") name: String, pageable: Pageable): Page<Student> {
        return studentRepository.findByNameLike("%" + name + "%", pageable)
    }

    @GetMapping("findAll")
    fun findAll(pageable: Pageable):Page<Student>{
        return studentRepository.findAll(pageable)
    }
}