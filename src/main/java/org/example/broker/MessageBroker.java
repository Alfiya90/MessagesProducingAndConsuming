package org.example.broker;

import org.example.consumer.MessageConsumerTask;
import org.example.model.Message;
import org.example.producer.MessageProducerTask;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

public class MessageBroker {
    private  static final String MASSAGE_OF_MESSAGE_IS_PRODUCED = "Message '%s' is produced. '%s'\n";
    private final String MESSAGE_CONSUMED = "Message '%s' is consumed. '%s'\n";
    private  final Queue<Message> messages;
    private  final int MAX_MESSAGES_IN_QUEUE;



    public MessageBroker(final int maxMessagesInQueue ) {
        this.messages = new ArrayDeque<>(maxMessagesInQueue);
        this.MAX_MESSAGES_IN_QUEUE = maxMessagesInQueue;
    }
    public synchronized void addMessageInQueue(final Message message, MessageProducerTask messageProducerTask)  {
        try{
            while (!this.isShouldBeProduce(messageProducerTask)) {
                super.wait();
            }
            this.messages.add(message);
            System.out.printf(MASSAGE_OF_MESSAGE_IS_PRODUCED, message, messageProducerTask.getName());
            super.notify();
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        }

    }

    public synchronized Message consumeMessage(MessageConsumerTask messageConsumerTask) {
        try{
            while(!this.isShouldBeConsume(messageConsumerTask)) {
                System.out.println(Thread.currentThread().getName() + "очередь пуста");
                super.wait();
            }

                final Message consumedMessage =  this.messages.poll();
                super.notify();
                System.out.printf(MESSAGE_CONSUMED, consumedMessage, messageConsumerTask.getName());
                /* return Optional.of(consumedMessage);*/
                return consumedMessage;

        } catch (final InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(interruptedException);
          /*  return Optional.empty();*/
        }
    }


    private boolean isShouldBeConsume(MessageConsumerTask messageConsumerTask) {
        return messages.size() >= messageConsumerTask.getMinMessagesToConsume() && !this.messages.isEmpty();
    }

     private  boolean isShouldBeProduce( MessageProducerTask messageProducerTask) {
        return messages.size() <= messageProducerTask.getMaxMessagesToProduce() && messages.size() <= MAX_MESSAGES_IN_QUEUE;
     }
}
