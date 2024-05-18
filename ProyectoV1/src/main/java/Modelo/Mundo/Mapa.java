package Modelo.Mundo;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Enemigos.Serpiente;
import Modelo.Enemigos.Goblin;
import Modelo.Enemigos.Perro;
import Modelo.Enums.Iconos;
import Modelo.Eventos.*;
import Modelo.Eventos.Aleatorios.*;
import Modelo.Bases.Evento;
import Modelo.Eventos.Jefes.CombateWendigo;
import Modelo.Eventos.Jefes.CombateLobo;
import Modelo.Eventos.Jefes.CombateDragon;
import UI.Interfaces.Interfaz;
import lombok.Data;

import java.util.Random;

/**
 * La clase Mapa representa el mapa del juego, gestionando la progresión del jugador y los eventos en cada sala.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
@Data
public class Mapa {

    public Mapa(Jugador jugador, Interfaz interfaz) {
        this.jugador = jugador;
        this.interfaz = interfaz;
    }
    /**
     * Jugador de la partida
     */
    Jugador jugador;
    /**
     * Generador de números aleatorios
     */
    Random rng = new Random();
    /**
     * Nivel del mundo
     */
    int nivelActual = 1;
    /**
     * Sala actual, vuelve a 0 despues de cada nivel
     */
    int sala = 0;
    /**
     * Interfaz del juego
     */
    Interfaz interfaz;

    /**
     * Método para avanzar el nivel del mundo.
     * <p>Cambia el fondo
     * <p>Reinicia la sala
     */
    public void avanzarNivel() {
        nivelActual++;
        switch (nivelActual) {
            case 1:
                interfaz.setFondo(Iconos.NIVEL1.getRutaIcono());
                break;
            case 2:
                interfaz.setFondo(Iconos.NIVEL2.getRutaIcono());
                break;
            case 3:
                interfaz.setFondo(Iconos.NIVEL3.getRutaIcono());
                break;
        }
        sala = 0;
    }

    /**
     * Método para avanzar la sala.
     * <p>Dependiendo de la distancia hasta el jefe, tiene diferentes comportamientos:
     * <p>1-Si la distancia es -1 (El jefe ha sido derrotado) avanza el nivel y genera una nueva sala.
     * <p>2-Si la distancia es 0 genera una batalla contra el jefe del nivel.
     * <p>3-Si la distancias es la mitad del total, se genra una recompensa especial.
     * <p>4-En cualquier otro caso genera 2 o 3 eventos.
     *
     * @return Eventos generados.
     */
    public Evento[] avanzarSala() {
        sala++;
        Evento[] eventos;
        if (distanciaAJefe() == -1) {
            jugador.restaurarVida();
            jugador.subirNivel();
            avanzarNivel();
            return avanzarSala();
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

    /**
     * Determina la distancia hasta el jefe.
     *
     * @return Devuelve la distancia hasta el jefe.
     */
    int distanciaAJefe() {
        return (distanciaTotal() - sala);
    }

    /**
     * Determina la longitud total del nivel.
     *
     * @return Devuelve la longitud del nivel.
     */
    int distanciaTotal() {
        return 3+(2*nivelActual);
    }

    /**
     * Genera un evento aleatorio, COn una mayor probabilidad de generar combates y eventos aleatorios.
     *
     * @return Devuelve el evento que se ha generado.
     */
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

    /**
     * Genera un evento específico.
     *
     * @param codEvento Codigo del evento a generar: <p>0-Combate <p>1-Evento aleatorio <p>2-Tienda <p>3-Hoguera <p>4-Recompensa especial <p>5-Combate con el jefe del nivel.
     * @return Devuelve el evento que se ha generado.
     */
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
                        return new CombateLobo(jugador, interfaz);
                    case 2:
                        return new CombateWendigo(jugador, interfaz);
                    case 3:
                        return new CombateDragon(jugador, interfaz);
                }
        }
        return null;
    }

    /**
     * Genera uno de los eventos aleatorios, divididos por los niveles del mundo.
     *
     * @return Devuelve un evento de la clase Aleatorio.
     */
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

    /**
     * Genera un enemigo dependiendo del nivel.
     *
     * @return Devuelve el enemigo generado.
     */
    private Enemigo generarEnemigo() {
        switch (nivelActual) {
            case 1:
                return new Goblin();
            case 2:
                return new Perro();
            case 3:
                return new Serpiente();
        }
        return null;
    }
}