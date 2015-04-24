package socialnetwork.tweetterm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Timeline {

    private List<Message> messages = new ArrayList();

    public void postMessage(Message message) {;
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<Message> getMessagesForUser(User user) {
        return messages
                .stream()
                .filter(message -> message.getUser().equals(user.getUsername()))
                .sorted((m1, m2) -> m2.getTimestamp().compareTo(m1.getTimestamp()))
                .collect(Collectors.toList());
    }

    public List<Message> getAggregateMessagesForUser(User user) {
        List<User> userFilter = new ArrayList<>(user.getFollowedUsers());
        userFilter.add(user);
        return userFilter
                .stream()
                .parallel()
                .flatMap(followedUser -> getMessagesForUser(followedUser).stream())
                .sorted((m1, m2) -> m2.getTimestamp().compareTo(m1.getTimestamp()))
                .collect(Collectors.toList());

    }

}
