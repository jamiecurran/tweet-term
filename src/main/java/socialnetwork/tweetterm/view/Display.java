package socialnetwork.tweetterm.view;

import socialnetwork.tweetterm.model.commands.Command;
import socialnetwork.tweetterm.model.Message;

import java.util.List;

public interface Display {

    void welcomeMessage();

    Command readInput();

    void showTimeline(List<Message> userTimeLine);

    void showInputPrompt();

    void showWall(List<Message> userWall);
}
