package socialnetwork.tweetterm.util;

import socialnetwork.tweetterm.model.commands.*;

import java.time.Clock;
import java.time.Instant;

public class CommandInputParser {

    private Clock clock;

    public CommandInputParser(Clock clock) {
        this.clock = clock;
    }

    public CommandInputParser() {
        this(Clock.systemDefaultZone());
    }

    public Command parseInput(String input) {
        if(input.contains("->")){
            return parsePostCommand(input);
        } else if(!input.matches("^\\w+$") && input.contains("follows")) {
            return parseFollowCommand(input);
        } else if(!input.matches("^\\w+$") && input.contains("wall")) {
            return parseWallCommand(input);
        } else {
            return parseViewCommand(input);
        }
    }

    private Command parseWallCommand(String input) {
        String[] arguments = input.split("wall");
        return new Wall(arguments[0].trim(), Instant.now(clock));
    }

    private Command parseFollowCommand(String input) {
        String[] arguments = input.split("follows");
        return new Follow(arguments[0].trim(), arguments[1].trim(), Instant.now(clock));
    }

    private Command parseViewCommand(String input) {
        return new View(input.trim(), Instant.now(clock));
    }

    private Command parsePostCommand(String input) {
        String[] arguments = input.split("->");
        return new Post(arguments[0].trim(), arguments[1].trim(), Instant.now(clock));
    }
}
