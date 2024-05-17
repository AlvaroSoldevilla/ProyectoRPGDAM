package Modelo.Equipamiento.Accesorios;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;
import UI.Interfaces.Interfaz;

public class MasDmg extends Accesorio {
    public MasDmg(){
        nombre = "Collar de daño";
        inicioTurno = true;
        inicioCombate = true;
    }
    @Override
    public void aplicarEfecto(Jugador jugador, Interfaz interfaz) {
        jugador.setDmg(jugador.getDmg() + 5);

    }
}
