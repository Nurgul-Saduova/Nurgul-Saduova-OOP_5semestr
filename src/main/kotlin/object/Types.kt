package objects

import `object`.Grades
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Types : IntIdTable() {
    override val id: Column<EntityID<Int>> = Grades.integer("id").autoIncrement().entityId()
    val name = varchar("name", 50)
    val shortName = varchar("shortName", 50)

    override val primaryKey = PrimaryKey(id, name = "PK_TYPE")
}