package socialnetwork.tweetterm.util;

import org.junit.Before;
import org.junit.Test;
import socialnetwork.tweetterm.model.commands.*;

import java.time.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class CommandInputParserTest {

    private Clock testClock;
    private CommandInputParser testObj;

    @Before
    public void setup() {
        LocalDateTime dateTime = LocalDateTime.of(2014, 02, 19, 12, 0);
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        testClock = Clock.fixed(instant, ZoneId.systemDefault());
        testObj = new CommandInputParser(testClock);
    }

    @Test
    public void postCommandSyntax() {
        String postCommand = "Alice -> test message";
        Command expected = new Post("Alice", "test message", Instant.now(testClock));
        assertThat(testObj.parseInput(postCommand), equalTo(expected));
    }

    @Test
    public void viewCommandSyntax() {
        String viewCommand = "Alice";
        Command expected = new View("Alice", Instant.now(testClock));
        assertThat(testObj.parseInput(viewCommand), equalTo(expected));
    }

    @Test
    public void followCommandSyntax() {
        String followCommand = "Charlie follows Alice";
        Command expected = new Follow("Charlie", "Alice", Instant.now(testClock));
        assertThat(testObj.parseInput(followCommand), equalTo(expected));
    }

    @Test
    public void wallCommandSyntax() {
        String wallCommand = "Charlie wall";
        Command expected = new Wall("Charlie", Instant.now(testClock));
        assertThat(testObj.parseInput(wallCommand), equalTo(expected));
    }


}
