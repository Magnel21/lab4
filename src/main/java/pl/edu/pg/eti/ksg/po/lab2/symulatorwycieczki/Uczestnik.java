package pl.edu.pg.eti.ksg.po.lab2.symulatorwycieczki;

public interface Uczestnik {

    double getBazowaPredkosc();

    int getUmiejetnosciNawigacyjne();

    double getPoziomZmeczenia();

    void opiszWycieczke(Wycieczka wycieczka);

    void reagujNaWedrowke(Wedrowka w, double czas);

    void reagujNaAtrakcje(Atrakcja a, double czas);
}
