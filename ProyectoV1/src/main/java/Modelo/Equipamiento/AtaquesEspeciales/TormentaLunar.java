package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import UI.Interfaces.Interfaz;

/**
 * La clase TormentaLunar representa un ataque especial que inflige daño múltiple al objetivo.
 * Ataque especial del primer jefe.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class TormentaLunar extends AtaqueEspecial {

    /**
     * Constructor que inicializa el ataque.
     */
    public TormentaLunar() {
        nombre = "Tormenta Lunar";
    }

    /**
     * Hace daño al objetivo 3 veces, cada ataque hace menos daño.
     *
     * @param objetivo Entidad que recibe el ataque
     * @param atacante Entidad que hace el ataque
     * @param interfaz Interfaz del juego, se usará principalmente para mostrar mensajes.
     * @return Devuelve verdadero si el ataque se realiza correctamente.
     */
    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante,interfaz)) {
            interfaz.imprimirMensaje("Tormenta Lunar");
            objetivo.recibirDmg(atacante.getDmg(),interfaz);
            objetivo.recibirDmg(atacante.getDmg() - 10,interfaz);
            objetivo.recibirDmg(atacante.getDmg() - 15,interfaz);
            return true;
        } else {
            return false;
        }
    }
}

