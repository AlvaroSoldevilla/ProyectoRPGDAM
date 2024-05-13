package Modelo.Enemigos;

import Modelo.Bases.Enemigo;

public class Goblin extends Enemigo {
    public Goblin() {
        salud = 20;
        dmg = 5;
        defensa = 2;
        ataques = AtaquesEnemigo.PERRO.getAtaques();
    }
}
