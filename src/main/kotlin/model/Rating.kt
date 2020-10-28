package model

import repo.Item

class Rating (
    val weights: Map<Task, Int>,
    val sumTask: Array<Int>,
    val course: Course,
    val student: Student,
    var value: Int
) {

    val ratings = mutableMapOf<String, Double>()


}