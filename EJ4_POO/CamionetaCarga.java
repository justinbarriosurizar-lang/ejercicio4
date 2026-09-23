package EJ4_POO;
// Herencia
public class CamionetaCarga extends Vehiculo {
    private double capacidadToneladas;

    public CamionetaCarga(String placa, String marca, String modelo, double tarifaDiaria, double capacidadToneladas) {
        super(placa, marca, modelo, tarifaDiaria);
        this.capacidadToneladas = capacidadToneladas;
    }

    @Override
    public double calcularCosto(int dias) {
        double recargoCarga = 100.0 * capacidadToneladas * dias;
        return (tarifaDiaria * dias) + recargoCarga;
    }

    public double getCapacidadToneladas() {
        return capacidadToneladas;
    }
}