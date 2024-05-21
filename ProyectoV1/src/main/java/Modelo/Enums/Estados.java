package Modelo.Enums;

import lombok.Getter;

/**
 * El enum Estados contiene los diferentes estados del juego.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
@Getter
public enum Estados {

    VENENO("Veneno",true,true,4,3,Iconos.ENVENENADO),
    QUEMADURA("Quemadura",true,true,2,4,Iconos.QUEMADURA),
    CEGADO("Cegado",false,true,2,1,Iconos.CEGADO),
    MALDITO("Maldito", true, true, 2,3,Iconos.MALDICION),
    BENDITO("Bendito", true, true, 2,1,Iconos.BENDITO),
    CONGELADO("Congelado", false, true, 1,1,Iconos.CONGELADO),
    SILENCIADO("Silenciado",true,true,1,1,Iconos.SILENCIADO),
    ELECTRIFICADO("Electrificado",true,true,5,2,Iconos.ELECTROCUTADO),
    ESPINAS("Espinas",false,false,5,0,Iconos.ESPINAS),
    FORTALEZA("Fortaleza",true,true,5, 2,Iconos.MASDEFENSA),
    RABIA("Rabia",false,false,7, 0,Iconos.RABIA),
    EVASION("Evasión",true,true,2, 4,Iconos.EVASION),
    MENOSDEFENSA("Menos defensa",true,true,10, 6,Iconos.MENOSDEFENSA),
    DESORIENTADO("Desorientado",false,true,2,1,Iconos.CONFUSO),
    CONTRAATACANDO("Contraatacando",false,true,2,1,null),
    RESISTENCIAVENENO("Resistencia a Veneno",false,true,2,3,Iconos.RESISTVEN),
    RESISTENCIAQUEMADURA("Resistencia a Quemadura",false,true,2,3,Iconos.RESISTQUEM),
    RESISTENCIAELECTRICIDAD("Resistencia a Electricidad",false,true,2,3,Iconos.RESISTELEC);

    /**
     * Constructor para inicializar los atributos del estado.
     *
     * @param nombre         Nombre del estado.
     * @param puedeMultiples Indica si el estado se puede aplicar múltiples veces.
     * @param deterioro      Indica si el estado se deteriora con el tiempo.
     * @param efecto         Efecto del estado.
     * @param duracion       Duración del estado.
     * @param icono          Icono que representa el estado.
     */
    Estados(String nombre, boolean puedeMultiples, boolean deterioro,int efecto,int duracion, Iconos icono) {
        this.nombre = nombre;
        this.puedeMultiples = puedeMultiples;
        this.deterioro = deterioro;
        this.efecto = efecto;
        this.duracion = duracion;
        this.icono = icono;
    }

    /**
     * Nombre del estado.
     */
    final String nombre;
    /**
     * Determina si se puede volver a aplicar un estado si la entidad ya sufre del estado.
     */
    final boolean puedeMultiples;
    /**
     * Determina si el estado va reduciendo su duración cada turno.
     */
    final boolean deterioro;
    /**
     * Determina el efecto del estado.
     * Dependiendo del estado, el efecto puede determinar distintos efectos, por ejemplo, el daño que hace en el caso de VENENO o el multiplicado de fallo en el caso de DESORIENTADO
     */
    final int efecto;
    /**
     * Determina la duracion del estado.
     */
    final int duracion;
    /**
     * El icono del estado
     */
    final Iconos icono;

}
