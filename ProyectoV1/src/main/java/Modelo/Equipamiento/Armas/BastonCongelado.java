package Modelo.Equipamiento.Armas;

import Modelo.Bases.Arma;
import Modelo.Enums.Estados;

import java.util.ArrayList;

public class BastonCongelado extends Arma {
    public BastonCongelado(){
        nombre = "Bastón de hielo";
        dmg=30;
        bonus = new ArrayList<>();
        bonus.add(Estados.CONGELADO);
    }
}
