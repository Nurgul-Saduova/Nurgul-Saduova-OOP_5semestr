package model

import objects.CourseTutors
import objects.Tutors
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Tutor(
    id: EntityID<Int>
) : Person(id) {
    companion object : IntEntityClass<Tutor>(Tutors)
    var name by Tutors.name
    var post by Tutors.post

    var course by Course via CourseTutors
}