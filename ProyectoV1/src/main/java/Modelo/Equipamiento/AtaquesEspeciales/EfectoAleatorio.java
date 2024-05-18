package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

import java.util.Random;


/**
 * La clase EfectoAleatorio representa un ataque especial que aplica un efecto de estado aleatorio al objetivo.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class EfectoAleatorio extends AtaqueEspecial {

    /**
     * Constructor que inicializa el ataque.
     */
    public EfectoAleatorio() {
        nombre = "Efecto aleatorio";
        coste = 10;
    }

    /**
     * Aplica uno de tres efectos al objetivo
     *
     * @param objetivo Entidad que recibe el ataque.
     * @param atacante Entidad que hace el ataque.
     * @param interfaz Interfaz del juego, se usará principalmente para mostrar mensajes.
     * @return Devuelve verdadero si el ataque se realiza correctamente.
     */
    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        Random rng = new Random();
        if (puedeAtacar(atacante,interfaz)) {
            switch (rng.nextInt(0,3)) {
                case 0:
                    objetivo.infligirEstado(Estados.QUEMADURA,interfaz);
                    break;
                case 1:
                    objetivo.infligirEstado(Estados.ELECTRIFICADO,interfaz);
                    break;
                case 2:
                    objetivo.infligirEstado(Estados.CONGELADO,interfaz);
                    break;
            }
            return true;
        } else {
            return false;
        }
    }
}