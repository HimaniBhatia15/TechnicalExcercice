package main.java.com.himani.messageprocessor.model;

import main.java.com.himani.messageprocessor.model.enums.MessageEnumType;


public class Event {

	private MessageEnumType messageType;
    private Object eventBody;

    public Event(MessageEnumType messageType, Object eventBody) {
        this.messageType = messageType;
        this.eventBody = eventBody;
    }

    public MessageEnumType getMessageType() {
        return messageType;
    }

    public Object getEventBody() {
        return eventBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (messageType != event.messageType) return false;
        return eventBody != null ? eventBody.equals(event.eventBody) : event.eventBody == null;
    }

    @Override
    public int hashCode() {
        int result = messageType != null ? messageType.hashCode() : 0;
        result = 46 * result + (eventBody != null ? eventBody.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "messageType=" + messageType +
                ", eventBody=" + eventBody +
                '}';
    }
}
