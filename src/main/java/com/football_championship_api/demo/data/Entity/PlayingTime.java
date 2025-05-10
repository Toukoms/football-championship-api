package com.football_championship_api.demo.data.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayingTime {
    private Number value;
    private DurationUnit durationUnit;

    public void convertToSeconds() {
        if (durationUnit == null || value == null) return;

        switch (durationUnit) {
            case SECOND:
                break;
            case MINUTE:
                value = value.doubleValue() * 60;
                durationUnit = DurationUnit.SECOND;
                break;
            case HOUR:
                value = value.doubleValue() * 3600;
                durationUnit = DurationUnit.SECOND;
                break;
            default:
                throw new IllegalArgumentException("Unrecognized duration unit: " + durationUnit);
        }
    }

    public void convertToHour() {
        if (durationUnit == null || value == null) return;

        switch (durationUnit) {
            case SECOND:
                value = value.doubleValue() / 3600;
                durationUnit = DurationUnit.HOUR;
                break;
            case MINUTE:
                value = value.doubleValue() / 60;
                durationUnit = DurationUnit.HOUR;
                break;
            case HOUR:
                break;
            default:
                throw new IllegalArgumentException("Unrecognized duration unit: " + durationUnit);
        }
    }

    public void convertToMinute() {
        if (durationUnit == null || value == null) return;

        switch (durationUnit) {
            case SECOND:
                value = value.doubleValue() / 60;
                durationUnit = DurationUnit.MINUTE;
                break;
            case HOUR:
                value = value.doubleValue() * 60;
                durationUnit = DurationUnit.MINUTE;
                break;
            case MINUTE:
                break;
            default:
                throw new IllegalArgumentException("Unrecognized duration unit: " + durationUnit);
        }
    }
}
