package main.java.com.himani.messageprocessor.service;

import main.java.com.himani.messageprocessor.exceptions.EventHandlerException;
import main.java.com.himani.messageprocessor.model.Event;


public interface EventHandler {
	 void handleEvent(Event event) throws EventHandlerException;
}
