package payment;
public class QRCodePayment implements Payment {

    public QRCodePayment() {
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("       ▄▄▄▄▄▄▄  ▄ ▄ ▄▄▄▄▄▄▄ ");
        System.out.println("       █ ▄▄▄ █ ▀█▄█ █ ▄▄▄ █ ");
        System.out.println("       █ ███ █ █▀ █ █ ███ █ ");
        System.out.println("       █▄▄▄▄▄█ █ ▄▀ █▄▄▄▄▄█ ");
        System.out.println("       ▄▄▄ ▄▄▄▄█▀▀▀▄▄▄ ▄ ▄  ");
        System.out.println("       ▄▀▀▄▄▄▀▀▄▀ ▀▄▀▀ █ ▀▄ ");
        System.out.println("       █▀▄▄▄▄▄▀▀ █▀▀█▄▄ ▀▀▄ ");
        System.out.println("       ▄▄▄▄▄▄▄ █▀▀▄█▄▄█ ▀ ▄ ");
        System.out.println("       █ ▄▄▄ █ █ ▄▀▀▀█▄█ █▀ ");
        System.out.println("       █ ███ █ █▄█▀▄▀▄▀▄▀▄▀ ");
        System.out.println("       █▄▄▄▄▄█ █ ▀ ▀▄▄ ▀ █  ");
        System.out.println();
        System.out.println("       Amount to pay: RM " + String.format("%.2f", amount));

        return true;
    }

    @Override
    public String getPayMethodName() {
        return "QR Code Payment";
    }
}