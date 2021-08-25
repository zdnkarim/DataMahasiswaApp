package dao

import model.Student

class StudentDao : Dao<Student, String> {
    private var student = arrayListOf<Student>().apply {
        add(Student("Paijo", "001", "Information System", "201A", "Airlangga"))
        add(Student("Painem", "002", "Math", "301A", "Airlangga"))
        add(Student("Paija", "003", "Physic", "101A", "Airlangga"))
        add(Student("Tukimen", "004", "Biology", "201A", "Airlangga"))
        add(Student("Tukiyem", "005", "Physic", "101A", "Airlangga"))
    }

    override fun getData(): List<Student> {
        return student
    }

    override fun addData(item: Student) {
        student.add(item)
    }

    override fun deleteData(uniqueID: String) {
        student.remove(student.find { student ->
            student.nim == uniqueID
        })
    }
}