import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KRS {
    static Scanner input = new Scanner(System.in);
    static List<String[]> KRS = new ArrayList<>();

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
        if (KRS.isEmpty()) {
            System.out.println("Data Tidak Tersedia");
            return;
        }
        
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

            for (String[] data : KRS) {
                if (data[0].equals(nim)) {
                    ditemukan = true;
                    System.out.printf("%-15s %-15s %-15s %-15s %-10s\n",
                            data[0], data[1], data[2], data[3], data[4]);
                    totalSKS += Integer.parseInt(data[4]);
                }
            }

            if (ditemukan) {
                System.out.println("\nTotal SKS: " + totalSKS);
                break;
            } else {
                System.out.println("Tidak ada data untuk NIM " + nim);
            }
        } while (true);
    }

    public static void analisisKRS() {
        if (KRS.isEmpty()) {
            System.out.println("Belum ada data mahasiswa.");
            return;
        }

        int mahasiswaKurangSKS = 0;
        for (String[] data : KRS) {
            if (Integer.parseInt(data[4]) < 20) {
                mahasiswaKurangSKS++;
            }
        }

        System.out.println("\n--- Analisis Data KRS ---");
        System.out.println("Jumlah mahasiswa yang mengambil SKS kurang dari 20: " + mahasiswaKurangSKS);
    }

    public static void tambahKRS() {
        int nim, sks, totalSKS, maxSKS;
        String nama, kodeMatKul, namaMatKul;
        totalSKS = 0;
        maxSKS = 24;

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
            int sisaSKS = maxSKS - totalSKS;
            System.out.println("Sisa SKS yang tersedia: " + sisaSKS);

            do {
                System.out.print("Jumlah SKS (1-3) : ");
                sks = input.nextInt();
                input.nextLine();

                if (sks < 1 || sks > 3) {
                    System.out.println("Jumlah SKS tidak valid, masukkan ulang.");
                    continue;
                } else {
                    if (totalSKS + sks > maxSKS) {
                        System.out.println("Total SKS tidak boleh lebih dari 24. Total SKS saat ini: " + totalSKS);
                        continue;
                    } else {
                        totalSKS += sks;
                        break;
                    }
                }
            } while (true);

            KRS.add(new String[] {
                    String.valueOf(nim),
                    nama,
                    kodeMatKul,
                    namaMatKul,
                    String.valueOf(sks)
            });

            System.out.println("Data mata kuliah berhasil ditambahkan.");

            String choice;
            while (true) {
                System.out.print("Tambah mata kuliah lain? (y/t) : ");
                choice = input.nextLine();

                if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("t")) {
                    break;
                } else {
                    System.out.println("Input Salah, Mohon masukkan input yang benar!");
                }
            }

            if (choice.equalsIgnoreCase("t")) {
                break;
            }
        }
    }
}