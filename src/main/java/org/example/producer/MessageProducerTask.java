package org.example.producer;

import org.example.broker.MessageBroker;
import org.example.model.Message;


public class MessageProducerTask implements Runnable {

    private final MessageBroker messageBroker;
    private final FactoryMessage factoryMessage;
    private final int maxMessagesToProduce;
    private final String name;

    public MessageProducerTask(MessageBroker messageBroker, FactoryMessage factoryMessage, final int maxMessagesToProduce, String name) {
        this.messageBroker = messageBroker;
        this.factoryMessage = factoryMessage;
        this.maxMessagesToProduce = maxMessagesToProduce;
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public int getMaxMessagesToProduce() {
        return this.maxMessagesToProduce;
    }

    @Override
    public void run() {
            try{
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(1000);
                    final Message producedMessage = this.factoryMessage.create();
                    this.messageBroker.addMessageInQueue(producedMessage, this);
                }

            } catch (final InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
            }

    }








}
