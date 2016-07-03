package com.alexander.examples.design.sorting;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * Class for creating test lists of data, serialized to a file for persistent access.
 * Created by Alexander on 25/06/2016.
 */
public class Data {

    public static void main(String args[]) throws IOException {
        int size = 10;
        //Setup data files
        BasicConfigurator.configure();
        writeData(size, new RandomDataGenerator());
        writeData(size, new OrderedDataGenerator());
        writeData(size, new ReverseOrderedDataGenerator());
    }

    static String pathSep = System.getProperty("file.separator");
    static String packagePath = Data.class.getPackage().getName().replace(".", pathSep);
    static String mavenPrefix = "src"+pathSep+"test"+pathSep+"java";

    public static List<Integer> readData(int limit, DataGenerator generator) throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(new File(mavenPrefix+pathSep+packagePath+pathSep+generator.getName()+limit+".txt")));
        List<Integer> list = (List<Integer>) input.readObject();
        return list;
    }

    public static void writeData(int limit, DataGenerator generator) throws IOException {
        List<Integer> random = generator.getData(limit);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(mavenPrefix+pathSep+packagePath+pathSep+generator.getName()+limit+".txt")));
        out.writeObject(random);
    }

    public interface DataGenerator {
        public List<Integer> getData(int limit);
        public String getName();
    }

    public static class ReverseOrderedDataGenerator implements DataGenerator {

        @Override
        public List<Integer> getData(int limit) {
            List<Integer> list = new LinkedList<Integer>();
            for (int i = limit-1; i >= 0; i--){
                list.add(i);
            }
            return list;
        }

        @Override
        public String getName() {
            return "Reverse";
        }
    }

    public static class OrderedDataGenerator implements DataGenerator {

        @Override
        public List<Integer> getData(int limit) {
            List<Integer> list = new LinkedList<Integer>();
            for (int i = 0; i < limit; i++){
                list.add(i);
            }
            return list;
        }

        @Override
        public String getName() {
            return "Ordered";
        }
    }

    public static class RandomDataGenerator implements DataGenerator {
        private Logger log = LoggerFactory.getLogger(RandomDataGenerator.class);
        @Override
        public List<Integer> getData(int limit){
            Random rand = new Random();
            List<Integer> list = new LinkedList<Integer>();
            Map<Integer, Integer> data = new HashMap<Integer, Integer>(limit);
            for (int i = 0; i < limit; i++) {
                log.debug("Index {}", i);
                int randIndex = rand.nextInt(limit);
                while (data.get(randIndex) != null) {
                    log.debug("Clash on number {}", randIndex);
                    randIndex = rand.nextInt(limit);
                }
                data.put(randIndex, i);
                list.add(randIndex);
            }
            log.debug("List contents");
            log.debug("{}", list);
            log.debug("Set size {} and contents", data.size());
            log.debug("{}", data);
            return list;
        }

        @Override
        public String getName() {
            return "Random";
        }
    }
}
