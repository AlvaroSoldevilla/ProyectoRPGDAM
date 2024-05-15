package Modelo.Eventos;

import Modelo.Bases.Evento;
import Modelo.Enums.Iconos;
import UI.Interfaces.Interfaz;
import lombok.Data;

@Data
public abstract class Aleatorio extends Evento {
    public Aleatorio(Interfaz interfaz) {
        super(interfaz);
        titulo = "Evento";
        icono = Iconos.ALEATORIO;
    }
    protected String textoFinal;
    protected String[] opciones;

    @Override
    public void terminarEvento() {
        System.out.println(textoFinal);
    }
}
