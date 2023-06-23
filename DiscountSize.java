public enum DiscountSize {
  NO_DISCOUNT(0),
  DISCOUNT_5(0.05),
  DISCOUNT_10(0.10),
  DISCOUNT_15(0.15),
  DISCOUNT_20(0.20);

  private final double value;

  DiscountSize(double value) {
    this.value = value;
  }

  public double getValue() {
    return value;
  }
}
