package Modelo.Enemigos;

import Modelo.Bases.Enemigo;
import Modelo.Enums.AtaquesEnemigo;
import Modelo.Enums.Iconos;

public class Goblin extends Enemigo {
    public Goblin() {
        salud = 20;
        dmg = 5;
        defensa = 2;
        ataques = AtaquesEnemigo.GOBLIN.getAtaques();
        icono = Iconos.GOBLIN;
    }
}
