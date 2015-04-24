package socialnetwork.tweetterm;

import socialnetwork.tweetterm.model.Message;
import socialnetwork.tweetterm.model.commands.Follow;
import socialnetwork.tweetterm.model.commands.View;
import socialnetwork.tweetterm.model.commands.Wall;
import socialnetwork.tweetterm.view.Display;
import org.junit.Before;
import org.junit.Test;
import socialnetwork.tweetterm.model.commands.Post;

import java.time.Instant;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AppTest {

    private Display console;
    private TwitterClone tweetTerm;
    private App testObj;

    @Before
    public void setup() {
        console = mock(Display.class);
        tweetTerm = mock(TwitterClone.class);
        testObj = new App(console, tweetTerm);
    }

    @Test
    public void testCommandResolvesToPost(){
        Post command = new Post("Alice", "test message", Instant.now());
        when(console.readInput()).thenReturn(command);
        testObj.processCommand(command);
        Message message = new Message(command.getMessage(), command.getUser(), command.getTimestamp());
        verify(tweetTerm).postMessage(message, command.getUser(), command.getTimestamp());
    }

    @Test
    public void testCommandResolvesToViewTimeline(){
        View command = new View("Alice", Instant.now());
        when(console.readInput()).thenReturn(command);
        testObj.processCommand(command);
        verify(tweetTerm).viewTimeline(command.getUser());
    }

    @Test
    public void testCommandResolvesToFollowUser() {
        Follow command = new Follow("Charlie", "Alice", Instant.now());
        when(console.readInput()).thenReturn(command);
        testObj.processCommand(command);
        verify(tweetTerm).followUser(command.getUser(), command.getUserToFollow());
    }

    @Test
    public void testCommandResolvesToWall() {
        Wall command = new Wall("Charlie", Instant.now());
        when(console.readInput()).thenReturn(command);
        testObj.processCommand(command);
        verify(tweetTerm).viewAggregateTimeline(command.getUser());
    }
}