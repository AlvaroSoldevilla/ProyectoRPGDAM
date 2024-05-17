package Modelo.Enemigos.Jefes;

import Modelo.Bases.Enemigo;
import Modelo.Enums.AtaquesEnemigo;
import Modelo.Enums.Iconos;

public class Wendigo extends Enemigo {
    public Wendigo(){
        nombre ="Wendigo";
        maxSalud = 90;
        salud = maxSalud;
        dmg = 25;
        dmgBase = dmg;
        defensa = 15;
        defensaBase = defensa;
        ataques = AtaquesEnemigo.WENDIGO.getAtaques();
        icono = Iconos.WENDIGO;
    }
}
