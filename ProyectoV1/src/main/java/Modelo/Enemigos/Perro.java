package Modelo.Enemigos;

import Modelo.Bases.Enemigo;
import Modelo.Enums.AtaquesEnemigo;
import Modelo.Enums.Iconos;

public class Perro extends Enemigo {
    public Perro() {
        nombre= "Little Wendigo";
        salud = 50;
        dmg = 15;
        defensa = 8;
        ataques = AtaquesEnemigo.PERRO.getAtaques();
        icono = Iconos.PERRO;
    }
}
