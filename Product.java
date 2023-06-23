import java.util.Random;

public class Product {
  private String name;
  private double price;
  private String category;
  private static DiscountSize discountSize;
  public Product(String name, double price, String category) {
    setName(name);
    setPrice(price);
    setCategory(category);
    this.discountSize = DiscountSize.NO_DISCOUNT;
  }
  public static void assignRandomDiscount() {
    DiscountSize[] discounts = DiscountSize.values();
    Random random = new Random();
    int index = random.nextInt(discounts.length);
    DiscountSize randomDiscount = discounts[index];

    Product product = new Product("Sample Product", 100.0, Category.NORMAL);  // Создаем экземпляр класса Product
    try {
      product.setDiscountSize(randomDiscount);  // Вызываем метод setDiscountSize() на экземпляре класса Product
    } catch (TooMuchSaleException e) {
      System.out.println("TooMuchSaleException: " + e.getMessage());
    }
  }


  // Геттеры и сеттеры

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Product name cannot be null or empty.");
    }
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    if (price < 0) {
      throw new IllegalArgumentException("Product price cannot be negative.");
    }
    this.price = price;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    if (category == null) {
      throw new IllegalArgumentException("Category cannot be null.");
    }
    this.category = category;
  }

  public DiscountSize getDiscountSize() {
    return discountSize;
  }

  public void setDiscountSize(DiscountSize discountSize) throws TooMuchSaleException {
    if (discountSize == null) {
      throw new IllegalArgumentException("Discount size cannot be null.");
    }
    if (this.category == Category.PREMIUM && discountSize.getValue() > 0.15) {
      throw new TooMuchSaleException("Discount greater than 15% is not allowed for premium products.");
    }
    this.discountSize = discountSize;
  }






  @Override
  public String toString() {
    return
        "Название " + name + '\n' +
        " Цена " + price + '\n' +
        " Категория " + category + '\n' +
        " Скидка " + discountSize + '\n';
  }
}
