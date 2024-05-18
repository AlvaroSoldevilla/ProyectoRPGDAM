package Modelo.Bases;

import UI.Interfaces.Interfaz;
import lombok.Data;

/**
 * La clase abstracta AtaqueEspecial representa un ataque especial en el juego.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
@Data
public abstract class AtaqueEspecial {

    /**
     * Nombre del ataque.
     */
    protected String nombre;
    /**
     * Coste de mana del ataque.
     * Solo afecta al jugador.
     */
    protected int coste;

    /**
     * Metodo abstracto que dicta el efecto del ataque
     *
     * @param objetivo Entidad que recibe el ataque
     * @param atatcante Entidad que hace el ataque
     * @param interfaz Interfaz del juego, se usará principalmente para mostrar mensajes.
     * @return Devuelve verdadero si el ataque se realiza correctamente.
     */
    public abstract boolean hacerAtaque(Entidad objetivo, Entidad atatcante, Interfaz interfaz);

    /**
     * Metodo que comprueba si el atacante puede realizar el ataque especial.
     * Como solo comprueba si el atacante tiene suficiente maná, solo afecta al jugador, los enemigos siempre pueden hacer el ataque.
     *
     * @param atatcante Entidad que está intentando realizar el ataque especial.
     * @param interfaz Interfaz del juego, se usará principalmente para mostrar mensajes.
     * @return Devuelve verdadero si el atacante puede hacer el ataque.
     */
    public boolean puedeAtacar(Entidad atatcante, Interfaz interfaz) {
        if ((atatcante instanceof Jugador jugador)) {
            if (jugador.getMana()>=coste) {
                jugador.setMana(jugador.getMana()-coste);
                return true;
            } else {
                interfaz.imprimirMensaje("No tienes mana suficiente");
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return nombre + " " + coste;
    }
}
