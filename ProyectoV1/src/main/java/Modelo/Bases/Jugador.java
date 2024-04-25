package Modelo.Bases;

import lombok.Data;

import java.util.List;

@Data
public abstract class Jugador extends Entidad{

    protected int oro;
    protected int mana;
    protected int maxMana;
    protected Arma arma;
    protected Armadura armadura;
    protected List<Accesorio> accesorios;


    public void restaurarVida() {
        salud=maxSalud;
    }

    public void restaurarMana() {
        mana=maxMana;
    }



    public boolean cambiarArma(Arma nueva) {
        if (nueva != null) {
            try {
                arma = nueva;
                return true;
            }catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean cambiarArmadura(Armadura nueva) {
        if (nueva != null) {
            try {
                armadura = nueva;
                return true;
            }catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public void ganarOro(int oro) {
        this.oro +=oro;
    }

    public void mostrarEstadisticas() {
        System.out.println("Tus estadisticas:" + "\n" +"Vida: " + salud + "/" + maxSalud + "\n" +
                "Mana" + mana + "/" + maxMana +  "\n" + "Ataque: " + dmg + "\n" + "Defensa: " + defensa);
    }
    @Override
    public void multiplicarEstadisticas(int multiplicador) {
        salud *= multiplicador;
        mana *= multiplicador;
        dmg *= multiplicador;
        defensa *= multiplicador;
    }

}
