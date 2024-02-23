package fr.norsys.testapp;


import java.util.HashMap;
import java.util.Map;

import fr.norsys.javaapp.Coin;
import fr.norsys.javaapp.Product;
import fr.norsys.javaapp.VendingMachine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest{


    Map<Coin, Integer> coins = new HashMap<>();
    Map<Product, Integer> products = new HashMap<>();
    VendingMachine vendingMachine ;


    MainTest(){
        coins.put(Coin.COIN_1,20);
        coins.put(Coin.COIN_2,20);
        coins.put(Coin.COIN_5,20);
        coins.put(Coin.COIN_10,20);

        products.put(Product.WATER,20);
        products.put(Product.COCA,20);
        products.put(Product.TWIX,20);
        products.put(Product.BUENO,20);
        vendingMachine = new VendingMachine(coins,products);

    }



    @Test
    public void ProductExisting(){
        vendingMachine = new VendingMachine(coins,products);
        Map<Coin, Integer> coinsEnter = new HashMap<>();
        coinsEnter.put(Coin.COIN_5,2);
        assertEquals(10,vendingMachine.getTotalCoinsValue(coinsEnter));
    }

    @Test
    @DisplayName("Decrement product quantity on successful operation")
    public void testDecrementProductQuantity() {
        vendingMachine = new VendingMachine(coins,products);
        Map<Coin, Integer> coinsEnter = new HashMap<>();
        coinsEnter.put(Coin.COIN_2,3);
        assertEquals("Success",vendingMachine.BuyProduct(coinsEnter,Product.WATER));
    }

    @Test
    @DisplayName(" Testing total coins values  ")
    public void Testing_total_coins_value(){
        vendingMachine = new VendingMachine(coins,products);
        Map<Coin, Integer> coinsEnter = new HashMap<>();
        coinsEnter.put(Coin.COIN_2,3);
        assertEquals(6,vendingMachine.getTotalCoinsValue(coinsEnter));
        assertEquals(360,vendingMachine.getTotalCoinsValue(coins));
    }



    @Test
    @DisplayName("Check if coins are enough for the operation")
    public void testIfCoinsEnough() {
        vendingMachine = new VendingMachine(coins,products);
        Map<Coin, Integer> coinsEnter = new HashMap<>();
        coinsEnter.put(Coin.COIN_10,1);
        coinsEnter.put(Coin.COIN_1,1);
        assertEquals("Success",vendingMachine.BuyProduct(coinsEnter,Product.WATER));
        assertThrows(RuntimeException.class, () -> vendingMachine.BuyProduct(coinsEnter, Product.BUENO));

    }

    @Test
    @DisplayName("Calculate change after purchase")
    public void testCalculateChange(){
        vendingMachine = new VendingMachine(coins,products);
        Map<Coin, Integer> coinsEnter = new HashMap<>();
        coinsEnter.put(Coin.COIN_10,2);
        assertEquals(8,vendingMachine.calculateChange(Product.BUENO,coinsEnter));
        assertEquals(13,vendingMachine.calculateChange(Product.COCA,coinsEnter));


    }

    @Test
    @DisplayName("Check if the machine has enough coins for a purchase")
    public void testIfMachineHasEnoughCoins(){
        vendingMachine = new VendingMachine(coins,products);
        Map<Coin, Integer> coinsEnter = new HashMap<>();
        coinsEnter.put(Coin.COIN_10,1);
        coinsEnter.put(Coin.COIN_5,1);
        assertEquals("Success",vendingMachine.BuyProduct(coinsEnter,Product.BUENO));


    }

    @Test
    @DisplayName("Check if the machine has the change")
    public void testIfMachineHasChange(){

        Map<Coin, Integer> coinsEnter = new HashMap<>();

        Map<Coin, Integer> MachineCoins = new HashMap<>();
        MachineCoins.put(Coin.COIN_1,2);
        MachineCoins.put(Coin.COIN_2,0);
        MachineCoins.put(Coin.COIN_5,0);
        MachineCoins.put(Coin.COIN_10,20);

        vendingMachine = new VendingMachine(MachineCoins,products);

        coinsEnter.put(Coin.COIN_10,2);
        assertThrows(RuntimeException.class, () -> vendingMachine.BuyProduct(coinsEnter, Product.BUENO));


    }



    @Test
    @DisplayName("Get optimal change after a operation")
    public void testOptimalChange(){
        vendingMachine = new VendingMachine(coins,products);
        Map<Coin, Integer> coinsEnter = new HashMap<>();
        Map<Coin, Integer> optimalChange = new HashMap<>();

        coinsEnter.put(Coin.COIN_10,2);

        optimalChange.put(Coin.COIN_5,1);
        optimalChange.put(Coin.COIN_2,1);
        optimalChange.put(Coin.COIN_1,1);

        assertEquals(optimalChange,vendingMachine.OptimalChange(coinsEnter,Product.BUENO,false));
        assertEquals(coinsEnter,vendingMachine.OptimalChange(coinsEnter,Product.BUENO,true));

    }

    @Test
    @DisplayName("Get back your coins after a canceling")
    public void testCancelOpertation(){
        vendingMachine = new VendingMachine(coins,products);
        Map<Coin, Integer> coinsEnter = new HashMap<>();
        Map<Coin, Integer> optimalChange = new HashMap<>();

        coinsEnter.put(Coin.COIN_10,2);

        optimalChange.put(Coin.COIN_5,1);
        optimalChange.put(Coin.COIN_2,1);
        optimalChange.put(Coin.COIN_1,1);

        assertEquals(coinsEnter,vendingMachine.OptimalChange(coinsEnter,Product.BUENO,true));

    }





}