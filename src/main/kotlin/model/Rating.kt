package model

import objects.Ratings
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

open class Rating(
    id: EntityID<Int>
) : IntEntity(id) {
    companion object : IntEntityClass<Rating>(Ratings)
    var value by Ratings.value


}