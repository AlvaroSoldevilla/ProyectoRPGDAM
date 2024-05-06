package Modelo.Bases;

import lombok.Data;

@Data
public abstract class Evento {
    protected String titulo;
    protected Jugador jugador;

    public abstract void empezarEvento();
    public abstract void terminarEvento();
}
