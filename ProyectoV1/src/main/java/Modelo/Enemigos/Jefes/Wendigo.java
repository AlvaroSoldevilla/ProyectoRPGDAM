package Modelo.Enemigos.Jefes;

import Modelo.Bases.Enemigo;
import Modelo.Enemigos.AtaquesEnemigo;

public class Wendigo extends Enemigo {
    public Wendigo(){
        nombre ="Frostreaper";
        salud = 200;
        dmg = 36;
        defensa = 36;
        ataques = AtaquesEnemigo.WENDIGO.getAtaques();
    }
}
