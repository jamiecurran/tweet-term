package socialnetwork.tweetterm;

import socialnetwork.tweetterm.model.Message;
import socialnetwork.tweetterm.model.Timeline;
import socialnetwork.tweetterm.model.User;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TimelineTest {

    private Timeline testObj;
    private Instant timestamp;

    @Before
    public void setup() {
        testObj = new Timeline();
        timestamp = Instant.now();
    }

    @Test
    public void canPostMessage() {
        testObj.postMessage(new Message("test message", "Alice", timestamp));
        assertThat(testObj.getMessages().size(), is(1));
    }

    @Test
    public void getMessagesForUser() {
        User alice = new User("Alice");
        User bob = new User("Bob");
        testObj.postMessage(new Message("test message #1", "Alice", timestamp));
        testObj.postMessage(new Message("test message #2", "Alice", timestamp));
        List<Message> userAMessages = testObj.getMessagesForUser(alice);
        List<Message> userBMessages = testObj.getMessagesForUser(bob);
        assertThat(userAMessages.size(), is(2));
        assertThat(userBMessages.size(), is(0));
    }

    @Test
    public void getAggregateTimelineForUser() {
        User alice = new User("Alice");
        User bob = new User("Bob");
        testObj.postMessage(new Message("test message #1", "Alice", timestamp));
        testObj.postMessage(new Message("test message #2", "Alice", timestamp));
        testObj.postMessage(new Message("test message #3", "Bob", timestamp));
        alice.addFollowingUser(bob);
        List<Message> messages = testObj.getAggregateMessagesForUser(alice);
        assertThat(messages.size(), is(3));
    }
}