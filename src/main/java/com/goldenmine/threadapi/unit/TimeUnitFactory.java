package com.GoldenMine.Thread.ThreadAPI.Unit;

import java.util.concurrent.TimeUnit;

public enum TimeUnitFactory {
    MS(new TimeUnit() {
        @Override
        public double calculate(double n) {
            return 1F/(n/1000F);
        }
    }),
    FPS(new TimeUnit() {
        @Override
        public double calculate(double n) {
            return n;
        }
    }),
    NS(new TimeUnit() {
        @Override
        public double calculate(double n) {
            return Math.round(1/(n/1000000000F));
        }
    }),
    SECOND(new TimeUnit() {
        @Override
        public double calculate(double n) {
            return 1F/n;
        }
    }),
    MINUTE(new TimeUnit() {
        @Override
        public double calculate(double n) {
            return 1F/(n*60F);
        }
    }),
    HOUR(new TimeUnit() {
        @Override
        public double calculate(double n) {
            return 1F/(n*60F*60F);
        }
    }),
    DAY(new TimeUnit() {
        @Override
        public double calculate(double n) {
            return 1F/(n*60F*60F*24F);
        }
    });


    private TimeUnit unit;

    TimeUnitFactory(TimeUnit unit) {
        this.unit = unit;
    }

    public double convert(double n) {
        return unit.calculate(n);
    }

    public static abstract class TimeUnit {

        public abstract double calculate(double n); // n의 단위를 FPS로 변환하기
    }
}
