package com.example.demo.threadpack.fightpack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author freedom
 * @Description
 * @Date $ 2020/11/28 20:44
 */
public class FightQueryExample {
    private static List<String> fightCompany = Arrays.asList("CSA" , "CEA","HNA");

    public static void main(String[] args) {
        List<String> results = search("SH","BJ");
        System.out.println("---------结果是----------");
        results.forEach(System.out::println);
    }

    public static List<String> search(String origin,String dest){
        final List<String> result = new ArrayList<>();
        List<FightQueryTask> tasks = fightCompany.stream().map(f -> createSearchTask(f,origin,dest)).collect(Collectors.toList());

        tasks.forEach(Thread::start);
        tasks.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        tasks.stream().map(FightQueryTask::get).forEach(result::addAll);
        return result;
    }
    private static FightQueryTask createSearchTask(String fight,String origin,String dest){
        return new FightQueryTask(fight,origin,dest);
    }
}
