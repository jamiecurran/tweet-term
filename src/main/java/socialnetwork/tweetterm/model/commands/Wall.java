package socialnetwork.tweetterm.model.commands;

import socialnetwork.tweetterm.TwitterClone;
import socialnetwork.tweetterm.model.Message;
import socialnetwork.tweetterm.view.Display;

import java.time.Instant;
import java.util.List;

public class Wall extends Command {

    public Wall(String username, Instant timestamp) {
        super(username, timestamp);
    }

    @Override
    public void execute(Display display, TwitterClone twitterClone) {
        List<Message> userAggregateTimeLine = twitterClone.viewAggregateTimeline(getUser());
        display.showWall(userAggregateTimeLine);
        display.showInputPrompt();
    }
}
