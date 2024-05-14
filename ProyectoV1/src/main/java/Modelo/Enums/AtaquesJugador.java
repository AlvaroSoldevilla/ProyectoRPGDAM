package Modelo.Enums;

import Modelo.AtaquesEspeciales.*;
import Modelo.Bases.AtaqueEspecial;
import lombok.Getter;


import java.util.Arrays;
import java.util.List;

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

    AtaquesJugador(AtaqueEspecial[] ataques) {
        this.ataques = Arrays.stream(ataques).toList();
    }

    @Getter
    List<AtaqueEspecial> ataques;
}
