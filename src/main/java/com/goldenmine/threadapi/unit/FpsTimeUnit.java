package com.goldenmine.threadapi.unit;

public enum FpsTimeUnit {
  MS {
    @Override
    public double convert(double n) {
      return 1F / (n / 1000F);
    }
  },
  FPS {
    @Override
    public double convert(double n) {
      return n;
    }
  },
  NS {
    @Override
    public double convert(double n) {
      return Math.round(1 / (n / 1000000000F));
    }
  },
  SECOND {
    @Override
    public double convert(double n) {
      return 1F / n;
    }
  },
  MINUTE {
    @Override
    public double convert(double n) {
      return 1F / (n * 60F);
    }
  },
  HOUR {
    @Override
    public double convert(double n) {
      return 1F / (n * 60F * 60F);
    }
  },
  DAY {
    @Override
    public double convert(double n) {
      return 1F / (n * 60F * 60F * 24F);
    }
  };

  /**
   * Convert n to fps.
   *
   * @param n value to convert
   * @return Converted value
   */
  public abstract double convert(double n);
}
