package Modelo.Enemigos.Jefes;

import Modelo.Bases.Enemigo;
import Modelo.Enums.AtaquesEnemigo;
import lombok.Data;

@Data
public class DragonFase2 extends Enemigo {
    public DragonFase2(){
        nombre = "Adula, Dragón Lunar";
        salud = 300;
        dmg = 40;
        defensa = 45;
        ataques = AtaquesEnemigo.DRAGONFASE2.getAtaques();
    }
    int hieloAcumulado = 0;
}
