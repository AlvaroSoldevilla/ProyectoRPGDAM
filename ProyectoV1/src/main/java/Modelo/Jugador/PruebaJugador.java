package Modelo.Jugador;

import Modelo.Armaduras.ArmaduraCuero;
import Modelo.Armas.Espada;
import Modelo.Bases.Jugador;
import Modelo.Enums.AtaquesJugador;

public class PruebaJugador extends Jugador {
    public PruebaJugador() {
        nombre = "Prueba";
        maxSalud = 15;
        maxMana = 5;
        salud = maxSalud;
        mana = maxMana;
        dmg = 3;
        arma = new Espada();
        armadura = new ArmaduraCuero();
        armas.add(arma);
        armaduras.add(armadura);
        ataques = AtaquesJugador.CABALLERO.getAtaques();
        aplicarEfectosEquipamiento(0);
    }
}
