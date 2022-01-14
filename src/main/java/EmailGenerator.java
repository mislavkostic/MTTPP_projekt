import java.util.Random;

public class EmailGenerator {
    String availableChars, email, emailService;
    StringBuilder stringBuilder;
    Random rnd;

    public EmailGenerator(){
        availableChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        email = "";
        emailService = "@gmail.com";
        stringBuilder = new StringBuilder();
        rnd = new Random();
    }

    public String generateEmail(){
        while(stringBuilder.length() < 10){ //length of random string
            int index = (int) (rnd.nextFloat() * availableChars.length());
            stringBuilder.append(availableChars.charAt(index));
        }
        stringBuilder.append(emailService);
        email = stringBuilder.toString();
        return email;
    }
}
