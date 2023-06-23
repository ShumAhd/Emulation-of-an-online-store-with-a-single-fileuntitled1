public class Order {
  private Customer customer;
  private Product product;
  private int quantity;
  private double totalPrice;

  public Order(Customer customer, Product product, int quantity, double totalPrice) {
    setCustomer(customer);
    setProduct(product);
    setQuantity(quantity);
    calculateTotalPrice();
  }

  // Геттеры и сеттеры

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    if (customer == null) {
      throw new IllegalArgumentException("Customer cannot be null.");
    }
    this.customer = customer;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    if (product == null) {
      throw new IllegalArgumentException("Product cannot be null.");
    }
    this.product = product;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity must be greater than zero.");
    }
    this.quantity = quantity;
    calculateTotalPrice();
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  private void calculateTotalPrice() {
    double discountedPrice = product.getPrice() * (1 - product.getDiscountSize().getValue());
    totalPrice = quantity * discountedPrice;
  }


  @Override
  public String toString() {
    return
        "Покупатель " + customer + '\n' +
        " Продукт " + product + '\n' +
        " Количество " + quantity + '\n' +
        " Цена итого " + totalPrice + '\n';
  }
}
