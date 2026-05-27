package presentation;

import java.util.Scanner;

public class InputValidator {

    private Scanner scanner;

    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readMenuOption(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (isNumeric(input)) {
                return Integer.parseInt(input);
            }
            System.out.println("  [!] Opción no válida. Ingrese un número.");
        }
    }

    public int readPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (isNumeric(input) && Integer.parseInt(input) >= 0) {
                return Integer.parseInt(input);
            }
            System.out.println("  [!] Ingrese solo números enteros positivos.");
        }
    }

    public double readPositiveDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value > 0) return value;
                System.out.println("  [!] El valor debe ser mayor a cero.");
            } catch (NumberFormatException e) {
                System.out.println("  [!] Ingrese solo números (ej: 180000).");
            }
        }
    }

    public String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty() && input.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+")) {
                return input;
            }
            System.out.println("  [!] Este campo solo permite letras.");
        }
    }

    public String readNumericString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty() && isNumeric(input)) {
                return input;
            }
            System.out.println("  [!] Este campo solo permite números.");
        }
    }

    public String readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (isValidDate(input)) {
                return input;
            }
            System.out.println("  [!] Formato de fecha inválido. Use DD/MM/YYYY (solo números y /).");
        }
    }

    public String readTime(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (isValidTime(input)) {
                return input;
            }
            System.out.println("  [!] Formato de hora inválido. Use HH:MM (ej: 06:00, 14:30).");
        }
    }

    private boolean isNumeric(String value) {
        return value != null && !value.isEmpty() && value.matches("\\d+");
    }

    private boolean isValidDate(String value) {
        if (value == null || !value.matches("\\d{2}/\\d{2}/\\d{4}")) return false;
        try {
            int day   = Integer.parseInt(value.substring(0, 2));
            int month = Integer.parseInt(value.substring(3, 5));
            int year  = Integer.parseInt(value.substring(6));
            return day >= 1 && day <= 31 && month >= 1 && month <= 12 && year >= 2000;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidTime(String value) {
        if (value == null || !value.matches("\\d{2}:\\d{2}")) return false;
        try {
            int hour   = Integer.parseInt(value.substring(0, 2));
            int minute = Integer.parseInt(value.substring(3));
            return hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}