package socialnetwork.tweetterm.util;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeDisplayFormatter {

    private Clock clock;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM YY").withZone(ZoneId.systemDefault());

    public TimeDisplayFormatter() {
        this(Clock.systemDefaultZone());
    }

    public TimeDisplayFormatter(Clock clock) {
        this.clock = clock;
    }

    public String format(Instant timestamp) {
        Duration duration = Duration.between(timestamp, clock.instant());
        if(duration.toHours() >= 24){
            return dateTimeFormatter.format(timestamp);
        } else if(duration.toHours() > 0 && duration.toHours() < 24) {
            return pluralise("hour", "hours", duration.toHours());
        } else if(duration.toMinutes() > 0 && duration.toMinutes() < 60){
            return pluralise("minute", "minutes", duration.toMinutes());
        } else {
            return pluralise("second", "seconds", duration.getSeconds());
        }
    }

    private String pluralise(String singular, String plural, long count) {
        if(count > 1) {
            return String.format("%d %s", count, plural);
        } else {
            return String.format("%d %s", count, singular);
        }
    }

}
