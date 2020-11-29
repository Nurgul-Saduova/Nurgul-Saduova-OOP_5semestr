package objects

import `object`.Grades
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Ratings : IntIdTable() {
    override val id: Column<EntityID<Int>> = integer("id").autoIncrement().entityId() // Column<Int>
    val value = Grades.integer("value")

    override val primaryKey = PrimaryKey(id, name = "PK_RATING")

}