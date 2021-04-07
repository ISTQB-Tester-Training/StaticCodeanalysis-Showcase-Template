package staticanalysisshowcasetestpackage;

import staticanalysisshowcasepackage.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TimePairTest {

    TimePair aTimePair = new TimePair();

    @Test
    public void timeDifferenceTest_1h () {
        assertEquals(1.0, aTimePair.getTimeDifference("10:30", "11:30"), 0.0);
    }

    @Test
    public void timeDifferenceTest_0h () {
        assertEquals(0.0, aTimePair.getTimeDifference("10:00", "10:00"), 0.0);
    }

    @Test
    public void timeDifferenceTest_invalidStartTimeFormat() {

        TimePair.TimePairException aTimePairException = assertThrows(TimePair.TimePairException.class,
                () -> aTimePair.getTimeDifference("SS:00", "11:00"));

        assertEquals(501, (long) aTimePairException.getMessageNr());
        System.out.println("Invalid START Time - Error Number: " + aTimePairException.getMessageNr() + " " +
                "Error text: " + aTimePairException.getMessageText());
    }

    @Test
    public void timeDifferenceTest_invalidEndTimeFormat() {

        TimePair.TimePairException aTimePairException = assertThrows(TimePair.TimePairException.class,
                () -> aTimePair.getTimeDifference("11:00", "EE:00"));

        assertEquals(502, (long) aTimePairException.getMessageNr());
        System.out.println("Invalid END Time - Error Number: " + aTimePairException.getMessageNr() + " " +
                "Error text: " + aTimePairException.getMessageText());
    }

    @Test
    public void timeDifferenceTest_invalidTimePeriod() {

        TimePair.TimePairException aTimePairException = assertThrows(TimePair.TimePairException.class,
                () -> aTimePair.getTimeDifference("19:00", "03:00"));

        assertEquals(503, (long) aTimePairException.getMessageNr());
        System.out.println("Invalid TIME PERIOD - Error Number: " + aTimePairException.getMessageNr() + " " +
                "Error text: " + aTimePairException.getMessageText());
    }

    @Test
    public void getPauseTime_0min () {
        assertEquals(0, aTimePair.getPauseTime("8:00", "12:30")*60, 0.001);
    }

    @Test
    public void getPauseTime_0min2 () {
        assertEquals(0, aTimePair.getPauseTime("08:00", "08:00")*60, 0.001);
    }

    @Test
    public void getPauseTime_0min3 () {
        assertEquals(0, aTimePair.getPauseTime("08:00", "14:00")*60, 0.001);
    }

    @Test
    public void getPauseTime_30min () {
        assertEquals(30, aTimePair.getPauseTime("8:00", "15:00")*60, 0.001);
    }

    @Test
    public void getPauseTime_35min () {
        assertEquals(35, aTimePair.getPauseTime("8:00", "17:35")*60, 0.001);
    }

    @Test
    public void getPauseTime_45min () {
        assertEquals(45, aTimePair.getPauseTime("8:00", "17:45")*60, 0.001);
    }
}