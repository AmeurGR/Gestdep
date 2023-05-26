package com.example.Gestdep.admin;

import com.example.Gestdep.student.Student;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminService {

    private AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAdmins() { return adminRepository.findAll(); }

    public void addNewAdmin(Admin admin) {
        Optional<Admin> adminOptional =adminRepository.findStudentByEmail(admin.getEmail());
        if(adminOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        adminRepository.save(admin);
    }

    public void deleteAdmin(Long adminId) {
        boolean exist = adminRepository.existsById(adminId);
        if(!exist){
            throw new IllegalStateException("Student with id "+adminId+" does not exist");
        }
        adminRepository.deleteById(adminId);
    }
    @Transactional
    public void updateAdmin(Long adminId, Admin body) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(()-> new IllegalStateException("Student with id "
                +adminId+" does not exist"));

        if(body.getName() != null && body.getName().length() >0 && !Objects.equals(admin.getName(),body.getName())){
            admin.setName(body.getName());
        }
        if(body.getEmail() != null && body.getEmail().length() >0 && !Objects.equals(admin.getEmail(),body.getEmail())){
            Optional<Admin> adminOptional = adminRepository.findStudentByEmail(body.getEmail());
            if(adminOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            admin.setEmail(body.getEmail());
        }
        if((body.getAge() != admin.getAge()) && body.getAge() !=0 ){

            admin.setAge(body.getAge());
        }
    }
}
