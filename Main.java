
import java.time.Year;
import java.time.YearMonth;
import java.util.Scanner;

public class Main {
    static int counter = 0;

    public static void main(String[] args) {

        checkData();
    }

    public static void checkData() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Ödenecek tutarı giriniz : ");
            String amount = scanner.next().trim();
            if (!isValidAmount(amount)) {
                throw new InvalidAmountException("Geçersiz ödeme tutarı.  ");
            }

            System.out.print("Kredi Kart No giriniz : ");
            String cardNumber = scanner.next().trim();
            if (!isValidCardNumber(cardNumber)) {
                throw new InvalidCardNumberException("Geçersiz kredi kartı no. Kart no harf içeremez ve 16 haneden oluşmalıdır. ");
            }

            System.out.print("Son kullanma tarihini giriniz (MM/YYYY) : ");
            String expireDate = scanner.next().trim();
            if (!isValidExpireDate(expireDate)) {
                throw new InvalidExpireDateException("Geçersiz son kullanma tarihi. Giriş formatınızı yada geçerli bir tarih girdiğinizi kontrol edin. ");
            }
            System.out.print("Güvenlik kodunu giriniz :");
            String securityCode = scanner.next().trim();
            if (!isValidSecurityCode(securityCode)) {
                throw new InvalidSecurityCodeException("Geçersiz güvenlik kodu. Güvenlik kodu 3 haneden oluşmalı ve harf içermemelidir.");
            }
            pay(Double.parseDouble(amount), cardNumber, expireDate, Integer.parseInt(securityCode));
        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
        } catch (InvalidCardNumberException e) {
            System.out.println(e.getMessage());
        } catch (InvalidExpireDateException e) {
            System.out.println(e.getMessage());
        } catch (InvalidSecurityCodeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void pay(double amount, String cardNumber, String expireDate, int securiyCode) {
        do {
            try {
                int random = (int) (Math.random() * 100);
                if (random > 75) {
                    counter++;
                    throw new SystemNotWorkingException("Ödeme başarısız.Bir kez daha deneyin." + "Random Number : " + random);
                }
                System.out.println("Ödeme işlemi başarıyla tamamlandı. " + "Random Number : " + random);
                break;

            } catch (SystemNotWorkingException e) {
                System.out.println(e.getMessage());
                System.out.println("******************************************");
                if (counter <= 1) {
                    checkData();
                    counter++;
                }
                else
                    System.out.println("***ÖDEME TAMAMLANAMADI.***");

            }
        } while (counter <= 1);

    }

    private static boolean isValidSecurityCode(String securityCode) {
        if (securityCode.length() != 3) {
            return false;
        }

        for (char c : securityCode.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;

    }

    private static boolean isValidExpireDate(String expireDate_) {
        if (expireDate_.contains("/")) {
            String[] dateArray = expireDate_.split("/");
            if (dateArray.length == 2) {
                int expireDateMonth = Integer.parseInt(dateArray[0]);
                int expireDateYear = Integer.parseInt(dateArray[1]);
                if (Year.now().getValue() <= expireDateYear && expireDateMonth <= 12) {
                    if (Year.now().getValue() == expireDateYear && YearMonth.now().getMonthValue() < expireDateMonth) {
                        return true;
                    }
                    return true;
                } else
                    return false;
            } else
                return false;
        } else
            return false;

    }

    private static boolean isValidCardNumber(String cardNumber) {
        if (cardNumber.length() == 16) {
            for (char c : cardNumber.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        } else
            return false;
    }

    private static boolean isValidAmount(String amount_) {
        try {
            double amount = Double.parseDouble(amount_);
            if (amount <= 0) {
                throw new NumberFormatException("Girilen tutar 0 ve 0'dan daha küçük olamaz.");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
