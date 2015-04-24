package socialnetwork.tweetterm.view;

import socialnetwork.tweetterm.model.Message;
import socialnetwork.tweetterm.model.commands.Command;
import socialnetwork.tweetterm.util.CommandInputParser;
import socialnetwork.tweetterm.util.TimeDisplayFormatter;

import java.util.List;
import java.util.Scanner;

public class DisplayScanner implements Display {

    private CommandInputParser parser;
    private TimeDisplayFormatter formatter;
    private Scanner display = new Scanner(System.in);

    public DisplayScanner(CommandInputParser commandInputParser, TimeDisplayFormatter formatter) {
        this.parser = commandInputParser;
        this.formatter = formatter;
    }

    @Override
    public void welcomeMessage() {
        System.out.printf("Twitter command line clone:\n");
    }

    @Override
    public Command readInput() {
        String input = display.nextLine();
        return parser.parseInput(input);
    }

    @Override
    public void showTimeline(List<Message> userTimeLine) {
        userTimeLine
                .stream()
                .forEach(message -> System.out.printf("%s (%s ago)\n", message.getMessage(), formatter.format(message.getTimestamp())));
    }

    @Override
    public void showInputPrompt() {
        System.out.printf("> ");

    }

    @Override
    public void showWall(List<Message> userWall) {
        userWall
                .stream()
                .forEach(message -> System.out.printf("%s - %s (%s ago)\n", message.getUser(), message.getMessage(), formatter.format(message.getTimestamp())));
    }
}
