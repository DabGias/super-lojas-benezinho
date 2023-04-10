package br.com.fiap.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tb_produto")
public class Produto {
    @Id
    @GeneratedValue(
            generator = "seq_produto",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "seq_produto",
            sequenceName = "seq_produto",
            allocationSize = 1
    )
    @Column(name = "id_produto")
    private Long id;

    @Column(name = "nome_produto")
    private String nome;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE }
    )
    @JoinColumn(
            name = "id_pedido",
            referencedColumnName = "id_pedido",
            foreignKey = @ForeignKey(
                    name = "fk_tb_pedido",
                    value = ConstraintMode.CONSTRAINT
            )
    )
    private Pedido pedido;

    @ManyToMany
    @JoinTable(
            name = "tb_produto_categoria",
            joinColumns = {
                    @JoinColumn(
                            name = "id_produto",
                            foreignKey = @ForeignKey(
                                    name = "fk_produto",
                                    value = ConstraintMode.CONSTRAINT
                            )
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "id_categoria",
                            foreignKey = @ForeignKey(
                                    name = "fk_categoria",
                                    value = ConstraintMode.CONSTRAINT
                            )
                    )
            }
    )
    private Set<Categoria> categorias = new LinkedHashSet<>();

    public Produto() {}

    public Produto(Long id, String nome, Pedido pedido, Set<Categoria> categorias) {
        this.id = id;
        this.nome = nome;
        this.pedido = pedido;
        this.categorias = categorias;
    }

    public Produto(String nome, Pedido pedido, Set<Categoria> categorias) {
        this.nome = nome;
        this.pedido = pedido;
        this.categorias = categorias;
    }

    public Produto addCategoria(Categoria c) {
        this.getCategorias().add(c);
        c.getProdutos().add(this);

        return this;
    }

    public Produto rmvCategoria(Categoria c) {
        this.getCategorias().remove(c);
        c.getProdutos().remove(this);

        return this;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Produto {");
        sb.append(" id = ").append(id);
        sb.append(", nome = '").append(nome).append('\'');
        sb.append(", pedido = ").append(pedido);
        sb.append(", categorias = ").append(categorias);
        sb.append(" }");

        return sb.toString();
    }
}
