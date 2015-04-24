package socialnetwork.tweetterm;

import socialnetwork.tweetterm.model.Message;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class TweetTermTest {

    private TweetTerm testObj;

    @Before
    public void setup() {
        testObj = new TweetTerm();
    }

    @Test
    public void createUserOnFirstPost() {
        Message message = new Message("test message", "Alice", Instant.now());
        testObj.postMessage(message, message.getUser(), message.getTimestamp());
        assertThat(testObj.numberOfUsers(), is(1));
    }

    @Test
    public void followUser(){
        String follower = "Charlie";
        String following = "Alice";
        Message message1 = new Message("test message", following, Instant.now());
        Message message2 = new Message("test message", follower, Instant.now());
        testObj.postMessage(message1, message1.getUser(), message1.getTimestamp());
        testObj.postMessage(message2, message2.getUser(), message2.getTimestamp());
        testObj.followUser(follower, following);
        assertThat(testObj.getFollowingList(follower).size(), is(1));
        assertThat(testObj.getFollowingList(follower).get(0).getUsername(), equalTo(following));
    }

    @Test
    public void viewAggregateTimeline() {
        String follower = "Charlie";
        String following = "Alice";
        Message message1 = new Message("test message", following, Instant.now());
        Message message2 = new Message("test message", follower, Instant.now());
        testObj.postMessage(message1, message1.getUser(), message1.getTimestamp());
        testObj.postMessage(message2, message2.getUser(), message2.getTimestamp());
        testObj.followUser(follower, following);
        assertThat(testObj.viewAggregateTimeline(follower).size(), is(2));
    }

}
