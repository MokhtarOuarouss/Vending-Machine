package fr.norsys.javaapp;

public enum Coin {
    COIN_10(10), COIN_5(5), COIN_2(2), COIN_1(1);

    private int value;
    
    private Coin(int value){
        this.value=value;
    }

    public int getValue() {
        return value;
    }
}
