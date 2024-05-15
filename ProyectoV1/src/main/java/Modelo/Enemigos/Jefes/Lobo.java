package Modelo.Enemigos.Jefes;

import Modelo.Bases.Enemigo;
import Modelo.Enums.AtaquesEnemigo;

public class Lobo extends Enemigo {
    public Lobo(){
        nombre = "Lobo guardían de Adula";
        salud = 170;
        dmg = 20;
        defensa = 30;
        ataques = AtaquesEnemigo.LOBO.getAtaques();
    }
}
