# Pengenalan Concurrency

## Concurrency

Concurrency adalah kemampuan dimana suatu sistem dapat bekerja secara
selang seling dengan cara berpindah pindah.

Contohnya:

> Seseorang memasak, walaupun dia sendirian tapi dia dapat mengerjaka
> suatu pekerjaan secara berpindah pidah, misal memotong bawang,
> merebus air dan mengaduk kuah sop

## Parallel

Parallel adalah kemampuan dimana suatu sistem dapat bekerja secara
bersama sama dalam satu waktu yang bersamaan.

Contohnya:

> Bayangkan pekerjaan memasak sebelumnya dilakukan oleh satu orang
> untuk memotong bawang, merebus air dan mengaduk kuah sop.
> Bagaimana jika pekerjaan itu dilakukan oleh tiga orang sekaligus
> dalam waktu yang bersamaan. Tentula pekerjaan itu akan cepat
> terselesaikan. Ini yang disebut parallel atau simultan (terjadi 
> secara serentank di waktu yang sama)


## Synchronous vs Asynchronous

__*Synchronous*__ merupakan proses pengeksekusian kode dari atas kebawah
yang di eksekusi secara berurutan dan saling menunggu satu proses
dengan proses yang lainnya. Sedangkan __*Asynchronus*__ merupakan 
proses pengeksekusian kode yang terus berjalan dari atas kebawah
yang di eksekusi secara berurutan dan terus berjalan tanpa peduli
harus menunggu kode yang sebelumnya sudah selesai di jalankan atau belum

___

# Thread

## Thread

Thread adalah implementasi dari concurrency dan parallel.
Thread merupakan peroses ringan, membuat thread bukan berarti 
melakukan pekerjaan

## Thread Sleep

Thread sleep merupakan fitur yang bermanfaat sebagai simulasi
pengkodean. simulasi disini bermaksud mengukur kecepatan respose
dari service (kode) yang akan dibuat.

Contoh:

> Pada production, kita perlu menghubungkan kode kita dengan kode
> third party library dalam hal ini payment, karena payment adalah
> third party library kita perlu mengirim request dan paymentnya 
> perlu mengirim response. Antara mengirim reqeust dan menerima 
> response tentu diperlukan waktu (response time), untuk
> mengsimulasikan waktu pada tahap development kita bisa menggunakan *Thread sleep*

## User Thread vs Daemon Thread

__*User thread*__ adalah thread utama sedangkan __*Daemon thread*__ adalah
thread pendukung, JVM akan menunggu semua user thread selesai, sedangkan
pada daemon thread tidak akan ditunggu jika user thread tugasnya sudah selesai.

Contoh:

> Pada satu perusahaan ada staf atau pekerja kantoran (user thread)
> dan cleaning service (daemon thread). Pada saat staf atau pekerja
> kantoran sedang bekerja sembari cleaning sevice bertugas membersikan
> kantor maka kantor tidak boleh ditutup terlebih dahulu.
> namun apabila pekerja sudah selesai mengerjakan tugasnya (waktu pulang kantor)
> maka cleaning service tidak perlu meyelesaikan tugasnya membersihkan kantor
> karena tugas utama (para pekerja) sudah selesai. Cleaning service dapat
> membersikah kantor bila ada pekerja kantor yang akan masuk dilain waktu
























