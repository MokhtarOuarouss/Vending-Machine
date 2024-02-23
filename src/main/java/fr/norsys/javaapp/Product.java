package fr.norsys.javaapp;

public enum Product {
    WATER(5), COCA(7), TWIX(10), BUENO(12);

    private int price;
    private Product(int price){
        this.price=price;
    }

    public int getPrice(){
        return this.price;
    }
}
