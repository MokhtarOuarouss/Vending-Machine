package fr.norsys.javaapp;

import java.util.HashMap;
import java.util.Map;

public class tetss {


    Map<Coin, Integer> coins = new HashMap<Coin, Integer>();
    Map<Product, Integer> products = new HashMap<Product, Integer>();


    public tetss(Map<Coin, Integer> coins, Map<Product, Integer> products) {

        this.coins = coins;
        this.products = products;

    }

    public boolean isProductAvailable(Product product) {
        return products.get(product) > 0;
    }


    public int getTotalCoinValue(Coin[] coinsArray) {
        int totalCoinValue = 0;
        for (Coin coin : coinsArray) {
            totalCoinValue += coin.getValue();
        }
        return totalCoinValue;
    }

    public boolean hasEnoughCoins(Product product, Coin[] coinsArray) {
        int totalCoinValue = getTotalCoinValue(coinsArray);
        return totalCoinValue >= product.getPrice();
    }


    public String buyProduct(Coin[] coinsArray, Product product) {
        if (isProductAvailable(product) && hasEnoughCoins(product, coinsArray)) {
            int totalCoinValue = getTotalCoinValue(coinsArray);

            if (totalCoinValue >= product.getPrice()) {
                // Update product and coin quantities
                products.put(product, products.get(product) - 1);

                for (Coin coin : coinsArray) {
                    coins.put(coin, coins.get(coin) + 1);
                }

                System.out.println(product + " : " + products.get(product));
                System.out.println("Coins:");
                for (Coin coin : coinsArray) {
                    System.out.println(coin + " : " + coins.get(coin));
                }

                return "Success";
            } else {
                return "Coins not enough";
            }
        } else {
            return "Product not available or insufficient coins.";
        }
    }








}
