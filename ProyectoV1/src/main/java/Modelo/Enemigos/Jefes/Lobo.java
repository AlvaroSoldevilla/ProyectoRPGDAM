package Modelo.Enemigos.Jefes;

import Modelo.Bases.Enemigo;
import Modelo.Enums.AtaquesEnemigo;
import Modelo.Enums.Iconos;

public class Lobo extends Enemigo {
    public Lobo(){
        nombre = "Lobo guardían de Adula";
        maxSalud = 50;
        salud = maxSalud;
        dmg = 20;
        dmgBase = dmg;
        defensa = 10;
        defensaBase = defensa;
        ataques = AtaquesEnemigo.LOBO.getAtaques();
        icono = Iconos.LOBO;
    }
}
