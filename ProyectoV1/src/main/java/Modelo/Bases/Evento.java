package Modelo.Bases;

public abstract class Evento {
    String titulo;
    int numOpciones;
    boolean fin;

    public abstract void empezarEvento();
    public abstract void terminarEvento();
    public abstract void consecuencias();
}
