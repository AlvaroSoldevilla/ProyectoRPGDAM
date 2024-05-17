package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

public class VenenoSeguro extends AtaqueEspecial {

    public VenenoSeguro() {
        nombre = "Envenenar";
        coste = 7;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante,interfaz)) {
            objetivo.infligirEstado(Estados.VENENO,interfaz);
            return true;
        } else {
            return false;
        }
    }



}