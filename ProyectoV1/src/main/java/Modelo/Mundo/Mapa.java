package Modelo.Mundo;

import Modelo.Bases.Jugador;
import Modelo.Eventos.Combate;
import Logica.Partida;
import Modelo.Bases.Evento;
import Modelo.Enemigos.PruebaEnemigo;
import Modelo.Eventos.Hoguera;
import Modelo.Eventos.Tienda;
import lombok.Data;

import java.util.Random;

@Data
public class Mapa {
    public Mapa(Jugador jugador) {
        this.jugador = jugador;
    }
    Jugador jugador;
    Random rng = new Random();
    int nivelActual;
    int sala;

    public Evento[] avanzarSala() {
        sala++;
        Evento[] eventos;
        if (distanciaAJefe() == 0) {
            eventos = new Evento[1];
            eventos[0] = generarEvento(5);
            return eventos;
        } else if (distanciaAJefe() == (5+(2*nivelActual)) / 2) {
            eventos = new Evento[1];
            eventos[0] = generarEvento(4);
            return eventos;
        } else {
            eventos = new Evento[rng.nextInt(2,4)];
            for (int i = 0; i < eventos.length; i++) {
                eventos[i] = generarEvento();
            }
            return eventos;
        }
    }

    int distanciaAJefe() {
        return (5+(2*nivelActual)) - sala;
    }

    Evento generarEvento() {

        int codEvento = rng.nextInt(0,4);

        switch (codEvento) {
            case 0:
                //Combate
                return new Combate(jugador,new PruebaEnemigo());
            case 1:
                //Evento aleatorio
                return null;
            case 2:
                //Tienda
                return new Tienda();
            case 3:
                //Hoguera
                return new Hoguera(jugador);
        }
        return null;
    }

    Evento generarEvento(int codEvento) {
        switch (codEvento) {
            case 0:
                //Combate
                return new Combate(jugador,new PruebaEnemigo());
            case 1:
                //Evento aleatorio
                return null;
            case 2:
                //Tienda
                return new Tienda();
            case 3:
                //Hoguera
                return new Hoguera(jugador);
            case 4:
                //Recompensa especial
                break;
            case 5:
                //Jefe
                break;
        }
        return null;
    }
}