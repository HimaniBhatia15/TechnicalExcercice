package main.java.com.himani.messageprocessor.messagestore;

import main.java.com.himani.messageprocessor.exceptions.*;
import main.java.com.himani.messageprocessor.model.Adjustment;
import main.java.com.himani.messageprocessor.model.Event;
import main.java.com.himani.messageprocessor.model.Sale;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MessageStore {

	 public static List<Sale> salesHistory = new ArrayList<>();
	    public static List<Adjustment> adjustmentsHistory = new ArrayList<>();
	    public static Queue<Event> messageQueue = new LinkedList<>();

	    public static Event nextEvent() throws EmptyMessageQueueException {
	        if (messageQueue.isEmpty()) {
	            throw new EmptyMessageQueueException("The event queue is null");
	        }
	        return messageQueue.poll();
	    }

	    public static boolean hasNextEvent() {
	        return !messageQueue.isEmpty();
	    }

	    public static int totalSales() {
	        return salesHistory.size();
	    }

	    public static void addSale(Sale sale) {
	        salesHistory.add(sale);
	    }

	    public static void addAdjustment(Adjustment adjustment) {
	        adjustmentsHistory.add(adjustment);
	    }
}
