/**
 * Этот класс представляет собой исключение, которое выбрасывается при ошибке клиента.
 */
public class CustomerException extends Exception {
  /**
   * Конструктор для создания нового исключения с указанным сообщением об ошибке.
   *
   * @param message Сообщение об ошибке.
   */
  public CustomerException(String message) {
    super(message);
  }
}
