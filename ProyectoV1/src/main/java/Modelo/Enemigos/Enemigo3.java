package Modelo.Enemigos;

import Modelo.Bases.Enemigo;
import Modelo.Enums.AtaquesEnemigo;
import Modelo.Enums.Iconos;

public class Enemigo3 extends Enemigo {
    public Enemigo3() {
        nombre="Sepiente";
        salud = 70;
        dmg = 20;
        defensa = 10;
        ataques = AtaquesEnemigo.SERPIENTE.getAtaques();
        icono = Iconos.PERRO;
    }
}