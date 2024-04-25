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

class Cardplustwo implements CardInterface {

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