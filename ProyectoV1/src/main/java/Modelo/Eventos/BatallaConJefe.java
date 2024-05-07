package Modelo.Eventos;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Evento;
import lombok.Data;

@Data
public abstract class BatallaConJefe extends Evento {
    protected Enemigo jefe;
    protected int fases;
    protected abstract void cambiarFase();
}