package com.worldline.perfectnumber.service;

import com.worldline.perfectnumber.exception.EmptyArrayException;
import com.worldline.perfectnumber.exception.InvalidRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PerfectNumberServiceTest {

    private PerfectNumberService perfectNumberService;

    @BeforeEach
    void setup(){
        this.perfectNumberService = new PerfectNumberService();
    }

    @Test
    void findPerfectNumbers_EmptyArray_ThrowsEmptyArrayException(){
        int[] input = {};
        assertThrows(EmptyArrayException.class, () -> perfectNumberService.findPerfectNumbers(input, 0, 5));
    }

    @Test
    void findPerfectNumbers_StartLowerThanZero_ThrowsInvalidRangeException(){
        int[] input = {1,2,3};
        assertThrows(InvalidRangeException.class, () -> perfectNumberService.findPerfectNumbers(input, -1, 2));
    }

    @Test
    void findPerfectNumbers_StartBiggerThanLength_ThrowsInvalidRangeException(){
        int[] input = {1,2,3};
        assertThrows(InvalidRangeException.class, () -> perfectNumberService.findPerfectNumbers(input, 4, 2));
    }

    @Test
    void findPerfectNumbers_EndLowerThanStart_ThrowsInvalidRangeException(){
        int[] input = {1,2,3};
        assertThrows(InvalidRangeException.class, () -> perfectNumberService.findPerfectNumbers(input, 2, 1));
    }

    @Test
    void findPerfectNumbers_EndBiggerThanLength_ThrowsInvalidRangeException(){
        int[] input = {1,2,3};
        assertThrows(InvalidRangeException.class, () -> perfectNumberService.findPerfectNumbers(input, 1, 4));
    }

    @Test
    void findPerfectNumbers_ValidParameters_ReturnsPerfectNumbers(){
        int[] input = {6,4,5,33550336,28,99};
        List<Integer> perfectNumbers = perfectNumberService.findPerfectNumbers(input, 1, 5);
        assertEquals(2, perfectNumbers.size());
        assertEquals(33550336, perfectNumbers.get(0));
        assertEquals(28, perfectNumbers.get(1));
    }
}
