package pl.edu.pg.eti.ksg.po.lab2;

import java.util.HashSet;
import java.util.Set;

import pl.edu.pg.eti.ksg.po.lab2.symulatorwycieczki.*;
import pl.edu.pg.eti.ksg.po.lab2.symulatorwycieczki.gory.*;
import pl.edu.pg.eti.ksg.po.lab2.symulatorwycieczki.gory.beskidy.CmentarzZIWojny;
import pl.edu.pg.eti.ksg.po.lab2.symulatorwycieczki.gory.beskidy.DrewnianaCerkiew;
import pl.edu.pg.eti.ksg.po.lab2.symulatorwycieczki.ludzie.Czlowiek;
import pl.edu.pg.eti.ksg.po.lab2.symulatorwycieczki.ludzie.PrzewodnikStudencki;
import pl.edu.pg.eti.ksg.po.lab2.symulatorwycieczki.ludzie.Student;
import pl.edu.pg.eti.ksg.po.lab2.symulatorwycieczki.ludzie.StudentKSG;

public class JavaLab2 {
    public static void main(String[] args) {
        Wycieczka w = doParyza();
        System.out.println(w);

        PrzewodnikStudencki przewodnik = new PrzewodnikStudencki("Tomasz", "Nowak", Czlowiek.Plec.MEZCZYZNA);
        Set<Uczestnik> uczestnicy = new HashSet<>();
        uczestnicy.add(new Student("Barbara", "Informatyk", Czlowiek.Plec.KOBIETA));
        uczestnicy.add(new Student("Krzysztof", "Matematyk", Czlowiek.Plec.MEZCZYZNA));
        uczestnicy.add(new Student("Anna", "Fizyka", Czlowiek.Plec.KOBIETA));
        uczestnicy.add(new StudentKSG("Marek","Geodeta", Czlowiek.Plec.MEZCZYZNA));

        Grupa g = new Grupa(przewodnik, uczestnicy);

        SymulatorWycieczki symulator = new SymulatorWycieczki(g, w);

        symulator.dodajSluchaczPostepow((ElementWycieczki elementWycieczki, int lp, int liczbaElementow) -> System.out.println("POSTĘP: " + (lp + 1) + " / " + liczbaElementow));

        symulator.symuluj();
    }

    public static Wycieczka doKrakowa() {
        Wycieczka ret = new Wycieczka("Do Krakowa");
        ret.dodajElementWycieczki(new Droga(2.0));
        ret.dodajElementWycieczki(new Bagno(3.0));
        ret.dodajElementWycieczki(new PrzeprawaPrzezRzeke(1.5));
        ret.dodajElementWycieczki(new Las(4.0));
        ret.dodajElementWycieczki(new DrewnianaCerkiew("Lemkowska"));
        ret.dodajElementWycieczki(new CmentarzZIWojny("Kraków"));
        ret.dodajElementWycieczki(new Droga(6.0));

        return ret;
    }

    public static Wycieczka doParyza() {
        return new Wycieczka("Do Paryża")
                .dodajElementWycieczki(new Droga(4))
                .dodajElementWycieczki(new Panorama())
                .dodajElementWycieczki(new DrewnianaCerkiew("Notre-Dame"))
                .dodajElementWycieczki(new Bagno(8))
                .dodajElementWycieczki(new PrzeprawaPrzezRzeke(3.0))
                .dodajElementWycieczki(new Droga(2))
                .dodajElementWycieczki(new CmentarzZIWojny("Paryż"))
                .dodajElementWycieczki(new Droga(5))
                .dodajElementWycieczki(new Panorama());
    }

}
