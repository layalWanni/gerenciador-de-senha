package com.example.gerenciadorDeSenhas.controller;

import com.example.gerenciadorDeSenhas.entity.Password;
import com.example.gerenciadorDeSenhas.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@CrossOrigin
@RequestMapping("/api/passwords")
public class PasswordController {

    @Autowired
    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }
    final PasswordService passwordService;

    @RequestMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.passwordService.findById(id));
    }

    public Password unencrypt(Password password){
        byte[] decoded = Base64.getDecoder().decode(password.getSenha());
        String decodedString = new String(decoded);
        password.setSenha(decodedString);
        return password;
    }

    @GetMapping
    public ResponseEntity<List<Password>> getAllRequests(){
        return ResponseEntity.ok().body(passwordService.findAll().stream().map(this::unencrypt).collect(Collectors.toList()));
    }

//    @GetMapping
//    public ResponseEntity<Page<Password>> listByAllPage (Pageable pageable){
//        return ResponseEntity.ok().body(this.passwordService.listAll(pageable));
//    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Password password){
        try {
            String original = password.getSenha();
            String encoded = Base64.getEncoder().encodeToString(original.getBytes());
            password.setSenha(encoded);
            Password pass = this.passwordService.insert(password);
            return ResponseEntity.ok().body("Password cadastrada com sucesso.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Password password){
        try {
            this.passwordService.update(id, password);
            return ResponseEntity.ok().body("Password atualizada com sucesso.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            this.passwordService.delete(id);
            return ResponseEntity.ok().body("Password deletada com sucesso.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
