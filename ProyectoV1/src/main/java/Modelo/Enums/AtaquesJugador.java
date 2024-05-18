package Modelo.Enums;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Equipamiento.AtaquesEspeciales.*;
import lombok.Getter;


import java.util.Arrays;
import java.util.List;

/**
 * El enum AtaquesJugador contiene los ataques especiales que puede usar cada tipo de jugador.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public enum AtaquesJugador {
    CABALLERO(new AtaqueEspecial[]{
            new Parry(),
            new Fortaleza(),
            new GolpeAplastante(),
            new Rabia()
    }),
    MAGO(new AtaqueEspecial[]{
            new Parry(),
            new EfectoAleatorio(),
            new Meteorito(),
            new Purificacion()
    }),
    ASESINO(new AtaqueEspecial[]{
            new Parry(),
            new Evasion(),
            new Silencio(),
            new VenenoSeguro()
    });

    /**
     * Constructor para inicializar los ataques especiales del jugador.
     *
     * @param ataques Array de ataques especiales.
     */
    AtaquesJugador(AtaqueEspecial[] ataques) {
        this.ataques = Arrays.stream(ataques).toList();
    }

    /**
     * Lista de ataques especiales del jugador.
     */
    @Getter
    List<AtaqueEspecial> ataques;
}
