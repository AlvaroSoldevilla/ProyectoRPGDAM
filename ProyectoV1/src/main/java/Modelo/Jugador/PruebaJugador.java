package Modelo.Jugador;

import Modelo.Bases.Jugador;

public class PruebaJugador extends Jugador {

    public PruebaJugador() {
        nombre = "Prueba";
        maxSalud = 15;
        maxMana = 5;
        salud = maxSalud;
        mana = maxMana;
        dmg = 3;
        ataques = AtaquesJugador.GUERRERO.getAtaques();
        aplicarEfectosEquipamiento(0);
    }
}
