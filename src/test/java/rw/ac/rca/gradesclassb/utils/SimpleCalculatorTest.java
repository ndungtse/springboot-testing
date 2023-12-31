package rw.ac.rca.gradesclassb.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleCalculatorTest {

    @Test
    void givenTwoNumbers_whenAdded_returnSum(){
        //given, act, assert
        int actual = SimpleCalculator.add(1,4);
        int expected  = 5;
        assertEquals(actual,expected);
    }
    @Test
    public void givenZeroAndAnumber_whenAdded_returnAnumber(){
        assertEquals(SimpleCalculator.add(0,4),4);
    }
    @Test
    void givenZeroValues_whenAdded_returnZero(){
        int actual = SimpleCalculator.add(0,0);
        int expected = 0;
        assertEquals(actual,expected);
    }
    @Test
    void givenTwoNumbers_whenDivided_returnQuotient(){
        double actual = SimpleCalculator.divide(10,5);
        double expected = 2.0;
        assertEquals(actual,expected);
    }
    @Test
    void throwException_whenDivisorIsZero(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            SimpleCalculator.divide(10,0);
        });
        assertEquals(exception.getMessage(),"Divisor can't be zero");
    }
    @Test
    void returnNumber_WhenDivide_ByOne(){
        double expected = 8.0;
        double actual = SimpleCalculator.divide(8 , 1);
        assertEquals(actual , expected);
    }
    @Test
    void multiplied_byOne_returnNumber(){
        double actual = SimpleCalculator.multiply(5,1);
        double expected = 5.0;
        assertEquals(actual,expected);
    }

}
