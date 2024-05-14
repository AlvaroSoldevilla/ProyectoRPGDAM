package Modelo.Eventos;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Evento;
import Modelo.Enums.Iconos;
import UI.Interfaces.Interfaz;

public abstract class BatallaConJefe extends Evento {
    protected Enemigo jefe;

    public BatallaConJefe(Interfaz interfaz) {
        super(interfaz);
        icono = Iconos.BATALLAJEFE;
    }
}