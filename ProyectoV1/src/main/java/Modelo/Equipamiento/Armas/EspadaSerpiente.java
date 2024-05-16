package Modelo.Equipamiento.Armas;

import Modelo.Bases.Arma;
import Modelo.Enums.Estados;

import java.util.ArrayList;

public class EspadaSerpiente extends Arma {
    public EspadaSerpiente(){
        nombre = "Espada venenosa";
        dmg=20;
        bonus = new ArrayList<>();
        bonus.add(Estados.VENENO);
    }
}
