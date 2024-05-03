package codegenExample;
import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MethodHash {
    String value();
}

public class ActionMenu {


    private ArrayList<String> indeces = new ArrayList<String>();
    private ArrayList<String> allowedNames = new ArrayList<String>();

    private ArrayList<Action> allowedActions = new ArrayList<Action>();

    public String getPlayString(Card card) {
        return "Play " + card;
    }

    public String getDiscardString(Card card) {
        return "Discard " + card;
    }

    public String getShootString(String player) {
        return "Shoot " + player;
    }

    public void ShowGameState(Player player) {

    }

    public void allowAction(String action, Card card) {
        if (action.equals("play")) {
            allowedNames.add(getPlayString(card));
            indeces.add(action + card);
            allowedActions.add(() -> System.out.println("playing  " + card));
        }
        if (action.equals("discard")) {
            allowedNames.add(getDiscardString(card));
            indeces.add(action + card);
            allowedActions.add(() -> System.out.println("discarding  " + card));
        }
    }



    public void allowAction(String action, String player) {
        if (action.equals("shoot")) {
            allowedNames.add(getShootString(player));
            indeces.add(action + player);
            allowedActions.add(() -> System.out.println("shooting  " + player));
        }
    }

    public void disallowAction(String action, String player) {
        int index = indeces.indexOf(action + player);
        if (index >= 0) {
            allowedActions.remove(index);
            allowedNames.remove(index);
            indeces.remove(index);
        }
    }

    public void disallowAction(String action, Card card) {
        int index = indeces.indexOf(action + card);
        if (index >= 0) {
            allowedActions.remove(index);
            allowedNames.remove(index);
            indeces.remove(index);
        }
    }

    public void disallowAllActions() {
        allowedActions.clear();
        allowedNames.clear();
        indeces.clear();
    }

    public void displayAllowedActions() {
        for (int i = 0; i < allowedNames.size(); i++) {
            System.out.println(i+1 + " - " + allowedNames.get(i));
        }
        int choice = choice(allowedNames.size());
        allowedActions.get(choice-1).act();
    }

    public int choice(int choices){
        int choice = -1;

        while (choice < 1 || choice > choices) {
            Scanner sc = new Scanner(System.in);
            while (!sc.hasNextInt()){
                sc.next();
                System.out.print("Not a number\n");
            }
            choice = sc.nextInt();
            if (choice > choices){
                System.out.print("Number too big, try again\n");
            } else if (choice < 1){
                System.out.print("Number too small, try again\n");
            }
        }
        return choice;
    }
}

class GameState {
    static final ArrayList<Deck> decks = new ArrayList<>();
    static final ArrayList<Hand> hands = new ArrayList<>();
    static final ArrayList<PlayArea> playAreas = new ArrayList<>();

    public static void showGameState(Player player) {
        for (Hand hand : hands) {
            if (!hand.cards.isEmpty()) {
                if (hand.owner.equals(player)) {
                    System.out.println(hand);
                } else {
                    System.out.print(player.name);
                    System.out.print((player.name.endsWith("s")) ? "'" : "'s");
                    System.out.println("hand - " + player.hand.cards.size() + " cards");
                }
            }
        }
        for (PlayArea playArea : playAreas) {
            if (playArea.cards.isEmpty()) {
                System.out.println(playArea.toString());
            }
        }
        for (Deck deck : decks) {
            if (deck.cards.isEmpty()) {
                System.out.println(deck.toString());
            }
        }
    }
}

class PlayArea extends Location {

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(name).append("\n");
        for (Card card : cards) {
            string.append("- ").append(card).append("\n");
        }
        return string.toString();
    }

    public PlayArea() {
        GameState.playAreas.add(this);
    }
}

class Location {
    String name;
    ArrayList<Card> cards = new ArrayList<>();
}

class Hand extends Location {
    Player owner;
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("This hand\n");
        for (Card card : cards) {
            string.append("- ").append(card).append("\n");
        }
        return string.toString();
    }

    public Hand() {
        GameState.hands.add(this);
    }
}

class Deck extends Location {
    int visible;
    public Card getTop() {
        return cards.get(0);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(name).append(" - ")
                .append((visible == 1) ? getTop() : "hidden (")
                .append(cards.size())
                .append(" ")
                .append((cards.size() == 1) ? "card" : "cards");
        return string.toString();
    }

    public Deck() {
        GameState.decks.add(this);
    }
}

class Player {
    String name;
    Hand hand = new Hand();

    public Player() {
    }
}

interface Action {
    abstract void act();
}