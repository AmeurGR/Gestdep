package com.example.Gestdep.admin;
import com.example.Gestdep.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/admin")
public class AdminController {

    private  final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<Admin> getAdmins(){
        return adminService.getAdmins();

    }

    @PostMapping
    public String registerNewAmin(@RequestBody Admin admin){
        adminService.addNewAdmin(admin);
        return  "Admin Added!!!";
    }

    @DeleteMapping(path="{adminId}")
    public String deleteAdmin(@PathVariable("adminId")Long adminId){
        adminService.deleteAdmin(adminId);
        return "Admin deleted!!";

    }

    @PutMapping(path="{adminId}")
    public String updateADMIN(
            @PathVariable("adminId") Long adminId,
            @RequestBody Admin body){
        adminService.updateAdmin(adminId, body);
        return "Update was successful !!";

    }
}
