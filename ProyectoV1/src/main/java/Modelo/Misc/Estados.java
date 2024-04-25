package Modelo.Misc;

import lombok.Getter;

@Getter
public enum Estados {

    VENENO("Veneno",true,true,2),
    QUEMADURA("Quemadura",true,true,2),
    CEGADO("Cegado",false,true,2),
    MALDITO("Maldito", true, true, 2),
    BENDITO("Bendito", true, true, 2),
    CONGELADO("Congelado", false, true, 1),
    SILENCIADO("Silenciado",true,true,1),
    ELECTRIFICADO("Electrificado",true,true,2),
    ESPINAS("Espinas",false,false,5);

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
