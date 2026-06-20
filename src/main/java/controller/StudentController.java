package com.madhavi.placementportal.controller;

import com.madhavi.placementportal.entity.Student;
import com.madhavi.placementportal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @GetMapping("/{email}")
    public Student getStudent(@PathVariable String email){
        return studentRepository.findByEmail(email);
    }
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PostMapping("/register")
    public Student registerStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }
    @GetMapping
    public java.util.List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    @PostMapping("/login")
    public String loginStudent(@RequestBody Student student) {

        Student existingStudent =
                studentRepository.findByEmailAndPassword(
                        student.getEmail(),
                        student.getPassword()
                );

        if (existingStudent != null) {
            return "Login Successful";
        }

        return "Invalid Email or Password";
    }
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {

        Student student = studentRepository.findById(id).orElse(null);

        if (student != null) {
            student.setName(updatedStudent.getName());
            student.setEmail(updatedStudent.getEmail());
            student.setPassword(updatedStudent.getPassword());
            student.setPhone(updatedStudent.getPhone());
            student.setSkills(updatedStudent.getSkills());
            student.setResumePath(updatedStudent.getResumePath());

            return studentRepository.save(student);
        }

        return null;
    }
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentRepository.deleteById(id);

        return "Student Deleted Successfully";
    }
}
