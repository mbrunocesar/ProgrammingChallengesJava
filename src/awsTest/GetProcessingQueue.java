package awsTest;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class GetProcessingQueue {

    /*
     * Complete the 'findRequestsInQueue' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY wait as parameter.
     */

    public static List<Integer> findRequestsInQueue(List<Integer> wait) {
        List<Integer> processedList = new LinkedList<Integer>();
        
        int time = 0;
        int waitSize = wait.size();
        processedList.add(waitSize);
            
        while (waitSize > 0) {
            time++;
            wait.remove(0);

            waitSize = wait.size();
            for (int i = waitSize - 1; i >= 0; i--) {
                int current = wait.get(i);
                if (current <= time) {
                    wait.remove(i);
                }
            }
            
            waitSize = wait.size();
            processedList.add(waitSize);
        }
        
        

        return processedList;
    }

}

