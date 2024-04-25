package Modelo.Enemigos;

import Modelo.Bases.Enemigo;
import Modelo.Misc.Estados;

public class PruebaEnemigo extends Enemigo {
    public PruebaEnemigo() {
        nombre = "Prueba";
        maxSalud = 15;
        salud = maxSalud;
        dmg = 3;
        ataques = AtaquesEnemigo.PRUEBA.getAtaques();
    }
}
