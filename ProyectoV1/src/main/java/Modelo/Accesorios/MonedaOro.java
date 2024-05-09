package Modelo.Accesorios;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;

public class MonedaOro extends Accesorio {

    public MonedaOro(){
        nombre = "Moneda de oro";
        inicioTurno = false;
        inicioCombate = false;
        finCombate= true;
    }

    public void aplicarEfecto(Jugador jugador) {
        jugador.ganarOro(5);
    }
}
