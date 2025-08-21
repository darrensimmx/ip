public class TaskList {
    private final Task[] tasks = new Task[100];
    private int size = 0;

    public void add(Task t) {
        if (size < tasks.length) {
            tasks[size] = t;
            size++;
        } else {
            System.out.println("Task list is full!");
        }
    }

    public int size() {
        return size;
    }

    public Task get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return tasks[index];
    }


    String toNumberedString() {
        if (size == 0) return "(no tasks yet)";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(i + 1).append(". ").append(tasks[i]).append("\n");
        }
        return sb.toString().trim();
    }

}
