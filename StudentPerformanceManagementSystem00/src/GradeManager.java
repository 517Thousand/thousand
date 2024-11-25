import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Student {
    private String name;
    private String studentId;
    private String classId;
    private Map<String, Double> scores; // 存储课程和对应的成绩

    public Student(String name, String studentId, String classId) {
        this.name = name;
        this.studentId = studentId;
        this.classId = classId;
        this.scores = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getClassId() {
        return classId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public void addScore(String course, double score) {
        scores.put(course, score);
    }

    public Double getScore(String course) {
        return scores.get(course);
    }

    public Map<String, Double> getScores() {
        return scores;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", studentId='" + studentId + '\'' +
                ", classId='" + classId + '\'' +
                ", scores=" + scores +
                '}';
    }
}

public class GradeManager {
    private Map<String, Student> students = new HashMap<>();

    public void addStudent(String name, String studentId, String classId) {
        students.put(studentId, new Student(name, studentId, classId));
        System.out.println("Student added successfully.");
    }

    public void deleteStudent(String studentId) {
        if (students.remove(studentId) != null) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void updateStudentInfo(String studentId, String newName, String newClassId) {
        Student student = students.get(studentId);
        if (student != null) {
            student.setName(newName);
            student.setClassId(newClassId);
            System.out.println("Student information updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void updateStudentScore(String studentId, String course, double score) {
        Student student = students.get(studentId);
        if (student != null) {
            student.addScore(course, score);
            System.out.println("Student score updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void queryStudentInfo(String studentId) {
        Student student = students.get(studentId);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    public void queryStudentScore(String studentId, String course) {
        Student student = students.get(studentId);
        if (student != null) {
            Double score = student.getScore(course);
            if (score != null) {
                System.out.println("Score for course " + course + ": " + score);
            } else {
                System.out.println("Score not found for course " + course);
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradeManager manager = new GradeManager();

        while (true) {
            System.out.println("\n1. Add Student\n2. Delete Student\n3. Update Student Info (Admin)\n4. Update Student Score (Admin)\n5. Query Student Info\n6. Query Student Score\n7. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter student ID:");
                    String studentId = scanner.nextLine();
                    System.out.println("Enter class ID:");
                    String classId = scanner.nextLine();
                    manager.addStudent(name, studentId, classId);
                    break;
                case 2:
                    System.out.println("Enter student ID to delete:");
                    studentId = scanner.nextLine();
                    manager.deleteStudent(studentId);
                    break;
                case 3:
                    System.out.println("Enter student ID to update:");
                    studentId = scanner.nextLine();
                    System.out.println("Enter new name:");
                    name = scanner.nextLine();
                    System.out.println("Enter new class ID:");
                    classId = scanner.nextLine();
                    manager.updateStudentInfo(studentId, name, classId);
                    break;
                case 4:
                    System.out.println("Enter student ID to update score:");
                    studentId = scanner.nextLine();
                    System.out.println("Enter course:");
                    String course = scanner.nextLine();
                    System.out.println("Enter score:");
                    double score = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    manager.updateStudentScore(studentId, course, score);
                    break;
                case 5:
                    System.out.println("Enter student ID to query info:");
                    studentId = scanner.nextLine();
                    manager.queryStudentInfo(studentId);
                    break;
                case 6:
                    System.out.println("Enter student ID to query score:");
                    studentId = scanner.nextLine();
                    System.out.println("Enter course:");
                    course = scanner.nextLine();
                    manager.queryStudentScore(studentId, course);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}