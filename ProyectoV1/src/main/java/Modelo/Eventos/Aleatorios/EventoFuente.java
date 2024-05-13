package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.Interfaces.Interfaz;
import UI.MenusConsola;

public class EventoFuente extends Aleatorio {
    public EventoFuente(Jugador jugador, Enemigo enemigo, Interfaz interfaz) {
        super(interfaz);
        texto = "Ante ti se alza una fuente desgastada por el paso de los siglos. De su centro emana un flujo constante de agua transparente. Desconoces si este agua es potable";

        opciones = new String[2];
        opciones[0] = "Ignorar la fuente";
        opciones[1] = "Beber de la fuente";

        this.jugador = jugador;
        this.enemigo = enemigo;
    }

    Jugador jugador;
    Enemigo enemigo;
    @Override
    public void empezarEvento() {
        int opcionElegida;

        System.out.println(texto);

        opcionElegida = MenusConsola.menuEventoAleatorio(opciones);

        switch (opcionElegida) {
            case 0:
                textoFinal = "Decidiste ignorar esa fuente, no hubo consecuencias";
                break;
            case 1:
                textoFinal = "Bebiste del agua de la fuente, sientes como purifica tu interior. Tu salud se recuperó 10 puntos";
                jugador.curarVida(10);
                break;
        }
        if (!jugador.estaMuerto()) {
            terminarEvento();
        }

    }


}