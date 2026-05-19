# LatRes-PrakPBO_154

1. Menambahkan Koneksi Database MySQL

Sebelumnya:
- Data disimpan sementara menggunakan `FakeTodoRepository`
- Data akan hilang ketika aplikasi ditutup

Sekarang:
- Data disimpan permanen di database MySQL
- Data tetap ada meskipun aplikasi ditutup

2. Menambahkan Implementasi Repository MySQL

Sebelumnya:
- Repository menggunakan list dummy pada FakeTodoRepository

Sekarang:
- Dibuat repository baru bernama MysqlTodoRepository
Seluruh proses CRUD dilakukan menggunakan SQL

3. Menerapkan Arsitektur MVC

Project dikembangkan menggunakan pola MVC:

Model: Berisi representasi data dan repository.

Digunakan:
model/TodoTask.java
model/TodoRepository.java
dto/InsertTodoDTO.java
repository/MysqlTodoRepository.java

File yang ditambahkan untuk Controller
controller/TodoController.java

Fungsi:
- Menangani event button
- Menangani update tabel
- Menangani interaksi user

4. Menambahkan Service Layer

File yang ditambahkan:
service/TodoService.java

Fungsi:
- Menyimpan business logic aplikasi
- Menjadi penghubung antara Controller dan Repository

Tujuan:
- Agar controller tidak terlalu banyak logic
- Struktur aplikasi lebih clean

5. Mengubah Entry Point Aplikasi

File yang diubah:
Latres.java

Perubahan:
- Menggunakan MysqlTodoRepository
- Menghubungkan View, Service, dan Controller

Membuat Database:
todo_app.sql
