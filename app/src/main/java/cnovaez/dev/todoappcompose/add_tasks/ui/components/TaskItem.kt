package cnovaez.dev.todoappcompose.add_tasks.ui.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import cnovaez.dev.todoappcompose.add_tasks.ui.TaskViewModel
import cnovaez.dev.todoappcompose.add_tasks.ui.model.TaskModel

/**
 ** Created by Carlos A. Novaez Guerrero on 11/2/2023 8:09 PM
 ** cnovaez.dev@outlook.com
 **/

@Composable
fun TaskItem(task: TaskModel, taskViewModel: TaskViewModel) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = { /*taskViewModel.onItemLongPress(task)*/
                    taskViewModel.deleteTaskFromMemory(task)
                    taskViewModel.showDeleteSnackBar(true, task)
                },
                    onTap = {
                        taskViewModel.showNewTaskDialog(task)
                    }
                )


            }
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = task.description,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null,
            )
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = { taskViewModel.updateTaskCheckState(task) })
        }
    }
}

