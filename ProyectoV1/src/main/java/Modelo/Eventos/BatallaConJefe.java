package Modelo.Eventos;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Evento;
import Modelo.Enums.Iconos;
import UI.Interfaces.Interfaz;
import lombok.Getter;

public abstract class BatallaConJefe extends Evento {
    @Getter
    protected Enemigo jefe;

    public BatallaConJefe(Interfaz interfaz) {
        super(interfaz);
        icono = Iconos.BATALLAJEFE;
    }
}