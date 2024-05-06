package Logica;

import Modelo.Enemigos.PruebaEnemigo;
import Modelo.Eventos.Combate;
import Modelo.Jugador.PruebaJugador;

public class Main {
    public static void main(String[] args) {
        // Prueba combate
        /*Combate c = new Combate(new PruebaJugador(),new PruebaEnemigo());
        c.empezarEvento();*/

        Partida partida = new Partida();
        partida.iniciar();
    }
}