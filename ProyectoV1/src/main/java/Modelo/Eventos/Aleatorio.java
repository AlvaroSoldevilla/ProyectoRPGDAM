package Modelo.Eventos;

import Modelo.Bases.Evento;

public abstract class Aleatorio extends Evento {
    public Aleatorio() {
        titulo = "Evento";
    }
    protected String texto;
    protected String textoFinal;
    protected String[] opciones;

    @Override
    public void terminarEvento() {
        System.out.println(textoFinal);
    }
}
