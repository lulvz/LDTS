package model;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position getLeft() {
        return new Position(x - 1, y);
    }

    public Position getRight() {
        return new Position(x + 1, y);
    }

    public Position getDown() {
        return new Position(x, y + 1);
    }

    public Position getUp() {
        return new Position(x, y - 1);
    }

    public Position getRandomNeighbour() {
        int n = (int) (Math.random() * 4);
        switch (n) {
            case 0:
                return getUp();
            case 1:
                return getRight();
            case 2:
                return getDown();
            default:
                return getLeft();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        // if the object is the same return true
        if (this == o) return true;
        // if the object is null of the class is different from this one, return false
        if (o == null || getClass() != o.getClass()) return false;

        // if the object passed is of type Position too, then compare x and y
        Position position = (Position) o;
        return (position.x == x && position.y == y);
    }

    public int distance(Position position) {
        return Math.abs(position.x - x) + Math.abs(position.y - y);
    }

    public Position getNextPosition(int dx, int dy) {
        return new Position(getX() + dx, getY() + dy);
    }
}
