package Modelo.Eventos;

import Modelo.Bases.Evento;
import Modelo.Bases.Jugador;
import Modelo.Enums.Iconos;
import UI.Interfaces.Interfaz;
import UI.MenusConsola;

import java.util.Scanner;

public class Hoguera extends Evento {
    public Hoguera(Jugador j, Interfaz interfaz) {
        super(interfaz);
        titulo = "Hoguera";
        texto = "Encuentras una hoguera";
        opciones = new String[]{"Restaurar vida","Cambiar Equipamiento"};
        this.jugador = j;
        icono = Iconos.HOGUERA;
    }


    @Override
    public void empezarEvento() {
        Scanner sc = new Scanner(System.in);
        interfaz.actualizar();
        while (interfaz.botonPulsado()==-1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (interfaz.botonPulsado() != -1) {
                opcion = interfaz.botonPulsado();
            }
        }
        interfaz.reiniciarPulsado();
        switch (opcion) {
            case 0:
                jugador.restaurarVida();
                setTexto("Te has curado por completo");
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                while (interfaz.botonPulsado()==-1) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case 1:
                setTexto("¿Que equipamiento quieres cambiar?");
                opciones = new String[]{"Cambiar armadura","Cambiar arma"};
                interfaz.actualizar();
                while (interfaz.botonPulsado()==-1) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (interfaz.botonPulsado() != -1) {
                        opcion = interfaz.botonPulsado();
                    }
                }
                interfaz.reiniciarPulsado();
                switch (opcion) {
                    case 0:
                        opciones = new String[jugador.getArmaduras().size()];
                        for (int i = 0; i < jugador.getArmaduras().size(); i++) {
                            opciones[i] = jugador.getArmaduras().get(i).getNombre();
                        }
                        setTexto("Elige una nueva arma");
                        interfaz.actualizar();
                        while (interfaz.botonPulsado()==-1) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (interfaz.botonPulsado() != -1) {
                                jugador.cambiarArmadura(jugador.getArmaduras().get(interfaz.botonPulsado()));
                            }
                        }
                        interfaz.reiniciarPulsado();
                        setTexto("Cambiaste tu arma");
                        opciones = new String[]{"Seguir"};
                        interfaz.actualizar();
                        while (interfaz.botonPulsado()==-1) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;
                    case 1:
                        opciones = new String[jugador.getArmas().size()];
                        for (int i = 0; i < jugador.getArmas().size(); i++) {
                            opciones[i] = jugador.getArmas().get(i).getNombre();
                        }
                        setTexto("Elige una nueva armadura");
                        interfaz.actualizar();
                        while (interfaz.botonPulsado()==-1) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (interfaz.botonPulsado() != -1) {
                                jugador.cambiarArma(jugador.getArmas().get(interfaz.botonPulsado()));
                            }
                        }
                        setTexto("Cambiaste tu armadura");
                        opciones = new String[]{"Seguir"};
                        interfaz.actualizar();
                        while (interfaz.botonPulsado()==-1) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;
                }
                break;
        }
    }

    @Override
    public void terminarEvento() {}
}
