package Modelo.Bases;

import Modelo.Misc.Estados;
import lombok.Data;

import java.util.List;

@Data
public abstract class Arma {
    protected String nombre;
    protected int dmg;
    protected List<Estados> bonus;
}
