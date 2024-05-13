package Modelo.Bases;

import UI.Interfaces.Interfaz;
import lombok.Data;

@Data
public abstract class Evento {
    public Evento(Interfaz interfaz) {
        this.interfaz = interfaz;
    }
    protected Interfaz interfaz;
    protected String titulo;
    protected Jugador jugador;

    public abstract void empezarEvento();
    public abstract void terminarEvento();
}
