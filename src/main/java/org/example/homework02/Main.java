package org.example.homework02;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        studentList()
                .stream()
                .filter((student) -> student.getAddress().getCity().equals("New York")
                        && student.getAge() > 15)
                .flatMap(Main::mapToRecord)
                .sorted(Comparator.comparingDouble(StudentGrade::score).reversed())
                .limit(3)
                .forEach(System.out::println);
    }

    private static Stream<StudentGrade> mapToRecord(Student student) {
        return student
                .getGrades()
                .stream()
                .map((grade) -> new StudentGrade(
                        student.getName(),
                        student.getSchool(),
                        grade.getSubject(),
                        grade.getScore()
                ));
    }

    private static List<Student> studentList() {
        return List.of(
                Student.Builder.getInstance()
                        .setName("Katie Bell")
                        .setAge(13)
                        .setSchool("British International School")
                        .setAddress(new Address("Malaga", "C. Centaurea"))
                        .setGrade(new Grade("Math", 8.4))
                        .setGrade(new Grade("English", 10.2))
                        .setGrade(new Grade("Informatica", 11))
                        .build(),
                Student.Builder.getInstance()
                        .setName("Lavender Brown")
                        .setAge(18)
                        .setSchool("North London Collegiate")
                        .setAddress(new Address("New York", "Canons Dr, Edgware HA8 7RJ"))
                        .setGrade(new Grade("Math", 10.8))
                        .setGrade(new Grade("English", 8.6))
                        .setGrade(new Grade("Informatica", 9.4))
                        .build(),
                Student.Builder.getInstance()
                        .setName("Colin Creevey")
                        .setAge(12)
                        .setSchool("Newington College")
                        .setAddress(new Address("New York", "200 Stanmore Rd, Stanmore NSW 2048"))
                        .setGrade(new Grade("Math", 7.5))
                        .setGrade(new Grade("English", 9.7))
                        .setGrade(new Grade("Informatica", 6.6))
                        .build(),
                Student.Builder.getInstance()
                        .setName("Seamus Finnigan")
                        .setAge(17)
                        .setSchool("Virtus College, The British Sixth Form")
                        .setAddress(new Address("Madrid", "C. de la Salvia, 46, 28109 Alcobendas"))
                        .setGrade(new Grade("Math", 12))
                        .setGrade(new Grade("English", 9.2))
                        .setGrade(new Grade("Informatica", 11.8))
                        .build(),
                Student.Builder.getInstance()
                        .setName("Lee Jordan")
                        .setAge(16)
                        .setSchool("Ranum Efterskole College")
                        .setAddress(new Address("New York", "Seminarievej 23, 9681 Ranum"))
                        .setGrade(new Grade("Math", 7))
                        .setGrade(new Grade("English", 11.5))
                        .setGrade(new Grade("Informatica", 10.3))
                        .build()
        );
    }
}
