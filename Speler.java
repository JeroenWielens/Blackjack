package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Speler {
	private String naam;
	List<Kaart> kaarten;

	Speler(String naam) {
		this.naam = naam;
		kaarten = new ArrayList<>();
	}


	 void addKaart(Kaart kaart) {

		this.kaarten.add(kaart);
	}

	public List<Kaart> getKaarten() {

		return kaarten;
	}
}
