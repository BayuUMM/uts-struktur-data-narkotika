package id.ac.umm.strukdat;

public class PutusanService {

    private final DynamicArray<Putusan> daftarPutusan;

    public PutusanService() {
        daftarPutusan = new DynamicArray<>();
    }

    // Menambahkan putusan
    public void tambahPutusan(Putusan putusan) {
        if (putusan == null) {
            throw new IllegalArgumentException(
                    "Putusan tidak boleh null"
            );
        }

        if (cariPutusan(putusan.caseId()) != null) {
            throw new IllegalArgumentException(
                    "Case ID sudah terdaftar"
            );
        }

        daftarPutusan.add(putusan);
    }

    // Mencari putusan berdasarkan Case ID
    public Putusan cariPutusan(String caseId) {
        if (caseId == null || caseId.isBlank()) {
            throw new IllegalArgumentException(
                    "Case ID tidak boleh kosong"
            );
        }

        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan putusan = daftarPutusan.get(i);

            if (putusan.caseId().equalsIgnoreCase(caseId)) {
                return putusan;
            }
        }

        return null;
    }

    // Menghapus putusan berdasarkan Case ID
    public boolean hapusPutusan(String caseId) {
        if (caseId == null || caseId.isBlank()) {
            throw new IllegalArgumentException(
                    "Case ID tidak boleh kosong"
            );
        }

        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan putusan = daftarPutusan.get(i);

            if (putusan.caseId().equalsIgnoreCase(caseId)) {
                daftarPutusan.remove(i);
                return true;
            }
        }

        return false;
    }

    // Mengambil putusan berdasarkan indeks
    public Putusan getPutusan(int index) {
        return daftarPutusan.get(index);
    }

    // Menghitung jumlah putusan
    public int jumlahPutusan() {
        return daftarPutusan.size();
    }

    // Memeriksa apakah data kosong
    public boolean isEmpty() {
        return daftarPutusan.isEmpty();
    }

    // Menghapus seluruh data
    public void hapusSemuaPutusan() {
        daftarPutusan.clear();
    }
}
