package model.game.elements;

import model.game.elements.bombs.Bomb;

import java.util.ArrayList;

public class Boomberman extends Element {
    private int health;
    private int maxBombs;
    private ArrayList<Bomb> bombs;
    private int score;
    private static char symbol = 'B';

    // constructor
    public Boomberman(int x, int y, int health, int maxBombs) {
        super(x, y);
        this.health = health;
        this.maxBombs = maxBombs;
        this.bombs = new ArrayList<>();
        this.score = 0;
    }

    public static void setSymbol(char s) {
        symbol = s;
    }
    public char getSymbol() {
        return symbol;
    }

    public String getColor() {
        return "#00E5FF";
    }

    // health functions
    public void decreaseHealth() {
        this.health--;
    }

    public void increasehealth() {
        this.health++;
    }

    public int getHealth() { return health; }
    // end of health functions

    // score functions

    public void increaseScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    // end of score functions

    // bomb functions
    public ArrayList<Bomb> getBombs() {
        return this.bombs;
    }

    // returns the bomb in the first position of the array
    public Bomb getNextBomb() {
        return this.bombs.get(0);
    }

    // removes the first bomb in the list
    public void popBomb() {
        this.bombs.remove(0);
    }

    // inserts bomb at end of the array if there is space
    public void insertBomb(Bomb bomb) {
        if (this.bombs.size() < this.maxBombs) {
            this.bombs.add(bomb);
        }
    }

    public boolean canAddBomb() {
        return this.bombs.size() < this.maxBombs;
    }

    public int getMaxBombs() {
        return this.maxBombs;
    }

    public void setMaxBombs(int maxBombs) {
        this.maxBombs = maxBombs;
    }

    public void setBombs(ArrayList<Bomb> bombs) {
        this.bombs = bombs;
    }
    // end of bomb functions
}