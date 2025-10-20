package greedy.fractionalKnapsack;

import java.util.Arrays;

class Item {
    int value, weight;

    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class FractionalKnapsack {

    static double fractionalKnapsack(int W, Item[] items) {
        Arrays.sort(items, (a, b) -> Double.compare((double) b.value / b.weight, (double) a.value / a.weight));

        double totalValue = 0.0;

        for (Item item : items) {
            if (W >= item.weight) {
                W -= item.weight;
                totalValue += item.value;
            } else {
                totalValue += (double) item.value * ((double) W / item.weight);
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Item[] items = {
                new Item(60, 10),
                new Item(100, 20),
                new Item(120, 30)
        };
        int capacity = 50;

        double maxValue = fractionalKnapsack(capacity, items);
        System.out.println("Maximum value in knapsack = " + maxValue);
        // Output: Maximum value in knapsack = 240.0
    }
}


// Complexity Analysis:
// Time Complexity: O(n log n) due to sorting the items based on value-to-weight ratio.
// Space Complexity: O(1) as we are using only a constant amount of extra space