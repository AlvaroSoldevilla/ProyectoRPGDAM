package Modelo.Enemigos;

import Modelo.Bases.Enemigo;
import Modelo.Enums.AtaquesEnemigo;

public class Enemigo2 extends Enemigo {
    public Enemigo2() {
        salud = 50;
        dmg = 15;
        defensa = 8;
        ataques = AtaquesEnemigo.ENEMIGO2.getAtaques();
    }
}
