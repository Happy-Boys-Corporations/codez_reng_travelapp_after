import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AuthService {

    private static final Path USER_DATA_PATH = Paths.get("Data", "user_data.txt");
    private static final Path ADMIN_DATA_PATH = Paths.get("Data", "admin_data.txt");

    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Could not find SHA-256 algorithm", e);
        }
    }

    private static boolean verifyPassword(String plainPassword, String hashedPassword) {
        String hashOfPlainPassword = hashPassword(plainPassword);
        return hashOfPlainPassword.equals(hashedPassword);
    }

    public static boolean authenticateUser(String username, String password) throws IOException {
        if (!Files.exists(USER_DATA_PATH)) return false;

        List<String> lines = Files.readAllLines(USER_DATA_PATH);
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equalsIgnoreCase("User Name : " + username)) {
                if (i + 1 < lines.size() && lines.get(i + 1).startsWith("Password : ")) {
                    String storedHash = lines.get(i + 1).substring("Password : ".length());
                    return verifyPassword(password, storedHash);
                }
            }
        }
        return false;
    }

    public static void registerUser(String userName, String password, String email, String question, String answer) throws IOException {
        File file = USER_DATA_PATH.toFile();
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        String hashedPassword = hashPassword(password);

        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {

            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm a, dd/MM/yyyy");
            String timeAndDate = myDateObj.format(myFormatObj);

            pw.println("User Name : " + userName);
            pw.println("Password : " + hashedPassword);
            pw.println("Email : " + email);
            pw.println("Security Question : " + question);
            pw.println("Answer : " + answer);
            pw.println("Time & Date : " + timeAndDate);
            pw.println("===============================================");
        }
    }

    public static boolean authenticateAdmin(String username, String password) throws IOException {
        if (!Files.exists(ADMIN_DATA_PATH)) {
            updateAdminCredentials("admin", "admin");
            return username.equalsIgnoreCase("admin") && password.equals("admin");
        }

        List<String> lines = Files.readAllLines(ADMIN_DATA_PATH);
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equalsIgnoreCase("Name : " + username)) {
                if (i + 1 < lines.size() && lines.get(i + 1).startsWith("Password : ")) {
                    String storedHash = lines.get(i + 1).substring("Password : ".length());
                    return verifyPassword(password, storedHash);
                }
            }
        }
        return false;
    }

    public static void updateAdminCredentials(String name, String password) throws IOException {
        String hashedPassword = hashPassword(password);

        try (BufferedWriter writer = Files.newBufferedWriter(ADMIN_DATA_PATH)) {
            writer.write("==========================================");
            writer.newLine();
            writer.write("=== ###  Travel Agency Admin Data  ### ===");
            writer.newLine();
            writer.write("==========================================");
            writer.newLine();
            writer.write("Name : " + name);
            writer.newLine();
            writer.write("Password : " + hashedPassword);
            writer.newLine();
            writer.write("==========================================");
            writer.newLine();
        }
    }

    public static void addUserByAdmin(String userName, String password, String email, String question, String answer) throws IOException {
        File file = USER_DATA_PATH.toFile();
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        String hashedPassword = hashPassword(password);

        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {

            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm a, dd/MM/yyyy");
            String timeAndDate = myDateObj.format(myFormatObj);

            pw.println("             ## Added by Admin ##");
            pw.println("User Name : " + userName);
            pw.println("Password : " + hashedPassword);
            pw.println("Email : " + email);
            pw.println("Security Question : " + question);
            pw.println("Answer : " + answer);
            pw.println("Time & Date : " + timeAndDate);
            pw.println("===============================================");
        }
    }
}