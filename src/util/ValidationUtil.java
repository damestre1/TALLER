package util;

public class ValidationUtil {

    public static boolean validateEmail(
            String email
    ){

        return email.contains(
                "@"
        );

    }

}