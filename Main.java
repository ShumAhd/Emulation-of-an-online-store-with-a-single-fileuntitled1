import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
  private static final int MAX_QUANTITY = 100;
  private static final int RANDOM_QUANTITY_BOUND = 5;
  private static final String CUSTOMER_NOT_FOUND = "Несуществующий покупатель";
  private static final String PRODUCT_NOT_FOUND = "Несуществующий товар";
  private static final String INVALID_QUANTITY = "Некорректное количество товара";

  public static void main(String[] args) {
    List<Customer> customers = createCustomers();
    List<Product> products = createProducts();

    List<Order> orders = new ArrayList<>();
    try {
      Order order1 = makeRandomPurchase(customers, products);
      addOrder(orders, order1);

      Order order2 = makeRandomPurchase(customers, products);
      addOrder(orders, order2);

      Order order3 = makeRandomPurchase(customers, products);
      addOrder(orders, order3);

      Order order4 = makeRandomPurchase(customers, products);
      addOrder(orders, order4);

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
    Product.assignRandomDiscount();
    products.add(new Product("Товар 2", 200.0, Category.PREMIUM));
    Product.assignRandomDiscount();
    products.add(new Product("Товар 3", 300.0, Category.STANDARD));
    Product.assignRandomDiscount();
    products.add(new Product("Товар 4", 400.0, Category.PREMIUM));
    Product.assignRandomDiscount();
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

  public static Order makeRandomPurchase(List<Customer> customers, List<Product> products)
      throws CustomerException, ProductException, AmountException {
    Customer randomCustomer = getRandomElement(customers);
    Product randomProduct = getRandomElement(products);
    int randomQuantity = getRandomQuantity();

    return makePurchase(randomCustomer, randomProduct, randomQuantity);
  }
  public static Order makePurchase(Customer customer, Product product, int quantity)
      throws CustomerException, ProductException, AmountException {
    if (quantity < 0 || quantity > MAX_QUANTITY) {
      throw new AmountException("Неверное количество товара");
    }

//    if (!customer.isValid()) {
//      throw new CustomerException("Неверный покупатель");
//    }
//
//    if (!product.isValid()) {
//      throw new ProductException("Неверный товар");
//    }

    double cost = calculateCost(product.getPrice(), quantity);
    Order order = new Order(customer, product, quantity, cost);

    return order;
  }

  public static <T> T getRandomElement(List<T> list) {
    Random random = new Random();
    int randomIndex = random.nextInt(list.size());
    return list.get(randomIndex);
  }

  public static int getRandomQuantity() {
    Random random = new Random();
    return random.nextInt(RANDOM_QUANTITY_BOUND) - 1;
  }
  public static double calculateCost(double price, int quantity) {
    return price * quantity;
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
