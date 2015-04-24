package socialnetwork.tweetterm;

import socialnetwork.tweetterm.model.commands.Command;
import socialnetwork.tweetterm.util.CommandInputParser;
import socialnetwork.tweetterm.util.TimeDisplayFormatter;
import socialnetwork.tweetterm.view.Display;
import socialnetwork.tweetterm.view.DisplayConsole;
import socialnetwork.tweetterm.view.DisplayScanner;

public class App {

    private Display display;
    private TwitterClone tweetTerm;

    public static void main(String[] args) {
        Display console = new DisplayConsole(new CommandInputParser(), new TimeDisplayFormatter());
        //Display console = new DisplayScanner(new CommandInputParser(), new TimeDisplayFormatter());
        TwitterClone tweetTerm = new TweetTerm();
        App app = new App(console, tweetTerm);
        app.run();
    }

    public App(Display display, TwitterClone clone){
        this.display = display;
        this.tweetTerm = clone;
    }

    private void run() {
        display.welcomeMessage();
        display.showInputPrompt();
        while (true) {
            Command command = display.readInput();
            processCommand(command);
        }
    }

    public void processCommand(Command command){
        command.execute(display, tweetTerm);
    }

}
