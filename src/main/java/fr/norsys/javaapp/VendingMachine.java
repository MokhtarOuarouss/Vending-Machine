package fr.norsys.javaapp;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {


    Map<Coin, Integer> coins = new HashMap<Coin, Integer>();
    Map<Product, Integer> products = new HashMap<Product, Integer>();

    
    public VendingMachine(Map<Coin, Integer> coins, Map<Product, Integer> products) {

        this.coins = coins;
        this.products = products;
        
    }

    public String BuyProduct(Map<Coin, Integer> coinsEnter, Product product) {
        return BuyProduct(coinsEnter, product, false);
    }


    public String BuyProduct(Map<Coin, Integer> coinsEnter , Product product,boolean cancel ){
        if (!cancel){

            if (isProductAvailable(product) && hasEnoughCoins(product,coinsEnter)) {
                products.put(product, products.get(product) - 1);

                System.out.println(product + " | price :" + product.getPrice());
                System.out.println( " sum Coins giving :" + getTotalCoinsValue(coinsEnter));

                Map<Coin, Integer> changeCoins = OptimalChange( coinsEnter,  product,cancel);

                for (Map.Entry<Coin, Integer> entrychangeCoins : changeCoins.entrySet()) {
                    System.out.println(entrychangeCoins.getKey() + " / " + entrychangeCoins.getValue());
                }

                return "Success";

            } else {
                throw new RuntimeException("coins not enough or product not available");
            }

        }

        else {
            return "Canceled Operation";
        }

        
    }

    public Map<Coin, Integer> OptimalChange(Map<Coin, Integer> coinsEnter, Product product,boolean cancel) {
        int totalCoinsValue = getTotalCoinsValue(coinsEnter);
        int productPrice = product.getPrice();
        Map<Coin, Integer> changeCoins = new HashMap<>();

        if(cancel){
            return coinsEnter ;
        }

        if (totalCoinsValue >= productPrice) {
            int change = totalCoinsValue - productPrice;

            for (Coin coin : Coin.values()) {
                int coinValue = coin.getValue();
                if (change >= coinValue) {

                    Integer coinsCount = coins.get(coin);  //coins count in machine
                    if (coinsCount != null) {
                        int coinsToReturn = change / coinValue;
                        coinsToReturn = Math.min(coinsToReturn, coinsCount);
                        if (coinsToReturn > 0) {
                            changeCoins.put(coin, coinsToReturn);

                            
                            change -= coinsToReturn * coinValue;
                            coins.put(coin,coinsCount - coinsToReturn);
                        }
                    }
                }
            }

            if (change == 0) {
                return changeCoins;
            }
            else throw new RuntimeException("machine has  Not enough coins to change");
        } 

        throw new RuntimeException("Not enough coins to make the purchase");

    }







    public boolean isProductAvailable(Product product) {
        return products.get(product) > 0;
    }


    public int getTotalCoinsValue(Map<Coin, Integer> coins) {
        return coins.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getValue() * entry.getValue())
                .sum();
    }

    

    public boolean hasEnoughCoins(Product product, Map<Coin, Integer> coins) {

        return getTotalCoinsValue(coins) >= product.getPrice();
    }

    public int calculateChange(Product product, Map<Coin, Integer> coins) {

        return getTotalCoinsValue(coins) - product.getPrice();
    }




    
}
