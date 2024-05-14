package Modelo.Jugador;

import Modelo.Armaduras.ArmaduraCuero;
import Modelo.Armas.Daga;
import Modelo.Bases.Jugador;
import Modelo.Enums.AtaquesJugador;

public class Asesino extends Jugador {
   public Asesino(){
    nombre="Asesino";
    dmg=3;
    maxSalud = 30;
    salud=maxSalud;
    maxMana = 30;
    mana=maxMana;
    defensa = 5;
    oro = 40;
    arma= new Daga();
    armas.add(arma);
    armadura= new ArmaduraCuero();
    armaduras.add(armadura);
    ataques = AtaquesJugador.ASESINO.getAtaques();
    aplicarEfectosEquipamiento(0);
   }
}
