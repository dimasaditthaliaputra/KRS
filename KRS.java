import java.util.Scanner;

public class KRS {
    static Scanner input = new Scanner(System.in);
    static int baris = 0;
    static String[][] KRS = new String[20][5];;

    public static void main(String[] args) {
        do {
            System.out.println("\n=== Sistem Pemantauan KRS Mahasiswa ===");
            System.out.println("1. Tambah Data KRS");
            System.out.println("2. Tampilkan Daftar KRS Mahasiswa");
            System.out.println("3. Analisis Data KRS");
            System.out.println("4. Keluar");
            System.out.println("=======================================");
            System.out.print("Pilih menu: ");
            int menu = input.nextInt();
            input.nextLine();

            switch (menu) {
                case 1:
                    tambahKRS();
                    break;
                case 2:
                    tampilkanKRS();
                    break;
                case 3:
                    analisisKRS();
                    break;
                case 4:
                    System.out.println("Terima kasih!");
                    return;
                default:
                    System.out.println("\nMaaf Menu Tidak Tersedia, Masukkan Ulang Menu");
                    break;
            }
        } while (true);
    }

    public static void tampilkanKRS() {
        System.out.println("\n--- Tampilkan Daftar KRS Mahasiswa ---");
        do {
            System.out.print("Masukkan NIM mahasiswa: ");
            String nim = input.nextLine();

            boolean ditemukan = false;
            int totalSKS = 0;
            System.out.println("\nDaftar KRS: ");
            System.out.println("------------------------------------------------------------------------");
            System.out.printf("%-15s %-15s %-15s %-15s %-10s\n",
                    "NIM", "Nama", "Kode Mk", "Nama Matkul", "SKS");
            System.out.println("------------------------------------------------------------------------");

            for (int i = 0; i < KRS.length; i++) {
                if (KRS[i][0] != null && KRS[i][0].equals(nim)) {
                    ditemukan = true;
                    System.out.printf("%-15s %-15s %-15s %-15s %-10s\n",
                            KRS[i][0], KRS[i][1], KRS[i][2], KRS[i][3], KRS[i][4]);
                    totalSKS += Integer.parseInt(KRS[i][4]);
                }
            }

            if (ditemukan) {
                System.out.println("\nTotal SKS: " + totalSKS);
                break;
            } else {
                System.out.println("Tidak ada data untuk NIM " + nim);
                System.out.println("Masukkin lagi ganteng\n");
            }
        } while (true);
    }

    public static void analisisKRS() {
        if (baris == 0) {
            System.out.println("Belum ada data mahasiswa.");
            return;
        }

        int mahasiswaKurangSKS = 0;
        for (int i = 0; i < baris; i++) {
            if (Integer.parseInt(KRS[i][4]) < 20) {
                mahasiswaKurangSKS++;
            }
        }

        System.out.println("\n--- Analisis Data KRS ---");
        System.out.println("Jumlah mahasiswa yang mengambil SKS kurang dari 20: " + mahasiswaKurangSKS);

    }

    public static void tambahKRS() {
        int nim, sks;
        String nama, kodeMatKul, namaMatKul;

        System.out.println("\n--- Tambah Data KRS ---");
        System.out.print("Nama : ");
        nama = input.nextLine();
        System.out.print("NIM : ");
        nim = input.nextInt();
        input.nextLine();

        while (true) {
            System.out.print("Kode Mata Kuliah : ");
            kodeMatKul = input.nextLine();
            System.out.print("Nama Mata Kuliah : ");
            namaMatKul = input.nextLine();
            do {
                System.out.print("Jumlah SKS (1-3) : ");
                sks = input.nextInt();
                input.nextLine();

                if (sks < 1 || sks > 3) {
                    System.out.println("Jumlah SKS tidak valid, masukkan ulang.");
                    continue;
                } else {
                    break;
                }
            } while (true);

            KRS[baris][0] = String.format("%d", nim);
            KRS[baris][1] = nama;
            KRS[baris][2] = kodeMatKul;
            KRS[baris][3] = namaMatKul;
            KRS[baris][4] = String.format("%d", sks);
            baris++;

            System.out.println("Data mata kuliah berhasil ditambakan.");

            System.out.print("Tambah mata kuliah lain? (y/t) : ");
            String choice = input.nextLine();

            do {
                if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("t")) {
                    break;
                }
                System.out.println("Masukkan input yang benar!");
            } while (true);

            if (choice.equalsIgnoreCase("t")) {
                break;
            }
        }
    }
}