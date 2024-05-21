package Modelo.Enums;

import Modelo.Equipamiento.AtaquesEspeciales.*;
import Modelo.Bases.AtaqueEspecial;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * El enum AtaquesEnemigo contiene los ataques especiales que puede usar cada enemigo.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public enum AtaquesEnemigo {
    GOBLIN(new AtaqueEspecial[]{new Rabia()}),
    PERRO(new AtaqueEspecial[]{new GarraMaldita()}),
    SERPIENTE(new AtaqueEspecial[]{new VenenoSeguro()}),
    LOBO(new AtaqueEspecial[]{new TormentaLunar()}),
    WENDIGO(new AtaqueEspecial[]{new GarraMaldita()}),
    DRAGONFASE1(new AtaqueEspecial[]{new AlientoHelado()}),
    DRAGONFASE2(new AtaqueEspecial[]{new AlientoHelado()}),
    ;

    /**
     * Constructor para inicializar los ataques especiales del enemigo.
     *
     * @param ataques Array de ataques especiales.
     */
    AtaquesEnemigo(AtaqueEspecial[] ataques) {
        this.ataques = Arrays.stream(ataques).toList();
    }

    /**
     * Lista de ataques especiales del enemigo.
     */
    @Getter
    List<AtaqueEspecial> ataques;
}
