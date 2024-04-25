package Modelo.Jugador;

import Modelo.AtaquesESpeciales.PruebaAtaque;
import Modelo.Bases.AtaqueEspecial;
import lombok.Getter;


import java.util.Arrays;
import java.util.List;

public enum AtaquesJugador {
    GUERRERO(new AtaqueEspecial[]{new PruebaAtaque()}),MAGO(new AtaqueEspecial[]{});

    AtaquesJugador(AtaqueEspecial[] ataques) {
        this.ataques = Arrays.stream(ataques).toList();
    }

    @Getter
    List<AtaqueEspecial> ataques;
}
