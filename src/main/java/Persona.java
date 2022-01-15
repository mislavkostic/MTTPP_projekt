public class Persona {
    String email;
    String firstName;
    String lastName;
    String phoneNumber;
    String password;
    EmailGenerator generator;

    public Persona(EmailGenerator generator){
        this.generator = generator;
        email = generator.generateEmail();
        firstName = "John";
        lastName = "Doe";
        phoneNumber = "+1234567890";
        password = "test1234";
    }
}
