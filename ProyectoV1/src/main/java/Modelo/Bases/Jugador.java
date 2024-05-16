package Modelo.Bases;

import Modelo.Enums.Estados;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class Jugador extends Entidad{

    protected int oro;
    protected int mana;
    protected int manaTemp = mana;
    protected int maxMana;
    protected Arma arma;
    protected Armadura armadura;
    protected List<Arma> armas = new ArrayList<>();
    protected List<Armadura> armaduras = new ArrayList<>();
    protected List<Accesorio> accesorios = new ArrayList<>();


    @Override
    public void infligirEstado(Estados estado) {
        if (armadura.getInmunidades() == null) {
            try {
                estadosSufridos.put(estado, estadosSufridos.get(estado) + estado.getDuracion());
            } catch (NullPointerException e) {
                estadosSufridos.put(estado, estado.getDuracion());
            }
        } else {
            if (!armadura.getInmunidades().contains(estado)) {
                estadosSufridos.put(estado, estadosSufridos.get(estado) + estado.getDuracion());
            }
        }
    }

    public void curarVida(int vida) {
        if (salud + vida <= maxSalud) {
            salud += vida;
        } else {
            salud = maxSalud;
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

    public boolean guardarArma(Arma nueva) {
        if (nueva!=null) {
            armas.add(nueva);
            return true;
        } else {
            return false;
        }
    }

    public void eliminarArma(int index) {
        armas.remove(index);
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
    public boolean guardarArmadura(Armadura nueva) {
        if (nueva!=null) {
            armaduras.add(nueva);
            return true;
        } else {
            return false;
        }
    }
    public void eliminarArmadura(int index) {
        armaduras.remove(index);
    }

    public void ganarOro(int oro) {
        this.oro += oro;
    }
    public void gastarOro(int oro) {
        this.oro-=oro;
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

        defensaBase = defensa;
        dmgBase = dmg;
    }

    public boolean addAccesorio(Accesorio nuevo) {
        if (nuevo!=null) {
            accesorios.add(nuevo);
            if (nuevo.isPermanente()) {
                nuevo.aplicarEfecto(this);
            }
            return true;
        } else {
            return false;
        }
    }

    public void mostrarEstadisticas() {
        System.out.println("Tus estadisticas:" + "\n" +"Vida: " + salud + "/" + maxSalud + "\n" +
                "Mana: " + mana + "/" + maxMana +  "\n" + "Ataque: " + dmg + "\n" + "Defensa: " + defensa);
    }

    @Override
    public String getEstadisticas() {
        return "Vida: " + salud + "/" + maxSalud + "\n" + "Mana: " + mana + "/" + maxMana +  "\n" + "Ataque: " + dmg + "\n" + "Defensa: " + defensa;
    }

    public void mostrarArmaduras() {
        armaduras.forEach((a)-> System.out.print(a.getNombre()));
        System.out.println();
    }

    public void mostrarArmas() {
        armas.forEach((a)-> System.out.print(a.getNombre()));
        System.out.println();
    }
    @Override
    public void multiplicarEstadisticas(double multiplicador) {
        saludTemp = salud;
        salud *= (int) multiplicador;
        manaTemp = mana;
        mana *= (int) multiplicador;
        dmg *= (int) multiplicador;
        defensa *= (int) multiplicador;
    }

    @Override
    public void finTurno() {
        if (saludTemp != 0) {
            salud = saludTemp;
            saludTemp = 0;
        }
        if (manaTemp != 0) {
            mana = manaTemp;
            manaTemp = 0;
        }
        dmg = dmgBase;
        defensa = defensaBase;
        bloqueando = false;
    }

}
