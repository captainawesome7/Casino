package Base;

public class Card {
	private String suit;
	private int value;
	private String name = "";
	
	public Card(int $value,String $suit){
		value = $value;
		suit = $suit;
		giveName();
	}
	
	public String getSuit(){
		return suit;
	}
	
	public int getValue(){
		return value;
	}
	
	public void setValue(int i) {
		value = i;	
	}
	
	public String getName(){
		return name;
	}
	
	public void giveName(){
		switch(value){
		case 1: name = "Ace"; break;
		case 2: name = "two"; break;
		case 3: name = "three"; break;
		case 4: name = "four"; break;
		case 5: name = "five"; break;
		case 6: name = "six"; break;
		case 7: name = "seven"; break;
		case 8: name = "eight"; break;
		case 9: name = "nine"; break;
		case 10: name = "ten"; break;
		case 11: name = "Jack"; break;
		case 12: name = "Queen"; break;
		case 13: name = "King"; break;
		}
	}
	
	
	public String toString(){		
		String str = "The " + name + " of " + suit;
		return str;
	}


}
