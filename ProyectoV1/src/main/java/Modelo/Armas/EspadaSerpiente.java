package Modelo.Armas;

import Modelo.Bases.Arma;
import Modelo.Misc.Estados;

import java.util.ArrayList;

public class EspadaSerpiente extends Arma {
    public EspadaSerpiente(){
        nombre = "Espada venenosa";
        dmg=20;
        bonus = new ArrayList<>();
        bonus.add(Estados.VENENO);
    }
}
