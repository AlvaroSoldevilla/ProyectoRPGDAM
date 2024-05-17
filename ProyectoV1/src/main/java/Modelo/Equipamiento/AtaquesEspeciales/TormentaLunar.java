package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import UI.Interfaces.Interfaz;

public class TormentaLunar extends AtaqueEspecial {

    public TormentaLunar() {
        nombre = "Tormenta Lunar";
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante,interfaz)) {
            objetivo.recibirDmg(atacante.getDmg(),interfaz);
            objetivo.recibirDmg(atacante.getDmg() - 10,interfaz);
            objetivo.recibirDmg(atacante.getDmg() - 15,interfaz);
            return true;
        } else {
            return false;
        }
    }



}

