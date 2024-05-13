package Modelo.Enemigos;

import Modelo.Bases.Enemigo;

public class Enemigo3 extends Enemigo {
    public Enemigo3() {
        salud = 70;
        dmg = 20;
        defensa = 10;
        ataques = AtaquesEnemigo.ENEMIGO3.getAtaques();
    }
}