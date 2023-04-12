package br.com.fiap;

import br.com.fiap.model.Categoria;
import br.com.fiap.model.Cliente;
import br.com.fiap.model.Pedido;
import br.com.fiap.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    final static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory(
            LocalDate.now().getDayOfWeek() != DayOfWeek.WEDNESDAY ? "maria-db" : "oracle"
    );
    final static EntityManager MANAGER = FACTORY.createEntityManager();

    public static void main(String[] args) {
        Categoria eletronicos = new Categoria();
        eletronicos.setNome("Eletrônicos");

        Categoria mobile = new Categoria();
        mobile.setNome("Mobile");

        Cliente cliente = new Cliente();
        cliente.setNome("Pedro");
        cliente.setEmail("pedro@gmail.com");

        Produto prodt = new Produto();
        prodt.setNome("iPad");
        prodt.addCategoria(eletronicos);
        prodt.addCategoria(mobile);

        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setCliente(cliente);
        pedido.addProduto(prodt);

        MANAGER.getTransaction().begin();
        MANAGER.persist(eletronicos);
        MANAGER.persist(mobile);
        MANAGER.persist(prodt);
        MANAGER.persist(pedido);
        MANAGER.getTransaction().commit();

        Pedido pedidoFindId = findById(1L);
        List<?> pedidos = findAll();

        System.out.println(pedidoFindId);
        System.out.println(pedidos);

        MANAGER.close();
        FACTORY.close();
    }

    private static Pedido findById(Long id) { return MANAGER.find(Pedido.class, id); }

    private static List<?> findAll() { return MANAGER.createQuery("From Pedido").getResultList(); }
}