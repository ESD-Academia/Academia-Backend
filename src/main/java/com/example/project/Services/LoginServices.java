package com.example.project.Services;

import com.example.project.Entities.Login;
import com.example.project.Models.LoginModel;
import com.example.project.Repositories.LoginRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServices {
    @Autowired
    private LoginRepository loginRepository;

    public Login createLogin(LoginModel loginModel) {
        Login newLogin = new Login();
        newLogin.setEmail(loginModel.getEmail());
        newLogin.setPassword(loginModel.getPassword());

        return loginRepository.save(newLogin);
    }

    public Login fetchDepartment(String email, String password) {
        return loginRepository.findByEmailAndPassword(email, password);
    }

    public Login fetchLogin(String email) {
        return loginRepository.findByEmail(email);
    }

    public boolean validateLogin(String email, String password) {
        return fetchDepartment(email, password) != null;
    }

}
