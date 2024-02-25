package com.example.project.Controllers;

import com.example.project.Entities.Departments;
import com.example.project.Entities.Login;
import com.example.project.Models.BooleanResponseModel;
import com.example.project.Models.LoginModel;
import com.example.project.Services.LoginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginServices loginServices;

    @PostMapping("/createLogin")
    public ResponseEntity<Login> createLogin(@RequestBody LoginModel loginModel) {
        try {
            Login newLogin = loginServices.createLogin(loginModel);

            return ResponseEntity.of(Optional.of(newLogin));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/validateLogin")
    public ResponseEntity<BooleanResponseModel> validateLogin(@RequestBody LoginModel loginModel) {
        try {
            boolean stat = loginServices.validateLogin(loginModel.getEmail(), loginModel.getPassword());

            BooleanResponseModel booleanResponseModel = new BooleanResponseModel(stat);

            return ResponseEntity.of(Optional.of(booleanResponseModel));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }


}
