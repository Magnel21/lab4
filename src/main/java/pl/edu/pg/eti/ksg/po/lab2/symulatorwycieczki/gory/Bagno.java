package pl.edu.pg.eti.ksg.po.lab2.symulatorwycieczki.gory;

import pl.edu.pg.eti.ksg.po.lab2.symulatorwycieczki.Wedrowka;

public class Bagno extends Wedrowka {
    public Bagno(double odleglosc) {
        super(odleglosc);
    }

    @Override
    public String getNazwa() {
        return "Bagno";
    }

    @Override
    public double modyfikujPredkoscGrupy(double predkosc) {
        return .2;
    }

    @Override
    public int getTrudnoscNawigacyjna() {
        return 5;
    }
}
