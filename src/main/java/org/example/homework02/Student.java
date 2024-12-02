package org.example.homework02;

import java.util.ArrayList;
import java.util.List;

public final class Student {
    private final String name;

    private final Integer age;

    private final List<Grade> grades;

    private final String school;

    private final Address address;

    Student(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.school = builder.school;
        this.grades = builder.grades;
        this.address = builder.address;
    }

     static final class Builder {
        private String name;

        private Integer age;

        private List<Grade> grades = new ArrayList<>();

        private String school;

        private Address address;

        public static Builder getInstance() {
            return new Builder();
        }

        private Builder() { }

        public Builder setName(String studentName) {
            this.name = studentName;
            return this;
        }

        public Builder setAge(Integer studentAge) {
            this.age = studentAge;
            return this;
        }

        public Builder setGrade(Grade grade) {
            this.grades.add(grade);
            return this;
        }

        public Builder setSchool(String studentSchool) {
            this.school = studentSchool;
            return this;
        }

        public Builder setAddress(Address studentAddress) {
            this.address = studentAddress;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public String getSchool() {
        return school;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Student{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", grades=" + grades
                + ", school='" + school + '\''
                + ", address=" + address
                + '}';
    }
}
