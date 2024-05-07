package Modelo.Bases;

import lombok.Data;


@Data
public abstract class Accesorio {
    protected String nombre;
    protected boolean permanente;
    protected boolean inicioTurno;
    protected boolean inicioCombate;

    public abstract void aplicarEfecto(Jugador jugador);

}
