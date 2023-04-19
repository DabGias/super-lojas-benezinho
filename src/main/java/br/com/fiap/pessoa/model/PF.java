package br.com.fiap.pessoa.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_pf")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("PF")
public class PF extends Pessoa {
    @Column(name = "cpf_pf")
    private String cpf;

    public PF() {}

    public PF(String nome, LocalDate dtNascimento) {
        super(nome, dtNascimento);
    }

    public PF(String nome, LocalDate dtNascimento, String cpf) {
        super(nome, dtNascimento);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "PF { " +
                "cpf = '" + cpf + '\'' +
                " } " + super.toString();
    }
}
