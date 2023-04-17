package br.com.fiap;

import br.com.fiap.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
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

        Unidade sede = new Unidade();
        sede.setSede(null);
        sede.setNome("Unidade Brasil");
        sede.setChefe(MANAGER.find(Funcionario.class, 1));

        Unidade filialSP = new Unidade();
        filialSP.setSede(sede);
        filialSP.setNome("São Paulo");
        filialSP.setChefe(MANAGER.find(Funcionario.class, 2));

        Unidade filialVilaMariana = new Unidade();
        filialVilaMariana.setNome("Vila Mariana");
        filialVilaMariana.setSede(filialSP);
        filialVilaMariana.setChefe(MANAGER.find(Funcionario.class, 3));

        Unidade filialSantana = new Unidade();
        filialSantana.setNome("Santana");
        filialSantana.setSede(filialSP);
        filialSantana.setChefe(MANAGER.find(Funcionario.class, 3));

        Unidade filialSantoAmaro = new Unidade();
        filialSantoAmaro.setNome("Santo Amaro");
        filialSantoAmaro.setSede(filialSP);
        filialSantoAmaro.setChefe(MANAGER.find(Funcionario.class, 3));

        Funcionario gabriel = new Funcionario();
        gabriel.setMatricula("123456789");

        Funcionario prof = new Funcionario();
        prof.setMatricula("21773");

        Funcionario ricardo = new Funcionario();
        ricardo.setMatricula("987654321");

        MANAGER.getTransaction().begin();
        MANAGER.persist(eletronicos);
        MANAGER.persist(mobile);
        MANAGER.persist(prodt);
        MANAGER.persist(pedido);
        Arrays.asList(gabriel, prof, ricardo).forEach(MANAGER::persist);
        Arrays.asList(sede, filialSP, filialVilaMariana, filialSantana, filialSantoAmaro).forEach(MANAGER::persist);
        MANAGER.getTransaction().commit();

        gabriel.addUnidade(MANAGER.find(Unidade.class, 1));
        gabriel.addUnidade(MANAGER.find(Unidade.class, 2));
        prof.addUnidade(MANAGER.find(Unidade.class, 1));
        ricardo.addUnidade(MANAGER.find(Unidade.class, 1));

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