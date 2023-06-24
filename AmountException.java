/**
 * Этот класс представляет собой исключение, которое выбрасывается при ошибке суммы.
 */
public class AmountException extends Exception {

  /**
   * Конструктор для создания нового исключения с указанным сообщением об ошибке.
   *
   * @param message Сообщение об ошибке.
   */
  public AmountException(String message) {
    super(message);
  }
}
