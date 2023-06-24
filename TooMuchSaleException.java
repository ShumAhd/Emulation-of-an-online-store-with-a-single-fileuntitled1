/**
 * Этот класс представляет собой исключение, которое выбрасывается при слишком большой скидке.
 */
public class TooMuchSaleException extends Exception {
  /**
   * Конструктор для создания нового исключения с указанным сообщением об ошибке.
   *
   * @param message Сообщение об ошибке.
   */
  public TooMuchSaleException(String message) {
    super(message);
  }
}
