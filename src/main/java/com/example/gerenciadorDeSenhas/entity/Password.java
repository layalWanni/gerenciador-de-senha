package com.example.gerenciadorDeSenhas.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@NoArgsConstructor
@Table(name = "passwords", schema = "public")
public class Password {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Getter @Setter
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Getter @Setter
    @Column(name = "url", nullable = false)
    private String url;

    @Getter @Setter
    @Column(name = "senha", nullable = false)
    private String senha;

}
