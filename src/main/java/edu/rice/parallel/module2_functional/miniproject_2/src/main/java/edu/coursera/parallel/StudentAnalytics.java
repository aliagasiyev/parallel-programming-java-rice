package edu.rice.parallel.module2_functional.miniproject_2.src.main.java.edu.coursera.parallel;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A simple wrapper class for various analytics methods.
 */
public final class StudentAnalytics {
    /**
     * Sequentially computes the average age of all actively enrolled students
     * using loops.
     *
     * @param studentArray Student data for the class.
     * @return Average age of enrolled students
     */
    public double averageAgeOfEnrolledStudentsImperative(final Student[] studentArray) {
        List<Student> activeStudents = new ArrayList<>();

        for (Student s : studentArray)
            if (s.checkIsCurrent())
                activeStudents.add(s);

        double ageSum = 0.0;
        for (Student s : activeStudents)
            ageSum += s.getAge();

        return ageSum / activeStudents.size();
    }

    /**
     * TODO compute the average age of all actively enrolled students using
     * parallel streams. This should mirror the functionality of
     * averageAgeOfEnrolledStudentsImperative. This method should not use any
     * loops.
     *
     * @param studentArray Student data for the class.
     * @return Average age of enrolled students
     */
    public double averageAgeOfEnrolledStudentsParallelStream(final Student[] studentArray) {
         return Stream.of(studentArray)
                      .parallel()
                      .filter(Student::checkIsCurrent)
                      .mapToDouble(Student::getAge)
                      .average()
                      .getAsDouble();
    }

    /**
     * Sequentially computes the most common first name out of all students that
     * are no longer active in the class using loops.
     *
     * @param studentArray Student data for the class.
     * @return Most common first name of inactive students
     */
    public String mostCommonFirstNameOfInactiveStudentsImperative(final Student[] studentArray) {
        List<Student> inactiveStudents = new ArrayList<>();

        for (Student s : studentArray)
            if (!s.checkIsCurrent())
                inactiveStudents.add(s);

        Map<String, Integer> nameCounts = new HashMap<>();

        for (Student s : inactiveStudents) {
            if (nameCounts.containsKey(s.getFirstName()))
                nameCounts.put(s.getFirstName(), nameCounts.get(s.getFirstName()) + 1);
            else
                nameCounts.put(s.getFirstName(), 1);
        }

        String mostCommon = null;
        int mostCommonCount = -1;
        for (Map.Entry<String, Integer> entry : nameCounts.entrySet()) {
            if (mostCommon == null || entry.getValue() > mostCommonCount) {
                mostCommon = entry.getKey();
                mostCommonCount = entry.getValue();
            }
        }

        return mostCommon;
    }

    /**
     * TODO compute the most common first name out of all students that are no
     * longer active in the class using parallel streams. This should mirror the
     * functionality of mostCommonFirstNameOfInactiveStudentsImperative. This
     * method should not use any loops.
     *
     * @param studentArray Student data for the class.
     * @return Most common first name of inactive students
     */
    public String mostCommonFirstNameOfInactiveStudentsParallelStream(final Student[] studentArray) {
        return Stream.of(studentArray)
                     .parallel()
                     .filter(s -> !s.checkIsCurrent())
                     .map(Student::getFirstName)
                     .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                     .entrySet()
                     .stream()
                     .parallel()
                     .max(Map.Entry.comparingByValue())
                     .get().getKey();
    }

    /**
     * Sequentially computes the number of students who have failed the course
     * who are also older than 20 years old. A failing grade is anything below a
     * 65. A student has only failed the course if they have a failing grade and
     * they are not currently active.
     *
     * @param studentArray Student data for the class.
     * @return Number of failed grades from students older than 20 years old.
     */
    public int countNumberOfFailedStudentsOlderThan20Imperative(final Student[] studentArray) {
        int count = 0;
        for (Student s : studentArray)
            if (!s.checkIsCurrent() && s.getAge() > 20 && s.getGrade() < 65)
                count++;
        return count;
    }

    /**
     * TODO compute the number of students who have failed the course who are
     * also older than 20 years old. A failing grade is anything below a 65. A
     * student has only failed the course if they have a failing grade and they
     * are not currently active. This should mirror the functionality of
     * countNumberOfFailedStudentsOlderThan20Imperative. This method should not
     * use any loops.
     *
     * @param studentArray Student data for the class.
     * @return Number of failed grades from students older than 20 years old.
     */
    public int countNumberOfFailedStudentsOlderThan20ParallelStream(final Student[] studentArray) {
        return (int) Stream.of(studentArray).parallel()
                           .filter(s -> s.getGrade() < 65 && s.getAge() > 20 && !s.checkIsCurrent())
                           .count();
    }
}
