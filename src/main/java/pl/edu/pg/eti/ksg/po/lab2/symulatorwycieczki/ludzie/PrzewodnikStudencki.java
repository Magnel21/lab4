package pl.edu.pg.eti.ksg.po.lab2.symulatorwycieczki.ludzie;

import pl.edu.pg.eti.ksg.po.lab2.symulatorwycieczki.Atrakcja;
import pl.edu.pg.eti.ksg.po.lab2.symulatorwycieczki.Wycieczka;
import pl.edu.pg.eti.ksg.po.lab2.symulatorwycieczki.gory.Panorama;
import pl.edu.pg.eti.ksg.po.lab2.symulatorwycieczki.gory.beskidy.CmentarzZIWojny;
import pl.edu.pg.eti.ksg.po.lab2.symulatorwycieczki.gory.beskidy.DrewnianaCerkiew;

public class PrzewodnikStudencki extends Student {

    public PrzewodnikStudencki(String imie, String nazwisko, Plec plec) {
        super(imie, nazwisko, plec, 5.0);
    }
    public PrzewodnikStudencki(String imie, String nazwisko, Plec plec, double regeneracja) {
        super(imie, nazwisko, plec, 5.0, regeneracja, 18);
    }

    @Override
    public void opiszWycieczke(Wycieczka wycieczka) {
        mow("Witam wszystkich! Opowiem Wam teraz o planowanej trasie.");
        super.opiszWycieczke(wycieczka);
    }

    @Override
    public int getUmiejetnosciNawigacyjne() {
        return 4;
    }

    @Override
    public void reagujNaAtrakcje(Atrakcja a, double czas) {
        if(a instanceof DrewnianaCerkiew) {
            DrewnianaCerkiew cerkiewka = (DrewnianaCerkiew) a;
            mow("Znajdujemy się przy drewnianej cerkwi w miejscowości " + cerkiewka.getMiejscowosc() + ". Tego typu obiekty są charakterystyczne dla Beskidów. Po lewej stronie można dostrzec wieżę o konstrukcji słupowo-ramowej...");
            regeneruj(czas);
        }
        else if(a instanceof Panorama) {
            mow("Z tego miejsca rozpościera się piękny widok na pobliskie pasmo górskie. Patrząc od lewej, możemy zobaczyć...");
            regeneruj(czas);
        }
        else if(a instanceof CmentarzZIWojny) {
            CmentarzZIWojny cmentarz = (CmentarzZIWojny) a;
            mow("Oto cmentarz z czasów I Wojny Światowej w miejscowości "+cmentarz.getMiejscowosc()+". Wiele z tych cmentarzy zostało zaprojektowanych przez Dušana Jurkoviča.");
            regeneruj(czas);
        }
        else {
            super.reagujNaAtrakcje(a, czas);
        }
    }
}
