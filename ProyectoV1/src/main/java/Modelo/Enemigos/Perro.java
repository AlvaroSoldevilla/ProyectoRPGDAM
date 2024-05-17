package Modelo.Enemigos;

import Modelo.Bases.Enemigo;
import Modelo.Enums.AtaquesEnemigo;
import Modelo.Enums.Iconos;

public class Perro extends Enemigo {
    public Perro() {
        nombre= "Mini Wendigo";
        maxSalud = rng.nextInt(40,61);
        salud = maxSalud;
        dmg = rng.nextInt(10,21);
        dmgBase = dmg;
        defensa = rng.nextInt(5,16);
        defensaBase = defensa;
        ataques = AtaquesEnemigo.PERRO.getAtaques();
        icono = Iconos.PERRO;
    }
}
