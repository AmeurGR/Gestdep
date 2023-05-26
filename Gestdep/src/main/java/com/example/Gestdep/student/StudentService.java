package com.example.Gestdep.student;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional =studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);


    }

    public void deleteStudent(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if(!exist){
            throw new IllegalStateException("Student with id "+studentId+" does not exist");
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(Long studentId, Student body) {

        Student student = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException("Student with id "
                +studentId+" does not exist"));

        if(body.getName() != null && body.getName().length() >0 && !Objects.equals(student.getName(),body.getName())){
            student.setName(body.getName());
        }
        if(body.getEmail() != null && body.getEmail().length() >0 && !Objects.equals(student.getEmail(),body.getEmail())){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(body.getEmail());
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(body.getEmail());
        }
        if((body.getAge() != student.getAge()) && body.getAge() !=0 ){

            student.setAge(body.getAge());
        }







    }
}
