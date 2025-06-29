package org.example;

import org.example.controllers.ClientesController;
import org.example.entities.Cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        // DI
        ClientesController clientesController = new ClientesController();
        Scanner scanner = new Scanner(System.in);

        int opcion = 0;
        while (true) {
            System.out.println("Bienvenido al menú.");
            System.out.println("Escoja una de las siguientes opciones:");
            System.out.println("1. Crear un nuevo usuario.");
            System.out.println("2. Ver la lista completa de los clientes.");
            System.out.println("3. Actualizar la información de un cliente.");
            System.out.println("4. Eliminar un cliente.");
            System.out.println("5. Buscar un cliente por ciudad registrada.");
            System.out.println("6. Salir.");
            opcion = scanner.nextInt();
            scanner.nextLine(); //limpiar buffer

            switch (opcion) {
                case 1:
                    crearCliente(clientesController, scanner);
                    break;
                case 2:
                    List<Cliente> clientes = clientesController.listarClientes();
                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes.");
                    } else {
                        System.out.println("Listado de clientes: ");
                        for (Cliente cliente : clientes) {
                            System.out.println("ID:" + cliente.getId() + ", Cliente: " + cliente.getNombre() + " " + cliente.getApellidos() + " " + cliente.getCiudad() + " " + cliente.getSexo() + " " + cliente.getFechaDeNacimiento() + " " + cliente.getCorreoElectronico() + " " + cliente.getTelefono() + " "); //lista sólo por id, nombre y apellidos
                        }
                    }
                    break;
                case 3:
                    actualizarCliente(clientesController, scanner);
                    break;
                case 4:
                    System.out.println("Introduzca el ID del cliente a eliminar: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine(); //buffer
                    System.out.println(clientesController.eliminarCliente(id) ? "Cliente eliminado." : "Cliente no encontrado.");
                    break;
                case 5:
                    System.out.println("Introduzca la ciudad de busqueda: ");
                    String ciudad = scanner.nextLine();
                    List<Cliente> clientesEnCiudad = clientesController.buscarClientes(ciudad);
                    if (clientesEnCiudad.isEmpty()) {
                        System.out.println("No hay clientes.");
                    } else {
                        System.out.println("Listado de clientes en: " + ciudad + ".");
                        for (Cliente cliente : clientesEnCiudad) {
                            System.out.println("ID:" + cliente.getId() + ", Cliente: " + cliente.getNombre() + " " + cliente.getApellidos() + " " + cliente.getCiudad() + " " + cliente.getSexo() + " " + cliente.getFechaDeNacimiento() + " " + cliente.getCorreoElectronico() + " " + cliente.getTelefono() + " ");
                        }
                    }
                    break;
                case 6:
                    System.out.println("¡Gracias!");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    static void crearCliente(ClientesController clientesController, Scanner scanner) {
        System.out.println("Introduzca los siguientes datos para crear un nuevo usuario:");
        System.out.println("Introduzca su nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Introduzca los apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.println("Introduzca su sexo: ");
        String sexo = scanner.nextLine();
        System.out.println("Introduzca su ciudad de residencia: ");
        String ciudad = scanner.nextLine();
        System.out.println("Introduzca fecha de nacimiento: ");
        LocalDate fechaDeNacimiento = LocalDate.parse(scanner.nextLine()); //hay que meterlo como yyyy-mm-dd
        System.out.println("Introduzca su número de teléfono: ");
        int telefono = scanner.nextInt();
        scanner.nextLine(); //buffer
        System.out.println("Introduzca su correo electrónico: ");
        String correoElectronico = scanner.nextLine();

        clientesController.agregarCliente(nombre, apellidos, sexo, ciudad, fechaDeNacimiento, telefono, correoElectronico);
        System.out.println("Cliente agregado.");
        System.out.println(" ");

    }

    static void actualizarCliente(ClientesController clientesController, Scanner scanner) {
        System.out.println("Introduzca el ID del cliente que quiere cambiar: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); //buffer
        System.out.println("Introduzca los siguientes datos para actualizar la información de un cliente:");

        System.out.println("Ciudad de residencia: ");
        String ciudad = scanner.nextLine();
        System.out.println("Número de teléfono: ");
        int telefono = scanner.nextInt();
        scanner.nextLine(); //buffer
        System.out.println("Correo electrónico: ");
        String correoElectronico = scanner.nextLine();

        System.out.println(clientesController.actualizarInfo(id, ciudad, telefono, correoElectronico) ? "Datos del cliente actualizados." : "Cliente no registrado.");
    }
}