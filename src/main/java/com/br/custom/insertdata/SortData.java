package com.br.custom.insertdata;

import com.br.custom.insertdata.data.Banks;
import com.br.custom.insertdata.data.Customers;
import java.util.Random;

/**
 *
 * @author Leonardo S. Rodrigues <leonardo.silva.rodrigues2@gmail.com>
 * @since 17/04/2018
 * @version 1.0
 */
public class SortData {

    public Integer getData(Integer[] array) {
        Integer data = array[randomVal(array.length)];
        return data;
    }

    public String getData(String[] array) {
        String data = array[randomVal(array.length)];
        return data;
    }

    private int randomVal(int maxVal) {
        Random r = new Random();
        int minimum = 0;
        int maximum = maxVal;
        int result = r.nextInt(maximum - minimum) + minimum;
        return result;
    }

}
