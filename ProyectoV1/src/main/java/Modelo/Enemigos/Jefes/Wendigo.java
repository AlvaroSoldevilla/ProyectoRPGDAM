package Modelo.Enemigos.Jefes;

import Modelo.Bases.Enemigo;
import Modelo.Enums.AtaquesEnemigo;

public class Wendigo extends Enemigo {
    public Wendigo(){
        nombre ="Wendigo";
        salud = 200;
        dmg = 36;
        defensa = 36;
        ataques = AtaquesEnemigo.WENDIGO.getAtaques();
    }
}
