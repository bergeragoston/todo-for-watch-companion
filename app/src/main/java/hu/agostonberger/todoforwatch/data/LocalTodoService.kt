package hu.agostonberger.todoforwatch.data

import org.slf4j.LoggerFactory

class LocalTodoService : TodoService {

    companion object {
        private val log = LoggerFactory.getLogger(LocalTodoService::class.java)
    }

    override val incompleteTodoItems: List<TodoItem>
        get() {
            log.info("Item GET called")
            //TODO
            return listOf(TodoItem(1, "kenyér"), TodoItem(2, "karamalz"), TodoItem(3, "sajt"))
        }

    override fun markItemAsDone(todoItem: TodoItem) {
        log.info("Mark item as done: {}", todoItem.name)
        //TODO
    }
}