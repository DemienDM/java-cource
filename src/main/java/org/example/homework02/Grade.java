package org.example.homework02;

final class Grade {

    private final String subject;

    private final double score;

    Grade(String subject, double score) {
        this.subject = subject;
        this.score = score;
    }

    public String getSubject() {
        return subject;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Grade{"
                + "subject='" + subject + '\''
                + ", score=" + score
                + '}';
    }
}
