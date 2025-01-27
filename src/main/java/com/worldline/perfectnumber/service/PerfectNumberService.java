package com.worldline.perfectnumber.service;

import com.worldline.perfectnumber.exception.EmptyArrayException;
import com.worldline.perfectnumber.exception.InvalidRangeException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerfectNumberService {

    public List<Integer> findPerfectNumbers(int[] input, int start, int end){
        if(input.length == 0){
            throw new EmptyArrayException("Input array cannot be empty");
        }
        if(start < 0 || start >= input.length || end <= start || end > input.length){
            throw new InvalidRangeException("Invalid range provided for array. Start index should be inclusive and End index should be exclusive");
        }

        List<Integer> perfectNumbers = new ArrayList<>();

        for(int i = start; i < end; i++){
            int number = input[i];
            if(this.isPerfect(number)){
                perfectNumbers.add(number);
            }
        }

        return perfectNumbers;
    }

    private boolean isPerfect(int number) {
        int sum = 0;
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        return sum == number;
    }
}
