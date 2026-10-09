package session_eight_topics.class_problems;

import java.util.*;

/**
 * Session 8 - Class Problem 3: Online Examination System
 * 
 * Demonstrates:
 * - Abstract Question base class with polymorphic evaluation logic.
 * - Question types: MultipleChoiceQuestion, TrueFalseQuestion, ShortAnswerQuestion.
 * - Attempt lifecycle management (In Progress -> Submitted) preventing post-submission modifications.
 */
public class OnlineExaminationSystem {

    public static abstract class Question {
        protected String id;
        protected String prompt;
        protected int points;
        protected String correctAnswer;

        public Question(String id, String prompt, int points, String correctAnswer) {
            this.id = id;
            this.prompt = prompt;
            this.points = points;
            this.correctAnswer = correctAnswer;
        }

        public String getId() {
            return id;
        }

        public int getPoints() {
            return points;
        }

        public abstract boolean evaluate(String answer);
    }

    public static class MultipleChoiceQuestion extends Question {
        public MultipleChoiceQuestion(String id, String prompt, int points, String correctAnswer) {
            super(id, prompt, points, correctAnswer);
        }

        @Override
        public boolean evaluate(String answer) {
            return answer != null && answer.trim().equalsIgnoreCase(this.correctAnswer.trim());
        }
    }

    public static class TrueFalseQuestion extends Question {
        public TrueFalseQuestion(String id, String prompt, int points, String correctAnswer) {
            super(id, prompt, points, correctAnswer);
        }

        @Override
        public boolean evaluate(String answer) {
            return answer != null && answer.trim().equalsIgnoreCase(this.correctAnswer.trim());
        }
    }

    public static class ShortAnswerQuestion extends Question {
        public ShortAnswerQuestion(String id, String prompt, int points, String correctAnswer) {
            super(id, prompt, points, correctAnswer);
        }

        @Override
        public boolean evaluate(String answer) {
            return answer != null && answer.trim().equalsIgnoreCase(this.correctAnswer.trim());
        }
    }

    public static class Student {
        private String id;
        private String name;

        public Student(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class Examination {
        private String examCode;
        private String title;
        private List<Question> questions;

        public Examination(String examCode, String title) {
            this.examCode = examCode;
            this.title = title;
            this.questions = new ArrayList<>();
        }

        public void addQuestion(Question q) {
            this.questions.add(q);
        }

        public List<Question> getQuestions() {
            return Collections.unmodifiableList(questions);
        }

        public String getTitle() {
            return title;
        }
    }

    public static class Attempt {
        private Student student;
        private Examination examination;
        private Map<String, String> recordedAnswers;
        private boolean submitted;

        public Attempt(Student student, Examination examination) {
            this.student = student;
            this.examination = examination;
            this.recordedAnswers = new LinkedHashMap<>();
            this.submitted = false;
            System.out.printf("%s started by %s.%n", examination.getTitle(), student.getName());
        }

        public void recordAnswer(int questionIndex, String answer) {
            if (submitted) {
                System.out.println("Cannot change answers for a submitted examination.");
                return;
            }
            List<Question> questions = examination.getQuestions();
            if (questionIndex >= 1 && questionIndex <= questions.size()) {
                Question q = questions.get(questionIndex - 1);
                recordedAnswers.put(q.getId(), answer);
                System.out.printf("Answer recorded for Question %d.%n", questionIndex);
            }
        }

        public void submit() {
            if (submitted) {
                System.out.println("Attempt has already been submitted.");
                return;
            }
            this.submitted = true;
            int totalScore = 0;
            int maxScore = 0;

            StringBuilder resultSummary = new StringBuilder();
            List<Question> questions = examination.getQuestions();

            for (int i = 0; i < questions.size(); i++) {
                Question q = questions.get(i);
                maxScore += q.getPoints();
                String ans = recordedAnswers.get(q.getId());
                boolean correct = q.evaluate(ans);

                if (correct) {
                    totalScore += q.getPoints();
                    resultSummary.append(String.format("Question %d: Correct (%d points)", i + 1, q.getPoints()));
                } else {
                    resultSummary.append(String.format("Question %d: Incorrect (0 points)", i + 1));
                }

                if (i < questions.size() - 1) {
                    resultSummary.append(", ");
                }
            }

            System.out.printf("%s submitted by %s. Result: %s. Total score: %d/%d.%n",
                    examination.getTitle(), student.getName(), resultSummary.toString(), totalScore, maxScore);
        }
    }

    public static void main(String[] args) {
        // Setup Exam A with MCQ and TF questions
        Examination examA = new Examination("EXAM-A", "Exam A");
        examA.addQuestion(new MultipleChoiceQuestion("Q1", "Which of the following is an OOP concept?", 5, "C"));
        examA.addQuestion(new TrueFalseQuestion("Q2", "Java supports multiple inheritance for classes?", 5, "False"));

        Student student1 = new Student("S1", "Student 1");

        // Student 1 starts Exam A
        Attempt attempt = new Attempt(student1, examA);

        // Student 1 answers Question 1 (MCQ) with option C
        attempt.recordAnswer(1, "C");

        // Student 1 answers Question 2 (TF) with True
        attempt.recordAnswer(2, "True");

        // Student 1 submits Exam A
        attempt.submit();

        // Student 1 attempts to change answer for Question 1
        attempt.recordAnswer(1, "B");
    }
}
