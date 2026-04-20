package entity;
public class Popcorn {
    
    private String popcornFlavor;
    private char popcornSize;
    private double popcornPrice;

    public Popcorn(String popcornFlavor, char popcornSize, double popcornPrice) {
        this.popcornFlavor = popcornFlavor;;
        this.popcornSize = popcornSize;
        this.popcornPrice = popcornPrice;
    }
    public Popcorn(String popcornFlavor, double popcornPrice) {
        this.popcornFlavor = popcornFlavor;;
        this.popcornSize = 'R';
        this.popcornPrice = popcornPrice;
    }

    public String getPopcornFlavor() {
        return popcornFlavor;
    }

    public char getPopcornSize() {
        return popcornSize;
    }

    public double getPopcornPrice() {
        return popcornPrice;
    }

    public void setPopcornFlavor(String popcornFlavor) {
        this.popcornFlavor = popcornFlavor;
    }

    public void setPopcornSize(char popcornSize) {
        this.popcornSize = popcornSize;
    }
    
    public void setPopcornPrice(double popcornPrice) {
        this.popcornPrice = popcornPrice;
    }
}