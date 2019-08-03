package main.java.com.himani.messageprocessor.service.impl;

import main.java.com.himani.messageprocessor.exceptions.EmptyMessageQueueException;
import main.java.com.himani.messageprocessor.exceptions.EventHandlerException;
import main.java.com.himani.messageprocessor.factory.EventHandlerFactory;
import main.java.com.himani.messageprocessor.messagestore.MessageStore;
import main.java.com.himani.messageprocessor.model.Event;
import main.java.com.himani.messageprocessor.service.EventHandler;
import main.java.com.himani.messageprocessor.service.MessageProcessor;
import main.java.com.himani.messageprocessor.service.ReportGenerator;

public class SalesMessageProcessor implements MessageProcessor {

	    private final ReportGenerator reportGenerator;

	    public SalesMessageProcessor(final ReportGenerator reportGenerator) {
	        this.reportGenerator = reportGenerator;
	    }

	    @Override
	    public void startProcessing() {
	        System.out.println("Message processor has started!");

	        int eventsProcessed = 0;
	        while (MessageStore.hasNextEvent()) {
	            Event event;
	            try {
	                event = MessageStore.nextEvent();
	            } catch (EmptyMessageQueueException e) {
	                System.err.println(e.getMessage());
	                return;
	            }

	            boolean success = processEvent(event);
	            if (!success) {
	                continue;
	            }
	            eventsProcessed++;

	            if (eventsProcessed % 10 == 0) {
	                String salesReport = reportGenerator.generateSalesReport(MessageStore.salesHistory);
	                System.out.println(salesReport);
	            }
	            if (eventsProcessed == 50) {
	                String adjustmentsReport = reportGenerator.generateAdjustmentsReport(MessageStore.adjustmentsHistory);
	                System.out.println(adjustmentsReport);
	                break;
	            }
	        }

	        System.out.println("Message processor has finished.");
	    }

	    private boolean processEvent(Event event) {
	    	 EventHandler eventHandler = (EventHandler) EventHandlerFactory.getHandler(event.getMessageType());
	        if (eventHandler == null) {
	            System.err.println("Error while getting EventHandler impl for type=" + event.getMessageType());
	            return false;
	        }

	        try {
	            eventHandler.handleEvent(event);
	        } catch (EventHandlerException e) {
	            System.err.println(e.getMessage());
	            return false;
	        }
	        return true;
	    }
	
}
