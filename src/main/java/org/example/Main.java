package org.example;

import org.example.broker.MessageBroker;
import org.example.consumer.MessageConsumerTask;
import org.example.producer.FactoryMessage;
import org.example.producer.MessageProducerTask;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        final int maxMessagesInQueue = 15;
        final MessageBroker messageBroker = new MessageBroker(maxMessagesInQueue);
        FactoryMessage factoryMessage = new FactoryMessage();

        final Thread firstProducingThread = new Thread(new MessageProducerTask(messageBroker, factoryMessage,15,"PRODUCER_1"));
        final Thread secondProducingThread =new Thread(new MessageProducerTask(messageBroker, factoryMessage, 11, "PRODUCER_2"));
        final Thread thirdProducingThread = new Thread(new MessageProducerTask(messageBroker, factoryMessage, 6, "PRODUCER_3"));

        final  Thread firstConsumingThread = new Thread(new MessageConsumerTask(messageBroker, 0, "CONSUMER_1"));
        final  Thread secondConsumingThread = new Thread(new MessageConsumerTask(messageBroker, 6, "CONSUMER_2"));
        final  Thread  thirdConsumingThread = new Thread(new MessageConsumerTask(messageBroker, 11, "CONSUMER_3"));

        startThreads(firstProducingThread, secondProducingThread, thirdProducingThread,
                        firstConsumingThread, secondConsumingThread, thirdConsumingThread);
    }
    private static void  startThreads(final Thread... threads) {
        Arrays.stream(threads).forEach(Thread::start);
    }
}