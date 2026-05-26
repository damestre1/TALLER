package model;

public class Passenger {

    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phoneNumber;
    private String passportNumber;
    private String nationality;

    public Passenger() {
    }

    public Passenger(String id, String firstName, String lastName,
                     int age, String email, String phoneNumber,
                     String passportNumber, String nationality) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passportNumber = passportNumber;
        this.nationality = nationality;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName=lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age=age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber=phoneNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber=passportNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality=nationality;
    }

    @Override
    public String toString() {

        return
                "\nCedula: "+id+
                        "\nNombre: "+firstName+
                        "\nApellido: "+lastName+
                        "\nEdad: "+age+
                        "\nCorreo: "+email+
                        "\nTelefono: "+phoneNumber+
                        "\nPasaporte: "+passportNumber+
                        "\nNacionalidad: "+nationality;

    }

}