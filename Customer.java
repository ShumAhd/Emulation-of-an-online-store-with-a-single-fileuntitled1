public class Customer {
  private String lastName;
  private String firstName;
  private int age;
  private String phone;
  private Gender gender;

  public Customer(String lastName, String firstName, int age, String phone, Gender gender) {
    setLastName(lastName);
    setFirstName(firstName);
    setAge(age);
    setPhone(phone);
    setGender(gender);
  }

  // Геттеры и сеттеры

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    if (lastName == null || lastName.isEmpty()) {
      throw new IllegalArgumentException("Last name cannot be null or empty.");
    }
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    if (firstName == null || firstName.isEmpty()) {
      throw new IllegalArgumentException("First name cannot be null or empty.");
    }
    this.firstName = firstName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    if (age < 0) {
      throw new IllegalArgumentException("Age cannot be negative.");
    }
    this.age = age;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    if (phone == null || phone.isEmpty()) {
      throw new IllegalArgumentException("Phone number cannot be null or empty.");
    }
    this.phone = phone;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    if (gender == null) {
      throw new IllegalArgumentException("Gender cannot be null.");
    }
    this.gender = gender;
  }


  @Override
  public String toString() {
    return
         lastName + " " + firstName + '\n' +
        " Возраст " + age + '\n' +
        " телефон " + phone + '\n' +
        " Пол " + gender + '\n';
  }

}
