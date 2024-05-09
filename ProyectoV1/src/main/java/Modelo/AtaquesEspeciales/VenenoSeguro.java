package Modelo.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Misc.Estados;

public class VenenoSeguro extends AtaqueEspecial {

    public VenenoSeguro() {
        nombre = "Envenenamiento al rival";
        coste = 7;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante) {
        if (puedeAtacar(atacante)) {
            objetivo.aplicarEfectoDeEstados(Estados.VENENO);
            return true;
        } else {
            return false;
        }
    }



}