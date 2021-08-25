import dao.Dao
import dao.StudentDao
import model.Student
import kotlin.system.exitProcess

class App {
    private val dao: Dao<Student, String> = StudentDao()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            App().startApp()
        }
    }

    fun startApp() {
        navigateToMainMenu()
    }

    fun printHeader() {
        println(
            """
            ======================================
            Aplikasi Data Mahasiswa
            ======================================
            1. Cetak semua Data Mahasiswa
            2. Tambah Data Mahasiswa
            3. Hapus Data Mahasiswa
            4. Keluar
            ======================================
        """.trimIndent()
        )
    }

    fun navigateToMenu(menu: String) {
        when (menu) {
            "1" -> {
                openMenuPrintStudent()
            }
            "2" -> {
                openMenuInsertStudent()
            }
            "3" -> {
                openMenuDeleteStudent()
            }
            "4" -> {
                exitProcess(0)
            }
            else -> {
                println("Pilihan tidak bisa")
            }
        }
        askToMainMenu()
    }

    private fun openMenuInsertStudent() {
        println("======================================")
        println("Nama        : ")
        val name = readLine().orEmpty()
        print("NIM         : ")
        val nim = readLine().orEmpty()
        print("Jurusan     : ")
        val major = readLine().orEmpty()
        print("Kelas       : ")
        val className = readLine().orEmpty()
        print("Universitas : ")
        val univ = readLine().orEmpty()
        dao.addData(Student(name, nim, major, className, univ))
        println("======================================")
        println("Tambah data berhasil")
    }

    private fun openMenuDeleteStudent() {
        println("======================================")
        println("Hapus data dengan NIM")
        readLine()?.let {
            dao.deleteData(it)
        }
        println("======================================")
        println("Hapus data berhasil")
    }

    private fun openMenuPrintStudent() {
        val students = dao.getData()
        if (students.isNotEmpty()) {
            students.forEachIndexed{ index, student ->
                println("======================================")
                println("Mahasiswa ke-${index+1}")
                println("======================================")
                println("Nama        : ${student.name}")
                println("NIM         : ${student.nim}")
                println("Jurusan     : ${student.major}")
                println("Kelas       : ${student.className}")
                println("Universitas : ${student.university}")
            }
        }else{
            println("======================================")
            println("Tidak ada Data!")
            println("======================================")
        }
    }

    private fun askToMainMenu() {
        println(
            """
            ======================================
            Kembali ke menu utama? (y/n)
            ======================================
        """.trimIndent()
        )
        if (readLine().equals("Y", ignoreCase = true)) {
            navigateToMainMenu()
        } else {
            exitProcess(0)
        }
    }

    private fun navigateToMainMenu() {
        printHeader()
        readLine()?.let {
            navigateToMenu(it)
        }
    }

}