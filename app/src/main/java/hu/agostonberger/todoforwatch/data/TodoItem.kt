package hu.agostonberger.todoforwatch.data

class TodoItem {
    val id: Long
    val name: String

    constructor(id: Long, name: String) {
        this.id = id
        this.name = name
    }
}