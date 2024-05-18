package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enemigos.Jefes.DragonFase1;
import Modelo.Enemigos.Jefes.DragonFase2;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

/**
 * La clase AlientoHelado representa un ataque especial que inflige daño y puede congelar al objetivo.
 * Ataque especial del tercer jefe
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class AlientoHelado extends AtaqueEspecial {

    /**
     * Constructor que inicializa el ataque.
     */
    public AlientoHelado() {
        nombre = "Aliento Helado";
    }

    /**
     * Hace daño al objetivo y si se acierta suficientes veces, congela al objetivo.
     *
     * @param objetivo Entidad que recibe el ataque.
     * @param atacante Entidad que hace el ataque.
     * @param interfaz Interfaz del juego, se usará principalmente para mostrar mensajes.
     * @return Devuelve verdadero si el ataque se realiza correctamente.
     */
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
