package ru.ac.uniyar.testingcourse;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {
    // Для каждого класса - состояния

    Course courseIsNotFull() {
        return new Course(3);
    } // еще есть места
    Course courseIsFull() {
        Course course = new Course(3);
        course.enroll(1);
        course.enroll(2);
        course.enroll(3);
        return course;
    } // нет мест
    Course waitingListIsNotEmpty() {
        Course course = new Course(2);
        course.enroll(1);
        course.enroll(2);
        course.enroll(3); // Список ожиданий
        return course;
    }

    // СОСТОЯНИЯ
    static void assertCourseIsNotFull(Course course) {
        assertThat(course.isFullyEnrolled())
                .isFalse(); // еще есть места
    }
    static void assertCourseIsFull(Course course) {
        assertThat(course.isFullyEnrolled())
                .isTrue(); // нет мест
    }
    static void assertWaitingListIsNotEmpty(Course course) {
        assertThat(course.hasWaitingList())
                .isTrue(); // список ожиданий не пуст
    }

    @Nested
    class CourseIsNotFull {
        Course course = courseIsNotFull();

        /**
         * Действие: enroll(unenrolled) - записать в курс не записавшегося студента
         * Ожидаемый результат: запишет студента, в курсе еще есть места
         */
        @Test
        void enrollUnenrolledStudent() {
            course.enroll(1);
            assertThat(course.getEnrollmentList())
                    .containsExactly(1);
            assertCourseIsNotFull(course); //
        }
        /**
         * Действие: enroll(enrolled) - записать уже записавшегося студента
         * Ожидаемый результат: ничего не произойдет
         */
        @Test
        void enrollEnrolledStudent() {
            course.enroll(1);
            course.enroll(1);
            assertThat(course.getEnrollmentList())
                    .containsExactly(1); // Порядок и дубликаты учитывает
        }
        /**
         * Действие: unenroll(unenrolled) - удалить незаписанного студента
         * Ожидаемый результат: ничего не произойдет
         */
        @Test
        void unenrollUnenrolledStudent() {
            course.unenroll(1);
            assertThat(course.getEnrollmentList())
                       .isEmpty();
        }
        /**
         * Действие: unenroll(enrolled) - удалить записанного студента
         * Ожидаемый результат: студент удален
         */
        @Test
        void unenrollEnrolledStudent() {
            course.enroll(1);
            course.unenroll(1);
            assertThat(course.getEnrollmentList())
                    .isEmpty();
        }
        /**
         * Действие: enroll(unenrolled) - записать не записавшегося студента
         * Ожидаемый результат: записать студента, кол-во студентов == max
         */
        @Test // Переход м/ду состояниями
        void enrollUnenrolledMaxCount() {
            course.enroll(1);
            course.enroll(2);
            course.enroll(3);
            assertCourseIsFull(course); // ПЕРЕХОД
        }
    }

    @Nested
    class CourseIsFull {
        Course course = courseIsFull();

        /**
         * Действие: unenroll(enrolled) - удалить записанного студента
         * Ожидаемый результат: удалит студента, появились свободное место
         */
        @Test
        void unenrollEnrolledStudent() {
            course.unenroll(1);
            assertThat(course.getEnrollmentList())
                    .containsExactly(2,3);
            assertCourseIsNotFull(course); // ПЕРЕХОД
        }
        /**
         * Действие: enroll(enrolled) - записать уже записавшегося студента
         * Ожидаемый результат: ничего не произойдет
         */
        @Test
        void enrollEnrolledStudent() {
            course.enroll(3);
            assertThat(course.getEnrollmentList())
                    .containsExactly(1,2,3);
        }
        /**
         * Действие: unenroll(unenrolled) - удалить незаписанного студента
         * Ожидаемый результат: ничего не произойдет
         */
        @Test
        void unenrollUnenrolledStudent() {
            course.unenroll(4);
            assertThat(course.getEnrollmentList())
                    .containsExactly(1,2,3);
        }
        /**
         * Действие: enroll(unenrolled) - записать не записавшегося студента
         * Ожидаемый результат: добавление студента в список ожидания
         */
        @Test
        void enrollUnenrolledStudent() {
            course.enroll(4);
            assertWaitingListIsNotEmpty(course);
        }
    }

    @Nested
    class WaitingListIsNotEmpty {
        Course course = waitingListIsNotEmpty();

        /**
         * Действие: enroll(enrolled) - записать уже записавшегося студента
         * Ожидаемый результат: ничего не произойдет
         */
        @Test
        void enrollEnrolledStudent() {
            course.enroll(2);
            assertThat(course.getWaitingList())
                    .containsExactly(3);
        }
        /**
         * Действие: enroll(waiting) - записать уже записанного студента в списке ожиданий
         * Ожидаемый результат: ничего не произойдет
         */
        @Test
        void enrollWaitingStudent() {
            course.enroll(3);
            assertThat(course.getWaitingList())
                    .containsExactly(3);
        }
        /**
         * Действие: enroll(unenrolled) - записать не записавшегося студента
         * Ожидаемый результат: добавление студента в список ожидания
         */
        @Test
        void enrollUnenrolledStudent() {
            course.enroll(4);
            assertThat(course.getWaitingList())
                    .containsExactly(3,4);
        }
        /**
         * Действие: unenroll(unenrolled) - удалить незаписанного студента
         * Ожидаемый результат: ничего не произойдет
         */
        @Test
        void unenrollUnenrolledStudent() {
            course.unenroll(4);
            assertThat(course.getWaitingList())
                    .containsExactly(3);
        }
        /**
         * Действие: unenroll(waiting) - удалить 1 студента из списка ожидания
         * Ожидаемый результат: студент удален из списка ожидания
         */
        @Test
        void unenrollWaitingStudent() {
            course.enroll(4);
            course.unenroll(3);
            assertThat(course.getWaitingList())
                    .containsExactly(4);
        }
        /**
         * Действие: unenroll(enrolled) - удалить студента из курса
         * Ожидаемый результат: первый студент из списка ожидания будет добавлен в курс
         */
        @Test
        void unenrollEnrolledStudent() {
            course.enroll(4); // в список ожидания
            course.unenroll(2);
            assertThat(course.getEnrollmentList())
                    .containsExactly(1,3); // 3 из списка -> курс
            assertWaitingListIsNotEmpty(course); // 4 остался
        }

        /**
         * Действие: unenroll(enrolled) - удалить студента из курса
         * Ожидаемый результат: единственный студент из списка ожидания будет добавлен в курс
         */
        @Test
        void unenrollEnrolledStudentAndCleanWaitingList() {
            course.unenroll(2);
            assertThat(course.getEnrollmentList())
                    .containsExactly(1,3);
            assertThat(course.hasWaitingList())
                    .isFalse(); // список пуст
        }
        /**
         * Действие: unenroll(waiting) - удалить единственного студента из списка ожидания
         * Ожидаемый результат: список ожидания пуст
         */
        @Test
        void unenrollWaitingStudentAndCleanWaitingList() {
            course.unenroll(3);
            assertThat(course.hasWaitingList())
                    .isFalse(); // список пуст
        }
    }
}
