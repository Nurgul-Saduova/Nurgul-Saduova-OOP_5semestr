package objects

import org.jetbrains.exposed.dao.id.IntIdTable

object CourseTutors : IntIdTable("course_in_tutors") {
    val course = CourseStudents.reference("course", Courses)
    val tutor = CourseStudents.reference("tutor", Tutors)
    override val primaryKey = PrimaryKey(course, tutor, name = "PK_COURSE_TUTOR")
}