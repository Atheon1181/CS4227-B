package inventory.controls;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

public class Timer extends AnimationTimer {
    private long timestamp;
    private long time = 0;
    private long fraction = 0;
    private Label timeLabel;

    public Timer(Label timeLabel) {
        this.timeLabel = timeLabel;
    }

    public Timer() {
    }

    public Timer(long timestamp, long time, long fraction, Label timeLabel) {
        this.timestamp = timestamp;
        this.time = time;
        this.fraction = fraction;
        this.timeLabel = timeLabel;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
        this.timestamp = 0;
        this.fraction = 0;
    }

    public long getFraction() {
        return fraction;
    }

    public void setFraction(long fraction) {
        this.fraction = fraction;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(Label timeLabel) {
        this.timeLabel = timeLabel;
    }

    @Override
    public void start() {
        // current time adjusted by remaining time from last run
        timestamp = System.currentTimeMillis() - fraction;
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
        // save leftover time not handled with the last update
        fraction = System.currentTimeMillis() - timestamp;
    }

    public void pause() {

    }

    @Override
    public void handle(long now) {
        long newTime = System.currentTimeMillis();
        if (timestamp + 1000 <= newTime) {
            long deltaT = (newTime - timestamp) / 1000;
            time += deltaT;
            timestamp += 1000 * deltaT;

            if (timeLabel != null) {
                timeLabel.setText(Long.toString(time) + " sec(s)");
            }
        }
    }

    @Override
    public String toString() {
        return time + "";
    }
}
