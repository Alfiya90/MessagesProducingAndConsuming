package org.example.consumer;

import org.example.broker.MessageBroker;
import org.example.model.Message;

import java.util.Optional;

public class MessageConsumerTask implements Runnable {

    private final MessageBroker messageBroker;
    private final int minMessagesToConsume;
    private final String name;


    public MessageConsumerTask(final MessageBroker messageBroker,final int minMessagesToConsume, String name ){
        this.messageBroker = messageBroker;
        this.minMessagesToConsume = minMessagesToConsume;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getMinMessagesToConsume(){
        return  this.minMessagesToConsume;
    }


    @Override
    public void run() {
        try{
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(1000);
              /*  final Optional<Message> optionalConsumedMessage = this.messageBroker.consumeMessage();*/
                Message optionalConsumedMessage = this.messageBroker.consumeMessage(this);
                /*optionalConsumedMessage.orElseThrow(MessageConsumeException::new);*/
            }
        } catch (final InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        }

    }
}
