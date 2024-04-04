/* Initialize a variable to keep track of the current tile */
int currentTile = 1;

/* Use a loop to find the key under the 100th tile */
loop while (currentTile <= 100) {
    if (currentTile == 100) {
        /* Key is found under the 100th tile */
        print("You found the key under tile " + currentTile + "!");
        break;
    }
    currentTile = currentTile + 1;
}