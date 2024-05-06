package Modelo.Enemigos;

import Modelo.AtaquesESpeciales.PruebaAtaque;
import Modelo.Bases.AtaqueEspecial;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum AtaquesEnemigo {
    PRUEBA(new AtaqueEspecial[]{new PruebaAtaque()});
    AtaquesEnemigo(AtaqueEspecial[] ataques) {
        this.ataques = Arrays.stream(ataques).toList();
    }
    @Getter
    List<AtaqueEspecial> ataques;
}
