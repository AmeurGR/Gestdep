package com.example.Gestdep.professor;

import com.example.Gestdep.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/professor")
public class ProfessorController {

    private ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public List<Professor> getProfessors(){
        return professorService.getProfessors();

    }


    @PostMapping
    public String registerNewProfessor(@RequestBody Professor professor){
        professorService.addNewProfessor(professor);
        return "Professor Added";
    }

    @DeleteMapping(path="{professorId}")
    public String deleteProfessor(@PathVariable("professorId")Long professorId) {
        professorService.deleteProfessor(professorId);
        return "Professor Deleted";
    }

    @PutMapping(path="{professorId}")
    public String updateProfessor(
            @PathVariable("professorId") Long professorId,
            @RequestBody Professor body){
        professorService.updateProfessor(professorId, body);

        return " The update was successful";

    }

}
