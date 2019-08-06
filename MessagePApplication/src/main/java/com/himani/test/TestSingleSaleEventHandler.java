package main.java.com.himani.test;


import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import main.java.com.himani.messageprocessor.exceptions.EventHandlerException;
import main.java.com.himani.messageprocessor.messagestore.MessageStore;
import main.java.com.himani.messageprocessor.model.Adjustment;
import main.java.com.himani.messageprocessor.model.Event;
import main.java.com.himani.messageprocessor.model.Sale;
import main.java.com.himani.messageprocessor.model.enums.AdjustmentEnumType;
import main.java.com.himani.messageprocessor.model.enums.MessageEnumType;
import main.java.com.himani.messageprocessor.service.impl.SingleSaleEventHandler;

import org.junit.Before;
import org.junit.Test;

  public class TestSingleSaleEventHandler {
    private SingleSaleEventHandler singleSaleEventHandler;
    private List<Sale> sales;
    private Sale sale1 = new Sale("Apple", BigDecimal.valueOf(0.50), 1);
    private Sale sale2 = new Sale("Orange", BigDecimal.valueOf(0.40), 2);

    @Before
    public void setUp() {
        singleSaleEventHandler = new SingleSaleEventHandler();
        sales = new ArrayList<>();
        MessageStore.salesHistory = sales;
    }

    @Test
    public void handleEvent_ok() throws Exception {
        Event event = new Event(MessageEnumType.MULTI_SALE, sale1);
        singleSaleEventHandler.handleEvent(event);
        assertEquals(sale1, MessageStore.salesHistory.get(0));
    }

    @Test(expected = EventHandlerException.class)
    public void handleEvent_unit_size_3_should_throw_exception() throws Exception {
        Event event = new Event(MessageEnumType.MULTI_SALE, sale2);
        singleSaleEventHandler.handleEvent(event);
    }

    @Test(expected = EventHandlerException.class)
    public void handleEvent_incorrect_body_type() throws Exception {
        Event event = new Event(MessageEnumType.MULTI_SALE, new Adjustment(AdjustmentEnumType.MULTIPLY,
                "Apple", BigDecimal.valueOf(2)));
        singleSaleEventHandler.handleEvent(event);
    }



}
