package pl.edu.pg.eti.ksg.po.lab2.symulatorwycieczki;

import java.util.HashSet;
import java.util.Set;

public class SymulatorWycieczki {

    private final Grupa grupa;
    private final Wycieczka wycieczka;
    private int aktualnaPozycja = 0;
    private double czasCalkowity = 0.0;
    private double pokonanaOdleglosc = 0.0;
    private final Set<SluchaczPostepow> obserwatorzy = new HashSet<>();

    public SymulatorWycieczki(Grupa grupa, Wycieczka wycieczka) {
        this.grupa = grupa;
        this.wycieczka = wycieczka;
    }

    public void symuluj() {
        System.out.println("=== Start symulacji wycieczki ===");
        System.out.println();
        System.out.println("Grupa wybrała się na wyprawę w składzie: ");
        for(Uczestnik uczestnik : grupa.getUczestnicy()) {
            System.out.println(uczestnik);
        }
        System.out.println();
        System.out.println("Osobą prowadzącą grupę jest " + grupa.getPrzewodnik());
        System.out.println();
        System.out.println("Przewodnik przedstawi teraz plan podróży");

        grupa.getPrzewodnik().opiszWycieczke(wycieczka);

        System.out.println();
        System.out.println("Wyprawa się rozpoczęła!");
        System.out.println();

        for(aktualnaPozycja = 0; aktualnaPozycja < wycieczka.getLiczbaElementowWycieczki(); aktualnaPozycja++) {
            ElementWycieczki element = wycieczka.getElementWycieczki(aktualnaPozycja);

            if(element instanceof Wedrowka) {
                Wedrowka wedrowka = (Wedrowka) element;

                System.out.println("Grupa przemierza teren: " + wedrowka.getNazwa());

                double predkoscGrupy = grupa.getPredkosc();
                predkoscGrupy = wedrowka.modyfikujPredkoscGrupy(predkoscGrupy);
                int brakiNawigacyjne = wedrowka.getTrudnoscNawigacyjna() - grupa.getPrzewodnik().getUmiejetnosciNawigacyjne();
                if(brakiNawigacyjne < 0)
                    brakiNawigacyjne = 0;

                if(brakiNawigacyjne > 0) {
                    System.out.println("Przewodnik miał trudności z orientacją w terenie.");
                    double kara = brakiNawigacyjne / 5.0;
                    predkoscGrupy = (1.0 - kara) * predkoscGrupy;
                }

                if(predkoscGrupy <= Double.MIN_VALUE) {
                    System.out.println("Grupa nie jest w stanie kontynuować marszu. Nocleg w obecnym miejscu.");
                    break;
                }

                System.out.println("Grupa pokonuje trasę z prędkością " + predkoscGrupy + " GOT/h");

                double czas = wedrowka.getOdleglosc() / predkoscGrupy;

                for(Uczestnik uczestnik : grupa.getUczestnicy()) {
                    uczestnik.reagujNaWedrowke(wedrowka, czas);
                }

                System.out.println("Marsz trwał " + czas + " godzin");
                czasCalkowity += czas;
                pokonanaOdleglosc += wedrowka.getOdleglosc();
            } else if (element instanceof Atrakcja) {
                Atrakcja atrakcja = (Atrakcja) element;

                System.out.println("Grupa zatrzymuje się przy atrakcji: " + atrakcja.getNazwa());

                for (Uczestnik uczestnik : grupa.getUczestnicy()) {
                    uczestnik.reagujNaAtrakcje(atrakcja, atrakcja.getCzas());
                }
            }

            for (SluchaczPostepow obserwator : obserwatorzy) {
                obserwator.aktualizujPostep(element, aktualnaPozycja, wycieczka.getLiczbaElementowWycieczki());
            }

            System.out.println();

        }
        System.out.printf("Cała wyprawa zajęła %.2f godzin, grupa pokonała %.2f GOT.\n", czasCalkowity, pokonanaOdleglosc);
        System.out.println("=== Koniec symulacji ===");
    }

    public void dodajSluchaczPostepow(SluchaczPostepow obserwator) {
        this.obserwatorzy.add(obserwator);
    }
}