package socialnetwork.tweetterm.model.commands;

import socialnetwork.tweetterm.TwitterClone;
import socialnetwork.tweetterm.view.Display;

import java.time.Instant;

public abstract class Command {

    protected Instant timestamp;
    protected String username;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Command command = (Command) o;

        if (!timestamp.equals(command.timestamp)) return false;
        return username.equals(command.username);

    }

    @Override
    public int hashCode() {
        int result = timestamp.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }

    public Command(String username, Instant timestamp) {
        this.username = username;
        this.timestamp = timestamp;
    }

    public abstract void execute(Display display, TwitterClone twitterClone);

    public String getUser(){
        return username;
    }

    public Instant getTimestamp(){
        return timestamp;
    }

}
