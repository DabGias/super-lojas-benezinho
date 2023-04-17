package br.com.fiap.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_unidade")
public class Unidade {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_unidade"
    )
    @SequenceGenerator(
            name = "seq_unidade",
            sequenceName = "seq_unidade",
            allocationSize = 1
    )
    @Column(name = "id_unidade")
    private Long id;

    @Column(name = "nome_unidade")
    private String nome;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(
            name = "id_unidade_sede",
            referencedColumnName = "id_unidade",
            foreignKey = @ForeignKey(
                    name = "fk_tb_unidade",
                    value = ConstraintMode.CONSTRAINT
            )
    )
    private Unidade sede;

    public Unidade() {}

    public Unidade(Long id, String nome, Unidade sede) {
        this.id = id;
        this.nome = nome;
        this.sede = sede;
    }

    public Unidade(String nome, Unidade sede) {
        this.nome = nome;
        this.sede = sede;
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

    public Unidade getSede() {
        return sede;
    }

    public void setSede(Unidade sede) {
        this.sede = sede;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Unidade {");
        sb.append(" id = ").append(id);
        sb.append(", nome = '").append(nome).append('\'');
        sb.append(", sede = ").append(sede);
        sb.append('}');

        return sb.toString();
    }
}
