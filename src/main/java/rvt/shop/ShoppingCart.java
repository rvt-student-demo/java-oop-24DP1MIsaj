package rvt.shop;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private Map<String, Item> items;

    public ShoppingCart() {
        this.items = new HashMap<>();
    }

    public void add(String product, int price) {
        items.compute(product, (key, item) -> {
            if (item == null) {
                return new Item(product, 1, price);
            }
            item.increaseQuantity();
            return item;
        });
    }

    public int price() {
        return items.values()
                .stream()
                .mapToInt(Item::price)
                .sum();
    }

    public void print() {
        items.values().forEach(System.out::println);
    }
}