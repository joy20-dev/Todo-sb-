const apiUrl = '/tasks';

const taskList = document.getElementById('task-list');
const taskTitle = document.getElementById('task-title');
const taskDesc = document.getElementById('task-desc');
const addTaskBtn = document.getElementById('add-task-btn');

// Fetch all tasks
async function fetchTasks() {
  const res = await fetch(apiUrl);
  const tasks = await res.json();
  taskList.innerHTML = '';

  tasks.forEach(task => {
    const li = document.createElement('li');

    // Checkbox for completed
    const checkbox = document.createElement('input');
    checkbox.type = 'checkbox';
    checkbox.checked = task.completed;
    checkbox.addEventListener('change', () => toggleCompleted(task, checkbox.checked));

    // Task info
    const infoDiv = document.createElement('div');
    infoDiv.className = 'task-info' + (task.completed ? ' completed' : '');
    
    const titleInput = document.createElement('input');
    titleInput.type = 'text';
    titleInput.value = task.title;
    titleInput.style.fontWeight = 'bold';

    const descInput = document.createElement('input');
    descInput.type = 'text';
    descInput.value = task.task;

    // Update task on blur
    [titleInput, descInput].forEach(input => {
      input.addEventListener('blur', () => updateTask(task.id, titleInput.value, descInput.value, checkbox.checked));
    });

    infoDiv.appendChild(titleInput);
    infoDiv.appendChild(descInput);

    // Delete button
    const delBtn = document.createElement('button');
    delBtn.textContent = 'Delete';
    delBtn.addEventListener('click', () => deleteTask(task.id));

    li.appendChild(checkbox);
    li.appendChild(infoDiv);
    li.appendChild(delBtn);

    taskList.appendChild(li);
  });
}

// Add new task
addTaskBtn.addEventListener('click', async () => {
  const title = taskTitle.value.trim();
  const task = taskDesc.value.trim();
  if (!title) return;

  await fetch(apiUrl, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ title, task, completed: false })
  });

  taskTitle.value = '';
  taskDesc.value = '';
  fetchTasks();
});

// Update task
async function updateTask(id, title, taskDesc, completed) {
  await fetch(`${apiUrl}/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ title, task: taskDesc, completed })
  });
  fetchTasks();
}

// Toggle completed
async function toggleCompleted(task, completed) {
  await updateTask(task.id, task.title, task.task, completed);
}

// Delete task
async function deleteTask(id) {
  await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });
  fetchTasks();
}

// Initial load
fetchTasks();
