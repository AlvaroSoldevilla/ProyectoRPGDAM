package Modelo.Jugador;

import Modelo.Armaduras.ArmaduraCuero;
import Modelo.Armas.Baston;
import Modelo.Bases.Armadura;
import Modelo.Bases.Jugador;

public class Mago extends Jugador {
    public Mago() {
        nombre="Mago";
        dmg=0;
        arma= new Baston();
        armas.add(arma);
        armadura= new ArmaduraCuero();
        armaduras.add(armadura);
    }
}
