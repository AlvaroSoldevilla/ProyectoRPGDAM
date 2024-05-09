package Modelo.Jugador;

import Modelo.Armaduras.ArmaduraCuero;
import Modelo.Armas.Baston;
import Modelo.Bases.Armadura;
import Modelo.Bases.Jugador;

public class Mago extends Jugador {
    public Mago() {
        nombre="Mago";
        maxSalud = 40;
        salud=maxSalud;
        maxMana = 80;
        mana=maxMana;
        dmg=3;
        defensa = 0;
        oro = 30;
        arma= new Baston();
        armas.add(arma);
        armadura= new ArmaduraCuero();
        armaduras.add(armadura);
        ataques = AtaquesJugador.MAGO.getAtaques();
        aplicarEfectosEquipamiento(0);
    }
}
