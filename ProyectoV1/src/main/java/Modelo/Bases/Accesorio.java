package Modelo.Bases;

import lombok.Data;


@Data
public abstract class Accesorio extends Equipamiento {
    protected boolean inicioTurno = false;
    protected boolean inicioCombate = false;
    protected boolean finCombate = false;
    protected boolean permanente = false;


    public abstract void aplicarEfecto(Jugador jugador);

}
