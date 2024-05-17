package Modelo.Jugador;

import Modelo.Equipamiento.Armaduras.ArmaduraCuero;
import Modelo.Equipamiento.Armas.Baston;
import Modelo.Bases.Jugador;
import Modelo.Enums.AtaquesJugador;
import Modelo.Enums.Iconos;

public class Mago extends Jugador {
    public Mago() {
        nombre="Mago";
        maxSalud = 40;
        salud=maxSalud;
        maxMana = 80;
        mana=maxMana;
        dmg=3;
        dmgBase = dmg;
        defensa = 0;
        defensaBase = defensa;
        oro = 30;
        arma= new Baston();
        armas.add(arma);
        armadura= new ArmaduraCuero();
        armaduras.add(armadura);
        ataques = AtaquesJugador.MAGO.getAtaques();
        aplicarEfectosEquipamiento(0);
        icono = Iconos.MAGO;
    }
}
