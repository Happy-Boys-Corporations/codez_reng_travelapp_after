import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
import java.nio.file.*;

public class UserData extends JFrame {

    private final JTable table;
    private final DefaultTableModel model;

    private static final String[] COLUMN_NAMES = { "User Name", "Password", "Email", "Security Question", "Answer", "Date and Time" };

    UserData() {
        // Frame Layout
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Travel Agency");
        this.setSize(700, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        Container c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.decode("#F2F2F2"));

        // Icon
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/Icon.png")));
        this.setIconImage(icon.getImage());

        // Fonts
        Font titleFont = new Font("Segoe UI Black", Font.BOLD, 60);
        Font buttonFont = new Font("Segoe UI Black", Font.PLAIN, 25);
        Font tableFont = new Font("Segoe UI", Font.PLAIN, 20);

        // Cursor for JButtons
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        // Title
        JLabel titleLabel = new JLabel();
        titleLabel.setText("User Data");
        titleLabel.setBounds(200, 10, 400, 80);
        titleLabel.setFont(titleFont);
        c.add(titleLabel);

        // JButtons
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBounds(54, 418, 184, 50);
        refreshButton.setFont(buttonFont);
        refreshButton.setCursor(cursor);
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setBackground(Color.decode("#2E75B6"));
        c.add(refreshButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(248, 418, 184, 50);
        deleteButton.setFont(buttonFont);
        deleteButton.setCursor(cursor);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBackground(Color.decode("#2E75B6"));
        c.add(deleteButton);

        JButton addButton = new JButton("Add");
        addButton.setBounds(442, 418, 184, 50);
        addButton.setFont(buttonFont);
        addButton.setCursor(cursor);
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.decode("#2E75B6"));
        c.add(addButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(54, 480, 278, 50);
        exitButton.setFont(buttonFont);
        exitButton.setCursor(cursor);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.decode("#C00000"));
        c.add(exitButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(342, 480, 284, 50);
        backButton.setFont(buttonFont);
        backButton.setCursor(cursor);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.decode("#2E75B6"));
        c.add(backButton);

        // JTable Layout
        table = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(COLUMN_NAMES);

        table.setModel(model);
        table.setFont(tableFont);
        table.setSelectionBackground(Color.decode("#8AC5FF"));
        table.setBackground(Color.WHITE);
        table.setRowHeight(30);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        table.getColumnModel().getColumn(3).setPreferredWidth(220);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(220);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(53, 96, 578, 300);
        scroll.setBackground(Color.WHITE);
        c.add(scroll);

        loadUsersIntoTable();

        // Refresh Button
        refreshButton.addActionListener(ae -> refreshTable());

        // Delete Button
        deleteButton.addActionListener(ae -> {
            if (table.getSelectionModel().isSelectionEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a user to delete", "Warning!",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                int selectedRow = table.getSelectedRow();
                String userToDelete = table.getModel().getValueAt(selectedRow, 0).toString();

                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete user '" + userToDelete + "'?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    try {
                        UserDataManager.deleteUser(userToDelete);
                        model.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(null, "User '" + userToDelete + "' has been deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error deleting user.", "File Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Add Button
        addButton.addActionListener(ae -> {
            setVisible(false);
            new AdminAdd().setVisible(true);
            dispose();
        });

        // Exit Button
        exitButton.addActionListener(ae -> System.exit(0));

        // Back Button
        backButton.addActionListener(ae -> {
            setVisible(false);
            new Admin().setVisible(true);
            dispose();
        });
    }

    private void loadUsersIntoTable() {
        try {
            List<String[]> allUsers = UserDataManager.getAllUsers();
            for (String[] userData : allUsers) {
                model.addRow(userData);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load user data.", "File Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void refreshTable() {
        model.setRowCount(0); // Clear existing data
        loadUsersIntoTable();
    }

    /**
     * Inner class to handle all data access logic for user data.
     * This separates file I/O from the UI code.
     */
    private static class UserDataManager {
        /**
         * Represents a single user's data block from the text file.
         */
        private static class UserRecord {
            final List<String> lines;
            final String userName;

            UserRecord(List<String> recordLines) {
                this.lines = new ArrayList<>(recordLines);
                this.userName = this.lines.stream()
                        .filter(line -> line.startsWith(USER_PREFIX))
                        .map(line -> line.substring(USER_PREFIX.length()))
                        .findFirst()
                        .orElse(null);
            }

            public String getUserName() {
                return userName;
            }

            public String[] toTableRow() {
                Map<String, String> userDataMap = new HashMap<>();
                for (String line : lines) {
                    String[] parts = line.split(" : ", 2);
                    if (parts.length == 2) {
                        userDataMap.put(parts[0].trim(), parts[1].trim());
                    }
                }
                return new String[]{
                        userDataMap.getOrDefault("User Name", ""), userDataMap.getOrDefault("Password", ""),
                        userDataMap.getOrDefault("Email", ""), userDataMap.getOrDefault("Security Question", ""),
                        userDataMap.getOrDefault("Answer", ""), userDataMap.getOrDefault("Time & Date", "")};
            }
        }

        private static final Path USER_DATA_FILE = Paths.get("Data", "user_data.txt");
        private static final String USER_PREFIX = "User Name : ";
        private static final String PASS_PREFIX = "Password : ";
        private static final String EMAIL_PREFIX = "Email : ";
        private static final String QSN_PREFIX = "Security Question : ";
        private static final String ANS_PREFIX = "Answer : ";
        private static final String DATE_PREFIX = "Time & Date : ";
        private static final String SEPARATOR = "===============================================";
        private static final String FILE_HEADER_LINE_1 = "===============================================";
        private static final String FILE_HEADER_LINE_2 = "====== ###  Travel Agency User Data  ### ======";
        private static final String FILE_HEADER_LINE_3 = "===============================================";

        private static List<UserRecord> readAllRecords() throws IOException {
            List<UserRecord> records = new ArrayList<>();
            if (!Files.exists(USER_DATA_FILE)) {
                return records;
            }

            List<String> allLines = Files.readAllLines(USER_DATA_FILE);
            List<String> currentRecordLines = new ArrayList<>();

            // Skip the file header if it exists
            int startLine = 0;
            if (allLines.size() >= 3 && allLines.get(0).equals(FILE_HEADER_LINE_1) && allLines.get(1).equals(FILE_HEADER_LINE_2) && allLines.get(2).equals(FILE_HEADER_LINE_3)) {
                startLine = 3;
            }

            for (int i = startLine; i < allLines.size(); i++) {
                String line = allLines.get(i);
                if (line.equals(SEPARATOR)) {
                    if (!currentRecordLines.isEmpty()) {
                        records.add(new UserRecord(currentRecordLines));
                        currentRecordLines = new ArrayList<>();
                    }
                } else {
                    currentRecordLines.add(line);
                }
            }
            // Add the last record if file doesn't end with a separator
            if (!currentRecordLines.isEmpty()) {
                records.add(new UserRecord(currentRecordLines));
            }
            return records;
        }

        private static void writeAllRecords(List<UserRecord> records) throws IOException {
            try (BufferedWriter writer = Files.newBufferedWriter(USER_DATA_FILE)) {
                // Write header
                writer.write(FILE_HEADER_LINE_1);
                writer.newLine();
                writer.write(FILE_HEADER_LINE_2);
                writer.newLine();
                writer.write(FILE_HEADER_LINE_3);
                writer.newLine();

                // Write records
                for (UserRecord record : records) {
                    for (String line : record.lines) {
                        writer.write(line);
                        writer.newLine();
                    }
                    writer.write(SEPARATOR);
                    writer.newLine();
                }
            }
        }

        public static List<String[]> getAllUsers() throws IOException {
            return readAllRecords().stream().map(UserRecord::toTableRow).collect(Collectors.toList());
        }

        public static void deleteUser(String userNameToDelete) throws IOException {
            List<UserRecord> allRecords = readAllRecords();
            List<UserRecord> recordsToKeep = allRecords.stream()
                    .filter(record -> record.getUserName() != null && !record.getUserName().equals(userNameToDelete))
                    .collect(Collectors.toList());

            // Only write if a change was actually made
            if (recordsToKeep.size() < allRecords.size()) {
                writeAllRecords(recordsToKeep);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserData().setVisible(true));
    }
}
