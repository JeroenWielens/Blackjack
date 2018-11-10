package blackjack;

import java.util.*;

public class Kaartdeck {

	private ArrayList<Kaart> deck = new ArrayList<>();
	private Random random;

	Kaartdeck() {
		random = new Random();
		List<String> type = Arrays.asList("Harten", "Ruiten", "Schoppen", "Klaveren");
		HashMap<String, Integer> nummers = new HashMap();
		nummers.put("2", 2);
		nummers.put("3", 3);
		nummers.put("4", 4);
		nummers.put("5", 5);
		nummers.put("6", 6);
		nummers.put("7", 7);
		nummers.put("8", 8);
		nummers.put("9", 9);
		nummers.put("10", 10);
		nummers.put("B", 10);
		nummers.put("V", 10);
		nummers.put("H", 10);
		nummers.put("A", 11);

		for (String kaart : type) {
			nummers.forEach((key, value) -> deck.add(new Kaart(kaart, key, value)));
		}
	}


	Kaart geeftKaart() {
		int index = random.nextInt(deck.size());
		Kaart kaart = deck.get(index);
		deck.remove(index);
		return kaart;
	}
}
