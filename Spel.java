package blackjack;

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

    Spel() {
        scanner = new Scanner(System.in);

    }

    void spelen() {
        deck = new Kaartdeck();
        jeroen = new Speler("Jeroen");
        bank = new Bank("Bank");
        gameIsFinished = false;

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
        while (bank.puntenTotaal() < 17){
            deelKaart(bank);
        }
        System.out.println("De bank heeft score: " + bank.puntenTotaal());
        if (jeroen.puntenTotaal() > bank.puntenTotaal() || bank.puntenTotaal() > 21) {
            System.out.println("Je hebt gewonnen!");
        }
        else {
            System.out.println("De bank heeft gewonnnen");
        }
        speelOpnieuw();
    }

    private void toonHand() {
        List<Kaart> kaarten = jeroen.getKaarten();
        Kaart kaart1 = kaarten.get(0);
        Kaart kaart2 = kaarten.get(1);

        System.out.println("Je hand is een " + kaart1.soort +" "+ kaart1.nummer + " met een " + kaart2.soort +" "+ kaart2.nummer);
    }

    private void startSpel() {
        System.out.println("Het spel wordt nu gestart \nSucces!");
    }


    private void deelKaart(Speler speler) {
        speler.addKaart(deck.geeftKaart());
    }

    private void bepaalWinnaar() {
        if (jeroen.puntenTotaal() == 21) {
            System.out.println("Je hebt gewonnen");
            gameIsFinished = true;
            speelOpnieuw();
        }
        else if (jeroen.puntenTotaal() > 21){
            System.out.println("Je hebt jezelf kapot gespeeld");
            gameIsFinished = true;
            speelOpnieuw();
        }
        else if (jeroen.puntenTotaal() <= 20) {
            System.out.println("De waarde van je hand is: " + jeroen.puntenTotaal() + ". Wil je verder spelen? y/n");
        }
    }

    private void speelOpnieuw() {
        System.out.println("Wil je nog een keer spelen? y/n");
        String opnieuwspelen = scanner.next();
        if (opnieuwspelen.equals("y")) {
            spelen();
        }
    }
}
