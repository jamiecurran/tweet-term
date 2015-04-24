package socialnetwork.tweetterm.util;

import org.junit.Before;
import org.junit.Test;

import java.time.*;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class TimeDisplayFormatterTest {

    private Clock testClock;

    @Before
    public void setup() {
        LocalDateTime dateTime = LocalDateTime.of(2014, 02, 19, 12, 0);
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        testClock = Clock.fixed(instant, ZoneId.systemDefault());
    }

    @Test
    public void returnsDateIfMessageOlderThanADay() {
        Instant timestamp = Instant.now(testClock).minus(Duration.ofDays(1));
        TimeDisplayFormatter testObj = new TimeDisplayFormatter(testClock);
        assertThat(testObj.format(timestamp), equalTo("18 Feb 14"));
    }

    @Test
    public void returnsMinutesIfLessThanHourMoreThan60Seconds() {
        Instant timestamp = Instant.now(testClock).minus(Duration.ofHours(5));
        TimeDisplayFormatter testObj = new TimeDisplayFormatter(testClock);
        assertThat(testObj.format(timestamp), equalTo("5 hours"));
    }

    @Test
    public void returnsHourIfMoreThan60Minutes() {
        Instant timestamp = Instant.now(testClock).minus(Duration.ofHours(1));
        TimeDisplayFormatter testObj = new TimeDisplayFormatter(testClock);
        assertThat(testObj.format(timestamp), equalTo("1 hour"));
    }

    @Test
    public void returnsSecondsIfLessThanAMinute() {
        Instant timestamp = Instant.now(testClock).minus(Duration.ofSeconds(4));
        TimeDisplayFormatter testObj = new TimeDisplayFormatter(testClock);
        assertThat(testObj.format(timestamp), equalTo("4 seconds"));
    }

}
