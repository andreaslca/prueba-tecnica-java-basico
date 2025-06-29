package org.example.persistence;

import jakarta.persistence.EntityManager;
import org.example.entities.Cliente;

import java.time.LocalDate;
import java.util.List;

public class ClienteJPA {

    public void agregarCliente(
            String nombre,
            String apellidos,
            String sexo,
            String ciudad,
            LocalDate fechaDeNacimiento,
            int telefono,
            String correoElectronico
    ) {
        EntityManager em = ConfigJPA.getEntityManager();
        em.getTransaction().begin();
        em.persist(new Cliente(nombre, apellidos, sexo, ciudad, fechaDeNacimiento, telefono, correoElectronico));
        em.getTransaction().commit();
        em.close();
    }

    public List<Cliente> listarClientes() {
        try (EntityManager em = ConfigJPA.getEntityManager()) {
            return em.createQuery("FROM Cliente", Cliente.class).getResultList();
        }
    }

    public boolean actualizarInfo(Long id, String ciudad, int telefono, String correoElectronico) {
        try (EntityManager em = ConfigJPA.getEntityManager()) {
            Cliente cliente = em.find(Cliente.class, id);
            if (cliente != null) {
                System.out.println("Ingrese los nuevos datos:");
                em.getTransaction().begin();

                cliente.setCiudad(ciudad);
                cliente.setTelefono(telefono);
                cliente.setCorreoElectronico(correoElectronico);

                em.getTransaction().commit();
                em.close();
                return true;
            } else {
                System.out.println("Cliente no encontrado.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error no esperado.");
            return false;
        }
    }

    public boolean eliminarCliente(Long id) {
        try (EntityManager em = ConfigJPA.getEntityManager()) {
            Cliente cliente = em.find(Cliente.class, id);
            if (cliente != null) {
                em.getTransaction().begin();
                em.remove(cliente);
                em.getTransaction().commit();
                return true;
            } else {
                return false;
            }
        }
    }

    public List<Cliente> buscarClientes(String ciudad) {
        try (EntityManager em = ConfigJPA.getEntityManager()) {
            return em.createQuery("FROM Cliente WHERE ciudad = '" + ciudad + "'", Cliente.class).getResultList();
        }
    }
}
