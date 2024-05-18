package Modelo.Bases;

import Modelo.Enums.Estados;
import lombok.Data;

import java.util.List;

/**
 * La clase abstracta Armadura representa una armadura en el juego con defensa y posibles inmunidades de estado.
 * Cada arma solo modifica las estadísticas.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
@Data
public abstract class Armadura extends Equipamiento {
    /**
     * Defensa de la armadura.
     */
    protected int defensa;
    /**
     * Lista de estados que cancelará al tener la armadura equipada.
     */
    protected List<Estados> inmunidades;
}
