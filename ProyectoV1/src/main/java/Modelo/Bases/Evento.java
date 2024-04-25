package Modelo.Bases;

public abstract class Evento {
    protected String titulo;
    protected Jugador j;

    public abstract void empezarEvento();
    public abstract void terminarEvento();
    public abstract void consecuencias();
}
