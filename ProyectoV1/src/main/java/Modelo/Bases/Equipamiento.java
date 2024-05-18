package Modelo.Bases;

import lombok.Data;

/**
 * La clase abstracta Equipamiento representa un objeto de equipamiento en el juego, esto incluye armaduras, armas y accesorios.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
@Data
public abstract class Equipamiento {
    /**
     * Nombre del equipamiento.
     */
    protected String nombre;
}
