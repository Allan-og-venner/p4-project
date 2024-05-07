package Test;
import java.util.*;
public class Main {
	static ArrayList<Player> players = new ArrayList<>();
	static Player currPlayer;
	static Deck deck;
	static Deck playPile;
	static Player savedPrevPlayer;
	static Player savedNextPlayer;
	static int returnNextPlayer;
	static int colors;
	static Card wildCard;
	static Card plusFour;
	static public void game() {
		print(test()+"");
		print(test2()+"");
		if (((-("false".indexOf("" + (returnNextPlayer==1)))) == 0 ? false : true )) {
			savedPrevPlayer.nextPlayer=savedNextPlayer;;
		};
		returnNextPlayer=0;;
		print("It is your turn, "+currPlayer.name+"\n");
		GameState.showGameState(currPlayer);for (Card card : currPlayer.hand.cards) 
		{
			if (((-("false".indexOf("" + (((-("false".indexOf("" + (((-("false".indexOf("" + (card.color.equals(playPile.getTop().color))))) == 0 ? false : true )||((-("false".indexOf("" + (card.color.equals("wild"))))) == 0 ? false : true ))))) == 0 ? false : true )||((-("false".indexOf("" + (card.ID.equals(playPile.getTop().ID))))) == 0 ? false : true ))))) == 0 ? false : true )) {
				ActionMenu.allowAction("play", card);
			};
		}
		ActionMenu.allowAction("draw");
		ActionMenu.displayAllowedActions();
		ActionMenu.disallowAllActions();
		if (((-("false".indexOf("" + (currPlayer.hand.numberOfCards()==0)))) == 0 ? false : true )) {
			end(currPlayer);
		};
		currPlayer=currPlayer.nextPlayer;;
	}
	static public void end(Player player) {
		print("YOU ARE THE WINNER "+player.name+", CONGLATURATIONS");
		System.exit(0);
	}
	static class Deck extends Location {
		public Deck() {
			GameState.decks.add(this);
		}
		int visible = 0;
		public void draw(Location hand){
			hand.cards.add(super.cards.get(0));
			super.cards.remove(0);
		}
		public Card getTop() {
			return super.cards.get(0);
		}
		public void drawMany(Location hand, int amountToDraw) {
			for (int i = 0; i < amountToDraw; i++){
				if (!hand.cards.isEmpty()){
					hand.cards.add(0, super.cards.get(0));
				}
				else {
					hand.cards.add(super.cards.get(0));
				}
				super.cards.remove(0);
			}
		}
		public void shuffle(){
			Collections.shuffle(super.cards);
		}
		public void add(Card card, int number) {
			for (int i = 0; i < number; i++) {
				if(!super.cards.isEmpty()){
					super.cards.add(0, card.clone());
				}
				else{
					super.cards.add(card.clone());
				}
			}
		}
		public boolean equals(Object other){
			return this.name.equals(((Deck) other).name);
		}
		@Override
public String toString() {
			StringBuilder string = new StringBuilder();
			string.append(name).append(" - ").append((visible == 1) ? getTop() + " (" : "hidden (").append(cards.size()).append(" ").append((cards.size() == 1) ? "card)" : "cards)");
			return string.toString();
		}
	}
	static class Player {
		String name;
		Hand hand;
		Player nextPlayer;
		public Player findNextPlayer(int count) {
			 Player tmp = this.nextPlayer;
			count = (players.size()+count)%players.size();for (int i = 0; i < count; i++) 
			{
				tmp = tmp.nextPlayer;
			}
			return tmp;
		}
		public String toString() {
			return this.name;
		}
		public boolean equals(Object other){
			return this.name.equals(((Player) other).name);
		}
		Player(String name){
			this.name = name;
			this.hand = new Hand(this);
		}
	}
	static class Cardreverse extends Card {
		Cardreverse(Card card){
			super(card);
		}
		@Override
public void onPlayed(Card card) {
			for (Player player : players) {
				player.nextPlayer=player.findNextPlayer(5);;
			}
		}
	}
	static class Cardplusfour extends Card {
		Cardplusfour(Card card){
			super(card);
		}
		@Override
public void onPlayed(Card card) {
			savedPrevPlayer=currPlayer;;
			savedNextPlayer=currPlayer.nextPlayer;;
			returnNextPlayer=1;;
			deck.drawMany(currPlayer.nextPlayer.hand, 4);
			currPlayer.nextPlayer=currPlayer.nextPlayer.nextPlayer;;
			ActionMenu.allowAction("changeColor", card, "red");
			ActionMenu.allowAction("changeColor", card, "yellow");
			ActionMenu.allowAction("changeColor", card, "green");
			ActionMenu.allowAction("changeColor", card, "blue");
			ActionMenu.displayAllowedActions();
		}
	}
	static class GameState {
		static final ArrayList<Deck> decks = new ArrayList<>();
		static final ArrayList<Hand> hands = new ArrayList<>();
		static final ArrayList<PlayArea> playAreas = new ArrayList<>();
		public static void showGameState(Player player) {
			for (Hand hand : hands) {
				if (!hand.cards.isEmpty()) {
					if (hand.owner.equals(player)) {
						System.out.println(hand);
					} 
					else {
						String owner = hand.owner.name;
						System.out.print(owner);
						System.out.print((owner.endsWith("s")) ? "'" : "'s");
						System.out.println(" hand - " + hand.cards.size() + " cards");
					}
				}
			}for (PlayArea playArea : playAreas) 
			{
				if (!playArea.cards.isEmpty()) {
					System.out.println(playArea.toString());
				}
			}for (Deck deck : decks) 
			{
				if (!deck.cards.isEmpty()) {
					System.out.println(deck.toString());
				}
			}
		}
	}
	static interface Action {
		abstract void act();
	}
	static class Cardplustwo extends Card {
		Cardplustwo(Card card){
			super(card);
		}
		@Override
public void onPlayed(Card card) {
			savedPrevPlayer=currPlayer;;
			savedNextPlayer=currPlayer.nextPlayer;;
			returnNextPlayer=1;;
			deck.drawMany(currPlayer.nextPlayer.hand, 2);
			currPlayer.nextPlayer=currPlayer.nextPlayer.nextPlayer;;
		}
	}
	static class Location {
		String name;
		ArrayList<Card> cards = new ArrayList<>();
		int numberOfCards() {
			return cards.size();
		}
	}
	static class ActionMenu {
		private static ArrayList<String> indexes = new ArrayList<String>();
		private static ArrayList<String> allowedNames = new ArrayList<String>();
		private static ArrayList<Action> allowedActions = new ArrayList<Action>();
		public static void displayAllowedActions() {
			if (allowedNames.size() > 0){
				for (int i = 0; i < allowedNames.size(); i++) {
					System.out.println(i+1 + " - " + allowedNames.get(i));
				}
				int choice = choice(allowedNames.size());
				allowedActions.get(choice-1).act();
			}
		}
		public static void disallowAllActions() {
			        allowedActions.clear();        
			allowedNames.clear();        
			indexes.clear();    
		}
		public static int choice(int choices) {
			int choice = -1;
			while (choice < 1 || choice > choices) {
				Scanner sc = new Scanner(System.in);
				while (!sc.hasNextInt()) {
					sc.next();
					System.out.print("Not a number\n");
				}
				choice = sc.nextInt();
				if (choice > choices) {
					System.out.print("Number too big, try again\n");
				} 
				else if (choice < 1) {
					System.out.print("Number too small, try again\n");
				}
			}
			return choice;
		}
		static String getchangeColorString(Card card, String color) {
			return "change to "+color;
		}
		static void allowAction(String action, Card card, String color) {
			if (action.equals("changeColor")) {
				allowedNames.add(getchangeColorString(card, color));
				indexes.add(action );
				allowedActions.add(() -> {
					card.color=color;;
				}
				);
			}
		}
		static void disallowAction(String action, Card card, String color) {
			int index = indexes.indexOf(action );
			if (index >= 0) {
				allowedActions.remove(index);
				allowedNames.remove(index);
				indexes.remove(index);
			}
		}
		static String getplayString(Card card) {
			return "play "+card;
		}
		static void allowAction(String action, Card card) {
			if (action.equals("play")) {
				allowedNames.add(getplayString(card));
				indexes.add(action );
				allowedActions.add(() -> {
					 int i = 0;;
					while (((-("false".indexOf("" + (i<currPlayer.hand.numberOfCards())))) == 0 ? false : true )) {
						if (((-("false".indexOf("" + (currPlayer.hand.cards.get(i).equals(card))))) == 0 ? false : true )) {
							currPlayer.hand.move(i, playPile);
							card.onPlayed(card);
						};
						i=i+1;;
					}
				}
				);
			}
		}
		static void disallowAction(String action, Card card) {
			int index = indexes.indexOf(action );
			if (index >= 0) {
				allowedActions.remove(index);
				allowedNames.remove(index);
				indexes.remove(index);
			}
		}
		static String getdrawString() {
			return "draw from deck";
		}
		static void allowAction(String action) {
			if (action.equals("draw")) {
				allowedNames.add(getdrawString());
				indexes.add(action );
				allowedActions.add(() -> {
					deck.draw(currPlayer.hand); 
					Card card = currPlayer.hand.cards.get(0);;
					print("You drew a "+card+"\n");
					if (((-("false".indexOf("" + (((-("false".indexOf("" + (((-("false".indexOf("" + (card.color.equals(playPile.getTop().color))))) == 0 ? false : true )||((-("false".indexOf("" + (card.color.equals("wild"))))) == 0 ? false : true ))))) == 0 ? false : true )||((-("false".indexOf("" + (card.ID.equals(playPile.getTop().ID))))) == 0 ? false : true ))))) == 0 ? false : true )) {
						ActionMenu.disallowAllActions();
						ActionMenu.allowAction("play", card);
						ActionMenu.displayAllowedActions();
						ActionMenu.disallowAllActions();
					};
				}
				);
			}
		}
		static void disallowAction(String action) {
			int index = indexes.indexOf(action );
			if (index >= 0) {
				allowedActions.remove(index);
				allowedNames.remove(index);
				indexes.remove(index);
			}
		}
	}
	static class Card implements Cloneable {
		String ID;
		public String toString() {
			if (((-("false".indexOf("" + (ID.equals("plusfour"))))) == 0 ? false : true )) {
				return "plus 4";
			};
			if (((-("false".indexOf("" + (ID.equals("plustwo"))))) == 0 ? false : true )) {
				return color+" plus 2";
			};
			if (((-("false".indexOf("" + (ID.equals("wild"))))) == 0 ? false : true )) {
				return "wild";
			};
			return color+" "+ID;
		}
		@Override
public Card clone() {
			try {
				return (Card) super.clone();
			} 
			catch (CloneNotSupportedException e) {
				System.err.println("Clone of card not supported");
				return null;
			}
		}
		public Card convert() {
			if(this.ID.equals( "plustwo")){
				return new Cardplustwo(this);
			}
			if(this.ID.equals( "plusfour")){
				return new Cardplusfour(this);
			}
			if(this.ID.equals( "skip")){
				return new Cardskip(this);
			}
			if(this.ID.equals( "reverse")){
				return new Cardreverse(this);
			}
			if(this.ID.equals( "wild")){
				return new Cardwild(this);
			}
			return this;
		}
		public Card(Card card){
			this.color = card.color;
			this.ID = card.ID;
		}
		public Card(){
			
		} 
		String color = ""; 
		void onPlayed(Card card){
			 Card a = convert();
			if (a.getClass() != Card.class) {
				a.onPlayed(card); 
			} 
			else {
				
			}
		}
	}
	static class Hand extends Location {
		String name;
		int maxSize;
		Player owner;
		public void move(int cardNum, Location moveToLocation) {
			if(!moveToLocation.cards.isEmpty()){
				moveToLocation.cards.add(0, super.cards.get(cardNum));
			}
			else{
				moveToLocation.cards.add(super.cards.get(cardNum));
			}
			super.cards.remove(cardNum);
		}
		@Override
public String toString() {
			StringBuilder string = new StringBuilder();
			string.append("\nThis hand\n");for (Card card : cards) 
			{
				string.append("- ").append(card).append("\n");
			}
			return string.toString();
		}
		public Hand(Player player) {
			GameState.hands.add(this);
			this.owner = player;
		}
		public boolean equals(Object other){
			return this.owner.equals(((Hand) other).owner);
		}
		public Hand() {
			GameState.hands.add(this);
		}
	}
	static class PlayArea extends Location {
		@Override
public String toString() {
			StringBuilder string = new StringBuilder();
			string.append(name).append("\n");for (Card card : cards) 
			{
				string.append("- ").append(card).append("\n");
			}
			return string.toString();
		}
		public PlayArea() {
			GameState.playAreas.add(this);
		}
		public boolean equals(Object other){
			return this.name.equals(((PlayArea) other).name);
		}
		public void move(int cardNum, Location moveToLocation) {
			if(!moveToLocation.cards.isEmpty()){
				moveToLocation.cards.add(0, super.cards.get(cardNum));
			}
			else{
				moveToLocation.cards.add(super.cards.get(cardNum));
			}
			super.cards.remove(cardNum);
		}
	}
	static class Cardwild extends Card {
		Cardwild(Card card){
			super(card);
		}
		@Override
public void onPlayed(Card card) {
			ActionMenu.allowAction("changeColor", card, "red");
			ActionMenu.allowAction("changeColor", card, "yellow");
			ActionMenu.allowAction("changeColor", card, "green");
			ActionMenu.allowAction("changeColor", card, "blue");
			ActionMenu.displayAllowedActions();
		}
	}
	static class Cardskip extends Card {
		Cardskip(Card card){
			super(card);
		}
		@Override
public void onPlayed(Card card) {
			savedPrevPlayer=currPlayer;;
			savedNextPlayer=currPlayer.nextPlayer;;
			returnNextPlayer=1;;
			currPlayer.nextPlayer=currPlayer.nextPlayer.nextPlayer;;
		}
	}
	static void print(String input) {
		System.out.print(input);
	}
	static int strlen(String input) {
		return input.length();
	}
	public static void generatePlayerList(ArrayList<String> args) {
		for (int i = 0; i<args.size(); i++) {
			Player tmp = new Player(args.get(i));
			players.add(tmp);
			if (i > 0) {
				players.get(i-1).nextPlayer = players.get(i);
			}
		}
		players.get(args.size()-1).nextPlayer = players.get(0);
	}
	static public int test() {
		return -("false".indexOf("" + (new ArrayList<>() {
			{
				add(1);
				add(2);
				add(3);
			}
		}
		.equals(new ArrayList<>() {
			{
				add(1);
				add(2);
				add(3);
			}
		}
		))));
	}
	static public int test2() {
		return -("false".indexOf("" + (new ArrayList<>() {
			{
				add(1);
				add(2);
				add(3);
			}
		}
		.equals(new ArrayList<>() {
			{
				add(2);
				add(3);
				add(4);
			}
		}
		))));
	}
	public static void main(String[] args) {
		generatePlayerList(new ArrayList<>() {
			{
				add("Nicolaj");
				add("Simon");
				add("Allan");
				add("Sina");
			}
		}
		);
		currPlayer = players.get(0);;
		deck = new Deck();;
		deck.name="Deck";;
		playPile = new Deck();;
		playPile.name="Play pile";;
		playPile.visible=1;;;;
		returnNextPlayer = 0;;
		colors = 0;;
		while (((-("false".indexOf("" + (colors<4)))) == 0 ? false : true )) {
			 String color = "";;
			if (((-("false".indexOf("" + (colors==0)))) == 0 ? false : true )) {
				color="red";;
			};
			if (((-("false".indexOf("" + (colors==1)))) == 0 ? false : true )) {
				color="blue";;
			};
			if (((-("false".indexOf("" + (colors==2)))) == 0 ? false : true )) {
				color="yellow";;
			};
			if (((-("false".indexOf("" + (colors==3)))) == 0 ? false : true )) {
				color="green";;
			}; 
			Card zero = new Card();;
			zero.ID=0+"";;
			zero.color=color;;
			deck.add(zero, 1); 
			int i = 1;;
			while (((-("false".indexOf("" + (i<9)))) == 0 ? false : true )) {
				 Card card = new Card();;
				card.ID=i+"";;
				card.color=color;;
				deck.add(card, 2);
				i=i+1;;
			} 
			Card plusCard = new Card();;
			plusCard.ID="plustwo";;
			plusCard.color=color;;
			deck.add(plusCard, 2); 
			Card skipCard = new Card();;
			skipCard.ID="skip";;
			skipCard.color=color;;
			deck.add(skipCard, 2);
			colors=colors+1;;
		}
		wildCard = new Card();;
		wildCard.ID="wild";;
		wildCard.color="wild";;
		deck.add(wildCard, 8);
		plusFour = new Card();;
		plusFour.ID="plusfour";;
		plusFour.color="wild";;
		deck.add(plusFour, 4);
		deck.shuffle();
		deck.draw(playPile);for (Player player : players) 
		{
			deck.drawMany(player.hand, 7);
		}
		while (true) {
			Main.game(); 
		}
	}
}
