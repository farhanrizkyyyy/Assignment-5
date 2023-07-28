import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static void endProgram(Timer timer, Scanner scanner, boolean isTimeOut, int randomNumber) {
        timer.cancel(); // stop timer jika waktu habis atau tebakan user benar
        if (isTimeOut) System.out.println("\n\nWaktu anda telah habis. Jawaban yang benar adalah " + randomNumber + ".");
        else System.out.println();
        System.out.println("Terimakasih telah bermain. Sampai jumpa!");
        if (isTimeOut) System.exit(0); // keluar program jika waktu habis
        scanner.close(); // tutup scanner jika waktu habis atau tebakan user benar
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // inisialisasi objek scanner
        Random random = new Random(); // inisialisasi objek random
        int randomNumber, answer, attempt = 1;
        randomNumber = random.nextInt(101); // generate satu angka random antara 0 dan 101
        Timer timer = new Timer(); // inisialisasi objek timer
        TimerTask timertask = new TimerTask() {
            int i = 1; // set value detik awal di 1

            public void run() {
                if (i == 60) endProgram(timer, scanner, true, randomNumber); // jika nilai i == 60, maka timer distop
                i++; // increment variabel i
            }
        };

        timer.schedule(timertask, 0, 1000); // mulai timer 0ms setelah program dijalankan dan increment detik setiap 1000ms

        System.out.println("Selamat datang di Permainan Tebak Angka!");
        System.out.println("Angka telah dipilih secara acak antara 1-100.");
        System.out.println("Anda memiliki 1 menit untuk menebak angka tersebut.\n");

        System.out.println("Mulai tebak angka:");

        while (true) {
            System.out.print("Tebak angka (1-100): ");
            try {
                answer = scanner.nextInt(); // input tebakan user
                if (answer >= 1 && answer <= 100) { // handle jika user input angka < 1 dan > 100
                    if (answer == randomNumber) { // output jika tebakan user dengan angka hasil generate sama
                        System.out.println("Selamat, tebakan anda benar!");
                        System.out.println("Anda berhasil menebak angka " + randomNumber + " dalam " + attempt + " kali percobaan.");
                        break;
                    } else {
                        System.out.print("Tebakan anda terlalu"); // output jika tebakan user dengan angka hasil generate sama
                        if (answer < randomNumber) System.out.println(" rendah.\n");
                        else System.out.println(" tinggi.\n");
                        attempt++; // increment nilai percobaan
                    }
                } else {
                    System.out.println("Input yang diterima hanya angka 1-100.\n"); // output jika tebakan user dengan angka hasil generate tidak sama
                }
            } catch (Exception e) {
                System.out.println("Input yang diterima hanya angka 1-100.\n"); // output jika user input bukan angka
                scanner.next();
            }
        }

        endProgram(timer, scanner, false, randomNumber);
    }
}