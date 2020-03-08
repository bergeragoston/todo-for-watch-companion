package hu.agostonberger.todoforwatch

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import hu.agostonberger.todoforwatch.data.LocalTodoService

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.slf4j.LoggerFactory

class MainActivity : AppCompatActivity() {

    companion object {
        private val log = LoggerFactory.getLogger(MainActivity::class.java)
    }

    val localTodoService: LocalTodoService = LocalTodoService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        localTodoService.incompleteTodoItems.forEach { todoItem ->
            log.info("Loading item {}", todoItem.name)
            val element = LinearLayout(this)
            element.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            val textField = TextView(this)
            textField.text = todoItem.name
            val checkBox = CheckBox(this)
            element.addView(textField)
            element.addView(checkBox)
            checkBox.setOnClickListener { view ->
                if (view is CheckBox) {
                    val checked: Boolean = view.isChecked
                    if(checked) {
                        localTodoService.markItemAsDone(todoItem)
                    }
                }
            }

            todoList.addView(element)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
