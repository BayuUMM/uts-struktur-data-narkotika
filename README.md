# UTS Struktur Data: Putusan Narkotika Manokwari

Aplikasi berbasis Java untuk menyimpan dan mengolah data 50 putusan pengadilan narkotika. Proyek ini dibuat untuk memenuhi tugas Ujian Tengah Semester (UTS) mata kuliah Struktur Data dengan menerapkan struktur data secara manual tanpa menggunakan Java Collections Framework sebagai struktur utama.

## Identitas Kelompok

| Nama | NIM | Pembagian Dataset |
|---|---|---|
| Bayu Febryan Palolongi | 202510370110154 | P001–P025 |
| Muhammad Yusuf Jatiwibowo | 202510370110123 | P026–P050 |

- **Mata Kuliah:** Struktur Data
- **Dosen Pengampu:** Galih W. Wicaksono
- **Semester:** 3
- **Kelas:** B

## Teknologi yang Digunakan

- Java
- Maven
- JUnit untuk pengujian otomatis
- Git dan GitHub untuk pengelolaan versi kode

## Struktur Data yang Digunakan

### 1. Struktur Data Utama: Dynamic Array

Dynamic Array digunakan untuk menyimpan kumpulan objek `Putusan`. Struktur ini dibuat secara manual menggunakan array bertipe `Object[]`, sehingga kapasitasnya dapat bertambah ketika jumlah data melebihi kapasitas awal.

Operasi yang tersedia antara lain:
- Menambahkan data.
- Menyisipkan data.
- Mengambil dan mengubah data berdasarkan indeks.
- Menghapus data.
- Mencari data.
- Memeriksa kondisi struktur menggunakan `validateInvariant()`.

### 2. Struktur Data Sekunder: Stack

Struktur Stack diimplementasikan pada kelas `RiwayatStack` menggunakan array manual.

Operasi yang tersedia:
- `push()` untuk menambahkan data.
- `pop()` untuk mengambil dan menghapus data teratas.
- `peek()` untuk melihat data teratas.
- `size()` untuk mengetahui jumlah data.
- `clear()` untuk mengosongkan stack.
- `validateInvariant()` untuk memeriksa kondisi struktur.

Stack menerapkan prinsip LIFO (*Last In, First Out*), yaitu data yang terakhir dimasukkan akan menjadi data pertama yang dikeluarkan.

## Fitur yang Telah Diimplementasikan

- Model data putusan melalui kelas `Putusan`.
- Antarmuka struktur data generik melalui `StrukturData`.
- Penyimpanan data menggunakan `DynamicArray`.
- Pengelolaan struktur Stack melalui `RiwayatStack`.
- Operasi dasar pengelolaan data melalui `PutusanService`.
- Import dataset CSV melalui `PutusanCsvImporter`.
- Validasi header, jumlah kolom, dan format data CSV.
- Penanganan baris tidak valid agar proses import tetap dapat dilanjutkan.
- Pengujian otomatis menggunakan JUnit.

## Persyaratan Menjalankan Proyek

Pastikan perangkat sudah memiliki:
- JDK 17 atau lebih baru.
- Apache Maven.
- Git (opsional, jika ingin mengambil repository).

## Cara Mengambil Repository

```bash
git clone https://github.com/BayuUMM/uts-struktur-data-narkotika.git
cd uts-struktur-data-narkotika
```

## Cara Build

Untuk melakukan kompilasi seluruh kode program:

```bash
mvn compile
```

## Cara Menjalankan Pengujian

Untuk menjalankan seluruh pengujian otomatis:

```bash
mvn test
```

Untuk membersihkan hasil build sebelumnya sekaligus menjalankan pengujian:

```bash
mvn clean test
```

**Catatan:** Repository ini berisi implementasi kelas dan pengujian otomatis. Perintah menjalankan aplikasi melalui `Main` belum dicantumkan karena belum ada kelas entry point yang dikonfirmasi.

## Struktur Repository

```text
uts-struktur-data-narkotika/
├── data/
│   └── putusan-50.csv
├── docs/
│   ├── adr/
│   ├── trace/
│   ├── demo/
│   └── data-provenance.csv
├── src/
│   ├── main/
│   │   └── java/id/ac/umm/strukdat/
│   │       ├── DynamicArray.java
│   │       ├── Putusan.java
│   │       ├── PutusanCsvImporter.java
│   │       ├── PutusanService.java
│   │       ├── RiwayatStack.java
│   │       └── StrukturData.java
│   └── test/
│       └── java/id/ac/umm/strukdat/
├── AI_USAGE.md
├── CONTRIBUTIONS.md
├── pom.xml
└── README.md
```

## Dataset

Dataset yang digunakan terdiri dari 50 data putusan pengadilan narkotika dengan kode perkara P001 sampai P050.

Untuk menjaga privasi, identitas terdakwa dianonimkan menggunakan kode seperti T001, T002, dan seterusnya.

Ketentuan pengolahan dataset:
- Nilai numerik yang tidak ditemukan diisi dengan `0`.
- Nilai teks atau tanggal yang tidak ditemukan diisi dengan `null`.
- Informasi sumber data dicatat dalam `docs/data-provenance.csv`.
- Dataset disimpan dalam format CSV agar dapat diproses oleh program.

## Dokumentasi

Dokumentasi tambahan dalam repository:

| File/Folder | Keterangan |
|---|---|
| `docs/adr/` | Catatan keputusan desain dan pemilihan struktur data |
| `docs/data-provenance.csv` | Informasi asal data dan atribut dataset |
| `docs/trace/` | Dokumentasi penelusuran proses |
| `docs/demo/` | Dokumentasi demonstrasi program |
| `AI_USAGE.md` | Catatan penggunaan bantuan AI |
| `CONTRIBUTIONS.md` | Pembagian dan catatan kontribusi anggota kelompok |

## Tujuan Proyek

Proyek ini bertujuan menerapkan konsep struktur data dan pemrograman berorientasi objek dalam pengelolaan data putusan pengadilan. Melalui proyek ini, mahasiswa diharapkan memahami implementasi struktur data secara manual, pengolahan data CSV, validasi data, serta pengujian program menggunakan Java.
