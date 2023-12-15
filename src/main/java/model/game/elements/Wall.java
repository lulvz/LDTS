package model.game.elements;

public class Wall extends Element {
    private int hp;
    private Boolean isDestroyable;

    // custom hp constructor
    public Wall(int x, int y, int hp, Boolean isDestroyable) {
        super(x, y);
        this.hp = hp; // custom hp
        this.isDestroyable = isDestroyable; // check if wall is destroyable
    }

    public char getSymbol() {
        if (isDestroyable) {
            return '=';
        } else {
            return '#';
        }
    }

    public String getColor() {
        if(isDestroyable){
            return "#4D2C12";
        }
        return "#666666";
    }

    public Boolean isDestroyable() {
        return this.isDestroyable;
    }

}
