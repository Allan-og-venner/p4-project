package codegenExample;

public class Card implements CardInterface {
    public String ID = "";

    public String toString() {
        return ID;
    }

    @Override
    public void onPlayed(String currPlayer) {
        //Empty because not defined
    }
}


interface CardInterface {
    public void onPlayed(String currPlayer);
}

class Cardplustwo extends Card {

    @Override
    public void onPlayed(String currPlayer) {
        //implement code for onPlayed plusTwo
    }
}

class Cardskip implements CardInterface {
    @Override
    public void onPlayed(String currPlayer) {
        //implement code for onPlayed skip
    }
}

class Animal {
    public String name;

    @Override
    public boolean equals(Object other) {
        if (other.getClass().equals(this.getClass())) {
            if (this.name.equals(((Animal) other).name))
                return true;
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