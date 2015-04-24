package socialnetwork.tweetterm.view;

import socialnetwork.tweetterm.model.commands.Command;
import socialnetwork.tweetterm.util.CommandInputParser;
import socialnetwork.tweetterm.model.Message;
import socialnetwork.tweetterm.util.TimeDisplayFormatter;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

public class DisplayConsole implements Display{

    private CommandInputParser parser;
    private TimeDisplayFormatter formatter;
    private Console display = System.console();

    public DisplayConsole(CommandInputParser commandInputParser, TimeDisplayFormatter formatter) {
        this.parser = commandInputParser;
        this.formatter = formatter;
    }

    @Override
    public void welcomeMessage() {
        display.printf("Twitter command line clone:\n");
    }

    @Override
    public Command readInput() {
        String input = display.readLine();
        return parser.parseInput(input);
    }

    @Override
    public void showTimeline(List<Message> userTimeLine) {
        userTimeLine
                .stream()
                .forEach(message -> display.printf("%s (%s ago)\n", message.getMessage(), formatter.format(message.getTimestamp())));
    }

    @Override
    public void showInputPrompt() {
        display.printf("> ");
    }

    @Override
    public void showWall(List<Message> userWall) {
        userWall
                .stream()
                .forEach(message -> display.printf("%s - %s (%s ago)\n", message.getUser(), message.getMessage(), formatter.format(message.getTimestamp())));
    }
}
