package Modelo.Enemigos.Jefes;

import Modelo.Bases.Enemigo;
import Modelo.Enemigos.AtaquesEnemigo;

public class DragonFase2 extends Enemigo {
    public DragonFase2(){
        nombre = "Azeroth, el Dragón del Infierno";
        salud = 300;
        dmg = 40;
        defensa = 45;
        ataques = AtaquesEnemigo.DRAGONFASE2.getAtaques();
    }
}
