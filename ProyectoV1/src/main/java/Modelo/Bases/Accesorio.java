package Modelo.Bases;

import Modelo.Misc.Estados;

import java.util.List;

public abstract class Accesorio {

    protected String nombre;
    protected boolean permanente;
    protected boolean inicioTurno;
    protected boolean inicioCombate;

    public abstract void aplicarEfecto();

}
