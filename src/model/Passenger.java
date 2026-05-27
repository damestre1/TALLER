package model;

public class Passenger {
    private String idNumber;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phoneNumber;
    private String passportNumber;
    private String nationality;

    public Passenger(String idNumber, String firstName, String lastName, int age, String email, String phoneNumber, String passportNumber, String nationality) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passportNumber = passportNumber;
        this.nationality = nationality;
    }

    public String getIdNumber() { return idNumber; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPassportNumber() { return passportNumber; }

    @Override
    public String toString() {
        return String.format(
                "  Cedula        : %s\n" +
                        "  Nombre        : %s %s\n" +
                        "  Edad          : %d\n" +
                        "  Email         : %s\n" +
                        "  Telefono      : %s\n" +
                        "  Pasaporte     : %s\n" +
                        "  Nacionalidad  : %s",
                idNumber, firstName, lastName, age,
                email, phoneNumber, passportNumber, nationality
        );
    }
}
