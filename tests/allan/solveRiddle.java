/* Determine the correct response to the sphinx's riddle */
func solveRiddle(int number) -> string {
    if (number % 3 == 0 && number % 5 == 0) {
        return "FizzBuzz";
    } 
    if (number % 3 == 0) {
        return "Fizz";
    } 
    if (number % 5 == 0) {
        return "Buzz";
    }
    return "I don't know.";
}

/* Solve the riddle for the number 15 */
string response = solveRiddle(15);

/* Display the response */
print("You respond to the sphinx: " + response);