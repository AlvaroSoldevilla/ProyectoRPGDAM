package Modelo.Enemigos;

import Modelo.AtaquesEspeciales.PruebaAtaque;
import Modelo.Bases.AtaqueEspecial;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum AtaquesEnemigo {
    //TODO: poner bien los ataques
    PRUEBA(new AtaqueEspecial[]{new PruebaAtaque()}),
    PERRO(new AtaqueEspecial[]{new PruebaAtaque()}),
    ENEMIGO2(new AtaqueEspecial[]{new PruebaAtaque()}),
    ENEMIGO3(new AtaqueEspecial[]{new PruebaAtaque()}),
    LOBO(new AtaqueEspecial[]{new PruebaAtaque()}),
    WENDIGO(new AtaqueEspecial[]{new PruebaAtaque()}),
    DRAGONFASE1(new AtaqueEspecial[]{new PruebaAtaque()}),
    DRAGONFASE2(new AtaqueEspecial[]{new PruebaAtaque()}),
    ;
    AtaquesEnemigo(AtaqueEspecial[] ataques) {
        this.ataques = Arrays.stream(ataques).toList();
    }
    @Getter
    List<AtaqueEspecial> ataques;
}
