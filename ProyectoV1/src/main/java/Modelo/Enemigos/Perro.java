package Modelo.Enemigos;

import Modelo.Bases.Enemigo;
import Modelo.Enums.AtaquesEnemigo;

public class Perro extends Enemigo {
    public Perro() {
        salud = 20;
        dmg = 5;
        defensa = 2;
        ataques = AtaquesEnemigo.PERRO.getAtaques();
    }
}
