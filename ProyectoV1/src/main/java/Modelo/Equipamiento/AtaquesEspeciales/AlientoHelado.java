package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enemigos.Jefes.DragonFase1;
import Modelo.Enemigos.Jefes.DragonFase2;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

public class AlientoHelado extends AtaqueEspecial {

    public AlientoHelado() {
        nombre = "Aliento Helado";
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante,interfaz)) {
            objetivo.recibirDmg(atacante.getDmg(),interfaz);
            if (atacante instanceof DragonFase1 dragon) {
                if (dragon.getHieloAcumulado() == 5) {
                    objetivo.infligirEstado(Estados.CONGELADO,interfaz);
                    dragon.setHieloAcumulado(0);
                } else {
                    dragon.setHieloAcumulado(dragon.getHieloAcumulado()+1);
                }
            } else if (atacante instanceof DragonFase2 dragon) {
                if (dragon.getHieloAcumulado() == 3) {
                    objetivo.infligirEstado(Estados.CONGELADO,interfaz);
                    dragon.setHieloAcumulado(0);
                } else {
                    dragon.setHieloAcumulado(dragon.getHieloAcumulado()+1);
                }
            }
            return true;
        } else {
            return false;
        }
    }



}
