package com.example.gerenciadorDeSenhas.controller;

import com.example.gerenciadorDeSenhas.entity.Password;
import com.example.gerenciadorDeSenhas.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping
    public ResponseEntity<Page<Password>> listByAllPage (Pageable pageable){
        return ResponseEntity.ok().body(this.passwordService.listAll(pageable));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Password password){
        try {
            Password pass = this.passwordService.insert(password);
//            this.passwordService.insert(password);
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
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestBody Password password){
        try {
            this.passwordService.delete(id, password);
            return ResponseEntity.ok().body("Password deletada com sucesso.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
