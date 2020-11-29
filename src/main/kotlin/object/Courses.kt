package objects


import `object`.Grades
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Courses : IntIdTable() {
    override val id: Column<EntityID<Int>> = integer("id").autoIncrement().entityId()
    var name = varchar("name", length = 50)

    override val primaryKey = PrimaryKey(id, name = "PK_COURSE")
}