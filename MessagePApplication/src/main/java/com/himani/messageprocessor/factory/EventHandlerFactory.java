package main.java.com.himani.messageprocessor.factory;

import main.java.com.himani.messageprocessor.model.enums.MessageEnumType;
import main.java.com.himani.messageprocessor.service.impl.SingleSaleEventHandler;
import main.java.com.himani.messageprocessor.service.impl.MultiSaleEventHandler;
import main.java.com.himani.messageprocessor.service.impl.AdjustmentEventHandler;

import java.beans.EventHandler;
import java.util.HashMap;
import java.util.Map;

public class EventHandlerFactory {
	private static Map<MessageEnumType, EventHandler> instanceStore;

    static {
        initSingletonStore();
    }

    public static EventHandler getHandler(MessageEnumType type) {
        final EventHandler handler = instanceStore.get(type);

        if (handler == null) {
            System.err.println("Unrecognized event type " + type + ". Ignoring the event");
        }

        return handler;
    }

    // To prevent creation of a number of unused objects
    private static void initSingletonStore() {
        HashMap<MessageEnumType,Object> instanceStore = new HashMap<>();
        instanceStore.put(MessageEnumType.SINGLE_SALE, new SingleSaleEventHandler());
        instanceStore.put(MessageEnumType.MULTI_SALE, new MultiSaleEventHandler());
        instanceStore.put(MessageEnumType.ADJUSTMENT, new AdjustmentEventHandler());
    }
}
