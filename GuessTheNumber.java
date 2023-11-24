private static int generateRandomNumber(int lowerBound, int upperBound) {
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    private static void provideHint(int chosenNumber, int level) {
        Random random = new Random();
        int hintType = random.nextInt(3); // Randomly select a hint type

        switch (level) {
            case 1:
                if (chosenNumber % 2 == 0) {
                    System.out.println("Hint: The number is even.");
                } else {
                    System.out.println("Hint: The number is odd.");
                }
                break;
            case 2:
                if (hintType == 0) {
                    if (chosenNumber % 5 == 0) {
                        System.out.println("Hint: The number is a multiple of 5.");
                    } else {
                        System.out.println("Hint: The number is not a multiple of 5.");
                    }
                } else if (hintType == 1) {
                    if (chosenNumber > 25) {
                        System.out.println("Hint: The number is greater than 25.");
                    } else {
                        System.out.println("Hint: The number is not greater than 25.");
                    }
                } else {
                    if (isPrime(chosenNumber)) {
                        System.out.println("Hint: The number is a prime number.");
                    } else {
                        System.out.println("Hint: The number is not a prime number.");
                    }
                }
                break;
            case 3:
                if (hintType == 0) {
                    int sqrt = (int) Math.sqrt(chosenNumber);
                    if (sqrt * sqrt == chosenNumber) {
                        System.out.println("Hint: The number is a perfect square.");
                    } else {
                        System.out.println("Hint: The number is not a perfect square.");
                    }
                } else if (hintType == 1) {
                    if (chosenNumber % 3 == 0) {
                        System.out.println("Hint: The number is a multiple of 3.");
                    } else {
                        System.out.println("Hint: The number is not a multiple of 3.");
                    }
                } else {
                    if (chosenNumber >= 10 && chosenNumber <= 20) {
                        System.out.println("Hint: The number is between 10 and 20.");
                    } else {
                        System.out.println("Hint: The number is not between 10 and 20.");
                    }
                }
                break;
            default:
                System.out.println("No more hints for this level.");
                break;
        }
    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static int playLevel(int lowerBound, int upperBound, int maxAttempts, int hints, String prize, Scanner scanner, int level) {
        int chosenNumber = generateRandomNumber(lowerBound, upperBound);
        int attempts = 0;
        int score = 100;

        System.out.println("Welcome to Level " + prize + "!");
        System.out.println("I have selected a number between " + lowerBound + " and " + upperBound + ". Guess it!");

        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();

            attempts++;

            if (userGuess == chosenNumber) {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                System.out.println("You won the " + prize + " prize!");
                System.out.println();
                return 1;
            } else if (userGuess < chosenNumber) {
                System.out.println("Too low! Try a higher number.");
            } else {
                System.out.println("Too high! Try a lower number.");
            }
            System.out.println();
            if (attempts < maxAttempts - 1 && hints > 0) {
                provideHint(chosenNumber, level);
                hints--;
                System.out.println("Remaining hints: " + hints);
            }

            score -= 20;
        }

        if (attempts == maxAttempts) {
            System.out.println("Sorry, you've run out of attempts!");
            System.out.println("The number was: " + chosenNumber);
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int level = 1;
        while (level <= 3) {
            int lowerBound = 1;
            int upperBound = 100; // Default upper bound

            // Adjust upper bound based on the level
            if (level == 2) {
                upperBound = 50;
            } else if (level == 3) {
                upperBound = 30;
            }

            int maxAttempts = 0;
            int hints = 0;
            String prize = "";

            if (level == 1) {
                maxAttempts = 10;
                hints = 1;
                prize = "Bronze";
            } else if (level == 2) {
                maxAttempts = 7;
                hints = 2;
                prize = "Silver";
            } else {
                maxAttempts = 5;
                hints = 3;
                prize = "Gold";
            }

            int playNext = playLevel(lowerBound, upperBound, maxAttempts, hints, prize, scanner, level); // Pass scanner to the method
            if (playNext == 0) {
                break;
            }

            level++; // Move to the next level
        }

        scanner.close(); // Close the scanner after finishing all levels
    }
