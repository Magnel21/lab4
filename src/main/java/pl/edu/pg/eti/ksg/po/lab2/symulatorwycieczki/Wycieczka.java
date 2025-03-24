package pl.edu.pg.eti.ksg.po.lab2.symulatorwycieczki;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TB
 */
public class Wycieczka {
    private final String nazwa;
    private final List<ElementWycieczki> elementy = new ArrayList<>();

    public Wycieczka(String nazwa) {
        this.nazwa = nazwa;
    }
    
    public Wycieczka dodajElementWycieczki(ElementWycieczki elementWycieczki) {
        elementy.add(elementWycieczki);
        return this;
    }
    
    /**
     * @return Dlugosc wycieczki w GOTach 
     */
    public double getDlugosc() {
        return elementy.stream()
                .filter(ew -> ew instanceof Wedrowka)
                .map(ew -> (Wedrowka)ew)
                .map(Wedrowka::getOdleglosc)
                .reduce(0.0, Double::sum);
    }
    
    /**
     * @return Sumaryczny czas wszystkich atrakcji
     */
    public double getCzasAtrakcji() {
        return elementy.stream()
                .filter(ew -> ew instanceof Atrakcja)
                .map(ew -> (Atrakcja)ew)
                .map(Atrakcja::getCzas)
                .reduce((Double::sum)).orElse(0.0);
    }
    
    public ElementWycieczki getElementWycieczki(int i) {
        return elementy.get(i);
    }
    
    public int getLiczbaElementowWycieczki() {
        return elementy.size();
    }

    public String getNazwa() {
        return nazwa;
    }
   
    
    @Override
    public String toString() {
        return "Wycieczka \"" + nazwa + "\", długość: "+ getDlugosc() + " GOT,"
                + " czas atrakcji: "+getCzasAtrakcji() + " h"
                ;
    }
    
    
}
