public class Product {
  private String name;
  private double price;
  private Category category;
  private DiscountSize discountSize;

  public Product(String name, double price, Category category) {
    setName(name);
    setPrice(price);
    setCategory(category);
    this.discountSize = DiscountSize.NO_DISCOUNT;
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

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    if (category == null) {
      throw new IllegalArgumentException("Category cannot be null.");
    }
    this.category = category;
  }

  public DiscountSize getDiscountSize() {
    return discountSize;
  }

  public void setDiscountSize(DiscountSize discountSize) {
    if (discountSize == null) {
      throw new IllegalArgumentException("Discount size cannot be null.");
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
