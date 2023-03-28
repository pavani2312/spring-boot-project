package com.student.controller;

import com.student.entity.Student;
import com.student.service.StudentServiceImpl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class StudentController {

    StudentServiceImpl studentService;
    public StudentController(StudentServiceImpl studentService) {

        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String listOfStudents(Model model){
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/students/new")
    public String createNewStudent(Model model){
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";
    }
    @PostMapping("/students")
    public String addStudents(@ModelAttribute("student") Student student){
        studentService.saveStudents(student);
        return "redirect:/students";
    }

    @GetMapping("/students/update/{id}")
    public String getStudents(@PathVariable("id")Long id, Model model){
        model.addAttribute("student",studentService.getById(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateExistingStudent(@PathVariable("id")Long id, @ModelAttribute("student")Student student, Model model){
        Student existingStudent = studentService.getById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        studentService.updateStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteExistingstudent(@PathVariable("id")Long id, Model model){

        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}

