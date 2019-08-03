package main.java.com.himani.messageprocessor;

import main.java.com.himani.messageprocessor.messagestore.MessageStore;
import main.java.com.himani.messageprocessor.service.MessageProcessor;
import main.java.com.himani.messageprocessor.service.ReportGenerator;
import main.java.com.himani.messageprocessor.service.impl.SalesMessageProcessor;
import main.java.com.himani.messageprocessor.service.impl.ReportGeneratorImpl;


public class Application {

	    public static void main(String[] args) {
	        ReportGenerator reportGenerator = new ReportGeneratorImpl();
	        SalesMessageProcessor messageProcessor = new SalesMessageProcessor(reportGenerator);

	        Stimulator simulator = new Stimulator();
	        MessageStore.messageQueue.addAll(simulator.generateEvents());
	        messageProcessor.startProcessing();
	   
	}

	
}
