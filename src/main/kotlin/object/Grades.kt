package `object`

import objects.Tasks
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.`java-time`.date

object Grades : IntIdTable() {
    override val id: Column<EntityID<Int>> = integer("id").autoIncrement().entityId()
    val value = integer("value")
    val date = date("date")

    val student = reference("student", Students)
    val task = reference("task", Tasks)
    override val primaryKey = PrimaryKey(id, name = "PK_GRADE")
}