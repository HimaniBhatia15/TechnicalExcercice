package main.java.com.himani.messageprocessor;

import java.math.BigDecimal;
import java.util.*;

import main.java.com.himani.messageprocessor.model.Adjustment;
import main.java.com.himani.messageprocessor.model.Event;
import main.java.com.himani.messageprocessor.model.Sale;
import main.java.com.himani.messageprocessor.model.enums.AdjustmentEnumType;
import main.java.com.himani.messageprocessor.model.enums.MessageEnumType;

public class Stimulator {
    Set<String> products = new HashSet<>(Arrays.asList("Apple", "Orange", "Banana", "Pear", "Mango"));
    Set<BigDecimal> adjustmentPrices = new HashSet<>(Arrays.asList(BigDecimal.valueOf(0.50),
            BigDecimal.valueOf(1.50), BigDecimal.valueOf(0.30)));
    Set<Integer> amounts = new HashSet<>(Arrays.asList(3,5,7));
    Set<AdjustmentEnumType> adjustmentTypes = new HashSet<>(Arrays.asList(AdjustmentEnumType.ADD, AdjustmentEnumType.MULTIPLY, AdjustmentEnumType.SUBTRACT));

    Map<String, BigDecimal> productPrices = new HashMap<String, BigDecimal>(){{
        put("Banana", BigDecimal.valueOf(0.20));
        put("Orange", BigDecimal.valueOf(0.40));
        put("Pear", BigDecimal.valueOf(0.45));
        put("Mango", BigDecimal.valueOf(1.00));}};


    public List<Event> generateEvents() {
        List<Event> bagOfEvents = new LinkedList<>();

        for (int i = 1; i < 60; i++) {
            if (i % 11 == 0) {
                bagOfEvents.add(new Event(MessageEnumType.ADJUSTMENT,
                        new Adjustment((AdjustmentEnumType) getRandom(adjustmentTypes),
                                (String) getRandom(products),
                                (BigDecimal) getRandom(adjustmentPrices))));
            }
            else if (i % 5 == 0) {
                String product = (String) getRandom(products);
                bagOfEvents.add(new Event(MessageEnumType.MULTI_SALE,
                        new Sale(product,
                                productPrices.get(product),
                                (Integer) getRandom(amounts))));
            } else {
                String product = (String) getRandom(products);
                bagOfEvents.add(new Event(MessageEnumType.SINGLE_SALE,
                        new Sale(product,
                                productPrices.get(product),
                                1)));
            }
        }

        return bagOfEvents;
    }

    private Object getRandom(Collection e) {
        return e.stream()
                .skip((int) (e.size() * Math.random()))
                .findFirst().get();
    }
}
