package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;


/**
 * La clase Parry representa un ataque especial que aplica el estado de contraataque al atacante.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class Parry extends AtaqueEspecial {

    /**
     * Constructor que inicializa el ataque.
     */
    public Parry() {
        nombre = "Parry";
        coste = 2;
    }

    /**
     * Aplica el efecto Contraataque.
     * Atacar a un objetivo con este estado hará que el ataque falle y el objetivo le haga daño al atacante.
     *
     * @param objetivo Entidad que recibe el ataque.
     * @param atacante Entidad que hace el ataque.
     * @param interfaz Interfaz del juego, se usará principalmente para mostrar mensajes.
     * @return Devuelve verdadero si el ataque se realiza correctamente.
     */
    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante,interfaz)) {
            interfaz.imprimirMensaje("Parry");
            atacante.infligirEstado(Estados.CONTRAATACANDO,interfaz);
        }
        return false;
    }
}
