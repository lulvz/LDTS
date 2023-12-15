package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PositionTest {
    @Test
    void getLeft() {
        Position position = new Position(1, 1);
        assertEquals(0, position.getLeft().getX());
        assertEquals(1, position.getLeft().getY());
    }

    @Test
    void getRight() {
        Position position = new Position(1, 1);
        assertEquals(2, position.getRight().getX());
        assertEquals(1, position.getRight().getY());
    }

    @Test
    void getUp() {
        Position position = new Position(1, 1);
        assertEquals(1, position.getUp().getX());
        assertEquals(0, position.getUp().getY());
    }

    @Test
    void getDown() {
        Position position = new Position(1, 1);
        assertEquals(1, position.getDown().getX());
        assertEquals(2, position.getDown().getY());
    }
}
