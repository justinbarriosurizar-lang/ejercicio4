package EJ4_POO.model;

// Herencia 
public class Automovil extends Vehiculo{
    private int cantidadPasajeros;
    private boolean esAutomatico;
    public Automovil(String placa, String marca, String modelo, double tarifaDiaria, int cantidadPasajeros, boolean esAutomatico){
        super(placa, marca, modelo, tarifaDiaria);
        this.cantidadPasajeros = cantidadPasajeros;
        this.esAutomatico = esAutomatico;
    }
    @Override
    public double calcularCosto(int dias){
        double recargo = esAutomatico ? (50.0 * dias) : 0.0;
        return (tarifaDiaria * dias)+recargo;
    }

    public int getCantidadPasajeros(){
        return cantidadPasajeros;
    }
    public boolean isEsAutomatico(){
        return esAutomatico;
    }
}
