package Modelo.Misc;

import lombok.Getter;

@Getter
public enum Estados {

    VENENO("Venenno",true,true,2),
    QUEMADURA("Quemadura",true,true,2),
    CEGADO("Cegado",false,true,2);

    Estados(String nombre, boolean puedeMultiples, boolean deterioro,int efecto) {
        this.nombre = nombre;
        this.puedeMultiples = puedeMultiples;
        this.deterioro = deterioro;
        this.efecto = efecto;
    }

    final String nombre;
    final boolean puedeMultiples;
    final boolean deterioro;
    final int efecto;


}
