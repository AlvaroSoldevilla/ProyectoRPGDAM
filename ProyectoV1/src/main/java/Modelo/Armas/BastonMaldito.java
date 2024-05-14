package Modelo.Armas;

import Modelo.Bases.Arma;
import Modelo.Enums.Estados;

import java.util.ArrayList;

public class BastonMaldito extends Arma {
    public BastonMaldito(){
        nombre = "Bastón del abismo";
        dmg=38;
        bonus = new ArrayList<>();
        bonus.add(Estados.MALDITO);
    }
}
