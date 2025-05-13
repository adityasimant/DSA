package LLD;

import java.util.*;

// Class to represent the Board
class Board {
    private int size;
    private Map<Integer, Integer> snakes;    // Key: head position, Value: tail position
    private Map<Integer, Integer> ladders;   // Key: bottom position, Value: top position

    public Board(int size) {
        this.size = size;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
    }

    public void addSnake(int head, int tail) {
        if (head <= tail) {
            throw new IllegalArgumentException("Snake head must be at a higher position than tail");
        }
        snakes.put(head, tail);
    }

    public void addLadder(int bottom, int top) {
        if (bottom >= top) {
            throw new IllegalArgumentException("Ladder top must be at a higher position than bottom");
        }
        ladders.put(bottom, top);
    }

    public int getSize() {
        return size;
    }

    public int getNextPosition(int position) {
        // Check if the position has a snake
        if (snakes.containsKey(position)) {
            System.out.println("Oops! Snake bite. Going down from " + position + " to " + snakes.get(position));
            return snakes.get(position);
        }

        // Check if the position has a ladder
        if (ladders.containsKey(position)) {
            System.out.println("Yay! Climbing ladder from " + position + " to " + ladders.get(position));
            return ladders.get(position);
        }

        // No snake or ladder, return the same position
        return position;
    }
}

// Class to represent a Player
class Player {
    private String name;
    private int position;

    public Player(String name) {
        this.name = name;
        this.position = 0; // Start position
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

// Class to handle dice rolling
class DiceRoller {
    private int numberOfDice;
    private Random random;

    public DiceRoller(int numberOfDice) {
        if (numberOfDice <= 0) {
            throw new IllegalArgumentException("Number of dice must be positive");
        }
        this.numberOfDice = numberOfDice;
        this.random = new Random();
    }

    public int roll() {
        int total = 0;
        for (int i = 0; i < numberOfDice; i++) {
            total += random.nextInt(6) + 1; // Each dice can roll 1-6
        }
        return total;
    }
}

// Main Game class to coordinate the game
class SnakeAndLadderGame {
    private Board board;
    private List<Player> players;
    private DiceRoller diceRoller;
    private boolean gameEnded;

    public SnakeAndLadderGame(int boardSize, int numberOfDice, List<String> playerNames) {
        this.board = new Board(boardSize);
        this.diceRoller = new DiceRoller(numberOfDice);
        this.players = new ArrayList<>();
        this.gameEnded = false;

        // Initialize players
        for (String name : playerNames) {
            players.add(new Player(name));
        }
    }

    public void configureSnakesAndLadders(Map<Integer, Integer> snakes, Map<Integer, Integer> ladders) {
        // Configure snakes
        for (Map.Entry<Integer, Integer> entry : snakes.entrySet()) {
            board.addSnake(entry.getKey(), entry.getValue());
        }

        // Configure ladders
        for (Map.Entry<Integer, Integer> entry : ladders.entrySet()) {
            board.addLadder(entry.getKey(), entry.getValue());
        }
    }

    public void startGame() {
        System.out.println("Game started with board size: " + board.getSize());
        System.out.println("Number of players: " + players.size());

        int currentPlayerIndex = 0;

        while (!gameEnded) {
            Player currentPlayer = players.get(currentPlayerIndex);

            // Roll the dice
            int diceValue = diceRoller.roll();
            System.out.println(currentPlayer.getName() + " rolled: " + diceValue);

            // Calculate new position
            int newPosition = currentPlayer.getPosition() + diceValue;

            // Check if the player won
            if (newPosition >= board.getSize()) {
                System.out.println(currentPlayer.getName() + " won the game!");
                gameEnded = true;
                break;
            }

            // Move the player
            currentPlayer.setPosition(board.getNextPosition(newPosition));
            System.out.println(currentPlayer.getName() + " moved to position: " + currentPlayer.getPosition());

            // Move to next player
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }
}

// Main class to demonstrate usage
public class SnakeAndLadder {
    public static void main(String[] args) {
        // Example configuration
        int boardSize = 100;  // 10x10 board
        int numberOfDice = 1;
        List<String> playerNames = Arrays.asList("Player1", "Player2", "Player3");

        // Create the game
        SnakeAndLadderGame game = new SnakeAndLadderGame(boardSize, numberOfDice, playerNames);

        // Configure snakes and ladders
        Map<Integer, Integer> snakes = new HashMap<>();
        snakes.put(62, 19); // Snake from 62 to 19
        snakes.put(95, 24); // Snake from 95 to 24
        snakes.put(98, 78); // Snake from 98 to 78

        Map<Integer, Integer> ladders = new HashMap<>();
        ladders.put(2, 38);  // Ladder from 2 to 38
        ladders.put(7, 14);  // Ladder from 7 to 14
        ladders.put(42, 84); // Ladder from 42 to 84

        game.configureSnakesAndLadders(snakes, ladders);

        // Start the game
        game.startGame();
    }
}