package Modelo.Bases;

import Modelo.Misc.Estados;
import lombok.Data;

import java.util.List;

@Data
public abstract class Jugador extends Entidad{

    protected int oro;
    protected int mana;
    protected int maxMana;
    protected Arma arma;
    protected Armadura armadura;
    protected List<Arma> armas;
    protected List<Armadura> armaduras;
    protected List<Accesorio> accesorios;


    public void InfligirEstado(Estados estado) {
        if (!armadura.getInmunidades().contains(estado)) {
            switch (estado) {
                case CONGELADO -> estadosSufridos.put(estado, estadosSufridos.get(estado) + estado.getEfecto());
                default -> estadosSufridos.put(estado, estadosSufridos.get(estado) + 1);
            }
        } else {
            //TODO:cambiar a mensaje en la interfaz
            System.out.println("Eres es inmune a " + estado.getNombre());
        }
    }

    public void restaurarVida() {
        salud=maxSalud;
    }

    public void restaurarMana() {
        mana=maxMana;
    }

    public boolean cambiarArma(Arma nueva) {
        if (nueva != null) {
            dmg-=arma.getDmg();
            arma = nueva;
            aplicarEfectosEquipamiento(1);
            return true;
        } else {
            return false;
        }
    }

    public boolean cambiarArmadura(Armadura nueva) {
        if (nueva != null) {
            defensa-=armadura.getDefensa();
            armadura = nueva;
            aplicarEfectosEquipamiento(2);
            return true;
        } else {
            return false;
        }
    }

    public void ganarOro(int oro) {
        this.oro += oro;
    }

    public void aplicarEfectosEquipamiento(int cambio) {
        switch (cambio) {
            case 0:
                defensa+=armadura.getDefensa();
                dmg+=arma.getDmg();
                break;
            case 1:
                dmg+=arma.getDmg();
                break;
            case 2:
                defensa+=armadura.getDefensa();
                break;
        }

    }

    public void mostrarEstadisticas() {
        System.out.println("Tus estadisticas:" + "\n" +"Vida: " + salud + "/" + maxSalud + "\n" +
                "Mana" + mana + "/" + maxMana +  "\n" + "Ataque: " + dmg + "\n" + "Defensa: " + defensa);
    }
    public void mostrarArmaduras() {
        armaduras.forEach(Armadura::getNombre);
    }

    public void mostrarArmas() {
        armas.forEach(Arma::getNombre);
    }
    @Override
    public void multiplicarEstadisticas(double multiplicador) {
        salud *= multiplicador;
        mana *= multiplicador;
        dmg *= multiplicador;
        defensa *= multiplicador;
    }

}
