package model

import persons
import repo.Item
import repo.ListRepo
import java.time.LocalDate

class Course(
    override val name: String
) : Item {
    val tutors = ArrayList<Tutor>()
    val students = ArrayList<Student>()
    val tasks = ArrayList<Task>()
    val ratings = ArrayList<Task>()

    fun addTutorByName(name: String) {
        persons[name]?.let {
            if (it is Tutor)
                tutors.add(it)
        }
    }

    fun checkRating(weights: Map<Task, Int>,sumTask: Array<Int>,controlWeek:Int){
        ratings[controlWeek] =
    }

    fun  addStudentByName(name: String) {
        persons[name]?.let {
            if (it is Student)
                students.add(it)
        }
    }

    fun addStudents(newStudents: ArrayList<Student>){
        newStudents.map{
            if(it in students)
                println("Student in course")
            else
                students.add(it)
        }
    }

    fun setGrade(taskName: String, studentName: String, value: Int, date: LocalDate = LocalDate.now()) {
        val task = tasks.find { it.name == taskName } ?: return
        val student = students.find { it.name == studentName } ?: return
        if (value !in 0..task.maxValue) return
        val grade = Grade(value, date, student)
        task.grades += grade
    }

    fun studentGrades(studentName: String) =
        tasks.map { task ->
            val value = task.grades
                .filter { it.student.name == studentName }
                .maxByOrNull { it.value }
                ?.value ?: 0
            task.name to value
        }.toMap()

    fun setTask(name:String, type: Type, description: String, maxValue: Int, deadline:LocalDate = LocalDate.now()){
        val task = Task(name,type,description,maxValue,deadline)
        tasks += task
    }

}


