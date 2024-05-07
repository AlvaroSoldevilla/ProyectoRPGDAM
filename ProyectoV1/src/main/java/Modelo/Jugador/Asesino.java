package Modelo.Jugador;

import Modelo.Armaduras.ArmaduraCuero;
import Modelo.Armas.Daga;
import Modelo.Bases.Jugador;

public class Asesino extends Jugador {
   public Asesino(){
    nombre="Asesino";
    dmg=0;
    arma= new Daga();
    armas.add(arma);
    armadura= new ArmaduraCuero();
    armaduras.add(armadura);
   }
}
