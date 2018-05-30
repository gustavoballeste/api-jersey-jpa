package br.com.itsimple.smartcities.test;

import javax.persistence.EntityManager;

import br.com.itsimple.smartcities.entity.Cliente;
import br.com.itsimple.smartcities.singleton.EntityManagerFactorySingleton;

public class Teste {

    public static void main(String[] args) {
        
        EntityManager em = null;

        Cliente cliente = new Cliente();
        cliente.setNome("Fernando Gouveia Lima");
        cliente.setEmail("qualquer@gmail.com");

        try {
            em = EntityManagerFactorySingleton
                                           .getInstance()
                                           .createEntityManager();
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            System.out.println("Cadastro não realizado");
        } finally {
            em.close();
        }
    }
}