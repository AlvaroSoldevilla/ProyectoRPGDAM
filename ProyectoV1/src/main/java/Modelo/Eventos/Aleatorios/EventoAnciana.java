package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.Interfaces.Interfaz;

public class EventoAnciana extends Aleatorio {
    public EventoAnciana(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo = "Anciana desamparada";
        texto = "En la distancia, avistas a una anciana tendida en el suelo. Te acercas a ella con paso firme.";
        opciones = new String[]{"Ayudas a la anciana a levantarse","Pasas de largo"};

        this.jugador = jugador;
    }
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
                setTexto("Ayudaste a la anciana a levantarse, te lo agradece pero te pide que la acompañes hasta su hogar" );
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
                        setTexto("Tras acompañarla a su hogar te entrega unas galletas por tu amabilidad. Te curas 8 de vida");
                        jugador.curarVida(8);
                        opciones = new String[]{"Seguir"};
                        interfaz.actualizar();
                        esperar();
                        break;

                    case 1:
                        setTexto("La anciana se queda extrañada y te observa alejandote, tal vez no pase nadie de buena fé que ayude a la anciana...");
                        jugador.setOro(jugador.getOro() - 5);
                        if (jugador.getOro()<0) {
                            jugador.setOro(0);
                        }
                        opciones = new String[]{"Seguir"};
                        interfaz.actualizar();
                        esperar();
                        break;

                    case 2:
                        setTexto("La anciana muestra tristeza pero lo entiende. Se despide de ti" );
                        opciones = new String[]{"Seguir"};
                        interfaz.actualizar();
                        esperar();
                        break;
               }
                break;
            case 1:
                setTexto("Decidiste pasar de largo. La anciana al verte ignorarla te tira una maldición por tu falta de bondad");
                jugador.setMana(jugador.getMana() - 5);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                esperar();
                break;
        }
        if (!jugador.estaMuerto()) {
            terminarEvento();
        }

    }

    @Override
    public void terminarEvento() {}

    private void esperar() {
        while (interfaz.botonPulsado()==-1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
