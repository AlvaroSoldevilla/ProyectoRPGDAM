package Modelo.Equipamiento.Accesorios;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

public class Antifuego extends Accesorio {

    public Antifuego(){
        nombre = "Anillo antifuego";
        inicioTurno = true;
        inicioCombate = true;
    }

    public void aplicarEfecto(Jugador jugador) {
        jugador.infligirEstado(Estados.RESISTENCIAQUEMADURA);
    }
}
