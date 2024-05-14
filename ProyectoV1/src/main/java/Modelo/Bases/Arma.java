package Modelo.Bases;

import Modelo.Enums.Estados;
import lombok.Data;

import java.util.List;

@Data
public abstract class Arma extends Equipamiento{
    protected int dmg;
    protected List<Estados> bonus;
}
