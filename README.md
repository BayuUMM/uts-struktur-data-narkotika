# UTS Struktur Data: Putusan Narkotika (Kelompok XX)

Aplikasi Java berorientasi objek untuk menyimpan dan mengolah 50 putusan pengadilan narkotika
dengan struktur data yang dibuat dari nol (tanpa koleksi bawaan `java.util`).

## Identitas Kelompok
| Nama | NIM | Bagian data |
|---|---|---|
| Bayu Febryan Palolongi | 202510370110154 | P001–P025 |
| Yusuf (isi nama lengkap) | (isi NIM) | P026–P050 |

Mata kuliah: Struktur Data | Dosen: Galih W. Wicaksono | Semester 3, Kelas B/D

## Struktur Data yang Dipilih
- Struktur utama: **(isi: array dinamis / singly linked list / stack / queue)**
- Struktur sekunder: **(isi: untuk audit / riwayat / antrean validasi)**
- Alasan pemilihan: lihat `docs/adr/`

## Cara Build
```bash
mvn compile
```

## Cara Test
```bash
mvn test
```

## Cara Menjalankan
```bash
(isi perintah, contoh: java -cp target/classes <package>.Main)
```

## Contoh Perintah
(isi contoh penggunaan: impor, cari, filter, agregasi, urutan, audit, ekspor)

## Struktur Repository
```
src/main/java/            kode program
src/test/java/            test otomatis
data/putusan-50.csv       dataset 50 putusan (tanpa PDF sumber)
docs/data-provenance.csv  asal tiap atribut penting
docs/adr/                 keputusan desain (minimal 2)
docs/trace/               trace manual
docs/demo/                bukti demo skenario
AI_USAGE.md               catatan penggunaan AI
CONTRIBUTIONS.md          kontribusi tiap anggota
```

## Dataset
50 putusan, nama terdakwa dianonimkan dengan kode. Nilai yang tidak ditemukan ditulis `0` (angka) atau `null` (teks/tanggal).
Aturan pengisian dan agregasi: lihat tab `aturan` pada template dataset.
