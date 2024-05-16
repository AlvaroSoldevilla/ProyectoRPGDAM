package Modelo.Bases;

import Modelo.Enums.Iconos;
import UI.Interfaces.Interfaz;
import lombok.Data;

@Data
public abstract class Evento {
    public Evento(){}
    public Evento(Interfaz interfaz) {
        this.interfaz = interfaz;
    }
    protected Interfaz interfaz;
    protected String titulo;
    protected String texto;
    protected Jugador jugador;
    protected Iconos icono;
    protected String[] opciones;
    protected int opcion;

    public abstract void empezarEvento();
    public abstract void terminarEvento();
}
