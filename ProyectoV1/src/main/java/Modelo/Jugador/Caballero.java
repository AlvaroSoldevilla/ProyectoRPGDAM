package Modelo.Jugador;

import Modelo.Equipamiento.Armaduras.ArmaduraCuero;
import Modelo.Equipamiento.Armas.Espada;
import Modelo.Bases.Jugador;
import Modelo.Enums.AtaquesJugador;
import Modelo.Enums.Iconos;

public class Caballero extends Jugador {
    public Caballero() {
        nombre = "Caballero";
        maxSalud = 100;
        salud = maxSalud;
        maxMana = 30;
        mana = maxMana;
        dmg = 5;
        dmgBase = dmg;
        defensa = 5;
        defensaBase = defensa;
        poderBloqueo = 10;
        oro = 30;
        arma = new Espada();
        armas.add(arma);
        armadura = new ArmaduraCuero();
        armaduras.add(armadura);
        ataques = AtaquesJugador.CABALLERO.getAtaques();
        aplicarEfectosEquipamiento(0);
        icono = Iconos.CABALLERO;
    }
}
