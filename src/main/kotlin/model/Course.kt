package model

import objects.CourseStudents
import objects.CourseTutors
import objects.Courses
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SizedCollection
import java.time.LocalDate

class Course(
    id: EntityID<Int>
) : IntEntity(id) {
    companion object : IntEntityClass<Course>(Courses)
    var name by Courses.name

    var students by Student via CourseStudents
    var tutors by Tutor via CourseTutors

    fun addTutorByName(name: String) {
        val tutor = Tutor.new {
            this.name = name
        }
        this.tutors= SizedCollection(tutors + listOf(tutor))
    }

      fun addStudentByName(name: String) {
        val student = Student.new {
            this.name = name
        }
        this.students = SizedCollection(students + listOf(student))
    }

    fun setGrade(task: Task, student: Student, value: Int, date: LocalDate = LocalDate.now()) {
        if (value !in 0..task.maxValue) return
        Grade.new {
            this.task = task
            this.date = date
            this.student = student
            this.count = count
        }
    }

    fun setTask(name: String,
                type: Type,
                description: String,
                maxValue: Int,
                deadline: LocalDate = LocalDate.now())
    {
        Task.new  {
            this.name = name
            this.maxValue = maxValue
            this.deadline = deadline
            this.description = description
            this.type = type
        }
    }

}