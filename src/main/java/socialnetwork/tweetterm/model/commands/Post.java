package socialnetwork.tweetterm.model.commands;

import socialnetwork.tweetterm.TwitterClone;
import socialnetwork.tweetterm.model.Message;
import socialnetwork.tweetterm.view.Display;

import java.time.Instant;

public class Post extends Command {

    private String message;

    public Post(String username, String message, Instant timestamp) {
        super(username, timestamp);
        this.message = message;
    }

    @Override
    public void execute(Display display, TwitterClone twitterClone) {
        Message message = new Message(getMessage(), getUser(), getTimestamp());
        twitterClone.postMessage(message, getUser(), getTimestamp());
        display.showInputPrompt();

    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Post post = (Post) o;

        return message.equals(post.message);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }
}
