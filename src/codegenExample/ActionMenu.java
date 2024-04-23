package codegenExample;
import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.Scanner;

public class ActionMenu {


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


    public void allowAction(String action, Card card) {
        if (action.equals("play")) {
            allowedNames.add(getPlayString(card));
            allowedActions.add(() -> System.out.println("playing " + card));
        }
        if (action.equals("discard")) {
            allowedNames.add(getDiscardString(card));
            allowedActions.add(() -> System.out.println("discarding  " + card));
        }
    }

    public void allowAction(String action, String player) {
        if (action.equals("shoot")) {
            allowedNames.add(getShootString(player));
            allowedActions.add(() -> System.out.println("shooting  " + player));
        }
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

interface Action {
    void act();
}