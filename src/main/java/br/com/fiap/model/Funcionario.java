package br.com.fiap.model;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(
        name = "tb_funcionario",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_matricula",
                        columnNames = "matricula_funcionario"
                )
        }
)
public class Funcionario {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_funcionario"
    )
    @SequenceGenerator(
            name = "seq_funcionario",
            sequenceName = "seq_funcionario",
            allocationSize = 1
    )
    @Column(name = "id_funcionario")
    private Long id;

    @Column(name = "matricula_funcionario")
    private String matricula;

    @ManyToMany
    @JoinTable(
            name = "tb_funcionario_unidade",
            joinColumns = {
                    @JoinColumn(
                            name = "id_funcionario",
                            foreignKey = @ForeignKey(
                                    name = "fk_tb_funcionario",
                                    value = ConstraintMode.CONSTRAINT
                            )
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "id_unidade",
                            foreignKey = @ForeignKey(
                                    name = "fk_tb_unidade",
                                    value = ConstraintMode.CONSTRAINT
                            )
                    )
            }
    )
    private Set<Unidade> unidades = new LinkedHashSet<>();

    public Funcionario() {}

    public Funcionario(Long id, String matricula, Set<Unidade> unidades) {
        this.id = id;
        this.matricula = matricula;
        this.unidades = unidades;
    }

    public Funcionario(String matricula, Set<Unidade> unidades) {
        this.matricula = matricula;
        this.unidades = unidades;
    }

    public Funcionario addUnidade(Unidade u) {
        this.unidades.add(u);

        return this;
    }

    public Funcionario rmvUnidade(Unidade u) {
        this.unidades.remove(u);

        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Unidade> getUnidades() {
        return Collections.unmodifiableList(this.unidades.stream().toList());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Funcionario {");
        sb.append(" id = ").append(id);
        sb.append(", matricula = '").append(matricula).append('\'');
        sb.append(", unidades = ").append(unidades);
        sb.append('}');

        return sb.toString();
    }
}
