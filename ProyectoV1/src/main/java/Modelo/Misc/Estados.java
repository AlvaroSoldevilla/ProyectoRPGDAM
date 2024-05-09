package Modelo.Misc;

import lombok.Getter;

@Getter
public enum Estados {

    VENENO("Veneno",true,true,2,1),
    QUEMADURA("Quemadura",true,true,2,1),
    CEGADO("Cegado",false,true,2,1),
    MALDITO("Maldito", true, true, 2,1),
    BENDITO("Bendito", true, true, 2,1),
    CONGELADO("Congelado", false, true, 1,1),
    SILENCIADO("Silenciado",true,true,1,1),
    ELECTRIFICADO("Electrificado",true,true,2,1),
    ESPINAS("Espinas",false,true,5,1),
    FORTALEZA("Fortaleza",true,true,5, 2),
    RABIA("Rabia",false,false,7, 2),
    EVASION("Evasión",true,true,2, 4),
    MENOSDEFENSA("Menos defensa",true,true,10, 6),
    DESORIENTADO("Desorientado",false,true,0,1),
    CONTRAATACANDO("Contraatacando",false,true,2,1),
    RESISTENCIAVENENO("Resistencia a Veneno",false,true,1,3),
    RESISTENCIAQUEMADURA("Resistencia a Quemadura",false,true,1,3),
    RESISTENCIAELECTRICIDAD("Resistencia a Electricidad",false,true,1,3);

    Estados(String nombre, boolean puedeMultiples, boolean deterioro,int efecto,int duracion) {
        this.nombre = nombre;
        this.puedeMultiples = puedeMultiples;
        this.deterioro = deterioro;
        this.efecto = efecto;
        this.duracion = duracion;
    }

    final String nombre;
    final boolean puedeMultiples;
    final boolean deterioro;
    final int efecto;
    final int duracion;

}
