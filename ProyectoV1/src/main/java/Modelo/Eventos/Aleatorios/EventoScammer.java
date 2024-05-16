package Modelo.Eventos.Aleatorios;

import Modelo.Equipamiento.Armas.Palo;
import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.Interfaces.Interfaz;

public class EventoScammer extends Aleatorio {
    public EventoScammer(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        texto = "Un enigmático mago aparece frente ante ti, te ofrece un arma de gran poder a cambio de la tuya. Su fiabilidad es cuestionable...";

        opciones = new String[]{"Aceptar el trato","No fiarte e ingorarlo"};

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
                setTexto("Tras aceptar el trato, el mago conjura un arma nueva y desaparece junto con la tuya. Sin embargo, lo que te entrega resulta ser un palo, poco útil en comparación." );
                int antigua;
                antigua = jugador.getArmas().indexOf(jugador.getArma());
                jugador.cambiarArma(new Palo());
                jugador.eliminarArma(antigua);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                break;
            case 1:
                setTexto("Decidiste ignorarlo. El mago parece decepcionado y desaparece.");
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                break;
        }
        if (!jugador.estaMuerto()) {
            terminarEvento();
        }

    }


}
