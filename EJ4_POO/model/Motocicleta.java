package EJ4_POO.model;

// Herencia
public class Motocicleta extends Vehiculo {
    private int cilindraje;
    public Motocicleta(String placa, String marca, String modelo, double tarifaDiaria, int cilindraje){
        super(placa, marca, modelo, tarifaDiaria);
        this.cilindraje = cilindraje;
    }

    @Override
    public double calcularCosto(int dias){
        double recargoFijo = (cilindraje > 250) ? 75.00 : 0.0;
        return (tarifaDiaria * dias) + recargoFijo;
    }
    public int getCilindraje(){
        return cilindraje;
    }
}
