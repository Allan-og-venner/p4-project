# CLIPS
## Card Language Intended for Prototyping Systems

The programming language CLIPS is intended for creating simple card games by programming.
Whether you are looking for a way of creating your own card game, a format for learning how to program by creating an already existing card game, 
or just want to try out a new programming language, CLIPS can be used for it all.

CLIPS has similar features to other languages, but also includes other card game related features such as already defined game classes.



### Declare variables
Variables can be declared using the types int, float, string and char in the following way:

```
int number = 1;

float decimal = 2.5;

string greeting = "Hello World!";

char letter = 'a';
```
Arrays can be created as a collection of one or more of the same type. They are indexed from 0.

```
string colors = ["red", "green", "blue", "yellow"];

print(colors[0]);
```

### Control structures
#### If-statement

In order to control the flow of a program, control structures has been implemented to use.
One of them being a single alternative conditional structure.
```
if (number < 10) {
    
    print("The number is less than 10");
    
}
```
#### Loops
An iteration can be a repeat-While structure which includes a condition to control the loop and body.


```
loop while (points < 100) {
    
    points = points + 5;
    
}
```
A loop can also be a repeat-For structure for iterating through an array.
```
string players = ["Simon", "Nicolaj", "Allan"];
loop for player in players {
    
    print(player);
    
}
```

### Functions

Functions within a programming language is effective for organizing ones code or creating a sub procedure which can be called multiple times.

A function is defined with the func keyword, the identifier for the function, the formal parameters and an arrow leading to the return type of the function.

A function can be called with the function identifier and the actual parameters.
```
/* Function definition */
func add(int number1, int number2) -> int {
    return number1 + number2;
}

int num1 = 3;
int num2 = 4;

/* Function call */
add(num1, num2);
```

### Actions
An action is defined similar to a function except for an addition which allows for a description of the action.

```
action changeColor(Card card, string color) -> void : "change to " + color {
    card.color = color;
}
```


### Class definition

Classes can be used to gather several variables and function related to a concept.

A class can be defined by having the Class keyword along with an identifier and then a block contained by curly brackets.
The block will contain the fields and methods associated with the class.

A class can also extend another class by inserting the extends keyword and a superclass.

```
Class Person {
    string name;
}

Class Child extends Person {
    string hobby;
    void changeHobby(string newHobby){
        hobby = newHobby;
    }
}
```

An instance of a class can be created by using the new keyword.

```
new Child();
```

#### Class access

Once an instance of a class is made. The fields and methods can be accessed by using a dot.

```
Child child = new Child();
child.changeHobby("Badminton");
print(child.hobby);

/* Prints 'Badminton' */
```

### CardType
You can declare a type of card which can have methods and fields.

A new card type is initialized with the cardType keyword and the possibility to set the cards ID, then a colon before defining the fields and methods of the card type.
```
cardType(ID = "skip"
     : onPlayed(Player player1) -> void {
        "do something";
     })
```


### Build in functions
#### Strlen
Some build in functions are available for easing the coding experience.
strlen(string) returns the length of a string - the number of characters in a string
```
string name = "Simon";
int number = strlen(name);
print(number);

/* prints '5' as the length of the variable name */
```
#### GeneratePlayerList
Takes an array of player names as input and creates an instance of the player class for each name.
Adds each instance to the build in players variable and sets the nextPlayer field for each player.

```
generatePlayerList({"Nicolaj", "Simon", "Allan", "Sina"});
```

### Build in variables

An array list of players are available and players are added to the list when the generatePlayerList function is called.
```
generatePlayerList({"Nicolaj", "Simon", "Allan", "Sina"});

Player currPlayer = players[0];
```


### Build in classes
#### Card
A Card has an ID which can be accessed, and a method called clone which returns a cloned card.

```
Card card = new Card();
card.ID = "skip";
print(card.ID);

/* prints 'skip' as the card ID */

Card nextCard = card.clone();
```
#### Location
A Location has a name, an array of cards and a method which returns the number of cards at the location.
Location is the superclass of Deck, Hand, PlayArea which means that these classes share the fields and methods of Location.

#### Deck

A Deck has the field visible which is set to 0 indicating that the deck is not visible.
A Deck has different methods associated with interacting with a deck.
You can draw one card from the top of a deck to a hand:

```
Deck deck = new Deck();
Hand hand = new Hand();
deck.draw(hand);
```
The method getTop returns the top card of a deck.

```
Card topCard = new Card();
topCard = deck.getTop();
```
You can also draw a selected number of cards from a deck with drawMany.

```
deck.drawMany(hand, 2);
```
The method shuffle will mix the cards in the deck in a random order.
```
deck.shuffle();
```
The method add can be used to add the same type of card a number of times to a deck.
```
deck.add(card, 9);
```
#### Hand

A hand can have a name and a maxSize. A hand can be constructed with a player associated to it or not.
```
Player player1 = new Player("Simon");
Hand hand1 = new Hand(player1);
Hand hand2 = new Hand();
```
The method move can be used to move a specific card from a hand to another location.
```
hand1.move(2, deck1);

/* moves the card from position 2 in the hand to deck1 */
```


#### PlayArea

The class PlayArea has a method move which can be used to move a specific card from a playArea to another location.
```
playArea.move(4, hand1);

/* moves the card from position 4 in the playArea to hand1 */
```

#### Player
A player is constructed with a name, and a hand and a nextPlayer is created for the player.

The method findNextPlayer can be used to find the player after specific number of turns.
```
player1.findNextPlayer(3);

/* Finds the player who is three places after player1 */
```


#### ActionMenu
An action menu is integrated which allows for different methods.

An action can be allowed for a certain cardType. 
In the following example, the action changeColor is allowed in four variations for the cardType "plusfour".

The method displayAllowedActions can be used to print all allowed actions related to a cardType.
```
action changeColor(Card card, string color) -> void : "change to " + color {
    card.color = color;
}

cardType(ID = "plusfour"
    : onPlayed(Card card) -> void {
        ...
        allowAction(changeColor(card, "red"));
        allowAction(changeColor(card, "yellow"));
        allowAction(changeColor(card, "green"));
        allowAction(changeColor(card, "blue"));
        displayAllowedActions();
        }
)
```
The method disallowAllActions can be used to clear all actions related to a cardType.


#### GameState

Decks, hands and playAreas are added to the gameState when they are created.
showGameState can be called which prints the state of all decks, hands and playAreas associated to a player.

```
showGameState(player1);
```

