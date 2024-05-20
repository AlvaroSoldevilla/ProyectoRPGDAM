package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

/**
 * La clase VenenoSeguro representa un ataque especial que aplica el estado de veneno al objetivo.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class VenenoSeguro extends AtaqueEspecial {

    /**
     * Constructor que inicializa el ataque.
     */
    public VenenoSeguro() {
        nombre = "Envenenar";
        coste = 7;
    }

    /**
     * Aplica el estado envenenado, que hace daño al objetivo cada turno.
     *
     * @param objetivo Entidad que recibe el ataque
     * @param atacante Entidad que hace el ataque
     * @param interfaz Interfaz del juego, se usará principalmente para mostrar mensajes.
     * @return Devuelve verdadero si el ataque se realiza correctamente.
     */
    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante,interfaz)) {
            interfaz.imprimirMensaje("Envenenar");
            objetivo.infligirEstado(Estados.VENENO,interfaz);
            return true;
        } else {
            return false;
        }
    }
}