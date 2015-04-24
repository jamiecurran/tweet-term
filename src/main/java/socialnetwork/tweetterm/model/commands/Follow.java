package socialnetwork.tweetterm.model.commands;

import socialnetwork.tweetterm.TwitterClone;
import socialnetwork.tweetterm.view.Display;

import java.time.Instant;

public class Follow extends Command {

    private String userToFollow;

    public Follow(String username, String userToFollow, Instant timestamp) {
        super(username, timestamp);
        this.userToFollow = userToFollow;
    }

    @Override
    public void execute(Display display, TwitterClone twitterClone) {
        twitterClone.followUser(getUser(), getUserToFollow());
        display.showInputPrompt();
    }

    public String getUserToFollow() {
        return userToFollow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Follow follow = (Follow) o;

        return userToFollow.equals(follow.userToFollow);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + userToFollow.hashCode();
        return result;
    }
}
