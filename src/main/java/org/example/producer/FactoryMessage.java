package org.example.producer;

import org.example.model.Message;

import static java.lang.String.format;

public final class FactoryMessage{
    private static final int INITIAL_NEXT_MESSAGE_INDEX = 1;
    private static final String TEMPLATE_CREATED_MESSAGE_DATA = "Message#%d";

    private int nextMessageIndex;

    public FactoryMessage(){
        this.nextMessageIndex =INITIAL_NEXT_MESSAGE_INDEX;
    }

    public Message create() {
        return  new Message(format(TEMPLATE_CREATED_MESSAGE_DATA, this. findAndIncrementIndex()));
    }

    private synchronized int findAndIncrementIndex(){
        return this.nextMessageIndex++;
    }

}