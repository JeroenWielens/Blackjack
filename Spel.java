package blackjack;

import javax.swing.*;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Spel {
    private Random random = new Random();
    private Kaartdeck deck;
    private Speler jeroen;
    private Bank bank;
    private Scanner scanner;
    private Boolean gameIsFinished;
    private Integer kaartentotaal;
    private Integer kaartentotaalBank;

    Spel() {
        scanner = new Scanner(System.in);
        deck = new Kaartdeck();
        jeroen = new Speler("Jeroen");
        bank = new Bank("Bank");
        gameIsFinished = false;
    }

    void spelen() {
        System.out.println("Welkom bij Blackjack! om het spel te spelen, druk op 'y'");
        String starten = scanner.next();

        if (starten.equals("y")) {
            startSpel();
            deelKaart(jeroen);
            deelKaart(jeroen);
            toonHand();
            while(!gameIsFinished) {
                bepaalWinnaar();
                volgendeKaart();
            }

        }
    }

    private void volgendeKaart() {
        String volgendeKaart = scanner.next();
        if(volgendeKaart.equals("y")) {
            deelKaart(jeroen);
        }
        if(volgendeKaart.equals("n")){
            System.out.println("Je hebt gepast, de bank gaat nu spelen");
            gameIsFinished = true;
            bankSpeelt();
        }
    }

    private void bankSpeelt() {
        while (kaartenTotaalBank() < 17){
            deelKaart(bank);
        }
        System.out.println("De bank heeft score: " + kaartenTotaalBank());
        if (kaartenTotaal() > kaartenTotaalBank() || kaartenTotaalBank() > 21) {
            System.out.println("Je hebt gewonnen!");
        }
        else {
            System.out.println("De bank heeft gewonnnen");
        }
    }

    private void toonHand() {
        List<Kaart> kaarten = jeroen.getKaarten();
        Kaart kaart1 = kaarten.get(0);
        Kaart kaart2 = kaarten.get(1);

        System.out.println("Je hand is een " + kaart1.soort +" "+ kaart1.nummer + " met een " + kaart2.soort +" "+ kaart2.nummer);
    }

//    private void toonScore() {
//        List<Kaart> kaarten = jeroen.getKaarten();
//        System.out.println("De waarde van je hand is: " + kaartenTotaal());
//    }

    void startSpel() {
        System.out.println("Het spel wordt nu gestart \nSucces!");
    }


    void deelKaart(Speler speler) {
        speler.addKaart(deck.geeftKaart());
    }

    void bepaalWinnaar() {
        if (kaartenTotaal() == 21) {
            System.out.println("Je hebt gewonnen");
            gameIsFinished = true;
        }
        else if (kaartenTotaal() > 21){
            System.out.println("Je hebt jezelf kapot gespeeld");
            gameIsFinished = true;
        }
        else if (kaartenTotaal() <= 20) {
            System.out.println("De waarde van je hand is: " + kaartenTotaal() + ". Wil je verder spelen? y/n");
        }
    }

    private Integer kaartenTotaal() {
        kaartentotaal = 0;
        List<Kaart> kaarten = jeroen.getKaarten();
        for (Kaart kaart : kaarten) {
            kaartentotaal += kaart.waarde;
        }
        return kaartentotaal;
    }
    private Integer kaartenTotaalBank() {
        kaartentotaalBank = 0;
        List<Kaart> kaarten = bank.getKaarten();
        for (Kaart kaart : kaarten) {
            kaartentotaalBank += kaart.waarde;
        }
        return kaartentotaalBank;
    }
}
