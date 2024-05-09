package Modelo.Mundo;

import Modelo.Bases.Jugador;
import Modelo.Eventos.*;
import Modelo.Eventos.Aleatorios.*;
import Modelo.Bases.Evento;
import Modelo.Enemigos.PruebaEnemigo;
import Modelo.Jefes.Bicho;
import Modelo.Jefes.Lobo;
import Modelo.Jefes.Dragon;
import lombok.Data;

import java.util.Random;

@Data
public class Mapa {
    public Mapa(Jugador jugador) {
        this.jugador = jugador;
    }
    Jugador jugador;
    Random rng = new Random();
    int nivelActual = 1;
    int sala = 0;

    public void avanzarNivel() {
        nivelActual++;
        sala = 0;
    }

    public Evento[] avanzarSala() {
        sala++;
        Evento[] eventos;
        if (distanciaAJefe() == -1) {
            avanzarNivel();
            avanzarSala();
            return null;
        } else if (distanciaAJefe() == 0) {
            eventos = new Evento[1];
            eventos[0] = generarEvento(5);
            return eventos;
        } else if (distanciaAJefe() == (distanciaTotal() / 2)) {
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
        return (distanciaTotal() - sala);
    }

    int distanciaTotal() {
        return 2+(2*nivelActual);
    }

    private Evento generarEvento() {

        int codEvento = rng.nextInt(0,4);

        switch (codEvento) {
            case 0:
                //Combate
                return new Combate(jugador,new PruebaEnemigo(),nivelActual);
            case 1:
                //Evento aleatorio
                return generarEventoAleatorio();
            case 2:
                //Tienda
                return new Tienda(jugador,nivelActual);
            case 3:
                //Hoguera
                return new Hoguera(jugador);
        }
        return null;
    }

    private Evento generarEvento(int codEvento) {
        switch (codEvento) {
            case 0:
                //Combate
                return new Combate(jugador,new PruebaEnemigo(),nivelActual);
            case 1:
                //Evento aleatorio
                return generarEventoAleatorio();
            case 2:
                //Tienda
                return new Tienda(jugador,nivelActual);
            case 3:
                //Hoguera
                return new Hoguera(jugador);
            case 4:
                //Recompensa especial
                return new RecompensaEspecial(jugador);
            case 5:
                //Jefe
                switch (nivelActual) {
                    case 1:
                        return new Bicho(jugador);
                    case 2:
                        return new Lobo(jugador);
                    case 3:
                        return new Dragon(jugador);
                }
        }
        return null;
    }

    private Aleatorio generarEventoAleatorio() {
        switch (nivelActual) {
            case 1:
                switch (rng.nextInt(0,3)) {
                    case 0:
                        return new EventoPintorMagico(jugador);
                    case 1:
                        return new EventoFuente(jugador, new PruebaEnemigo());
                    case 2:
                        return new EventoPiedra(jugador);
                }
            case 2:
                switch (rng.nextInt(0,3)) {
                    case 0:
                        return new EventoScammer(jugador);
                    case 1:
                        return new EventoGitanos(jugador, new PruebaEnemigo(),nivelActual);
                    case 2:
                        return new EventoNerd(jugador, new PruebaEnemigo(), nivelActual);
                }
            case 3:
                switch (rng.nextInt(0,4)) {
                    case 0:
                        return new EventoAnciana(jugador);
                    case 1:
                        return new EventoHerrero(jugador);
                    case 2:
                        return new EventoTaberna(jugador);
                    case 3:
                        return new EventoScammer(jugador);
                }
        }
        return null;
    }
}