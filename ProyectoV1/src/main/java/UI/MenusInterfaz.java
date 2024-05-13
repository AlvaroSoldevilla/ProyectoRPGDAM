package UI;

import Modelo.Bases.Entidad;
import Modelo.Bases.Evento;
import Modelo.Bases.Jugador;
import Modelo.Jugador.Asesino;
import Modelo.Jugador.Caballero;
import Modelo.Jugador.Mago;
import Modelo.Jugador.PruebaJugador;

public class MenusInterfaz {

    public static Jugador menuEleccionJugador(int elegido) {
        return switch (elegido) {
            case 1 -> new Caballero();
            case 2 -> new Mago();
            case 3 -> new Asesino();
            default -> null;
        };
    }

    public static Evento menuElegirEvento(Evento[] eventosActuales,int elegido) {
        return eventosActuales[elegido];
    }
}
