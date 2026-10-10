package session_ten_topics.class_problems;

import java.util.*;

/**
 * Session 10 - Class Problem 4: Class Marks Grid Analysis
 * 
 * Demonstrates:
 * - 2D grid processing for student marks.
 * - Calculating student totals, subject-wise averages, and topper identification.
 */
public class ClassMarksGridAnalysis {

    public static class StudentRecord {
        private String name;
        private int[] marks;

        public StudentRecord(String name, int[] marks) {
            this.name = name;
            this.marks = marks;
        }

        public String getName() {
            return name;
        }

        public int[] getMarks() {
            return marks;
        }

        public int getTotal() {
            int total = 0;
            for (int m : marks) {
                total += m;
            }
            return total;
        }
    }

    public static void analyzeMarks(List<StudentRecord> students) {
        if (students == null || students.isEmpty()) return;

        int numStudents = students.size();
        int numSubjects = students.get(0).getMarks().length;

        // 1. Calculate and print student totals
        StringBuilder totalsSb = new StringBuilder("Totals ");
        String topperName = "";
        int maxTotal = -1;

        for (int i = 0; i < numStudents; i++) {
            StudentRecord s = students.get(i);
            int total = s.getTotal();
            totalsSb.append(s.getName()).append(" ").append(total);
            if (i < numStudents - 1) {
                totalsSb.append(", ");
            }

            if (total > maxTotal) {
                maxTotal = total;
                topperName = s.getName();
            }
        }
        System.out.println(totalsSb.toString());

        // 2. Calculate subject averages
        double[] subjectTotals = new double[numSubjects];
        for (StudentRecord s : students) {
            int[] marks = s.getMarks();
            for (int j = 0; j < numSubjects; j++) {
                subjectTotals[j] += marks[j];
            }
        }

        StringBuilder averagesSb = new StringBuilder("averages ");
        for (int j = 0; j < numSubjects; j++) {
            double avg = subjectTotals[j] / numStudents;
            averagesSb.append(String.format(Locale.US, "%.2f", avg));
            if (j < numSubjects - 1) {
                averagesSb.append(", ");
            }
        }
        System.out.println(averagesSb.toString());

        // 3. Print topper
        System.out.printf("topper %s (%d)%n", topperName, maxTotal);
    }

    public static StudentRecord parseLine(String line) {
        // Example: Asha [78, 85, 90]
        int bracketStart = line.indexOf('[');
        int bracketEnd = line.indexOf(']');
        String name = line.substring(0, bracketStart).trim();
        String marksPart = line.substring(bracketStart + 1, bracketEnd);
        String[] tokens = marksPart.split(",");
        int[] marks = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            marks[i] = Integer.parseInt(tokens[i].trim());
        }
        return new StudentRecord(name, marks);
    }

    public static void main(String[] args) {
        String[] inputLines = {
            "Asha [78, 85, 90]",
            "Ravi [88, 92, 79]",
            "Neha [65, 70, 95]"
        };

        List<StudentRecord> students = new ArrayList<>();
        for (String line : inputLines) {
            students.add(parseLine(line));
        }

        analyzeMarks(students);
    }
}
