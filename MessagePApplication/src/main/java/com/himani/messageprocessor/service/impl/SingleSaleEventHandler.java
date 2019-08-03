package main.java.com.himani.messageprocessor.service.impl;

import main.java.com.himani.messageprocessor.exceptions.EventHandlerException;
import main.java.com.himani.messageprocessor.service.EventHandler;
import main.java.com.himani.messageprocessor.messagestore.MessageStore;
import main.java.com.himani.messageprocessor.model.Sale;
import main.java.com.himani.messageprocessor.model.Event;


public class SingleSaleEventHandler implements EventHandler {

    @Override
    public void handleEvent(Event event) throws EventHandlerException {
        if (!(event.getEventBody() instanceof Sale)) {
            throw new EventHandlerException("Event body for SingleSale event is not of type Sale");
        }

        Sale sale = (Sale) event.getEventBody();
        if (sale.getTotalUnits() != 1) {
            throw new EventHandlerException("SingleSale has incorrect totalUnits value=" + sale.getTotalUnits());
        }

        MessageStore.addSale(sale);
    }

}
