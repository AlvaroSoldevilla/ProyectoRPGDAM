package Modelo.Bases;

import Modelo.Enums.Estados;
import lombok.Data;

import java.util.List;

/**
 * La clase abstracta Arma representa un arma en el juego.
 * Cada arma solo modifica las estadísticas.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
@Data
public abstract class Arma extends Equipamiento{
    /**
     * Daño del arma.
     */
    protected int dmg;
    /**
     * Lista de estados que puede infligir el arma.
     */
    protected List<Estados> bonus;
}
