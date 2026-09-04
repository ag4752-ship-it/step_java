public class FileExtensionValidator {

    public static String validateFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "Rejected — invalid file type";
        }

        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex == filename.length() - 1) {
            return "Rejected — invalid file type";
        }

        String extension = filename.substring(lastDotIndex + 1);

        if (extension.equalsIgnoreCase("pdf") ||
            extension.equalsIgnoreCase("docx") ||
            extension.equalsIgnoreCase("zip")) {
            return "Accepted";
        } else {
            return "Rejected — invalid file type";
        }
    }

    public static void test(String filename) {
        String result = validateFileExtension(filename);
        System.out.printf("File: %-20s -> %s%n", filename, result);
    }

    public static void main(String[] args) {
        test("Assignment1.PDF");
        test("notes.txt");
        test("project_report.docx");
        test("submission.zip");
        test("script.py");
    }
}
