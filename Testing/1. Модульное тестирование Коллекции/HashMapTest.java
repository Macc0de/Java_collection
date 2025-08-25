package ru.ac.uniyar.testingcourse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class HashMapTest {
     HashMap<Integer, String> map = new HashMap<>();
    /**
     * Дано: объект, сохраняемый с помощью метода put()
     * Ожидаемый результат: объект может быть получен с помощью метода get();
     * Да, сохраняемый объект может быть получен с помощью get()
     * .contains("str"); - проверяет значение
     */
    @ParameterizedTest
    @CsvSource({
            "1, str",
            "2, java",
            "3, python"
    })
    void objectSavedPutCanGet(int key, String value) {
        map.put(key, value);

        assertThat(map.get(key))
                .isEqualTo(value);
    }

    /**
     * Дано: добавление значения, соответствующего существующему значению ключа
     * Ожидаемый результат: затирает старое значение
     * Да, старое значение перезаписывается
     * .contains("str"); - проверяет значение
     */
    @Test
    void useOldKey() {
        map.put(1, "sentence");
        map.put(1, "str");

        assertThat(map.get(1))
                .isEqualTo("str");
    }

    /**
     * Дано: HashMap c содержимым
     * Ожидаемый результат: метод clear() удаляет всё содержимое контейнера
     * Да, clear() удаляет всё содержимое
     * .isEmpty(); - HashMap пуст
     */
    @Test
    void isClearDeleteAll() {
        map.put(1, "str1");
        map.put(2, "str2");
        map.put(3, "str3");
        map.clear();

        assertThat(map)
                .isEmpty();
    }

    /**
     * Дано: записать значение по ключу null
     * Ожидаемый результат: значение можно получить по ключу null
     * Да, можно получить значение по ключу null
     * .contains("str"); - содержит записанное значение
     * или .isEqualTo("str");
     */
    @Test
    void nullAsKey() {
        map.put(null, "str");
        assertThat(map.get(null))
                .isEqualTo("str");
    }
}
