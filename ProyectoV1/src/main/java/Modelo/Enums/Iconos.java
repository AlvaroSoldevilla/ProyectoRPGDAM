package Modelo.Enums;

import lombok.Getter;

/**
 * El enum Iconos contiene la ruta relativa de cada imagen e icono.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */

@Getter
public enum Iconos {
    //Fondos
    NIVEL1("Imagenes/Fondos/Fondo1.png"),
    NIVEL2("Imagenes/Fondos/Fondo2.png"),
    NIVEL3("Imagenes/Fondos/Fondo3.png"),
    //Iconos Generales
    ALEATORIO("Imagenes/Iconos/IconoEventoAleatorio.png"),
    BATALLAJEFE("Imagenes/Iconos/IconoJefe.png"),
    COMBATE("Imagenes/Iconos/IconoCombate.png"),
    HOGUERA("Imagenes/Iconos/IconoHoguera.png"),
    TESORO("Imagenes/Iconos/IconoRecompensa.png"),
    TIENDA("Imagenes/Iconos/IconoTienda.png"),
    DMG("Imagenes/Iconos/IconoDmg.png"),
    MANA("Imagenes/Iconos/IconoMana.png"),
    ORO("Imagenes/Iconos/IconoOro.png"),
    SALUD("Imagenes/Iconos/IconoSalud.png"),
    DEFENSA("Imagenes/Iconos/IconoDefensa.png"),
    //Efectos de estado
    BENDITO("Imagenes/Iconos/Efectos de Estado/IconoBendito.png"),
    CEGADO("Imagenes/Iconos/Efectos de Estado/IconoCegado.png"),
    CONFUSO("Imagenes/Iconos/Efectos de Estado/IconoConfuso.png"),
    CONGELADO("Imagenes/Iconos/Efectos de Estado/IconoCongelado.png"),
    ELECTROCUTADO("Imagenes/Iconos/Efectos de Estado/IconoElectrocutado.png"),
    ESPINAS("Imagenes/Iconos/Efectos de Estado/IconoEspinas.png"),
    MALDICION("Imagenes/Iconos/Efectos de Estado/IconoMaldicion.png"),
    MASDEFENSA("Imagenes/Iconos/Efectos de Estado/IconoMasDefensa.png"),
    MENOSDEFENSA("Imagenes/Iconos/Efectos de Estado/IconoMenosDefensa.png"),
    QUEMADURA("Imagenes/Iconos/Efectos de Estado/IconoQuemadura.png"),
    RABIA("Imagenes/Iconos/Efectos de Estado/IconoRabia.png"),
    RESISTELEC("Imagenes/Iconos/Efectos de Estado/IconoResistenciaElectrocutado.png"),
    RESISTQUEM("Imagenes/Iconos/Efectos de Estado/IconoResistenciaQuemadura.png"),
    RESISTVEN("Imagenes/Iconos/Efectos de Estado/IconoResistenciaVeneno.png"),
    SILENCIADO("Imagenes/Iconos/Efectos de Estado/IconoSilenciado.png"),
    EVASION("Imagenes/Iconos/Efectos de Estado/IconoEvasion.png"),
    ENVENENADO("Imagenes/Iconos/Efectos de Estado/IconoVeneno.png"),
    //Jugadores
    CABALLERO("Imagenes/Personajes/CaballeroTemp.png"),
    MAGO("Imagenes/Personajes/Mago.png"),
    ASESINO("Imagenes/Personajes/Asesino.png"),
    //Jefes
    DRAGON("Imagenes/Enemigos/Dragon.png"),
    WENDIGO("Imagenes/Enemigos/Wendigo.png"),
    LOBO("Imagenes/Enemigos/HombreLobo.png"),
    //Enemigos
    GOBLIN("Imagenes/Enemigos/Goblin.png"),
    PERRO("Imagenes/Enemigos/Perro.png"),
    SERPIENTE("Imagenes/Enemigos/Serpiente.png"),
    ;

    /**
     * Constructor para inicializar la ruta del icono o imagen
     *
     * @param rutaIcono
     */
    Iconos(String rutaIcono) {
        this.rutaIcono = rutaIcono;
    }

    /**
     * La ruta del icono o imagen
     */
    String rutaIcono;
}
