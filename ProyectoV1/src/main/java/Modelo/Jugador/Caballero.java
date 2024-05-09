package Modelo.Jugador;

import Modelo.Armaduras.ArmaduraCuero;
import Modelo.Armas.Baston;
import Modelo.Armas.Espada;
import Modelo.Bases.Jugador;

public class Caballero extends Jugador {
    public Caballero() {
        nombre = "Caballero";
        maxSalud = 100;
        salud = maxSalud;
        maxMana = 30;
        mana = maxMana;
        dmg = 5;
        defensa = 5;
        oro = 30;
        arma = new Espada();
        armas.add(arma);
        armadura = new ArmaduraCuero();
        armaduras.add(armadura);
        ataques = AtaquesJugador.CABALLERO.getAtaques();
        aplicarEfectosEquipamiento(0);
    }
}
