package entity;
public class Drink {
    private String drinkName;
    private char drinkSize;
    private double drinkPrice;

    public Drink(String drinkName, char drinkSize, double drinkPrice) {
        this.drinkName = drinkName;
        this.drinkSize = drinkSize;
        this.drinkPrice = drinkPrice;
    }
    public Drink(String drinkName, double drinkPrice) {
        this.drinkName = drinkName;
        this.drinkSize = 'R';
        this.drinkPrice = drinkPrice;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public char getDrinkSize() {
        return drinkSize;
    }

    public double getDrinkPrice() {
        return drinkPrice;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public void setDrinkSize(char drinkSize) {
        this.drinkSize = drinkSize;
    }

    public void setDrinkPrice(double drinkPrice) {
        this.drinkPrice = drinkPrice;
    }
}