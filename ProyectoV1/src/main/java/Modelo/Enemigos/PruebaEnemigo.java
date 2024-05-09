package Modelo.Enemigos;

import Modelo.Bases.Enemigo;
import Modelo.Misc.Estados;

public class PruebaEnemigo extends Enemigo {
    public PruebaEnemigo() {
        nombre = "Prueba";
        maxSalud = 40;
        salud = maxSalud;
        dmg = 10;
        ataques = AtaquesEnemigo.PRUEBA.getAtaques();
    }
}
