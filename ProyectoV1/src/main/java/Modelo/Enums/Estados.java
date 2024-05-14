package Modelo.Enums;

import lombok.Getter;

@Getter
public enum Estados {

    VENENO("Veneno",true,true,2,1,Iconos.ENVENENADO),
    QUEMADURA("Quemadura",true,true,2,1,Iconos.QUEMADURA),
    CEGADO("Cegado",false,true,2,1,Iconos.CEGADO),
    MALDITO("Maldito", true, true, 2,1,Iconos.MALDICION),
    BENDITO("Bendito", true, true, 2,1,Iconos.BENDITO),
    CONGELADO("Congelado", false, true, 1,1,Iconos.CONGELADO),
    SILENCIADO("Silenciado",true,true,1,1,Iconos.SILENCIADO),
    ELECTRIFICADO("Electrificado",true,true,2,1,Iconos.ELECTROCUTADO),
    ESPINAS("Espinas",false,true,5,1,Iconos.ESPINAS),
    FORTALEZA("Fortaleza",true,true,5, 2,Iconos.MASDEFENSA),
    RABIA("Rabia",false,false,7, 2,Iconos.RABIA),
    EVASION("Evasión",true,true,2, 4,Iconos.EVASION),
    MENOSDEFENSA("Menos defensa",true,true,10, 6,Iconos.MENOSDEFENSA),
    DESORIENTADO("Desorientado",false,true,0,1,Iconos.CONFUSO),
    CONTRAATACANDO("Contraatacando",false,true,2,1,null),
    RESISTENCIAVENENO("Resistencia a Veneno",false,true,1,3,Iconos.RESISTVEN),
    RESISTENCIAQUEMADURA("Resistencia a Quemadura",false,true,1,3,Iconos.RESISTQUEM),
    RESISTENCIAELECTRICIDAD("Resistencia a Electricidad",false,true,1,3,Iconos.RESISTELEC);

    Estados(String nombre, boolean puedeMultiples, boolean deterioro,int efecto,int duracion, Iconos icono) {
        this.nombre = nombre;
        this.puedeMultiples = puedeMultiples;
        this.deterioro = deterioro;
        this.efecto = efecto;
        this.duracion = duracion;
        this.icono = icono;
    }

    final String nombre;
    final boolean puedeMultiples;
    final boolean deterioro;
    final int efecto;
    final int duracion;
    final Iconos icono;

}
