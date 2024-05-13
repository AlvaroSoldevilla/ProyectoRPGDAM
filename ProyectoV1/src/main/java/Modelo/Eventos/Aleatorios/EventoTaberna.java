package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.Interfaces.Interfaz;
import UI.MenusConsola;

public class EventoTaberna extends Aleatorio {
    public EventoTaberna(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        texto = "En tu camino hayaste una taberna a los pies de un río. Estás sediento y decides entrar";

        opciones = new String[3];
        opciones[0] = "Sentarte en la barra.";
        opciones[1] = "Ir al lavabo y marcharte.";

        this.jugador = jugador;
    }

    Jugador jugador;

    @Override
    public void empezarEvento() {
        int opcionElegida;

        System.out.println(texto);

        opcionElegida = MenusConsola.menuEventoAleatorio(opciones);

        switch (opcionElegida) {
            case 0:
                textoFinal = "Te sentaste en la barra y te preguntan que tomarás.";
                opciones = new String[3];
                opciones[0] = "Pedir agua. Coste: 2 de oro.";
                opciones[1] = "Pedir cerveza. Coste: 5 de oro.";
                opcionElegida = MenusConsola.menuEventoAleatorio(opciones);
                switch (opcionElegida){
                    case 0:
                        if (jugador.getOro()<2){
                            textoFinal = "No tienes oro para pagarlo y te echan de la taberna.";
                            break;
                        }
                        else {
                            textoFinal = "Te pusieron un vaso de agua. Está bastante fresca. Recuperaste 6 de vida";
                            jugador.setOro(jugador.getOro() - 2);
                            jugador.setSalud(jugador.getSalud() + 6);
                            if (jugador.getSalud()> jugador.getMaxSalud()){
                                jugador.setSalud(jugador.getMaxSalud());
                            }
                            textoFinal = "Agradeces el trago de agua y te marchas.";
                            break;
                        }
                    case 1:
                        if (jugador.getOro()<5){
                            textoFinal = "No tienes oro para pagarlo y te echan de la taberna.";
                            break;
                        }
                        else {
                            textoFinal = "Te pusieron una jarra de cerveza fría. Está bastante fresca pero te sientes algo mareado.";
                            jugador.setOro(jugador.getOro() - 5);
                            opciones = new String[2];
                            opciones[0] = "Pedir asistencia a un grupo sentados al fondo de la taberna.";
                            opciones[1] = "Levantarte e irte.";
                            opcionElegida = MenusConsola.menuEventoAleatorio(opciones);
                            switch (opcionElegida){
                                case 0:
                                    textoFinal = "Te desmayas frente al grupo al terminar de hablar. El grupo al que pediste asistencia era una banda de forajidos. Despiertas fuera de la taberna sin oro.";
                                    jugador.setOro(0);
                                    break;
                                case 1:
                                    textoFinal = "Al poco tiempo de salir de la taberna caiste al río. Tenía poca profundidad y te caiste sobre una roca pero al menos te sirvió para espabilarte. Pierdes 5 de vida.";
                                    jugador.setSalud(jugador.getSalud() - 5);
                                    break;
                            }
                            break;
                        }
                }
                break;
            case 1:
                textoFinal = "Bebiste agua en el baño de la taberna. El agua estaba sucia. Perdiste 2 de vida.";
                jugador.setSalud(jugador.getSalud() - 2);
                break;
        }
        if (!jugador.estaMuerto()) {
            terminarEvento();
        }
    }
}



