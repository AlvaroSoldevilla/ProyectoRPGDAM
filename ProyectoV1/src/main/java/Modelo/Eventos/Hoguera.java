package Modelo.Eventos;

import Modelo.Bases.Evento;
import Modelo.Bases.Jugador;
import UI.MenusConsola;

import java.util.Scanner;

public class Hoguera extends Evento {
    public Hoguera(Jugador j) {
        titulo = "Hoguera";
        this.j = j;
    }

    @Override
    public void empezarEvento() {
        Scanner sc = new Scanner(System.in);
        switch (MenusConsola.menuHoghuera()) {
            case 1:
                j.restaurarVida();
                break;
            case 2:
                switch (MenusConsola.menuInventario()) {
                    case 1:
                        j.mostrarArmaduras();
                        j.cambiarArmadura(j.getArmaduras().get(sc.nextInt()));
                        break;
                    case 2:
                        j.mostrarArmas();
                        j.cambiarArma(j.getArmas().get(sc.nextInt()));
                        break;
                }
                break;
        }
    }

    @Override
    public void terminarEvento() {}

    @Override
    public void consecuencias() {}
}
