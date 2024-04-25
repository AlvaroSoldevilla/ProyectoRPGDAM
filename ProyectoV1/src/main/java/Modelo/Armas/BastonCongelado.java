package Modelo.Armas;

import Modelo.Bases.Arma;
import Modelo.Misc.Estados;

import java.util.ArrayList;

public class BastonCongelado extends Arma {
    public BastonCongelado(){
        nombre = "Bastón de hielo";
        dmg=30;
        bonus = new ArrayList<>();
        bonus.add(Estados.CONGELADO);
    }
}
