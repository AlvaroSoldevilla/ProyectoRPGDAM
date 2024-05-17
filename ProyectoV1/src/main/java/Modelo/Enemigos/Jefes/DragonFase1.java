package Modelo.Enemigos.Jefes;

import Modelo.Bases.Enemigo;
import Modelo.Enums.AtaquesEnemigo;
import Modelo.Enums.Iconos;
import lombok.Data;

@Data
public class DragonFase1 extends Enemigo {
    public DragonFase1(){
        nombre = "Dragón azul";
        maxSalud = 100;
        salud = maxSalud;
        dmg = 30;
        dmgBase = dmg;
        defensa = 17;
        defensaBase = defensa;
        ataques = AtaquesEnemigo.DRAGONFASE1.getAtaques();
        icono = Iconos.DRAGON;
    }
    int hieloAcumulado = 0;
}
