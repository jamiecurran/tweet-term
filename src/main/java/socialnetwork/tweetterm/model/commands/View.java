package socialnetwork.tweetterm.model.commands;

import socialnetwork.tweetterm.TwitterClone;
import socialnetwork.tweetterm.model.Message;
import socialnetwork.tweetterm.view.Display;

import java.time.Instant;
import java.util.List;

public class View extends Command {

    public View(String username, Instant timestamp){
        super(username, timestamp);
    }

    @Override
    public void execute(Display display, TwitterClone twitterClone) {
        List<Message> userTimeLine = twitterClone.viewTimeline(getUser());
        display.showTimeline(userTimeLine);
        display.showInputPrompt();
    }
}
