package Modelo.Enums;

import Modelo.Equipamiento.AtaquesEspeciales.*;
import Modelo.Bases.AtaqueEspecial;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum AtaquesEnemigo {
    //TODO: poner bien los ataques
    PRUEBA(new AtaqueEspecial[]{new PruebaAtaque()}),
    GOBLIN(new AtaqueEspecial[]{new PruebaAtaque()}),
    PERRO(new AtaqueEspecial[]{new MiniGarraMaldita()}),
    SERPIENTE(new AtaqueEspecial[]{new VenenoSeguro()}),
    LOBO(new AtaqueEspecial[]{new TormentaLunar()}),
    WENDIGO(new AtaqueEspecial[]{new GarraMaldita()}),
    DRAGONFASE1(new AtaqueEspecial[]{new AlientoHelado()}),
    DRAGONFASE2(new AtaqueEspecial[]{new AlientoHelado()}),
    ;
    AtaquesEnemigo(AtaqueEspecial[] ataques) {
        this.ataques = Arrays.stream(ataques).toList();
    }
    @Getter
    List<AtaqueEspecial> ataques;
}
