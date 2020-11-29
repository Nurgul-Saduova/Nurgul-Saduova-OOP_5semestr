package `object`

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Students : IntIdTable() {
    override val id: Column<EntityID<Int>> = Grades.integer("id").autoIncrement().entityId()
    val name = varchar("name", length = 50)
    val group = varchar("group", length = 50)

    override val primaryKey = PrimaryKey(id, name = "PK_STUDENT")
}