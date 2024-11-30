package org.example.homework02;

public record StudentGrade(String studentName, String school, String subject, double score) {
    @Override
    public String toString() {
        return "StudentGrade{"
                + "studentName='" + studentName + '\''
                + ", school='" + school + '\''
                + ", subject='" + subject + '\''
                + ", score=" + score
                + '}';
    }
}
