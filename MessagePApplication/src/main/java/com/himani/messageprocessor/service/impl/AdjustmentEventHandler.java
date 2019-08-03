package main.java.com.himani.messageprocessor.service.impl;

import java.math.BigDecimal;

import main.java.com.himani.messageprocessor.exceptions.EventHandlerException;
import main.java.com.himani.messageprocessor.messagestore.MessageStore;
import main.java.com.himani.messageprocessor.model.Adjustment;
import main.java.com.himani.messageprocessor.model.Event;
import main.java.com.himani.messageprocessor.model.Sale;
import main.java.com.himani.messageprocessor.model.enums.AdjustmentEnumType;
import main.java.com.himani.messageprocessor.service.EventHandler;

public class AdjustmentEventHandler implements EventHandler {
    @Override
    public void handleEvent(Event event) throws EventHandlerException {
        if (!(event.getEventBody() instanceof Adjustment)) {
            throw new EventHandlerException("Event body for Adjustment event is not of type Adjustment");
        }

        Adjustment adjustment = (Adjustment) event.getEventBody();

        applyAdjustment(adjustment);

        MessageStore.addAdjustment(adjustment);
    }

    private void applyAdjustment(Adjustment adjustment) {
        for (Sale sale : MessageStore.salesHistory) {
            if (sale.getProductName().equals(adjustment.getProductName())) {

                if (adjustment.getType() == AdjustmentEnumType.ADD) {
                    sale.setUnitPrice(sale.getUnitPrice().add(adjustment.getAmount()));
                } else if (adjustment.getType() == AdjustmentEnumType.MULTIPLY) {
                    sale.setUnitPrice(sale.getUnitPrice().multiply(adjustment.getAmount()));
                } else if (adjustment.getType() == AdjustmentEnumType.SUBTRACT) {
                    BigDecimal newPrice = sale.getUnitPrice().subtract(adjustment.getAmount());
                    newPrice = newPrice.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : newPrice;
                    sale.setUnitPrice(newPrice);
                }
            }
        }
    }
}
