package Modelo.Eventos.Aleatorios;

import Modelo.Armas.Palo;
import Modelo.Bases.Arma;
import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.MenusConsola;

public class EventoScammer extends Aleatorio {
    public EventoScammer(Jugador jugador) {
        texto = "Un enigmático mago aparece frente ante ti, te ofrece un arma de gran poder a cambio de la tuya. Su fiabilidad es cuestionable...";

        opciones = new String[2];
        opciones[0] = "Aceptar el trato";
        opciones[1] = "No fiarte e ingorarlo";

        this.jugador = jugador;
    }

    Jugador jugador;
    Enemigo enemigo;
    @Override
    public void empezarEvento() {
        int opcionElegida;

        System.out.println(texto);

        opcionElegida = MenusConsola.menuEventoAleatorio(opciones);

        switch (opcionElegida) {
            case 0:
                textoFinal = "Tras aceptar el trato, el mago conjura un arma nueva y desaparece junto con la tuya. Sin embargo, lo que te entrega resulta ser un palo, poco útil en comparación." ;
                int antigua;
                antigua = jugador.getArmas().indexOf(jugador.getArma());
                jugador.cambiarArma(new Palo());
                jugador.eliminarArma(antigua);
                break;
            case 1:
                textoFinal = "Decidiste ignorarlo. El mago parece decepcionado y desaparece.";
                break;
        }
        if (!jugador.estaMuerto()) {
            terminarEvento();
        }

    }


}
