
package id.ac.umm.strukdat;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class PutusanCsvImporter {

    private static final String[] HEADER = {
            "caseId",
            "nomorPerkara",
            "pengadilan",
            "tanggalPutusan",
            "terdakwaKode",
            "jenisNarkotika",
            "beratBarangBuktiGram",
            "pasalDakwaan",
            "pasalTerbukti",
            "tuntutanBulan",
            "vonisBulan",
            "dendaRupiah",
            "barangBukti",
            "keadaanMemberatkan",
            "keadaanMeringankan",
            "sourcePages"
    };

    public record ImportResult(
            int berhasil,
            int gagal,
            String detailError
    ) {
    }

    public ImportResult importFile(
            Path filePath,
            DynamicArray<Putusan> tujuan
    ) throws IOException {

        if (filePath == null || tujuan == null) {
            throw new IllegalArgumentException(
                    "File dan tujuan import tidak boleh null."
            );
        }

        int berhasil = 0;
        int gagal = 0;
        int nomorBaris = 0;

        StringBuilder detailError = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {

            String baris = reader.readLine();
            nomorBaris++;

            if (baris == null) {
                throw new IllegalArgumentException("File CSV kosong.");
            }

            String[] header = parseCsvLine(baris);

            if (!isValidHeader(header)) {
                throw new IllegalArgumentException(
                        "Header CSV tidak sesuai dengan 16 atribut Putusan."
                );
            }

            while ((baris = reader.readLine()) != null) {
                nomorBaris++;

                if (baris.isBlank()) {
                    continue;
                }

                try {
                    String[] kolom = parseCsvLine(baris);

                    if (kolom.length != HEADER.length) {
                        throw new IllegalArgumentException(
                                "Jumlah kolom harus 16, ditemukan "
                                        + kolom.length
                        );
                    }

                    Putusan putusan = convertToPutusan(kolom);

                    tujuan.add(putusan);
                    berhasil++;

                } catch (RuntimeException e) {
                    gagal++;

                    detailError.append("Baris ")
                            .append(nomorBaris)
                            .append(": ")
                            .append(e.getMessage())
                            .append(System.lineSeparator());
                }
            }
        }

        return new ImportResult(
                berhasil,
                gagal,
                detailError.toString()
        );
    }

    private Putusan convertToPutusan(String[] kolom) {

        for (int i = 0; i < kolom.length; i++) {
            kolom[i] = kolom[i].trim();
        }

        if (kolom[0].isEmpty()
                || kolom[1].isEmpty()
                || kolom[2].isEmpty()
                || kolom[4].isEmpty()
                || kolom[5].isEmpty()) {

            throw new IllegalArgumentException(
                    "Ada atribut wajib yang kosong."
            );
        }

        LocalDate tanggal = LocalDate.parse(kolom[3]);

        double berat = Double.parseDouble(kolom[6]);
        int tuntutan = Integer.parseInt(kolom[9]);
        int vonis = Integer.parseInt(kolom[10]);
        long denda = Long.parseLong(kolom[11]);

        if (!Double.isFinite(berat)
                || berat < 0
                || tuntutan < 0
                || vonis < 0
                || denda < 0) {

            throw new IllegalArgumentException(
                    "Nilai numerik tidak boleh negatif atau tidak valid."
            );
        }

        return new Putusan(
                kolom[0],
                kolom[1],
                kolom[2],
                tanggal,
                kolom[4],
                kolom[5],
                berat,
                kolom[7],
                kolom[8],
                tuntutan,
                vonis,
                denda,
                kolom[12],
                kolom[13],
                kolom[14],
                kolom[15]
        );
    }

    private boolean isValidHeader(String[] header) {

        if (header.length != HEADER.length) {
            return false;
        }

        for (int i = 0; i < HEADER.length; i++) {
            if (!HEADER[i].equals(header[i].trim())) {
                return false;
            }
        }

        return true;
    }

    private String[] parseCsvLine(String line) {

        StringBuilder[] fields = new StringBuilder[line.length() + 1];

        int jumlahKolom = 0;
        boolean dalamKutip = false;
        boolean setelahKutip = false;

        fields[0] = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {

            char karakter = line.charAt(i);

            if (dalamKutip) {

                if (karakter == '"') {

                    if (i + 1 < line.length()
                            && line.charAt(i + 1) == '"') {

                        fields[jumlahKolom].append('"');
                        i++;

                    } else {
                        dalamKutip = false;
                        setelahKutip = true;
                    }

                } else {
                    fields[jumlahKolom].append(karakter);
                }

            } else {

                if (karakter == ',') {

                    jumlahKolom++;
                    fields[jumlahKolom] = new StringBuilder();
                    setelahKutip = false;

                } else if (karakter == '"') {

                    if (fields[jumlahKolom].length() != 0
                            || setelahKutip) {

                        throw new IllegalArgumentException(
                                "Format tanda kutip CSV tidak valid."
                        );
                    }

                    dalamKutip = true;

                } else {

                    if (setelahKutip) {
                        throw new IllegalArgumentException(
                                "Ada karakter setelah tanda kutip."
                        );
                    }

                    fields[jumlahKolom].append(karakter);
                }
            }
        }

        if (dalamKutip) {
            throw new IllegalArgumentException(
                    "Tanda kutip CSV tidak berpasangan."
            );
        }

        String[] hasil = new String[jumlahKolom + 1];

        for (int i = 0; i <= jumlahKolom; i++) {
            hasil[i] = fields[i].toString();
        }

        return hasil;
    }
}
