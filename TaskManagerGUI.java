import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskManagerGUI {
    private TaskManager taskManager;
    private JFrame frame;
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JButton addButton;
    private JButton removeButton;
    private JButton markCompleteButton;

    public TaskManagerGUI() {
        taskManager = new TaskManager();
        initUI();
    }

    private void initUI() {
        frame = new JFrame("Task Manager");
        frame.setLayout(new BorderLayout());

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        frame.add(new JScrollPane(taskList), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 1));

        titleField = new JTextField();
        descriptionArea = new JTextArea(3, 20);
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(new JScrollPane(descriptionArea));

        frame.add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        addButton = new JButton("Add Task");
        removeButton = new JButton("Remove Task");
        markCompleteButton = new JButton("Mark Complete");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(markCompleteButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTask();
            }
        });

        markCompleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markTaskComplete();
            }
        });

        loadTasks();
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void addTask() {
        String title = titleField.getText();
        String description = descriptionArea.getText();
        if (!title.isEmpty() && !description.isEmpty()) {
            Task task = new Task(title, description);
            taskManager.addTask(task);
            taskListModel.addElement(task);
            titleField.setText("");
            descriptionArea.setText("");
        }
    }

    private void removeTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            taskManager.removeTask(selectedIndex);
            taskListModel.remove(selectedIndex);
        }
    }

    private void markTaskComplete() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            Task task = taskListModel.getElementAt(selectedIndex);
            taskManager.markTaskComplete(selectedIndex, !task.isComplete());
            taskListModel.set(selectedIndex, task);
        }
    }

    private void loadTasks() {
        for (Task task : taskManager.getTasks()) {
            taskListModel.addElement(task);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TaskManagerGUI());
    }
}
