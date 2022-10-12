package com.example.gerenciadorDeSenhas.service;

import com.example.gerenciadorDeSenhas.entity.Password;
import com.example.gerenciadorDeSenhas.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepository;

    public Password findById(Long id){
        return this.passwordRepository.findById(id).orElse(new Password());
    }

    public Page<Password> listAll(Pageable pageable){
        return this.passwordRepository.findAll(pageable);
    }

    @Transactional
    public Password insert(Password password){
        return this.passwordRepository.save(password);
    }

    @Transactional
    public void update (Long id, Password password){
        if (id == password.getId()){
            this.passwordRepository.save(password);
        }
        else{
            throw new RuntimeException();
        }
    }

    @Transactional
    public void delete (Long id){
        Optional<Password> password = passwordRepository.findById(id);
        if (password.isPresent()){
            this.passwordRepository.delete(password.get());
        } else {
            throw new RuntimeException("Nao foi possivel deletar");
        }
    }


}
