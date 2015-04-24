package socialnetwork.tweetterm;

import socialnetwork.tweetterm.model.Message;

import java.time.Instant;
import java.util.List;

public interface TwitterClone {

    void postMessage(Message message, String user, Instant timestamp);
    List<Message> viewTimeline(String user);
    void followUser(String follower, String following);
    List<Message> viewAggregateTimeline(String user);
}
