package br.com.fiap.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(
        name = "tb_cliente",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_email_cliente",
                        columnNames = "email_cliente"
                )
        }
)
public class Cliente {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_cliente"
    )
    @SequenceGenerator(
            name =  "seq_cliente",
            sequenceName =  "seq_cliente",
            allocationSize = 1
    )
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "nome_cliente")
    private String nome;

    @Column(name = "email_cliente")
    private String email;

    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos = new LinkedHashSet<>();

    public Cliente() {}

    public Cliente(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Cliente(String nome, String email) {
        this.nome = nome;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cliente {");
        sb.append(" id = ").append(id);
        sb.append(", nome = '").append(nome).append('\'');
        sb.append(", email = '").append(email).append('\'');
        sb.append(" }");

        return sb.toString();
    }
}
