package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.Interfaces.Interfaz;
import UI.MenusConsola;

public class EventoTaberna extends Aleatorio {
    public EventoTaberna(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        texto = "En tu camino hayaste una taberna a los pies de un río. Estás sediento y decides entrar";

        opciones = new String[]{"Sentarte en la barra.","Ir al lavabo y marcharte."};

        this.jugador = jugador;
    }

    Jugador jugador;

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
                setTexto("Te sentaste en la barra y te preguntan que tomarás.");

                opciones = new String[]{"Pedir agua. Coste: 2 de oro.","Pedir cerveza. Coste: 5 de oro."};

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
                switch (opcion){
                    case 0:
                        if (jugador.getOro()<2){
                            setTexto( "No tienes oro para pagarlo y te echan de la taberna.");
                            opciones = new String[]{"Seguir"};
                            interfaz.actualizar();
                            break;
                        }
                        else {
                            setTexto("Te pusieron un vaso de agua. Está bastante fresca. Recuperaste 6 de vida");
                            jugador.setOro(jugador.getOro() - 2);
                            jugador.setSalud(jugador.getSalud() + 6);
                            if (jugador.getSalud()> jugador.getMaxSalud()){
                                jugador.setSalud(jugador.getMaxSalud());
                            }
                            setTexto("Agradeces el trago de agua y te marchas.");
                            opciones = new String[]{"Seguir"};
                            interfaz.actualizar();
                            break;
                        }
                    case 1:
                        if (jugador.getOro()<5){
                            setTexto("No tienes oro para pagarlo y te echan de la taberna.");
                            opciones = new String[]{"Seguir"};
                            interfaz.actualizar();
                            break;
                        }
                        else {
                            setTexto("Te pusieron una jarra de cerveza fría. Está bastante fresca pero te sientes algo mareado.");
                            jugador.setOro(jugador.getOro() - 5);

                            opciones = new String[]{"Pedir asistencia a un grupo sentados al fondo de la taberna.","Levantarte e irte."};

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
                            switch (opcion){
                                case 0:
                                    setTexto("Te desmayas frente al grupo al terminar de hablar. El grupo al que pediste asistencia era una banda de forajidos. Despiertas fuera de la taberna sin oro.");
                                    jugador.setOro(0);
                                    opciones = new String[]{"Seguir"};
                                    interfaz.actualizar();
                                    break;
                                case 1:
                                    setTexto("Al poco tiempo de salir de la taberna caiste al río. Tenía poca profundidad y te caiste sobre una roca pero al menos te sirvió para espabilarte. Pierdes 5 de vida.");
                                    jugador.setSalud(jugador.getSalud() - 5);
                                    opciones = new String[]{"Seguir"};
                                    interfaz.actualizar();
                                    break;
                            }
                            break;
                        }
                }
                break;
            case 1:
                setTexto("Bebiste agua en el baño de la taberna. El agua estaba sucia. Perdiste 2 de vida.");
                jugador.setSalud(jugador.getSalud() - 2);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                break;
        }
        if (!jugador.estaMuerto()) {
            terminarEvento();
        }
    }
}



