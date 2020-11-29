package model

import objects.Types
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Type (
    id: EntityID<Int>
) : IntEntity(id) {
    companion object : IntEntityClass<Type>(Types)
    var name by Types.name
    var shortName by Types.shortName
}