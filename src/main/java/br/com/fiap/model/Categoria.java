package br.com.fiap.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(
        name = "tb_categoria",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_nome_categoria",
                        columnNames = "nome_categoria"
                )
        }
)
public class Categoria {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_categoria"
    )
    @SequenceGenerator(
            name = "seq_categoria",
            sequenceName = "seq_categoria",
            allocationSize = 1
    )
    @Column(name = "id_categoria")
    private Long id;

    @Column(name = "nome_categoria")
    private String nome;

    @ManyToMany(mappedBy = "categorias")
    @OrderBy("nome asc")
    private Set<Produto> produtos = new LinkedHashSet<>();

    public Categoria() {}

    public Categoria(Long id, String nome, Set<Produto> produtos) {
        this.id = id;
        this.nome = nome;
        this.produtos = produtos;
    }

    public Categoria(String nome, Set<Produto> produtos) {
        this.nome = nome;
        this.produtos = produtos;
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

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Categoria {");
        sb.append(" id = ").append(id);
        sb.append(", nome = '").append(nome).append('\'');
        sb.append(" }");

        return sb.toString();
    }
}
