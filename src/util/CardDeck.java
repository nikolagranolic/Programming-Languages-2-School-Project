package util;

import java.util.ArrayList;
import java.util.Random;

public class CardDeck {
	private ArrayList<Card> cards = new ArrayList<>();
	
	public CardDeck() {
		Card[] cardPool = new Card[6];
		cardPool[0] = new SpecialCard("src/Images/specialcard.png");
		for(int i = 1; i <= 5; i++) {
			cardPool[i] = new BasicCard("src/Images/basicimage" + i + ".png", i);
		}
		int specialCardsRemaining = 5, index;
		Random rand = new Random();
		for(int i = 0; i < 52; i++) {
			index = rand.nextInt(6);
			if(index == 0 && specialCardsRemaining > 0) {
				cards.add(cardPool[index]);
				specialCardsRemaining--;
			}
			else {
				if(index == 0)
					index = rand.nextInt(5) + 1;
				cards.add(cardPool[index]);
			}
		}
	}
	
	public Card drawCard() {
		Random rand = new Random();
		return cards.get(rand.nextInt(53));
	}
}
