package br.com.fiap.pessoa.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tp_pessoa")
public abstract class Pessoa {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_pessoa"
    )
    @SequenceGenerator(
            name = "seq_pessoa",
            sequenceName = "seq_pessoa",
            allocationSize = 1
    )
    @Column(name = "id_pessoa")
    private Long id;

    @Column(name = "nome_pessoa")
    private String nome;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_nascimento_pessoa")
    private LocalDate dtNascimento;

    public Pessoa() {}

    public Pessoa(Long id, String nome, LocalDate dtNascimento) {
        this.id = id;
        this.nome = nome;
        this.dtNascimento = dtNascimento;
    }

    public Pessoa(String nome, LocalDate dtNascimento) {
        this.nome = nome;
        this.dtNascimento = dtNascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pessoa {");
        sb.append(" id = ").append(id);
        sb.append(", nome = '").append(nome).append('\'');
        sb.append(", dtNascimento = ").append(dtNascimento);
        sb.append('}');

        return sb.toString();
    }
}
