package Modelo.Equipamiento.Armaduras;

import Modelo.Bases.Armadura;
import Modelo.Enums.Estados;

import java.util.ArrayList;

public class ArmaduraMaldita extends Armadura {
    public ArmaduraMaldita(){
        nombre = "Armadura del abismo";
        defensa=30;
        inmunidades= new ArrayList<>();
        inmunidades.add(Estados.BENDITO);
    }
}
