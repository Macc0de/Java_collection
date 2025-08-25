package ru.ac.uniyar.rpnevaluator;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import static ru.ac.uniyar.rpnevaluator.RPNEvaluator.evaluate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RPNEvaluatorTest {
    // Не хватает операндов (например, ["+"]):
    // Деление на ноль (например, ["1", "0", "/"]) ??? ArithmeticException
    // Проверить на 0 элементов
    @Test
    void whenNoElementsThrowsException() {
        String[] arr = { };
        assertThatThrownBy(() -> evaluate(arr))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Проверка, что метод работает корректно при добавлении одного числа без операторов
     */
    @Test
    void isEvaluateCorrectWithOneElement() {
        String[] arr = {"100"};
        assertThat(evaluate(arr))
                .isEqualTo(100);
    }

    @Test
    void noOperandsThrowsException() { // Операнды
        String[] arr = {"*"};
        assertThatThrownBy(() -> evaluate(arr))
                .isInstanceOf(EmptyStackException.class);
    }

    /**
     * Проверка, что метод вычисляет значение выражения, заданного обратной польской записью
     * Выражение: 6-3*(2+9)
     * Ответ: -27
     */
    @Test
    void isEvaluateCorrect() {
        String[] arr = {"2", "5", "+", "1", "-", "2", "/", "10", "*"}; // (2+5-1)/2
        assertThat(evaluate(arr))
                .isEqualTo(30);
    }

    /**
     * Проверка, что метод выдает исключение NumberFormatException при невозможности конвертировать символ в число
     */
    @Test
    void notDigitThrowsException() {
        String[] arr = {"s", "4", "-"};
        assertThatThrownBy(() -> evaluate(arr))
                .isInstanceOf(NumberFormatException.class);
    }

    /**
     * Действие: добавление неверного оператора
     * Ожидаемый результат: IllegalArgumentException
     */
    @Test
    void invalidOperatorThrowsException() {
        String[] arr = {"10", "5", ":"};
        assertThatThrownBy(() -> evaluate(arr))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Проверка, что метод выдает исключение EmptyStackException при добавлении оператора в пустой стек
     */
    @Test
    void operatorInEmptyStackThrowsException() {
        String[] arr = {"10", "*"};
        assertThatThrownBy(() -> evaluate(arr))
                .isInstanceOf(EmptyStackException.class);
    }

    /**
     * Проверка, что метод выдает исключение IllegalArgumentException при условии, что размер стека не равен 1
     */
    @Test
    void noOperatorThrowsException() {
        String[] arr = {"1", "3"};
        assertThatThrownBy(() -> evaluate(arr))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Проверка, что метод выдает исключение IllegalArgumentException при условии, что оператор является неверным, хоть и содержит допустимые символы
     */
    @Test
    void invalidOperatorWithCorrectSymbolsThrowsException() {
        String[] arr = {"13", "6", "+-"};
        assertThatThrownBy(() -> evaluate(arr))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
