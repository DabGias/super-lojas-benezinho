package br.com.fiap.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tb_pedido")
public class Pedido {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_pedido"
    )
    @SequenceGenerator(
            name = "seq_pedido",
            sequenceName = "seq_pedido",
            allocationSize = 1
    )
    @Column(name = "id_pedido")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_pedido")
    private LocalDateTime dataPedido;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(
            name = "id_cliente",
            referencedColumnName = "id_cliente",
            foreignKey = @ForeignKey(
                    name = "fk_tb_cliente",
                    value = ConstraintMode.CONSTRAINT
            )
    )
    private Cliente cliente;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "tb_produto_pedido",
            foreignKey = @ForeignKey(
                    name = "fk_pedido_produto",
                    value = ConstraintMode.CONSTRAINT
            )
    )
    private Set<Produto> produtos = new LinkedHashSet<>();

    public Pedido() {}

    public Pedido(Long id, LocalDateTime dataPedido, Cliente cliente, Set<Produto> produtos) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.cliente = cliente;
        this.produtos = produtos;
    }

    public Pedido(LocalDateTime dataPedido, Cliente cliente, Set<Produto> produtos) {
        this.dataPedido = dataPedido;
        this.cliente = cliente;
        this.produtos = produtos;
    }

    public Pedido addProduto(Produto p) {
        this.getProdutos().add(p);

        return this;
    }

    public Pedido rmvProduto(Produto p) {
        this.getProdutos().remove(p);

        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pedido {");
        sb.append(" id = ").append(id);
        sb.append(", dataPedido = ").append(dataPedido);
        sb.append(", cliente = ").append(cliente);
        sb.append(", produtos = ").append(produtos);
        sb.append(" }");

        return sb.toString();
    }
}
