package model

import repo.Item

class Rating (
    val weights: Map<Task, Int>,
    val sumTask: Array<Int>
) {

}