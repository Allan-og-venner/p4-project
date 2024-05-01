package Test;

import java.util.*;
public class Main {
    static ArrayList<Player> players = Main.generatePlayerList(new ArrayList<>() {
           {
               add("p1");
               add("p2");
           }
       }
    );
    static class Animal {
        String name;
        hound hound2;
        static int count;
        public void increaseCount() {
            count=count+1;
        }
        String race;
        public void increaseCount2() {
            count=count+1;
        }
        public boolean equals(Object other) {
            if (other.getClass().equals(this.getClass())) {
                return (this.name==((Animal) other).name && this.hound2.equals(((Animal) other).hound2) && this.count==((Animal) other).count && this.race==((Animal) other).race);
            }
            return false;
        }

    }
    static class Dog extends Animal {
        public void bark() {
            Main.print("bow wow");
        }
        public boolean equals(Object other) {
            if (other.getClass().equals(super.getClass())) {
                return true;
            }
            return false;
        }
    }
    static class Cat extends Animal {
        public void meow() {
            Main.print("bow wow");
        }
        public boolean equals(Object other) {
            if (other.getClass().equals(super.getClass())) {
                return true;
            }
            return false;
        }
    }
    static class Springer extends Dog {
        public void annoy(String owner) {
            Main.print("bow wow");
        }
        public boolean equals(Object other) {
            if (other.getClass().equals(this.getClass())) {
                return true;
            }
            return false;
        }
    }
    static Springer copper2 = new Springer();
    static Dog copper = new Springer();;
    static Animal amber = new Cat();;
    static Cat ellie = new Cat();;
    static ArrayList<Main.Animal> pets = new ArrayList<>() {
        {
            add(copper);
            add(amber);
            add(ellie);
            add(copper2);
        }
    };
    class hound {
        int number;
        String region;
        public boolean equals(Object other) {
            if (other.getClass().equals(this.getClass())) {
                return (this.number==((hound) other).number && this.region==((hound) other).region);
            }
            return false;
        }
    }
    public static void game() {
        Main.print("bow wow");
    }
    public static void end() {
        Main.print("bow wow");
    }
    static void print(String input) {
        System.out.print(input);
    }
    static int strlen(String input) {
        return input.length();
    }
    public static ArrayList<Player> generatePlayerList(ArrayList<String> args) {
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i<args.size(); i++)
        {
            Player tmp = new Player();
            tmp.name = args.get(i);
            players.add(tmp);
            if(i > 0) {
                players.get(i-1).nextPlayer = players.get(i);
            }
        }
        players.get(args.size()-1).nextPlayer = players.get(0);
        return players;
    }
    public float test() {
        if (new ArrayList<>() {
            {
                add(5);
                add(4);
            }
        }
                .equals(new ArrayList<>() {
                            {
                                add(5);
                                add(4);
                            }
                        }
                )) {
            Main.print("bow wow");
        }
        return 1+1.1f;
    }
    public int printGreater(int a, int b) {
        if (a>b) {
            return a;
        }
        if (b>a) {
            return b;
        }
        return a;
    }
    public static void main(String[] args) {

        if (amber.equals(ellie)){
            Main.print("bow wow");
        }
        while (true) {
            Main.game();
        }
        //Main.end();
    }
}

class Card {
    String ID;
}

class Location {
    ArrayList<Card> cards = new ArrayList<>();
}

class ActionMenu {
    private ArrayList<String> indeces = new ArrayList<String>();
    private ArrayList<String> allowedNames = new ArrayList<String>();
    private ArrayList<Action> allowedActions = new ArrayList<Action>();
    public void displayAllowedActions() {
        for (int i = 0; i < allowedNames.size(); i++) {
            System.out.println(i+1 + " - " + allowedNames.get(i));
        }
        int choice = choice(allowedNames.size());
        allowedActions.get(choice-1).act();
    }
    public int choice(int choices) {
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
}

class Player {
    String name;
    Player nextPlayer;
    public Player findNextPlayer(int count){
        Player tmp = this.nextPlayer;for (int i = 0; i < count; i++)
        {
            tmp = tmp.nextPlayer;
        }
        return tmp;
    }
}

interface Action {
    abstract void act();
}

class PlayArea extends Location {
    public void move(int cardNum, Location moveToLocation){
        moveToLocation.cards.add(0, super.cards.get(cardNum));
        super.cards.remove(cardNum);
    }
}

class Hand extends Location {
    String name;
    int maxSize;
    Player owner;
    public void move(int cardNum, Location moveToLocation){
        moveToLocation.cards.add(0, super.cards.get(cardNum));
        super.cards.remove(cardNum);
    }
}

 class Deck extends Location {
    int visible = 0;
    public void draw(Location Hand){
        Hand.cards.add(super.cards.get(0));
        super.cards.remove(0);
    }
    public Card getTop(){
        return super.cards.get(0);
    }
    public void drawMany(Location Hand, int amountToDraw){
        for(int i = 0; i < amountToDraw; i++) {
            Hand.cards.add(0, super.cards.get(0));
            super.cards.remove(0);
        }
    }
    public void shuffle(){
        Collections.shuffle(super.cards);

    }
}


