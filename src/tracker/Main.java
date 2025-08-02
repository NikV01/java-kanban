package tracker;
import tracker.controllers.InMemoryTaskManager;
import tracker.controllers.Managers;
import tracker.controllers.TaskManager;
import tracker.model.Epic;
import tracker.model.Subtask;
import tracker.model.Task;
import tracker.model.TaskStatus;
public class Main {
    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();


        Task task1 = new Task(0, "Прогуляться", "Выйти на улицу", TaskStatus.NEW);
        Task task2 = new Task(0, "Попить", "Выпить воды", TaskStatus.NEW);
        Epic epic1 = new Epic(0, "Переезд ", "Организовать переезд");
        Subtask subtask1 = new Subtask(0, "Упаковать вещи", "Разобрать и упаковать мебель", TaskStatus.NEW, epic1);
        Subtask subtask2 = new Subtask(0, "Заказать транспорт", "Нанять машину для перевозки", TaskStatus.NEW, epic1);
        Epic epic2 = new Epic(0, "Заварить чай", "Сделать горячий чай");
        Subtask subtask3 = new Subtask(0, "Залить воду в чашку с чаем", "Налить горячую воду в чашку с чайным пакетиком", TaskStatus.NEW, epic2);

        manager.addTask(task1);
        manager.addTask(task2);
        manager.addEpic(epic1);
        manager.addSubtask(subtask1);
        manager.addSubtask(subtask2);
        manager.addEpic(epic2);
        manager.addSubtask(subtask3);
        System.out.println("Задачи, подзадачи, эпики:");
        System.out.println(task1);
        System.out.println(task2);
        System.out.println(epic1);
        System.out.println(subtask1);
        System.out.println(subtask2);
        System.out.println(epic2);
        System.out.println(subtask3);

        task1.setStatus(TaskStatus.DONE);
        subtask1.setStatus(TaskStatus.DONE);
        subtask2.setStatus(TaskStatus.IN_PROGRESS);
        subtask3.setStatus(TaskStatus.DONE);
        manager.updateTask(task1);
        manager.updateSubtask(subtask1);
        manager.updateSubtask(subtask2);
        manager.updateSubtask(subtask3);

        System.out.println("После изменения статусов:");
        System.out.println(task1);
        System.out.println(task2);
        System.out.println(epic1);
        System.out.println(subtask1);
        System.out.println(subtask2);
        System.out.println(epic2);
        System.out.println(subtask3);

        manager.removeTaskById(task2.getId());
        manager.removeEpicById(epic2.getId());

        System.out.println("После удаления:");
        System.out.println("Задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getAllEpics()) {
            System.out.println(epic);
        }
        System.out.println("Подзадачи:");
        for (Task sub : manager.getAllSubtasks()) {
            System.out.println(sub);
        }

        System.out.println("\nИстория просмотров:");
        manager.getTaskById(task1.getId());
        manager.getEpicById(epic1.getId());
        manager.getSubtaskById(subtask1.getId());
        manager.getSubtaskById(subtask2.getId());
        manager.getTaskById(task1.getId());
        manager.getEpicById(epic1.getId());

        for (Task viewed : manager.getHistory()) {
            System.out.println(viewed);
        }


    }
}
