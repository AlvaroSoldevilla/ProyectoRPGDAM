package Modelo.Enemigos.Jefes;

import Modelo.Bases.Enemigo;
import Modelo.Enums.AtaquesEnemigo;
import lombok.Data;

@Data
public class DragonFase1 extends Enemigo {
    public DragonFase1(){
        nombre = "Dragón azul";
        salud = 200;
        dmg = 30;
        defensa = 30;
        ataques = AtaquesEnemigo.DRAGONFASE1.getAtaques();
    }
    int hieloAcumulado = 0;
}
