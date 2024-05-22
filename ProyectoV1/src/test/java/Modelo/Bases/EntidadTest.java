package Modelo.Bases;

import Modelo.Enums.Estados;
import Modelo.Jugador.Asesino;
import UI.Interfaces.Interfaz;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EntidadTest {

    Entidad entidad;
    Interfaz interfaz = new Interfaz();
    @BeforeEach
    void iniciar() {
        entidad = new Asesino();
    }

    @Test
    void aplicarEstados() {
        int vida = entidad.salud;
        entidad.infligirEstado(Estados.VENENO,interfaz);
        entidad.infligirEstado(Estados.QUEMADURA,interfaz);
        entidad.aplicarEstados(interfaz);

        assertEquals(vida-Estados.VENENO.getEfecto()-Estados.QUEMADURA.getEfecto(),entidad.getSalud());
    }

    @Test
    void eliminarEstados() {
        entidad.infligirEstado(Estados.ELECTRIFICADO,interfaz);
        entidad.eliminarEstados(new Estados[] {Estados.ELECTRIFICADO});

        assertTrue(entidad.getEstadosSufridos().isEmpty());
    }

    @Test
    void aplicarEfectoDeEstados() {
        int vida = entidad.salud;
        entidad.aplicarEfectoDeEstados(Estados.VENENO,interfaz);

        assertEquals(vida-Estados.VENENO.getEfecto(),entidad.getSalud());
    }

    @Test
    void aumentarDefensa() {
        int defensa = entidad.getDefensa();
        entidad.aumentarDefensa(3);

        assertEquals(defensa+3,entidad.getDefensa());
    }

    @Test
    void bajarDefensa() {
        int defensa = entidad.getDefensa();
        entidad.bajarDefensa(3);

        assertEquals(defensa-3,entidad.getDefensa());
    }

    @Test
    void aumentarDmg() {
        int dmg = entidad.getDmg();
        entidad.aumentarDmg(5);

        assertEquals(dmg+5,entidad.getDmg());
    }

    @Test
    void aumentarSalud() {
        int vida = entidad.getSalud();
        entidad.aumentarSalud(5);

        assertEquals(vida+5,entidad.getSalud());
    }

    @Test
    void bloquear() {
        int defensa = entidad.getDefensa();
        entidad.bloquear();

        assertEquals(defensa+entidad.getPoderBloqueo(),entidad.getDefensa());
    }

    @ParameterizedTest
    @ValueSource(ints = {3,15})
    void recibirDmg(int dmg) {
        int salud = entidad.getSalud();
        entidad.recibirDmg(dmg,interfaz);

        if (dmg > entidad.defensa){
            assertEquals(salud-dmg,entidad.getSalud());
        } else {
            assertEquals(salud-1,entidad.getSalud());
        }
    }

    @Test
    void dmgVerdadero() {
        int salud = entidad.getSalud();
        entidad.dmgVerdadero(6,interfaz);

        assertEquals(salud-6,entidad.getSalud());
    }

    @Test
    void eliminarEstadosPerjudiciales() {
        entidad.infligirEstado(Estados.ELECTRIFICADO,interfaz);
        entidad.infligirEstado(Estados.VENENO,interfaz);
        entidad.infligirEstado(Estados.MALDITO,interfaz);
        entidad.infligirEstado(Estados.QUEMADURA,interfaz);
        entidad.eliminarEstadosPerjudiciales();

        assertTrue(entidad.getEstadosSufridos().isEmpty());
    }

    @Test
    void estaMuerto() {
        entidad.dmgVerdadero(9999,interfaz);

        assertTrue(entidad.estaMuerto());
    }

    @Test
    void muertoJusto() {
        entidad.dmgVerdadero(entidad.getSalud(),interfaz);

        assertTrue(entidad.estaMuerto());
    }

    @Test
    void infligirEstado() {
        entidad.infligirEstado(Estados.ELECTRIFICADO,interfaz);

        assertTrue(entidad.getEstadosSufridos().containsKey(Estados.ELECTRIFICADO));
    }

    @Test
    void multiplicarEstadisticas() {
        int[] estadisticas = new int[3];
        estadisticas[0] = entidad.getSalud()*2;
        estadisticas[1] = entidad.getDmg()*2;
        estadisticas[2]  = entidad.getDefensa()*2;

        int[] estadisticasMod = new int[3];
        entidad.multiplicarEstadisticas(2,true);
        estadisticasMod[0] = entidad.getSalud();
        estadisticasMod[1] = entidad.getDmg();
        estadisticasMod[2] = entidad.getDefensa();

        assertTrue((estadisticas[0] == estadisticasMod[0])&&(estadisticas[1] == estadisticasMod[1])&&(estadisticas[2] == estadisticasMod[2]));
    }

    @Test
    void finTurno() {
        int[] estadisticas = new int[3];
        estadisticas[0] = entidad.getSalud();
        estadisticas[1] = entidad.getDmg();
        estadisticas[2]  = entidad.getDefensa();

        int[] estadisticasMod = new int[3];
        entidad.multiplicarEstadisticas(2,true);
        entidad.finTurno();
        estadisticasMod[0] = entidad.getSalud();
        estadisticasMod[1] = entidad.getDmg();
        estadisticasMod[2] = entidad.getDefensa();

        assertTrue((estadisticas[0] == estadisticasMod[0])&&(estadisticas[1] == estadisticasMod[1])&&(estadisticas[2] == estadisticasMod[2]));
    }
}