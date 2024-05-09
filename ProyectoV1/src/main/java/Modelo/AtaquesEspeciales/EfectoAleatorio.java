package Modelo.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Misc.Estados;

import java.util.Random;

public class EfectoAleatorio extends AtaqueEspecial {

    public EfectoAleatorio() {
        nombre = "Efecto aleatorio";
        coste = 10;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante) {
        Random rng = new Random();
        if (puedeAtacar(atacante)) {
            switch (rng.nextInt(0,3)) {
                case 0:
                    objetivo.aplicarEfectoDeEstados(Estados.QUEMADURA);
                    break;

                case 1:
                    objetivo.aplicarEfectoDeEstados(Estados.ELECTRIFICADO);
                    break;

                case 2:
                    objetivo.aplicarEfectoDeEstados(Estados.CONGELADO);
                    break;
            }
            return true;
        } else {
            return false;
        }
    }



}


