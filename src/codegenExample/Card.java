package codegenExample;

public class Card implements Cloneable{
    public String ID = "";

    public String toString() {return ID;}

    @Override
    public Card clone() {
        try {
            return (Card) super.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println("Clone of card not supportedp");
            return null;
        }
    }


    public void onPlayed(String currPlayer) {
        //Empty because not defined
    }
}


class Cardplustwo extends Card {
    @Override
    public void onPlayed(String currPlayer) {
        //implement code for onPlayed plusTwo
    }
}

class Cardskip extends Card {
    @Override
    public void onPlayed(String currPlayer) {
        //implement code for onPlayed skip
    }
}

class Cardfour extends Card {

}

class Animal {
    public String name;

    @Override
    public boolean equals(Object other) {
        if (other.getClass().equals(this.getClass())) {
            return (this.name.equals(((Animal) other).name));
        }
        return false;
    }
}

class Dog extends Animal {
    public String owner;

    @Override
    public boolean equals(Object other) {
        if (other.getClass().equals(this.getClass())) {
            return (this.name.equals(((Dog) other).name) && this.owner.equals(((Dog) other).owner));
        }
        return false;
    }
}

class Cat extends Animal {
    public int killCount;
}