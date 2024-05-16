package Modelo.Equipamiento.Armas;

import Modelo.Bases.Arma;
import Modelo.Enums.Estados;

import java.util.ArrayList;

public class BastonCurativo extends Arma {
    public BastonCurativo(){
        nombre = "Bastón de sanación";
        dmg=20;
        bonus = new ArrayList<>();
        bonus.add(Estados.BENDITO);
    }
}