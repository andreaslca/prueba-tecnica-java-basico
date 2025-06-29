package org.example.controllers;


import org.example.entities.Cliente;
import org.example.persistence.ClienteJPA;

import java.time.LocalDate;
import java.util.List;

public class ClientesController {
    public ClienteJPA clienteJPA = new ClienteJPA();

    public void agregarCliente(String nombre, String apellidos, String sexo, String ciudad, LocalDate fechaDeNacimiento, int telefono, String correoElectronico) {
        clienteJPA.agregarCliente(nombre, apellidos, sexo, ciudad, fechaDeNacimiento, telefono, correoElectronico);
    }

    public List<Cliente> listarClientes() {
        return clienteJPA.listarClientes();
    }

    public boolean actualizarInfo(Long id, String ciudad, int telefono, String correoElectronico) { //
        return clienteJPA.actualizarInfo(id, ciudad, telefono, correoElectronico);
    }

    public boolean eliminarCliente(Long id) {
        return clienteJPA.eliminarCliente(id);
    }

    public  List<Cliente> buscarClientes(String ciudad){
        return clienteJPA.buscarClientes(ciudad);
    }
}


