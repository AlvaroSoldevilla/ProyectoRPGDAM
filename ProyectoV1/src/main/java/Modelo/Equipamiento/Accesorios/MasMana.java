package Modelo.Equipamiento.Accesorios;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;
import UI.Interfaces.Interfaz;

public class MasMana extends Accesorio {
    public MasMana(){
        nombre = "Collar de maná";
        inicioTurno = true;
        inicioCombate = true;
    }

    @Override
    public void aplicarEfecto(Jugador jugador, Interfaz interfaz) {
    jugador.setMana(jugador.getMana() + 5);
    }
}
