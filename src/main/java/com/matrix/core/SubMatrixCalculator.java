package com.matrix.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubMatrixCalculator {

    final MaximumHistogram mh;

    @Autowired
    public SubMatrixCalculator(MaximumHistogram mh) {
        this.mh = mh;
    }

    public SubMatrix maximum(int[][] input){
        int[] temp = new int[input[0].length];
        int maxArea = 0;
        int area = 0;
        SubMatrix maxMatrix = null;
        for(int i=0; i < input.length; i++){
            for(int j=0; j < temp.length; j++){
                if(input[i][j] == 0){
                    temp[j] = 0;
                }else{
                    temp[j] += input[i][j];
                }
            }
            SubMatrix details = mh.compute(i, temp);
            area = details.getMaxArea();
            if(area > maxArea){
                maxArea = area;
                maxMatrix = details;
            }
        }
        return maxMatrix;
    }
}
