package Modelo.Misc;

import lombok.Getter;
@Getter
public enum TiposDeDmg {
    NORMAL(null),
    FUEGO(Estados.QUEMADURA),
    VENENO(Estados.VENENO),
    BENDITO(Estados.BENDITO),
    MALDITO(Estados.MALDITO),
    ELECTRICO(Estados.ELECTRIFICADO);

    TiposDeDmg(Estados estadoAsociado) {
        this.estadoAsociado = estadoAsociado;
    }

    final Estados estadoAsociado;
}
