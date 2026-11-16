# Catatan Penggunaan AI

Sesuai kebijakan UTS (Bagian 6), seluruh penggunaan AI generatif dicatat di sini.
Setiap saran AI sudah diuji, disesuaikan, dan dijelaskan oleh anggota yang menggunakannya.

## Entri

### 1. Ekstraksi data putusan (P001–P010)
- **Tanggal:** 16 november 2026
- **Anggota:** Bayu Febryan Palolongi, Muhammad Yusuf Jatiwibowo
- **Alat:** Claude (Anthropic)traksi BebasMark) dan mengisi draf kolom tab `putusan` dan `provenance`
- **Prompt/ringkasan:** meminta bantuan menentukan letak dan nilai tiap atribut (nomor perkara, tanggal putusan, jenis narkotika, berat, pasal, tuntutan, vonis, denda, barang bukti)
- **Bagian keluaran yang dipakai:** draf nilai tiap atribut beserta nomor halaman sumber
- **Perubahan yang dilakukan:** (isi: koreksi yang lu lakukan setelah cek ke PDF, misal pasal dakwaan P006 dan P007)
- **Verifikasi:** (isi: nilai dicocokkan manual ke PDF putusan; reviewer: ...)

### 2. Kerangka repository
- **Tanggal:** 16 november 2026
- **Anggota:** Bayu Febryan Palolongi, Muhammad Yusuf Jatiwibowo
- **Alat:** Claude (Anthropic)
- **Tujuan:** membuat kerangka folder, `pom.xml`, dan draf README/CONTRIBUTIONS
- **Bagian keluaran yang dipakai:** struktur folder dan file kerangka
- **Perubahan yang dilakukan:** ...
- **Verifikasi:** (isi: contoh `mvn compile` berhasil)
