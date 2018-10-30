import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class DiceGame {

	private final Dice[] dices; 
	private int rolls, score, roundScore;
	private String hand;

	public DiceGame() {
		dices = new Dice[5];
		score = 0;
		roundScore = 0;
		rolls = 3;
		hand = "";

		for(int i = 0; i < dices.length; i++)
			dices[i] = new Dice();
	}
	
	public Dice getDice(int i) {
		return dices[i];
	}

	public Dice[] getDices() {
		return dices;
	}

	public int getRolls() {
		return rolls;
	}

	public int getScore() {
		return score;
	}
	
	public int getRoundScore() {
		return roundScore;
	}

	public String getHand() {
		return hand;
	}

	public void resetGame() {
		rolls = 3;
		score+=roundScore;
		roundScore = 0;
		hand = "";

		for(int i = 0; i < dices.length; i++)
			dices[i].setDice(1, false);
	}

	public void rollDices() {
		Random rand = new Random();

		for(int i = 0; i < dices.length; i++) {
			if(!dices[i].isHeld()) {
				dices[i].setValue(rand.nextInt(6) + 1);
				dices[i].setImg();
			}
			else
				dices[i].setHeld(false);

		}

		rolls--;
	}

	public void scorer() {

		HashMap<Integer, Integer> count = new HashMap<>();

		for(int i = 0; i < dices.length; i++) {
			if(count.get(dices[i].getValue()) == null)
				count.put(dices[i].getValue(), 1);
			else {
				int val = count.get(dices[i].getValue()) + 1;
				count.put(dices[i].getValue(), val);
			}
		}

		if(isXKind(count,5)) {
			roundScore += 10;
			hand = "Five of a Kind! +10";
		}
		else if(isStraight(count)) {
			roundScore += 8;
			hand = "Straight! +8";
		}
		else if(isXKind(count,4)) {
			roundScore += 7;
			hand = "Four of a Kind! +7";
		}
		else if(isFullHouse(count)) {
			roundScore += 6;
			hand = "Full House! +6";
		}
		else if(isXKind(count,3)) {
			roundScore += 5;
			hand = "Three of a Kind! +5";
		}
		else if(isTwoPair(count)) {
			roundScore += 4;
			hand = "Two Pairs! +4";
		}
		else if(isXKind(count,2)) {
			roundScore += 3;
			hand = "Two of a Kind! +3";
		}
		else {
			hand = "Nothing!";
		}

	}
	
	public boolean isXKind(HashMap<Integer, Integer> count, int x) {
		return count.containsValue(x);
	}

	public Boolean isTwoPair(HashMap<Integer, Integer> count) {

		List<Integer> diceList = new ArrayList<Integer>(count.values());

		if(diceList.size() > 3)
			return false;

		int val = 0;

		for(int i : diceList){
			if(i == 2)
				val++;
		}

		return val == 2;
	}

	public Boolean isFullHouse(HashMap<Integer, Integer> count) {

		return count.containsValue(3) && count.containsValue(2);

	}

	public Boolean isStraight(HashMap<Integer, Integer> count) {

		List<Integer> diceList = new ArrayList<Integer>(count.keySet());

		if(diceList.size() < 5)
			return false;

		int val = diceList.get(0);

		for(int i = 0; i < diceList.size(); i++) {

			if(val != diceList.get(i))
				return false;

			val++;
		}

		return true;

	}




}
