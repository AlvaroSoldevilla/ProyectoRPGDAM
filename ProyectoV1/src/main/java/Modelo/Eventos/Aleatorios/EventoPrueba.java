package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.MenusConsola;

public class EventoPrueba extends Aleatorio {
    public EventoPrueba(Jugador jugador) {
        texto = "Esto es un ejemplo de evento aleatorio";

        opciones = new String[3];
        opciones[0] = "Esta es la primera opción";
        opciones[1] = "Esta es la segunda opción";
        opciones[2] = "Esta es la tercera opción";

        this.jugador = jugador;
    }

    Jugador jugador;

    @Override
    public void empezarEvento() {
        int opcionElegida;

        System.out.println(texto);

        opcionElegida = MenusConsola.menuEventoAleatorio(opciones);

        switch (opcionElegida) {
            case 0:
                textoFinal = "Has elegido la primera opción";
                break;
            case 1:
                textoFinal = "Has elegido la segunda opción";
                break;
            case 2:
                textoFinal = "Has elegido la tercera opción";
                break;
        }
        terminarEvento();
    }


}
