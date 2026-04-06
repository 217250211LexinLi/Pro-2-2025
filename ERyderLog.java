import java.time.LocalDateTime;

public class ERyderLog {
    private String logId;
    private String event;
    private LocalDateTime timestamp;

    public ERyderLog(String logId, String event, LocalDateTime timestamp) {
        this.logId = logId;
        this.event = event;
        this.timestamp = timestamp;
    }

    
    public String getLogId() {
        return logId;
    }

    public String getEvent() {
        return event;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return logId + " - " + event + " - " + timestamp;
    }
}