/**
 * Этот класс представляет собой клиента.
 */
public class Customer {
  /**
   * Фамилия клиента.
   */
  private String lastName;
  /**
   * Имя клиента.
   */
  private String firstName;
  /**
   * Возраст клиента.
   */
  private int age;
  /**
   * Номер телефона клиента.
   */
  private String phone;
  /**
   * Пол клиента.
   */
  private Gender gender;

  /**
   * Конструктор для создания нового клиента с указанными параметрами.
   *
   * @param lastName Фамилия клиента.
   * @param firstName Имя клиента.
   * @param age Возраст клиента.
   * @param phone Номер телефона клиента.
   * @param gender Пол клиента.
   */
  public Customer(String lastName, String firstName, int age, String phone, Gender gender) {
    setLastName(lastName);
    setFirstName(firstName);
    setAge(age);
    setPhone(phone);
    setGender(gender);
  }

  // Геттеры и сеттеры

  /**
   * Возвращает фамилию клиента.
   *
   * @return Фамилия клиента.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Устанавливает фамилию клиента. Если фамилия равна null или пустая строка, выбрасывается исключение IllegalArgumentException.
   *
   * @param lastName Фамилия клиента.
   */
  public void setLastName(String lastName) {
    if (lastName == null || lastName.isEmpty()) {
      throw new IllegalArgumentException("Last name cannot be null or empty.");
    }
    this.lastName = lastName;
  }

  /**
   * Возвращает имя клиента.
   *
   * @return Имя клиента.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Устанавливает имя клиента. Если имя равно null или пустая строка, выбрасывается исключение IllegalArgumentException.
   *
   * @param firstName Имя клиента.
   */
  public void setFirstName(String firstName) {
    if (firstName == null || firstName.isEmpty()) {
      throw new IllegalArgumentException("First name cannot be null or empty.");
    }
    this.firstName = firstName;
  }

  /**
   * Возвращает возраст клиента.
   *
   * @return Возраст клиента.
   */
  public int getAge() {
    return age;
  }

  /**
   * Устанавливает возраст клиента. Если возраст меньше нуля, выбрасывается исключение IllegalArgumentException.
   *
   * @param age Возраст клиента.
   */
  public void setAge(int age) {
    if (age < 0 || age > 120) {
      throw new IllegalArgumentException("Age must be between 0 and 150.");
    }
    this.age = age;
  }

  /**
   * Возвращает номер телефона клиента.
   *
   * @return Номер телефона клиента.
   */
  public String getPhone() {
    return phone;
  }

  /**
   * Устанавливает номер телефона клиента. Если номер телефона равен null или пустая строка, выбрасывается исключение IllegalArgumentException.
   *
   * @param phone Номер телефона клиента.
   */
  public void setPhone(String phone) {
    if (phone == null || phone.isEmpty() || !phone.matches("\\d+")) {
      throw new IllegalArgumentException("Phone number cannot be null or empty or contain non-digit characters");
    }
    this.phone = phone;
  }

/**
 * Возвращает пол клиента.
 *
 * @return Пол клиента.
 */
public Gender getGender() {
  return gender;
}

  /**
   * Устанавливает пол клиента. Если пол равен null, выбрасывается исключение IllegalArgumentException.
   *
   * @param gender Пол клиента.
   */
  public void setGender(Gender gender) {
    if (gender == null) {
      throw new IllegalArgumentException("Gender cannot be null.");
    }
    this.gender = gender;
  }

  /**
   * Возвращает строковое представление объекта клиента.
   *
   * @return Строковое представление объекта клиента.
   */
  @Override
  public String toString() {
    return
        lastName + " " + firstName + '\n' +
            " Возраст " + age + '\n' +
            " телефон " + phone + '\n' +
            " Пол " + gender + '\n';
  }

}

