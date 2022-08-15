import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    public void setup() {
        calculator = Calculator.getNewCalculator();
    }

    @Test
    void exceptionIfPathIsIncorrect() {
        assertThrows(FileNotFoundException.class, () -> calculator.readFromFile("someExample"));
    }

    @Test
    void exceptionIfInvalidInstructionFormat() {
        assertThrows(NumberFormatException.class, () -> {
            calculator.readFromFile("fileExampleInvalidInstructionFormat");
            calculator.calculate();
        });
    }

    @Test
    void exceptionIfDivisionBy0() {
        assertThrows(ArithmeticException.class, () -> {
            calculator.readFromFile("exampleForTestDivisionBy0");
            calculator.calculate();
        });
    }

    @Test
    void ifCalculatorWorksCorrectly() {
        try {
            calculator.readFromFile("fileExample");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(4, calculator.calculate());
    }


}