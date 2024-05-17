package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.Interfaces.Interfaz;
import UI.MenusConsola;

public class EventoFuente extends Aleatorio {
    public EventoFuente(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        texto = "Ante ti se alza una fuente desgastada por el paso de los siglos. De su centro emana un flujo constante de agua transparente. Desconoces si este agua es potable";
        opciones = new String[]{"Ignorar la fuente","Beber de la fuente"};

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
                setTexto("Decidiste ignorar esa fuente, no hubo consecuencias");
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                break;
            case 1:
                setTexto("Bebiste del agua de la fuente, sientes como purifica tu interior. Tu salud se recuperó 10 puntos");
                jugador.curarVida(10);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                break;
        }
        if (!jugador.estaMuerto()) {
            terminarEvento();
        }

    }
}