import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Главный класс программы.
 */
public class Main {

  /**
   * Максимальное количество продукта в заказе.
   */
  private static final int MAX_QUANTITY = 100;
  /**
   * Граница для генерации случайного количества продукта.
   */
  private static final int RANDOM_QUANTITY_BOUND = 5;
  /**
   * Сообщение об ошибке для несуществующего покупателя.
   */
  private static final String CUSTOMER_NOT_FOUND = "Несуществующий покупатель";
  /**
   * Сообщение об ошибке для несуществующего товара.
   */
  private static final String PRODUCT_NOT_FOUND = "Несуществующий товар";
  /**
   * Сообщение об ошибке для некорректного количества товара.
   */
  private static final String INVALID_QUANTITY = "Некорректное количество товара";

  /**
   * Главный метод программы. Создает клиентов и продукты, совершает случайные покупки и выводит
   * результаты.
   *
   * @param args Аргументы командной строки (не используются).
   */
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

  /**
   * Создает список клиентов.
   *
   * @return Список клиентов.
   */
  public static List<Customer> createCustomers() {
    List<Customer> customers = new ArrayList<>();
    customers.add(new Customer("Иванов", "Иван", 25, "1234567890", Gender.MALE));
    customers.add(new Customer("Петрова", "Мария", 30, "9876543210", Gender.FEMALE));
    return customers;
  }

  /**
   * Создает список продуктов и присваивает им случайные скидки.
   *
   * @return Список продуктов.
   */
  public static List<Product> createProducts() {
    List<Product> products = new ArrayList<>();
    products.add(new Product("Товар 1", 100.0, Category.NORMAL));
    Product.assignRandomDiscount();
    products.add(new Product("Товар 2", 200.0, Category.PREMIUM));
    Product.assignRandomDiscount();
    products.add(new Product("Товар 3", 300.0, Category.NORMAL));
    Product.assignRandomDiscount();
    products.add(new Product("Товар 4", 400.0, Category.PREMIUM));
    Product.assignRandomDiscount();
    return products;
  }

  /**
   * Добавляет заказ в список заказов. Если заказ для этого клиента уже существует, выводится
   * сообщение об этом.
   *
   * @param orders Список заказов.
   * @param order  Заказ, который нужно добавить.
   */
  public static void addOrder(List<Order> orders, Order order) {
    orders.stream()
        .filter(existingOrder -> existingOrder.getCustomer().equals(order.getCustomer()))
        .findFirst()
        .ifPresent(existingOrder -> {
          System.out.println(
              "Заказ для покупателя " + order.getCustomer().getFirstName() + " уже существует");
        });

    orders.add(order);
    System.out.println("Добавлен заказ: " + '\n' + order);
  }

  /**
   * Создает случайный заказ.
   *
   * @param customers Список клиентов.
   * @param products  Список продуктов.
   * @return Случайный заказ.
   */
  public static Order makeRandomPurchase(List<Customer> customers, List<Product> products)
      throws CustomerException, ProductException, AmountException {
    Customer randomCustomer = getRandomElement(customers);
    Product randomProduct = getRandomElement(products);
    int randomQuantity = getRandomQuantity();

    return makePurchase(randomCustomer, randomProduct, randomQuantity);
  }

  /**
   * Создает заказ с указанными параметрами.
   *
   * @param customer Клиент, который сделал заказ.
   * @param product  Продукт, который был заказан.
   * @param quantity Количество заказанного продукта.
   * @return Заказ с указанными параметрами.
   */
  public static Order makePurchase(Customer customer, Product product, int quantity)
      throws CustomerException, ProductException, AmountException {
    if (quantity < 0 || quantity > MAX_QUANTITY) {
      throw new AmountException("Неверное количество товара");
    }

    double cost = calculateCost(product.getPrice(), quantity);
    Order order = new Order(customer, product, quantity);

    return order;
  }

  /**
   * Возвращает случайный элемент из списка.
   *
   * @param list Список элементов.
   * @return Случайный элемент из списка.
   */
  public static <T> T getRandomElement(List<T> list) {
    Random random = new Random();
    int randomIndex = random.nextInt(list.size());
    return list.get(randomIndex);
  }

  /**
   * Возвращает случайное количество продукта.
   *
   * @return Случайное количество продукта.
   */
  public static int getRandomQuantity() {
    Random random = new Random();
    return random.nextInt(RANDOM_QUANTITY_BOUND) - 1;
  }

  /**
   * Вычисляет стоимость заказа на основе цены и количества продукта.
   *
   * @param price    Цена продукта.
   * @param quantity Количество продукта.
   * @return Стоимость заказа.
   */
  public static double calculateCost(double price, int quantity) {
    return price * quantity;
  }

  /**
   * Проверяет корректность клиента. Если клиент равен null, выбрасывается исключение
   * CustomerException.
   *
   * @param customer Клиент для проверки.
   */
  public static void validateCustomer(Customer customer) throws CustomerException {
    if (customer == null) {
      throw new CustomerException(CUSTOMER_NOT_FOUND);
    }
  }

  /**
   * Проверяет корректность продукта. Если продукт равен null, выбрасывается исключение
   * ProductException.
   *
   * @param product Продукт для проверки.
   */
  public static void validateProduct(Product product) throws ProductException {
    if (product == null) {
      throw new ProductException(PRODUCT_NOT_FOUND);
    }
  }

  /**
   * Проверяет корректность количества продукта. Если количество меньше или равно нулю или больше
   * максимального значения, выбрасывается исключение AmountException.
   *
   * @param quantity Количество продукта для проверки.
   */
  public static void validateQuantity(int quantity) throws AmountException {
    if (quantity <= 0 || quantity > MAX_QUANTITY) {
      throw new AmountException(INVALID_QUANTITY);
    }
  }
}


