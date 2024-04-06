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
            wait.removeFirst();

            List<Integer> removalList = new LinkedList<Integer>();
            for (int element : wait) {
                if (element <= time) {
                    removalList.add(element);
                }
            }
            wait.removeAll(removalList);
            
            waitSize = wait.size();
            processedList.add(waitSize);
        }
        
        

        return processedList;
    }

}

