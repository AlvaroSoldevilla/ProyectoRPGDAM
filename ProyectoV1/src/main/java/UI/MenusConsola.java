package UI;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;

import java.util.Scanner;

public class MenusConsola {
    static Scanner scGen = new Scanner(System.in);
    public static int menuCombate() {
        int accion;

        do {
            System.out.println("Elige una accion:");
            System.out.println("1-Ataque normal");
            System.out.println("2-Ataque especial");

            accion = scGen.nextInt();
        } while (accion<0||accion>2);
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

    //TODO: Implementar menu de tienda
    public static int menuTienda() {

        return 0;
    }
}
