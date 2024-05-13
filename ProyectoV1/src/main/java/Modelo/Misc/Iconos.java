package Modelo.Misc;

import lombok.Getter;

import javax.swing.*;

@Getter
public enum Iconos {
    //Iconos Generales
    ALEATORIO(new ImageIcon("Imagenes/Iconos/IconoEventoAleatorio.png")),
    BATALLAJEFE(new ImageIcon("Imagenes/Iconos/IconoJefe.png")),
    COMBATE(new ImageIcon("Imagenes/Iconos/IconoCombate.png")),
    HOGUERA(new ImageIcon("Imagenes/Iconos/IconoHoguera.png")),
    TESORO(new ImageIcon("Imagenes/Iconos/IconoRecompensa.png")),
    TIENDA(new ImageIcon("Imagenes/Iconos/IconoTienda.png")),
    DMG(new ImageIcon("Imagenes/Iconos/IconoDmg.png")),
    MANA(new ImageIcon("Imagenes/Iconos/IconoMana.png")),
    ORO(new ImageIcon("Imagenes/Iconos/IconoOro.png")),
    SALUD(new ImageIcon("Imagenes/Iconos/IconoSalud.png")),
    DEFENSA(new ImageIcon("Imagenes/Iconos/IconoDefensa.png")),
    //Efectos de estado
    BENDITO(new ImageIcon("Imagenes/Iconos/Efectos de Estado/IconoBendito.png")),
    CEGADO(new ImageIcon("Imagenes/Iconos/Efectos de Estado/IconoCegado.png")),
    CONFUSO(new ImageIcon("Imagenes/Iconos/Efectos de Estado/IconoConfuso.png")),
    CONGELADO(new ImageIcon("Imagenes/Iconos/Efectos de Estado/IconoCongelado.png")),
    ELECTROCUTADO(new ImageIcon("Imagenes/Iconos/Efectos de Estado/IconoElectrocutado.png")),
    ESPINAS(new ImageIcon("Imagenes/Iconos/Efectos de Estado/IconoEspinas.png")),
    MALDICION(new ImageIcon("Imagenes/Iconos/Efectos de Estado/IconoMaldito.png")),
    MASDEFENSA(new ImageIcon("Imagenes/Iconos/Efectos de Estado/IconoMasDefensa.png")),
    MENOSDEFENSA(new ImageIcon("Imagenes/Iconos/Efectos de Estado/IconoMenosDefensa.png")),
    QUEMADURA(new ImageIcon("Imagenes/Iconos/Efectos de Estado/IconoQuemadura.png")),
    RABIA(new ImageIcon("Imagenes/Iconos/Efectos de Estado/IconoRabia.png")),
    RESISTELEC(new ImageIcon("Imagenes/Iconos/Efectos de Estado/IconoResistenciaElectrocutado.png")),
    RESISTQUEM(new ImageIcon("Imagenes/Iconos/Efectos de Estado/IconoResistenciaQuemadura.png")),
    RESISTVEN(new ImageIcon("Imagenes/Iconos/Efectos de Estado/IconoResistenciaVeneno.png")),
    SILENCIADO(new ImageIcon("Imagenes/Iconos/Efectos de Estado/IconoSilenciado.png")),
    EVASION(new ImageIcon("Imagenes/Iconos/Efectos de Estado/IconoEvasion.png")),
    ENVENENADO(new ImageIcon("Imagenes/Iconos/Efectos de Estado/IconoVeneno.png"));

    Iconos(ImageIcon icono) {
        this.icono = icono;
    }
    ImageIcon icono;
}
