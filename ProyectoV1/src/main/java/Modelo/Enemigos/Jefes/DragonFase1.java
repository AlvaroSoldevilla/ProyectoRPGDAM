package Modelo.Enemigos.Jefes;

import Modelo.Bases.Enemigo;
import Modelo.Enemigos.AtaquesEnemigo;

public class DragonFase1 extends Enemigo {
    public DragonFase1(){
        nombre = "Dragón rojo";
        salud = 200;
        dmg = 30;
        defensa = 30;
        ataques = AtaquesEnemigo.DRAGONFASE1.getAtaques();
    }

}
