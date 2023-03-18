package org.example.model;

import java.util.Objects;

public final class Message {
    private final String data;

    public Message(final String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(this.data, ((Message) o).data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.data);
    }

    public String toString() {
        return this.getClass().getName() + "[ data:" + this.data + "]";
    }
}
