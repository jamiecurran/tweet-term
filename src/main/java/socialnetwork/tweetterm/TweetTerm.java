package socialnetwork.tweetterm;

import socialnetwork.tweetterm.model.Message;
import socialnetwork.tweetterm.model.Timeline;
import socialnetwork.tweetterm.model.User;

import java.time.Instant;
import java.util.*;

public class TweetTerm implements TwitterClone {

    private Timeline timeline = new Timeline();
    private Map<String, User> users = new HashMap<>();

    @Override
    public List<Message> viewTimeline(String username) {
        if(users.containsKey(username)){
            return timeline.getMessagesForUser(users.get(username));
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void followUser(String follower, String following) {
        if(users.containsKey(follower) && users.containsKey(following)){
            users.get(follower).addFollowingUser(users.get(following));
        }
    }

    public List<User> getFollowingList(String follower) {
        if(users.containsKey(follower)) {
            Set<User> following = users.get(follower).getFollowedUsers();
            return new ArrayList<>(following);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Message> viewAggregateTimeline(String user) {
        if(users.containsKey(user)){
            return timeline.getAggregateMessagesForUser(users.get(user));
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void postMessage(Message message, String username, Instant timestamp) {
        if(!users.containsKey(username)) {
            users.put(username, new User(username));
        }
        timeline.postMessage(message);
    }


    public int numberOfUsers() {
        return users.size();
    }
}