package EJ4_POO;

import java.util.ArrayList;
import java.util.List;

// Composicion
public class SistemaRentaMovil {
    private List<Vehiculo> flota;
    private double totalIngresos;

    public SistemaRentaMovil() {
        this.flota = new ArrayList<>();
        this.totalIngresos = 0.0;
    }

    public boolean buscarPlacaExiste(String placa) {
        return buscarVehiculo(placa) != null;
    }

    public Vehiculo buscarVehiculo(String placa) {
        for (Vehiculo v : flota) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }

    public boolean agregarVehiculo(Vehiculo v) {
        if (buscarPlacaExiste(v.getPlaca())) {
            return false; // error para una placa duplicada
        }
        flota.add(v);
        return true;
    }

    public double cotizarAlquiler(String placa, int dias) {
        Vehiculo v = buscarVehiculo(placa);
        if (v == null || dias <= 0) {
            return -1.0; // Codigo de error para algunvehiculo no hallado
        }
        return v.calcularCosto(dias);
    }

    public boolean confirmarAlquiler(String placa, int dias) {
        Vehiculo v = buscarVehiculo(placa);
        if (v != null && v.isDisponible() && dias > 0) {
            double costo = v.calcularCosto(dias);
            if (v.registrarAlquiler()) {
                totalIngresos += costo; 
                return true;
            }
        }
        return false;
    }

    public boolean registrarDevolucion(String placa) {
        Vehiculo v = buscarVehiculo(placa);
        if (v != null && !v.isDisponible()) {
            return v.registrarDevolucion();
        }
        return false;
    }

    public String generarReporte() {
        int autoDisp = 0, autoAlq = 0;
        int motoDisp = 0, motoAlq = 0;
        int camionetaDisp = 0, camionetaAlq = 0;

        for (Vehiculo v : flota) {
            if (v instanceof Automovil) {
                if (v.isDisponible()) autoDisp++; else autoAlq++;
            } else if (v instanceof Motocicleta) {
                if (v.isDisponible()) motoDisp++; else motoAlq++;
            } else if (v instanceof CamionetaCarga) {
                if (v.isDisponible()) camionetaDisp++; else camionetaAlq++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n================ REPORTE ================\n");
        sb.append(String.format("Total Vehículos Registrados: %d\n", flota.size()));
        sb.append(String.format("Automoviles   -> Disponibles: %d | Alquilados: %d\n", autoDisp, autoAlq));
        sb.append(String.format("Motocicletas  -> Disponibles: %d | Alquilados: %d\n", motoDisp, motoAlq));
        sb.append(String.format("Camionetas    -> Disponibles: %d | Alquilados: %d\n", camionetaDisp, camionetaAlq));
        sb.append(String.format("Total Ingresos Acumulados: Q%.2f\n", totalIngresos));
        sb.append("=================================================");
        return sb.toString();
    }

    public double getTotalIngresos() {
        return totalIngresos;
    }
}