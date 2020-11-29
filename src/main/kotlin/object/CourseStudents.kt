package objects

import `object`.Students
import org.jetbrains.exposed.dao.id.IntIdTable

object CourseStudents : IntIdTable("course_in_students") {
    val course = reference("course", Courses)
    val student = reference("student", Students)
    override val primaryKey =
        PrimaryKey(course, student, name = "PK_COURSE_STUDENT")
}