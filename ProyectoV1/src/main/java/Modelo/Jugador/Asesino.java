package Modelo.Jugador;

import Modelo.Equipamiento.Armaduras.ArmaduraCuero;
import Modelo.Equipamiento.Armas.Daga;
import Modelo.Bases.Jugador;
import Modelo.Enums.AtaquesJugador;
import Modelo.Enums.Iconos;

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
    icono = Iconos.ASESINO;
   }
}
