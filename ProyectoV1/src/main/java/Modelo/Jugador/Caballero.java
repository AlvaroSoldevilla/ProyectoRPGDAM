package Modelo.Jugador;

import Modelo.Armaduras.ArmaduraCuero;
import Modelo.Armas.Baston;
import Modelo.Armas.Espada;
import Modelo.Bases.Jugador;

public class Caballero extends Jugador {
    public Caballero() {
        nombre="Caballero";
        dmg=0;
        arma= new Espada();
        armas.add(arma);
        armadura= new ArmaduraCuero();
        armaduras.add(armadura);
    }
}
