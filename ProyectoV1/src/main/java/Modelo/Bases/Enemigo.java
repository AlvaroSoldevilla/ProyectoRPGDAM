package Modelo.Bases;


import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * La clase abstracta Enemigo representa un enemigo en el juego.
 * Cada clase Enemigo solo modifica las estadísticas.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public abstract class Enemigo extends Entidad{

    /**
     * Generador de números aleatorios.
     */
    protected Random rng = new Random();
    /**
     * Lista de estados a los que el enemigo es inmune.
     */
    protected List<Estados> inmunidades = new ArrayList<>();

    /**
     * Metodo que añade un estado a la lista de estados infligidos.
     * Si el enemigo no es inmune al estado, se le añade.
     * Si el enemigo es inmune, se imprime un mensaje en la interfaz.
     *
     * @param estado Estado que se le quiere aplicar al enemigo.
     * @param interfaz La interfaz de la aplicacion, se usará principalmente para mostrar mensajes.
     */
    @Override
    public void infligirEstado(Estados estado, Interfaz interfaz) {
        if (inmunidades == null) {
            try {
                estadosSufridos.put(estado, estadosSufridos.get(estado) + estado.getDuracion());
            } catch (NullPointerException e) {
                estadosSufridos.put(estado, estado.getDuracion());
            }
        } else {
            if (!inmunidades.contains(estado)) {
                if (estadosSufridos.containsKey(estado)) {
                    estadosSufridos.put(estado, estadosSufridos.get(estado) + estado.getDuracion());
                } else {
                    estadosSufridos.put(estado, estado.getDuracion());
                }
            } else {
                interfaz.imprimirMensaje("El enemigo es inmune a " + estado.getNombre());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
