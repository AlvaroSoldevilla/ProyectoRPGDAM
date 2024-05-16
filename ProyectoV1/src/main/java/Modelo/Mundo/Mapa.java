package Modelo.Mundo;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Enemigos.Enemigo3;
import Modelo.Enemigos.Goblin;
import Modelo.Enemigos.Perro;
import Modelo.Eventos.*;
import Modelo.Eventos.Aleatorios.*;
import Modelo.Bases.Evento;
import Modelo.Enemigos.PruebaEnemigo;
import Modelo.Eventos.Jefes.CombateWendigo;
import Modelo.Eventos.Jefes.CombateLobo;
import Modelo.Eventos.Jefes.CombateDragon;
import UI.Interfaces.Interfaz;
import lombok.Data;

import java.util.Random;

@Data
public class Mapa {
    public Mapa(Jugador jugador, Interfaz interfaz) {
        this.jugador = jugador;
        this.interfaz = interfaz;
    }
    Jugador jugador;
    Random rng = new Random();
    int nivelActual = 1;
    int sala = 0;
    Interfaz interfaz;

    public void avanzarNivel() {
        nivelActual++;
        sala = 0;
    }

    public Evento[] avanzarSala() {
        sala++;
        Evento[] eventos;
        if (distanciaAJefe() == -1) {
            jugador.restaurarVida();
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
        return 3+(2*nivelActual);
    }

    private Evento generarEvento() {

        int codEvento = rng.nextInt(0,15);

        return switch (codEvento) {
            case 0, 1, 2, 3, 4, 5 ->
                //Combate
                    new Combate(jugador, generarEnemigo(), nivelActual, interfaz);
            case 6, 7, 8, 9, 10, 11 ->
                //Evento aleatorio
                    generarEventoAleatorio();
            case 12, 13 ->
                //Tienda
                    new Tienda(jugador, nivelActual, interfaz);
            case 14 ->
                //Hoguera
                    new Hoguera(jugador, interfaz);
            default -> null;
        };
    }

    private Evento generarEvento(int codEvento) {
        switch (codEvento) {
            case 0:
                //Combate
                return new Combate(jugador,generarEnemigo(),nivelActual, interfaz);
            case 1:
                //Evento aleatorio
                return generarEventoAleatorio();
            case 2:
                //Tienda
                return new Tienda(jugador,nivelActual, interfaz);
            case 3:
                //Hoguera
                return new Hoguera(jugador, interfaz);
            case 4:
                //Recompensa especial
                return new RecompensaEspecial(jugador, interfaz);
            case 5:
                //Jefe
                switch (nivelActual) {
                    case 1:
                        return new CombateWendigo(jugador, interfaz);
                    case 2:
                        return new CombateLobo(jugador, interfaz);
                    case 3:
                        return new CombateDragon(jugador, interfaz);
                }
        }
        return null;
    }

    private Aleatorio generarEventoAleatorio() {
        switch (nivelActual) {
            case 1:
                switch (rng.nextInt(0,3)) {
                    case 0:
                        return new EventoPintorMagico(jugador,interfaz);
                    case 1:
                        return new EventoFuente(jugador, interfaz);
                    case 2:
                        return new EventoPiedra(jugador, interfaz);
                }
            case 2:
                switch (rng.nextInt(0,3)) {
                    case 0:
                        return new EventoScammer(jugador, interfaz);
                    case 1:
                        //TODO: Poner enemigos especificos
                        return new EventoGitanos(jugador, generarEnemigo(),nivelActual, interfaz);
                    case 2:
                        return new EventoNerd(jugador, generarEnemigo(), nivelActual, interfaz);
                }
            case 3:
                switch (rng.nextInt(0,4)) {
                    case 0:
                        return new EventoAnciana(jugador, interfaz);
                    case 1:
                        return new EventoHerrero(jugador, interfaz);
                    case 2:
                        return new EventoTaberna(jugador, interfaz);
                    case 3:
                        return new EventoScammer(jugador, interfaz);
                }
        }
        return null;
    }

    //TODO: Poner los enemigos de cada nivel
    private Enemigo generarEnemigo() {
        switch (nivelActual) {
            case 1:
                switch (rng.nextInt(0,3)) {
                    case 0:
                        return new Goblin();
                    case 1:
                        return new Enemigo3();
                    case 2:
                        return new Enemigo3();
                }
            case 2:
                switch (rng.nextInt(0,3)) {
                    case 0:
                        return new Enemigo3();
                    case 1:
                        return new Perro();
                    case 2:
                        return new Enemigo3();
                }
            case 3:
                switch (rng.nextInt(0,4)) {
                    case 0:
                        return new Enemigo3();
                    case 1:
                        return new Enemigo3();
                    case 2:
                        return new Enemigo3();
                }
        }
        return null;
    }
}