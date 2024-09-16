package com.example.statemanagement

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Suppress("ktlint:standard:function-naming")
@Composable
fun TaskList(
    list: List<Task>,
    onCloseTask: (Task) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = Modifier,
    ) {
        items(items = list, key = { task -> task.id }) { task ->
            TaskItem(taskName = task.label, onClose = { onCloseTask(task) })
        }
    }
}
