package Modelo.Eventos;

import Modelo.Bases.Evento;
import Modelo.Bases.Jugador;
import Modelo.Enums.Iconos;
import UI.Interfaces.Interfaz;
/**
 * La clase Hoguera representa un evento donde el jugador encuentra una hoguera y puede restaurar su vida o cambiar equipamiento.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class Hoguera extends Evento {

    /**
     * Constructor que inicializa el evento de la hoguera con el jugador y la interfaz del juego.
     *
     * @param j        El jugador que participa en el evento.
     * @param interfaz La interfaz del juego.
     */
    public Hoguera(Jugador j, Interfaz interfaz) {
        super(interfaz);
        titulo = "Hoguera";
        texto = "Encuentras una hoguera";
        opciones = new String[]{"Restaurar vida","Cambiar Equipamiento"};
        this.jugador = j;
        icono = Iconos.HOGUERA;
    }


    /**
     * Evento que permite al jugador restaurar su vida o cambiar su equipamiento.
     * Si el jugador deccide cambiar el equipamiento, podrá elegir entre cambiar su arma o su armadura por una que tenga en su inventario (Listas armas y armaduras)
     */
    @Override
    public void empezarEvento() {
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
