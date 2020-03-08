package hu.agostonberger.todoforwatch.data

internal interface TodoService {
    val incompleteTodoItems: List<TodoItem>

    fun markItemAsDone(todoItem: TodoItem)
}
