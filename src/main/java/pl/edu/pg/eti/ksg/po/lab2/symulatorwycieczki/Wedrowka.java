package pl.edu.pg.eti.ksg.po.lab2.symulatorwycieczki;

public abstract class Wedrowka implements ElementWycieczki {
    private double odleglosc;

    public Wedrowka(double odleglosc) {
        this.odleglosc = odleglosc;
    }

    public double getOdleglosc() {
        return odleglosc;
    }

    public abstract double modyfikujPredkoscGrupy(double predkosc);

    public abstract int getTrudnoscNawigacyjna();
}