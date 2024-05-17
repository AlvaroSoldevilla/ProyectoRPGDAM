package Modelo.Enemigos;

import Modelo.Bases.Enemigo;
import Modelo.Enums.AtaquesEnemigo;
import Modelo.Enums.Iconos;

import java.util.Random;

public class Goblin extends Enemigo {
    public Goblin() {
        nombre = "Goblin";
        maxSalud = rng.nextInt(15,31);
        salud = maxSalud;
        dmg = rng.nextInt(3,8);
        dmgBase = dmg;
        defensa = rng.nextInt(0,6);
        defensaBase = defensa;
        ataques = AtaquesEnemigo.GOBLIN.getAtaques();
        icono = Iconos.GOBLIN;
    }
}
