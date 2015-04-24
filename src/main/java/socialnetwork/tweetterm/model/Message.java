package socialnetwork.tweetterm.model;

import java.time.Instant;

public class Message {

    private String username;
    private String message;
    private Instant timestamp;

    public Message(String message, String username, Instant timestamp) {
        this.message = message;
        this.username = username;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getUser() {
        return username;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (!username.equals(message1.username)) return false;
        if (!message.equals(message1.message)) return false;
        return timestamp.equals(message1.timestamp);

    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + timestamp.hashCode();
        return result;
    }
}
