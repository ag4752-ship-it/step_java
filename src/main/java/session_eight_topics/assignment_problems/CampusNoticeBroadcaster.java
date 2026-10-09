package session_eight_topics.assignment_problems;

import java.util.*;

/**
 * Session 8 - Assignment Problem 5: The Campus Notice Broadcaster
 * 
 * Demonstrates:
 * - NotificationChannel interface with polymorphic dispatch (EmailChannel, SmsChannel, AppChannel).
 * - Target department matching and multi-channel notice broadcast.
 * - Validation requiring non-empty title and target departments.
 */
public class CampusNoticeBroadcaster {

    public interface NotificationChannel {
        void send(Student student, String noticeTitle);
        String getChannelName();
    }

    public static class EmailChannel implements NotificationChannel {
        @Override
        public void send(Student student, String noticeTitle) {
            System.out.printf("[Email \u2192 %s] %s.%n", student.getName(), noticeTitle);
        }

        @Override
        public String getChannelName() {
            return "Email";
        }
    }

    public static class SmsChannel implements NotificationChannel {
        @Override
        public void send(Student student, String noticeTitle) {
            System.out.printf("[SMS \u2192 %s] %s.%n", student.getName(), noticeTitle);
        }

        @Override
        public String getChannelName() {
            return "SMS";
        }
    }

    public static class AppChannel implements NotificationChannel {
        @Override
        public void send(Student student, String noticeTitle) {
            System.out.printf("[App \u2192 %s] %s.%n", student.getName(), noticeTitle);
        }

        @Override
        public String getChannelName() {
            return "App";
        }
    }

    public static class Student {
        private String name;
        private String department;
        private List<NotificationChannel> preferredChannels;

        public Student(String name, String department, List<NotificationChannel> preferredChannels) {
            this.name = name;
            this.department = department;
            this.preferredChannels = new ArrayList<>(preferredChannels);
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public List<NotificationChannel> getPreferredChannels() {
            return preferredChannels;
        }
    }

    public static class Notice {
        private String title;
        private List<String> targetDepartments;

        public Notice(String title, List<String> targetDepartments) {
            this.title = title;
            this.targetDepartments = targetDepartments != null ? new ArrayList<>(targetDepartments) : new ArrayList<>();
        }

        public String getTitle() {
            return title;
        }

        public List<String> getTargetDepartments() {
            return targetDepartments;
        }
    }

    public static class NoticeBoard {
        private List<Student> students;

        public NoticeBoard() {
            this.students = new ArrayList<>();
        }

        public void registerStudent(Student student) {
            this.students.add(student);
        }

        public void postNotice(Notice notice) {
            if (notice == null || notice.getTitle() == null || notice.getTitle().trim().isEmpty()
                    || notice.getTargetDepartments().isEmpty()) {
                System.out.println("Cannot post notice: At least one target department is required.");
                return;
            }

            String depts = String.join(", ", notice.getTargetDepartments());
            System.out.printf("Notice '%s' posted to %s.%n", notice.getTitle(), depts);

            for (Student student : students) {
                if (notice.getTargetDepartments().contains(student.getDepartment())) {
                    for (NotificationChannel channel : student.getPreferredChannels()) {
                        channel.send(student, notice.getTitle());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        NotificationChannel email = new EmailChannel();
        NotificationChannel sms = new SmsChannel();
        NotificationChannel app = new AppChannel();

        Student asha = new Student("Asha", "CSE", Arrays.asList(email, app));
        Student ravi = new Student("Ravi", "ECE", Collections.singletonList(sms));

        NoticeBoard noticeBoard = new NoticeBoard();
        noticeBoard.registerStudent(asha);
        noticeBoard.registerStudent(ravi);

        // Admin posts notice 'Lab Closed Tomorrow' for CSE
        Notice n1 = new Notice("Lab Closed Tomorrow", Collections.singletonList("CSE"));
        noticeBoard.postNotice(n1);

        // Admin posts notice 'Fee Deadline Extended' for CSE and ECE
        Notice n2 = new Notice("Fee Deadline Extended", Arrays.asList("CSE", "ECE"));
        noticeBoard.postNotice(n2);

        // Admin attempts to post notice 'Sports Day' with no target department
        Notice n3 = new Notice("Sports Day", Collections.emptyList());
        noticeBoard.postNotice(n3);
    }
}
