package Modelo.Enemigos;

import Modelo.Bases.Enemigo;
import Modelo.Enums.AtaquesEnemigo;
import Modelo.Enums.Iconos;

public class Serpiente extends Enemigo {
    public Serpiente() {
        nombre="Sepiente";
        maxSalud = rng.nextInt(60,81);
        salud = maxSalud;
        dmg = rng.nextInt(20,31);
        dmgBase = dmg;
        defensa = rng.nextInt(10,21);
        defensaBase = defensa;
        poderBloqueo = 5;
        ataques = AtaquesEnemigo.SERPIENTE.getAtaques();
        icono = Iconos.SERPIENTE;
    }
}