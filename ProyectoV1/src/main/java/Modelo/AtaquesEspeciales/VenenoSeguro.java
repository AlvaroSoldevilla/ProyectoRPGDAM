package Modelo.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

public class VenenoSeguro extends AtaqueEspecial {

    public VenenoSeguro() {
        nombre = "Envenenamiento al rival";
        coste = 7;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante)) {
            objetivo.infligirEstado(Estados.VENENO);
            return true;
        } else {
            return false;
        }
    }



}