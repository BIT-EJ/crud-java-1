package com.auth.main.services;

import com.auth.main.entities.UserRepository;
import com.auth.main.entities.User;

import com.auth.main.exceptions.AuthException;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void verifyPassword(String pass) throws AuthException{
        if(pass.length() < 8){
            throw new AuthException("Senha tem que ter 8 caracteres");
        }
    }

    public void createUser(String name, String pass, String rp_pass) throws AuthException {
        var users = userRepository.findByName(name);

        if(!pass.equals(rp_pass)){
            throw new AuthException("Senha diferente da repetida");
        }

        if(!users.isEmpty()){
            throw new AuthException("Usuario ja existe");
        }

        verifyPassword(pass);

        User user =
                new User(name, passwordEncoder.encode(pass), System.currentTimeMillis());

        userRepository.save(user);
    }

    public void loginUser(String name, String pass) throws AuthException {
        var users = userRepository.findByName(name);

        if(users.isEmpty()){
            throw new AuthException("Usuario nao existe");
        }

        var user = users.get(0);

        if(!passwordEncoder.matches(pass, user.getPass())){
            throw new AuthException("Senha errada");
        }

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("logged_in_as", user.getId());
    }

    public void editUser(int id, String pass, String rpass) throws AuthException{
        var user = userRepository.findById(id);

        if(!passwordEncoder.matches(pass, user.getPass())){
            throw new AuthException("Senha errada");
        }

        verifyPassword(pass);
    
        var new_pass = passwordEncoder.encode(rpass);

        user.setPass(new_pass);

        userRepository.save(user);
    }
}
