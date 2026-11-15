
package id.ac.umm.strukdat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class PutusanCsvImporterTest {

    @TempDir
    Path tempDir;

    private final PutusanCsvImporter importer =
            new PutusanCsvImporter();

    private static final String HEADER =
            "caseId,nomorPerkara,pengadilan,tanggalPutusan,"
                    + "terdakwaKode,jenisNarkotika,beratBarangBuktiGram,"
                    + "pasalDakwaan,pasalTerbukti,tuntutanBulan,"
                    + "vonisBulan,dendaRupiah,barangBukti,"
                    + "keadaanMemberatkan,keadaanMeringankan,sourcePages";

    private static final String DATA_VALID =
            "P001,285/Pid.Sus/2025/PN Mnk,PN Manokwari,"
                    + "2026-03-11,T001,sabu,24.26,"
                    + "\"Pasal 114; Pasal 112\",Pasal 114,"
                    + "108,96,510000000,\"HP VIVO; sabu\","
                    + "meresahkan masyarakat,mengakui perbuatan,"
                    + "\"identitas:1;tuntutan:2\"";

    @Test
    void testImportSatuDataValid() throws IOException {

        Path file = tempDir.resolve("valid.csv");

        Files.writeString(file, HEADER + "\n" + DATA_VALID);

        DynamicArray<Putusan> data = new DynamicArray<>();

        PutusanCsvImporter.ImportResult result =
                importer.importFile(file, data);

        assertEquals(1, result.berhasil());
        assertEquals(0, result.gagal());
        assertEquals(1, data.size());
        assertTrue(data.validateInvariant());
    }

    @Test
    void testAtributPutusanTerbaca() throws IOException {

        Path file = tempDir.resolve("atribut.csv");

        Files.writeString(file, HEADER + "\n" + DATA_VALID);

        DynamicArray<Putusan> data = new DynamicArray<>();

        importer.importFile(file, data);

        Putusan putusan = data.get(0);

        assertEquals("P001", putusan.caseId());
        assertEquals("PN Manokwari", putusan.pengadilan());
        assertEquals("T001", putusan.terdakwaKode());
        assertEquals(24.26, putusan.beratBarangBuktiGram());
        assertEquals(108, putusan.tuntutanBulan());
        assertEquals(96, putusan.vonisBulan());
        assertEquals(510000000L, putusan.dendaRupiah());
    }

    @Test
    void testBarisTidakValidTidakMenghentikanImport()
            throws IOException {

        Path file = tempDir.resolve("campuran.csv");

        Files.writeString(
                file,
                HEADER + "\n"
                        + DATA_VALID + "\n"
                        + "P002,data-tidak-lengkap"
        );

        DynamicArray<Putusan> data = new DynamicArray<>();

        PutusanCsvImporter.ImportResult result =
                importer.importFile(file, data);

        assertEquals(1, result.berhasil());
        assertEquals(1, result.gagal());
        assertEquals(1, data.size());
        assertTrue(data.validateInvariant());
    }

    @Test
    void testImportBeberapaData() throws IOException {

        Path file = tempDir.resolve("multiple.csv");

        Files.writeString(
                file,
                HEADER + "\n"
                        + DATA_VALID + "\n"
                        + DATA_VALID.replace("P001", "P002")
                        + "\n"
                        + DATA_VALID.replace("P001", "P003")
        );

        DynamicArray<Putusan> data = new DynamicArray<>();

        PutusanCsvImporter.ImportResult result =
                importer.importFile(file, data);

        assertEquals(3, result.berhasil());
        assertEquals(0, result.gagal());
        assertEquals(3, data.size());
        assertTrue(data.validateInvariant());
    }

    @Test
    void testHeaderTidakSesuai() throws IOException {

        Path file = tempDir.resolve("header-salah.csv");

        Files.writeString(
                file,
                "id,nama,tanggal\n" + DATA_VALID
        );

        DynamicArray<Putusan> data = new DynamicArray<>();

        assertThrows(
                IllegalArgumentException.class,
                () -> importer.importFile(file, data)
        );

        assertTrue(data.isEmpty());
    }

    @Test
    void testTanggalTidakValidDitolak() throws IOException {

        Path file = tempDir.resolve("tanggal.csv");

        String dataSalah =
                DATA_VALID.replace("2026-03-11", "tanggal-salah");

        Files.writeString(file, HEADER + "\n" + dataSalah);

        DynamicArray<Putusan> data = new DynamicArray<>();

        PutusanCsvImporter.ImportResult result =
                importer.importFile(file, data);

        assertEquals(0, result.berhasil());
        assertEquals(1, result.gagal());
        assertTrue(data.isEmpty());
    }
    @Test
    void testImportDatasetAsli() throws IOException {

        Path file = Path.of("data", "putusan-50.csv");

        DynamicArray<Putusan> data = new DynamicArray<>();

        PutusanCsvImporter.ImportResult result =
                importer.importFile(file, data);

        System.out.println("\n===== HASIL IMPORT DATASET ASLI =====");
        System.out.println("Berhasil : " + result.berhasil());
        System.out.println("Gagal    : " + result.gagal());
        System.out.println("Total    : "
                + (result.berhasil() + result.gagal()));
        System.out.println("Invariant: " + data.validateInvariant());
        System.out.println("Detail error:\n" + result.detailError());

        System.out.println("Contoh P001: " + data.get(0));
        System.out.println("Contoh P004: " + data.get(3));

        assertEquals(50, result.berhasil() + result.gagal());
        assertEquals(result.berhasil(), data.size());
        assertTrue(data.validateInvariant());
    }
}
