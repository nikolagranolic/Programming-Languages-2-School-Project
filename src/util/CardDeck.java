package util;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
	private ArrayList<Card> cards = new ArrayList<>();
	
	public CardDeck() {
		for(int i = 0; i < 40; i++) {
			cards.add(new BasicCard(i / 10 + 1));
		}
//		for(int i = 0; i < 12; i++) {
//			cards.add(new SpecialCard());
//		}
		Collections.shuffle(cards);
	}
	
	public void drawCard() {
		Card temp = cards.remove(0);
		cards.add(temp);
	}
	
	
	public Card peek() {
		return cards.get(0);
	}
}
