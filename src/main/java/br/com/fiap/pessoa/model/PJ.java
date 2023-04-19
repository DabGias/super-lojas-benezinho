package br.com.fiap.pessoa.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_pj")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("PJ")
public class PJ extends Pessoa {
    @Column(name = "cnpj_pj")
    private String cnpj;

    public PJ() {}

    public PJ(String nome, LocalDate dtNascimento) {
        super(nome, dtNascimento);
    }

    public PJ(String nome, LocalDate dtNascimento, String cnpj) {
        super(nome, dtNascimento);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "PJ { " +
                "cnpj = '" + cnpj + '\'' +
                " } " + super.toString();
    }
}
