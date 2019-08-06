package main.java.com.himani.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import main.java.com.himani.messageprocessor.exceptions.EventHandlerException;
import main.java.com.himani.messageprocessor.model.Adjustment;
import main.java.com.himani.messageprocessor.model.Event;
import main.java.com.himani.messageprocessor.model.Sale;
import main.java.com.himani.messageprocessor.messagestore.MessageStore;
import main.java.com.himani.messageprocessor.model.Event;
import main.java.com.himani.messageprocessor.model.enums.AdjustmentEnumType;
import main.java.com.himani.messageprocessor.model.enums.MessageEnumType;
import main.java.com.himani.messageprocessor.service.impl.AdjustmentEventHandler;
import main.java.com.himani.messageprocessor.service.impl.MultiSaleEventHandler;

import org.junit.Before;
import org.junit.Test;

public class TestEventHandler {
	
	private AdjustmentEventHandler adjustmentEventHandler;
	
	private List<Sale> sales;
	
	private Sale sale1= new Sale("Apple",BigDecimal.valueOf(.40),1);
	
	private Sale sale2= new Sale("Orange",BigDecimal.valueOf(.55),1);
	
	private Sale sale3 = new Sale("Melon",BigDecimal.valueOf(.50),2);
	
	@Before
	public void setup(){
	sales = new ArrayList<>();
	sales.add(sale1);
	sales.add(sale2);
	sales.add(sale3);
	
	adjustmentEventHandler=new AdjustmentEventHandler();
	
	
	}

	@Test
	public void testHandleEvent() throws EventHandlerException{
		
		
		Event event = new Event(MessageEnumType.ADJUSTMENT,new Adjustment(AdjustmentEnumType.ADD,"Apple",BigDecimal.valueOf(0.30)));
		
		adjustmentEventHandler.handleEvent(event);
		
		for(Sale sale :  MessageStore.salesHistory){
			
			if(sale.getProductName().equals("Apple")){
				assertEquals(BigDecimal.valueOf(0.70),sale.getUnitPrice());
			}
			else if (sale.getProductName().equals("Orange")){
				assertEquals(BigDecimal.valueOf(0.55),sale.getUnitPrice());
			}
			
		}
		
	}
	
	 @Test
	    public void handleEvent_subtract() throws Exception {
	        Event event = new Event(MessageEnumType.ADJUSTMENT, new Adjustment(AdjustmentEnumType.SUBTRACT,
	                "Apple", BigDecimal.valueOf(0.30)));

	        adjustmentEventHandler.handleEvent(event);
	        for (Sale sale : MessageStore.salesHistory) {
	            if (sale.getProductName().equals("Apple")) {
	                assertEquals(BigDecimal.valueOf(0.10), sale.getUnitPrice());
	            } else if (sale.getProductName().equals("Orange")) {
	                assertEquals(BigDecimal.valueOf(0.55), sale.getUnitPrice());
	            }
	        }
	    }
	
	@Test(expected = EventHandlerException.class)
    public void handleEvent_incorrect_body_type() throws Exception {
        Event event = new Event(MessageEnumType.MULTI_SALE, sale1);
        adjustmentEventHandler.handleEvent(event);
    }


}
