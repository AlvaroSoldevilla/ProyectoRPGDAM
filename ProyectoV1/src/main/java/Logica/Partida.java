package Logica;

import Modelo.Bases.Evento;
import Modelo.Bases.Jugador;
import Modelo.Jugador.PruebaJugador;
import Modelo.Mundo.Mapa;
import UI.MenusConsola;
import lombok.Data;

@Data
public class Partida {
    Jugador jugador;
    Mapa mapa;

    public void iniciar() {
        jugador = new PruebaJugador();
        Mapa mapa1 = new Mapa(jugador);
        Evento[] eventosActuales;
        Evento evento;

        while (!jugador.estaMuerto()) {
            eventosActuales = mapa1.avanzarSala();
            if (eventosActuales.length == 1) {
                evento = eventosActuales[0];
                evento.empezarEvento();
            }
            evento = MenusConsola.menuElegirEvento(eventosActuales);
            evento.empezarEvento();
        }
    }
}
