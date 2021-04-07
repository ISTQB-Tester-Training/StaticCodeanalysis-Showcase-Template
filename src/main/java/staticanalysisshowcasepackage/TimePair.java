package staticanalysisshowcasepackage;

public class TimePair {

    public double getTimeDifference(String startTime, String endTime) {

        double aTimeDifference;

        int positionOfColon = -1, startTimeMin = 0, endTimeMin = 0;

        try {
            positionOfColon = startTime.indexOf(':');
            if (positionOfColon > 0) {
                int startTimeHH = Integer.parseInt(startTime.substring(0, positionOfColon));
                int startTimeMM = Integer.parseInt(startTime.substring(positionOfColon + 1));
                startTimeMin = startTimeHH*60 + startTimeMM;
            }
        } catch (Exception e) {
            throw new TimePairException(e.getMessage() + "(Input value: " + startTime + ")", 501);
        }

        try {
            positionOfColon = startTime.indexOf(':');
            if (positionOfColon > 0) {
                int endTimeHH = Integer.parseInt(endTime.substring(0, positionOfColon));
                int endTimeMM = Integer.parseInt(endTime.substring(positionOfColon + 1));
                endTimeMin = endTimeHH*60 +  endTimeMM;
            }
        } catch (Exception e) {
            throw new TimePairException(e.getMessage() + "(Input value: " + endTime + ")", 502);
        }

        aTimeDifference = (endTimeMin - startTimeMin)/60.0;

        if (aTimeDifference < 0.0) {
            throw new TimePairException("Invalid time period " +
                    startTime + " / " +
                    endTime +
                    " time difference " + aTimeDifference + " hours", 503);
        }
        return aTimeDifference;
    }

    public double getPauseTime(String startTime, String endTime) {

        double timeDifference = new TimePair().getTimeDifference(startTime, endTime);

        double pauseTime = 0.0;

        if (timeDifference <= 6.0) {
            pauseTime = 0.0;
        } else if (timeDifference > 6.0 && timeDifference <= 6.5) {
            pauseTime = timeDifference - 6.0;
        } else if (timeDifference > 6.5 && timeDifference <= 9.5) {
            pauseTime = 0.5;
        } else if (timeDifference > 9.5 && timeDifference <= 9.75) {
            pauseTime = timeDifference - 9.0;
        } else if (timeDifference > 9.75) {
            pauseTime = 0.75;
        }

        return pauseTime;
    }

    public class TimePairException extends RuntimeException {
        private final String message;
        private final Long messageNr;

        public TimePairException (String argMessage, long argMessageNr) {
            super(argMessage);
            message = argMessage;
            messageNr = argMessageNr;
        }
        public String getMessageText() {
            return message;
        }
        public Long getMessageNr() {
            return messageNr;
        }
    }
}

