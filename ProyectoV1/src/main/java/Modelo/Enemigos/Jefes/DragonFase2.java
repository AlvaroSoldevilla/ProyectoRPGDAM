package Modelo.Enemigos.Jefes;

import Modelo.Bases.Enemigo;
import Modelo.Enums.AtaquesEnemigo;
import Modelo.Enums.Iconos;
import lombok.Data;

@Data
public class DragonFase2 extends Enemigo {
    public DragonFase2(){
        nombre = "Adula, Dragón Lunar";
        maxSalud = 150;
        salud = maxSalud;
        dmg = 35;
        dmgBase = dmg;
        defensa = 20;
        defensaBase = defensa;
        ataques = AtaquesEnemigo.DRAGONFASE2.getAtaques();
        icono = Iconos.DRAGON;
    }
    int hieloAcumulado = 0;
}
