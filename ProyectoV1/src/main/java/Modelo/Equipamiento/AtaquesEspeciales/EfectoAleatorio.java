package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

import java.util.Random;

public class EfectoAleatorio extends AtaqueEspecial {

    public EfectoAleatorio() {
        nombre = "Efecto aleatorio";
        coste = 10;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        Random rng = new Random();
        if (puedeAtacar(atacante,interfaz)) {
            switch (rng.nextInt(0,3)) {
                case 0:
                    objetivo.infligirEstado(Estados.QUEMADURA,interfaz);
                    break;
                case 1:
                    objetivo.infligirEstado(Estados.ELECTRIFICADO,interfaz);
                    break;
                case 2:
                    objetivo.infligirEstado(Estados.CONGELADO,interfaz);
                    break;
            }
            return true;
        } else {
            return false;
        }
    }
}