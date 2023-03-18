package org.example.consumer;

public class MessageConsumeException extends RuntimeException{
    public MessageConsumeException() {
    }
    public MessageConsumeException(final String description) {
        super(description);
    }

    public MessageConsumeException(final Exception cause) {
        super(cause);
    }

    public MessageConsumeException(final String description, final Exception cause){
        super(description, cause);
    }
}
