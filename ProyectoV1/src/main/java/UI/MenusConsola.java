package UI;

import Modelo.Bases.Entidad;
import Modelo.Bases.Evento;
import Modelo.Bases.Jugador;
import Modelo.Jugador.Asesino;
import Modelo.Jugador.Caballero;
import Modelo.Jugador.Mago;
import Modelo.Jugador.PruebaJugador;

import java.util.Scanner;

public class MenusConsola {
    static Scanner scGen = new Scanner(System.in);
    public static int menuCombate() {
        int accion;

        do {
            System.out.println("Elige una accion:");
            System.out.println("1-Ataque normal");
            System.out.println("2-Ataque especial");
            System.out.println("3-Bloquear");
            System.out.println("4-Finta");
            System.out.println("5-Ver tus estadísticas");
            System.out.println("6-Ver tus efectos");
            System.out.println("7-Ver las estadísticas del enemigo");
            accion = scGen.nextInt();
        } while (accion<0||accion>7);
        return accion;
    }

    /*public static void menuDebug() {

    }*/

    public static int elegirAtaqueEspecial(Entidad jugador) {
        int accion;
        do {
            System.out.println("Ataques disponibles: ");
            jugador.getAtaques().forEach((a)->{
                a.mostrar();
                System.out.println();
            });
            accion = scGen.nextInt();
        } while (accion<0||accion>jugador.getAtaques().size());
        return accion;
    }

    public static int menuInventario() {
        int accion;
        do {
            System.out.println("1-Cambiar armadura");
            System.out.println("2-Cambiar arma");
            accion = scGen.nextInt();
        }while (accion<0||accion>2);
        return accion;
    }

    public static int menuHoghuera() {
        int accion;
        do {
            System.out.println("1-Curarte");
            System.out.println("2-Modificar equipamiento");
            accion = scGen.nextInt();
        }while (accion<0||accion>2);
        return accion;
    }


    public static int menuTienda(String objeto1, int precio1, String objeto2, int precio2, String estadistica, int cantidadMejora, int precio3) {
        int accion;
        do {
            System.out.println("0-Salir de la tienda");
            System.out.println("1-" + objeto1 + ": " + precio1 + " de oro");
            System.out.println("2-" + objeto2 + ": " + precio2 + " de oro");
            System.out.println("3-Aumentar tu " + estadistica + " por +" + cantidadMejora + ": " + precio3 + " de oro");
            accion = scGen.nextInt();
        } while (accion<0||accion>3);
        return accion;
    }

    public static Jugador menuEleccionJugador() {
        int accion;
        do {
            System.out.println("1-Caballero");
            System.out.println("2-Mago");
            System.out.println("3-Asesino");
            accion = scGen.nextInt();
        } while (accion<=0||accion>=4);
        return switch (accion) {
            case 1 -> new Caballero();
            case 2 -> new Mago();
            case 3 -> new Asesino();
            default -> new PruebaJugador();
        };
    }

    public static Evento menuElegirEvento(Evento[] eventosActuales) {
        for (int i = 0; i < eventosActuales.length; i++) {
            System.out.print(i + " " + eventosActuales[i].getTitulo() + " ");
        }
        System.out.println();
        return eventosActuales[scGen.nextInt()];
    }

    public static int menuEventoAleatorio(String[] opciones) {
        int elegida;
        do {
            for (int i = 0; i < opciones.length; i++) {
                System.out.println(i + " " + opciones[i]);
            }
            elegida = scGen.nextInt();
        } while (elegida < 0|| elegida > opciones.length-1);

        return elegida;
    }
}
