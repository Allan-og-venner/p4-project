/* Define the function to determine if the chosen path is safe */
func isSafePath(int adventureID) -> bool {
    int sum = 0;
    loop while (adventureID > 0) {
        sum = sum + (adventureID % 10);
        adventureID = adventureID / 10;
    }
    if (sum < 10) {
        return true;
    }
    return false;
}

/* Example use of the function with an adventureID */
int adventureID = 123; /* The player's adventure ID */
bool safePath = isSafePath(adventureID);

/* Display whether the path is safe or not */
if (safePath) {
    print("The path is safe. You may proceed.");
} 
if (!safePath) {
    print("Beware! The path leads to an endless maze.");
}