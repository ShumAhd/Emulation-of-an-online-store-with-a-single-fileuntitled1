import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
  private static final int MAX_QUANTITY = 100;
  private static final String CUSTOMER_NOT_FOUND = "Несуществующий покупатель";
  private static final String PRODUCT_NOT_FOUND = "Несуществующий товар";
  private static final String INVALID_QUANTITY = "Некорректное количество товара";

  public static void main(String[] args) {
    List<Customer> customers = createCustomers();
    List<Product> products = createProducts();

    List<Order> orders = new ArrayList<>();
    try {
      Order order1 = makePurchase(customers.get(0), products.get(0), 2);
      addOrder(orders, order1);

      Order order2 = makePurchase(customers.get(1), products.get(1), 1);
      addOrder(orders, order2);
    } catch (CustomerException | ProductException | AmountException e) {
      System.out.println(e.getMessage());
    }

    System.out.println("Количество совершенных покупок: " + orders.size());
  }

  public static List<Customer> createCustomers() {
    List<Customer> customers = new ArrayList<>();
    customers.add(new Customer("Иванов", "Иван", 25, "1234567890", Gender.MALE));
    customers.add(new Customer("Петрова", "Мария", 30, "9876543210", Gender.FEMALE));
    return customers;
  }

  public static List<Product> createProducts() {
    List<Product> products = new ArrayList<>();
    products.add(new Product("Товар 1", 100.0, Category.STANDARD));
    products.add(new Product("Товар 2", 200.0, Category.PREMIUM));
    return products;
  }

  public static void addOrder(List<Order> orders, Order order) {
    orders.stream()
        .filter(existingOrder -> existingOrder.getCustomer().equals(order.getCustomer()))
        .findFirst()
        .ifPresent(existingOrder -> {
          System.out.println("Заказ для покупателя " + order.getCustomer().getFirstName() + " уже существует");
        });

    orders.add(order);
    System.out.println("Добавлен заказ: " + '\n' + order);
  }

  public static Order makePurchase(Customer customer, Product product, int quantity)
      throws CustomerException, ProductException, AmountException {
    validateCustomer(customer);
    validateProduct(product);
    validateQuantity(quantity);

    double totalPrice = product.getPrice() * quantity;

    return new Order(customer, product, quantity, totalPrice);
  }

  public static void validateCustomer(Customer customer) throws CustomerException {
    if (customer == null) {
      throw new CustomerException(CUSTOMER_NOT_FOUND);
    }
  }

  public static void validateProduct(Product product) throws ProductException {
    if (product == null) {
      throw new ProductException(PRODUCT_NOT_FOUND);
    }
  }

  public static void validateQuantity(int quantity) throws AmountException {
    if (quantity <= 0 || quantity > MAX_QUANTITY) {
      throw new AmountException(INVALID_QUANTITY);
    }
  }
}
