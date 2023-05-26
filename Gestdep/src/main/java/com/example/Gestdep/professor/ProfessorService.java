package com.example.Gestdep.professor;

import com.example.Gestdep.student.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProfessorService {

    private ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }


    public List<Professor> getProfessors() { return professorRepository.findAll();  }


    public void addNewProfessor(Professor professor) {
        Optional<Professor> professorOptional =professorRepository.findStudentByEmail(professor.getEmail());
        if(professorOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        professorRepository.save(professor);
    }

    public void deleteProfessor(Long professorId) {
        boolean exist = professorRepository.existsById(professorId);
        if(!exist){
            throw new IllegalStateException("Student with id "+professorId+" does not exist");
        }
        professorRepository.deleteById(professorId);
    }
    @Transactional
    public void updateProfessor(Long professorId, Professor body) {

        Professor professor = professorRepository.findById(professorId).orElseThrow(()-> new IllegalStateException("Student with id "
                +professorId+" does not exist"));

        if(body.getName() != null && body.getName().length() >0 && !Objects.equals(professor.getName(),body.getName())){
            professor.setName(body.getName());
        }
        if(body.getEmail() != null && body.getEmail().length() >0 && !Objects.equals(professor.getEmail(),body.getEmail())){
            Optional<Professor> professorOptional = professorRepository.findStudentByEmail(body.getEmail());
            if(professorOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            professor.setEmail(body.getEmail());
        }
        if((body.getAge() != professor.getAge()) && body.getAge() !=0 ){

            professor.setAge(body.getAge());
        }
    }
}
