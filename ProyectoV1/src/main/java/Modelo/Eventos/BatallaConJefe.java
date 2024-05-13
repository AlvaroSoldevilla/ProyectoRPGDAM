package Modelo.Eventos;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Evento;
import UI.Interfaces.Interfaz;
import lombok.Data;


public abstract class BatallaConJefe extends Evento {
    protected Enemigo jefe;

    public BatallaConJefe(Interfaz interfaz) {
        super(interfaz);
    }
}