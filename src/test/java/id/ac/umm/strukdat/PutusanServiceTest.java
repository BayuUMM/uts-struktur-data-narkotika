package id.ac.umm.strukdat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PutusanServiceTest {

    private PutusanService service;

    @BeforeEach
    void setUp() {
        service = new PutusanService();
    }

    private Putusan buatPutusan(String caseId) {
        return new Putusan(
                caseId,
                "123/Pid.Sus/2026/PN Mlg",
                "Pengadilan Negeri Malang",
                LocalDate.of(2026, 1, 15),
                "T001",
                "Sabu",
                2.5,
                "Pasal 112",
                "Pasal 112",
                60,
                48,
                100000000L,
                "Barang bukti contoh",
                "Keadaan memberatkan contoh",
                "Keadaan meringankan contoh",
                "Halaman 1-5"
        );
    }

    @Test
    void testTambahPutusan() {
        service.tambahPutusan(buatPutusan("C001"));

        assertEquals(1, service.jumlahPutusan());
    }

    @Test
    void testCariPutusan() {
        Putusan putusan = buatPutusan("C001");
        service.tambahPutusan(putusan);

        assertEquals(putusan, service.cariPutusan("C001"));
    }

    @Test
    void testCariPutusanTidakDitemukan() {
        assertNull(service.cariPutusan("C999"));
    }

    @Test
    void testHapusPutusan() {
        service.tambahPutusan(buatPutusan("C001"));

        assertTrue(service.hapusPutusan("C001"));
        assertEquals(0, service.jumlahPutusan());
    }

    @Test
    void testHapusPutusanTidakDitemukan() {
        assertFalse(service.hapusPutusan("C999"));
    }

    @Test
    void testCaseIdDuplikat() {
        service.tambahPutusan(buatPutusan("C001"));

        assertThrows(
                IllegalArgumentException.class,
                () -> service.tambahPutusan(buatPutusan("C001"))
        );
    }

    @Test
    void testPutusanNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> service.tambahPutusan(null)
        );
    }

    @Test
    void testGetPutusan() {
        Putusan putusan = buatPutusan("C001");
        service.tambahPutusan(putusan);

        assertEquals(putusan, service.getPutusan(0));
    }

    @Test
    void testHapusSemuaPutusan() {
        service.tambahPutusan(buatPutusan("C001"));
        service.tambahPutusan(buatPutusan("C002"));

        service.hapusSemuaPutusan();

        assertTrue(service.isEmpty());
        assertEquals(0, service.jumlahPutusan());
    }

    @Test
    void testCaseIdTidakMembedakanHurufBesarKecil() {
        service.tambahPutusan(buatPutusan("C001"));

        assertNotNull(service.cariPutusan("c001"));
        assertTrue(service.hapusPutusan("c001"));
    }
}
