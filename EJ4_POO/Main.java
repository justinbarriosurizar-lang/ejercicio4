package EJ4_POO;

import java.util.Scanner;

// Dependencia
public class Main {
    public static void main(String[] args) {
        SistemaRentaMovil sistema = new SistemaRentaMovil();
        Scanner sc = new Scanner(System.in);

        sistema.agregarVehiculo(new Automovil("P343LOL", "Toyota", "Yaris", 150.0, 5, false));
        sistema.agregarVehiculo(new Automovil("P222JNS", "Honda", "Civic", 200.0, 5, true));
        sistema.agregarVehiculo(new Motocicleta("M002LSD", "Yamaha", "R3", 100.0, 321));
        sistema.agregarVehiculo(new Motocicleta("M125SEK", "Suzuki", "GN125", 80.0, 125));
        sistema.agregarVehiculo(new CamionetaCarga("C980NMO", "Isuzu", "Forward", 200.0, 1.5));
        sistema.agregarVehiculo(new CamionetaCarga("C444QNS", "Hino", "Dutro", 250.0, 3.0));

        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- MENÚ RENTAMOVIL ---");
            System.out.println("1. Cotizar Alquiler");
            System.out.println("2. Confirmar Alquiler");
            System.out.println("3. Registrar Devolución");
            System.out.println("4. Consultar Reporte General");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = sc.nextLine().trim();
            switch (opcion) {
                case "1":
                    System.out.print("Ingrese la placa del vehículo: ");
                    String placaCotiz = sc.nextLine().trim();
                    Vehiculo vCotiz = sistema.buscarVehiculo(placaCotiz);
                    
                    if (vCotiz == null) {
                        System.out.println("Error: No existe un vehículo registrado con esa placa.");
                        break;
                    }

                    System.out.print("Ingrese la cantidad de lo días: ");
                    try {
                        int dias = Integer.parseInt(sc.nextLine());
                        double total = sistema.cotizarAlquiler(placaCotiz, dias);
                        if (total > 0) {
                            System.out.printf("Cotisacion obtenida: Q%.2f y  Estado actual: %s\n", 
                                total, (vCotiz.isDisponible() ? "Disponible" : "Alquilado"));
                        } else {
                            System.out.println("Error: Los días de alquiler deben ser un número entero mayor a cero.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Formato de número inválido. Debe ingresar un valor entero.");
                    }
                    break;

                case "2":
                    System.out.print("Ingrese la placa del vehículo: ");
                    String placaAlq = sc.nextLine().trim();
                    System.out.print("Ingrese la cantidad de días: ");
                    try {
                        int dias = Integer.parseInt(sc.nextLine());
                        if (sistema.confirmarAlquiler(placaAlq, dias)) {
                            System.out.println("¡Alquiler confirmado con exito! Se ha registrado cobro.");
                        } else {
                            System.out.println("Error: No se pudo completar el alquiler (Vehículo ocupado, inexistente o días inválidos).");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Formato de número inválido. Debe ingresar un valor numérico entero.");
                    }
                    break;

                case "3":
                    System.out.print("Ingrese la placa para devolución: ");
                    String placaDev = sc.nextLine().trim();
                    if (sistema.registrarDevolucion(placaDev)) {
                        System.out.println("Devolución registrada exitosamente. El vehículo ahora está disponible.");
                    } else {
                        System.out.println("Error: El vehículo ya se encuentra disponible o la placa no existe.");
                    }
                    break;

                case "4":
                    System.out.println(sistema.generarReporte());
                    break;

                case "5":
                    salir = true;
                    System.out.println("Gracias por utilizar el sistema de RentaMovil.");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
        sc.close();
    }
}