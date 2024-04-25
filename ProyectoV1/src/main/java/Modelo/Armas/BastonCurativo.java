package Modelo.Armas;

import Modelo.Bases.Arma;
import Modelo.Misc.Estados;

import java.util.ArrayList;

public class BastonCurativo extends Arma {
    public BastonCurativo(){
        nombre = "Bastón de sanación";
        dmg=20;
        bonus = new ArrayList<>();
        bonus.add(Estados.BENDITO);
    }
}