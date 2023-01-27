package com.mycompany.comparc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author mess alem
 */

public class Comparc {
    public static void main(String[] args) {
        
        Scanner ne = new Scanner(System.in);
        boolean ms = true;
        
        while (ms){
            System.out.println("");
            System.out.println("#######################*************************************************#######################");
            System.out.println("#######################*************************************************#######################");
            System.out.println("select the mapping technique you want to implement");
            System.out.println("");
            System.out.println("A, Direct Mapping");
            System.out.println("B, Associative Mapping");
            System.out.println("C, Set-Associative Mapping ");
            System.out.println("D, EXIT TO THE PROGRAM ");
            System.out.println("");
            System.out.println("***********************************************************************************************");
            String s = ne.nextLine();
            
            switch (s.toUpperCase()) {
                case "A" -> {
                    DirectMapping();
                    ms = false;
                }
                case "B" -> {
                     AssociativeMapping();
                    ms = false;
                }
                case "C" -> {
                    SetAssociativeMapping();
                    ms = false;
                }
                case "D" -> {
                    System.out.println("");
                    System.out.println("######################***     you successfuly exit to the program     ***######################");
                    System.out.println("");
                    ms = false;
                    break;
                }
                default -> {
                    System.out.println("please try again, A if you went Direct Mapping,B if you went"
                            + " Associative Mapping,C if you went Set-Associative Mapping");
                }
            }
        }   
    }
    
    public static void DirectMapping(){

        System.out.println("the cache size is 4 KB , number of cache lines is 32 And with elements that occupy 8-bytes");
        Scanner ne = new Scanner(System.in);
        ArrayList<List> Mm_lins = new ArrayList<>();
        ArrayList<List> num = new ArrayList<>();
        for (int i = 0; i < 32; i++){
            for (int j = 0; j < 32; j++){
                ArrayList<Integer> num1 = new ArrayList<>();
                num1.add(i);
                num1.add(j);
                num.add(num1);
                if (((j + 1) % 16) == 0){
                    Mm_lins.add(num);
                    num = new ArrayList<>();
                }
            }
        }

        HashMap<Integer, List<Integer>> Cache = new HashMap<>();
        for (int i = 0; i < 32 ; i++){
            Cache.put(i, Mm_lins.get(i));
        }
        
        boolean s  = true;
        System.out.println("8KB Main Memory, randomly generated blocks");
        System.out.println(Mm_lins);
        System.out.println("4KB Cache Memory, portion of main memory");
        System.out.println(Cache);
        
        while (s){
            ArrayList<Integer> word = new ArrayList<>();
            Random random = new Random();


            for (int i = 0; i < 2; i++) {
                  word.add(random.nextInt(0, 31));
            }

            System.out.println("randomly generated word is "+word);
            int numbe = word.get(0) * 32 + word.get(1);
            int Mm_keys = numbe / 16;
            int cache_keys = Mm_keys % 32;

            if (!(Cache.containsKey(Mm_keys) || Cache.containsValue(Mm_lins.get(Mm_keys)))){
                System.out.println("it is cache miss, deliver this to the processor " + Mm_lins.get(Mm_keys));
                Cache.put(cache_keys, Mm_lins.get(Mm_keys));
                System.out.println(Cache);
            }

            else{
                System.out.println("it is cache hit, deliver this to the processor" + Mm_lins.get(Mm_keys));
                System.out.println(Cache);
            }

            System.out.println("to test again enter 1 if you don't enter any other keys");
            String m = ne.nextLine();
            if (m.equals("1")){ 
            }else{
                break;
            }
        }
    }
    
    public static void AssociativeMapping(){

        System.out.println("the cache size is 4 KB , number of cache lines is 32 And with elements that occupy 8-bytes");
        Scanner ne = new Scanner(System.in);
        ArrayList<List> Mm_lins = new ArrayList<>();
        ArrayList<List> num = new ArrayList<>();
        for (int i = 0; i < 32; i++){
            for (int j = 0; j < 32; j++){
                ArrayList<Integer> num1 = new ArrayList<>();
                num1.add(i);
                num1.add(j);
                num.add(num1);
                if (((j + 1) % 16) == 0){
                    Mm_lins.add(num);
                    num = new ArrayList<>();
                }
            }
        }

        ArrayList<List> Cache = new ArrayList<>();
        for (int i = 0; i < 32; i++){
            if (i % 16 == 0){
                continue;
            }
            Cache.add(0,Mm_lins.get(i));
        }
        
        boolean s  = true;
        System.out.println("8KB Main Memory, randomly generated blocks");
        System.out.println(Mm_lins);
        System.out.println("4KB Cache Memory, portion of main memory");
        System.out.println(Cache);

        while (s){
            
            ArrayList<Integer> word = new ArrayList<>();
            Random random = new Random();


            for (int i = 0; i < 2; i++) {
                  word.add(random.nextInt(0, 31));
            }


            System.out.println("randomly generated word is "+word);
            int numbe = word.get(0) * 32 + word.get(1);
            int Mm_keys = numbe / 16;

            if (Cache.contains(Mm_lins.get(Mm_keys))){
                System.out.println("it is cache hit, deliver this to the processor" + Mm_lins.get(Mm_keys));
                System.out.println("4KB Cache Memory, portion of main memory");
                System.out.println(Cache);
            }
            else{
                if (Cache.size() < 32){
                    System.out.println("it is cache miss, deliver this to the processor " + Mm_lins.get(Mm_keys));
                    Cache.add(0,Mm_lins.get(Mm_keys));
                    System.out.println("4KB Cache Memory, portion of main memory");
                    System.out.println(Cache);
                }
                else{
                    System.out.println("The cache is full, select the replacement technique to use");
                    System.out.println("");
                    System.out.println("A, Least Recently Used");
                    System.out.println("B, First In First Out");
                    System.out.println("C, Random Replacement");
                    System.out.println("");
                    while (true){
                        String ss = ne.nextLine();
                        switch (ss.toUpperCase()) {
                            case "A" -> {
                                Cache.remove(Cache.get(0));
                                System.out.println("it is cache miss, deliver this to the processor " + Mm_lins.get(Mm_keys));
                                Cache.add(0,Mm_lins.get(Mm_keys));
                                System.out.println("4KB Cache Memory, portion of main memory");
                                System.out.println(Cache);
                                break;
                            }
                            case "B" -> {
                                Cache.remove(Cache.get(31));
                                System.out.println("it is cache miss, deliver this to the processor " + Mm_lins.get(Mm_keys));
                                Cache.add(31,Mm_lins.get(Mm_keys));
                                System.out.println("4KB Cache Memory, portion of main memory");
                                System.out.println(Cache);
                                break;
                            }
                            case "C" -> {
                                int Cache_indexs = random.nextInt(0, 31);
                                Cache.remove(Cache.get(Cache_indexs));
                                System.out.println("it is cache miss, deliver this to the processor " + Mm_lins.get(Mm_keys));
                                Cache.add(Cache_indexs,Mm_lins.get(Mm_keys));
                                System.out.println("4KB Cache Memory, portion of main memory");
                                System.out.println(Cache);
                                break;
                            }
                            default -> {
                                System.out.println("please try again, A if you went Least Recently Used,B if you went"
                                        + " First In First Out,C if you went Random Replacement");
                            }
                        }
                    }
                }
            }

            System.out.println("to test again enter 1 if you don't enter any other keys");
            String m = ne.nextLine();
            if (m.equals("1")){ 
            }else{
                break;
            }
        }
    }

    public static void SetAssociativeMapping() {
        
        System.out.println("the cache size is 32 KB , number of cache lines is 256 And with elements that occupy 8-bytes");
        System.out.println("It is 16-way Set Associative");
        Scanner ne = new Scanner(System.in);
        ArrayList<List> Mm_lins = new ArrayList<>();
        ArrayList<List> num = new ArrayList<>();
        
        for (int i = 0; i < 512; i++){
            for (int j = 0; j < 512; j++){
                ArrayList<Integer> num1 = new ArrayList<>();
                num1.add(i);
                num1.add(j);
                num.add(num1);
                if (((j + 1) % 16) == 0){
                    Mm_lins.add(num);
                    num = new ArrayList<>();
                }
            }
        }

        
        HashMap<Integer, List<List>> Cache = new HashMap<>();
        for (int i = 0; i < 16; i++){
            ArrayList<List> sets = new ArrayList<>();
            for (int j = 0; j < 16; j+=2){
                sets.add(Mm_lins.get(i));
            }
            Cache.put(i,sets);
        }


        boolean s  = true;
        while (s){
            System.out.println("2MB Main Memory, randomly generated blocks");
            System.out.println(Mm_lins);
            System.out.println("32KB Cache Memory, portion of main memory");
            System.out.println(Cache);
            ArrayList<Integer> word = new ArrayList<>();
            Random random = new Random();

            for (int i = 0; i < 2; i++) {
                  word.add(random.nextInt(0, 511));
            }


            System.out.println("randomly generated word is "+word);
            int numbe = word.get(0) * 512 + word.get(1);
            int Mm_keys = numbe / 16;
            int cache_keys = Mm_keys % 256;
            int set_keys = cache_keys / 16;

            if (!Cache.containsKey(set_keys)){
                System.out.println("it is cache miss, deliver this to the processor " + Mm_lins.get(Mm_keys));
                ArrayList<List> set = new ArrayList<>();
                set.add(Mm_lins.get(Mm_keys));
                Cache.put(set_keys, set);

            }
            else if (!Cache.get(set_keys).contains(Mm_lins.get(Mm_keys))){
                System.out.println("it is cache miss, deliver this to the processor " + Mm_lins.get(Mm_keys));
                if (Cache.get(set_keys).size() < 16) {
                    Cache.get(set_keys).add(Mm_lins.get(Mm_keys));
                }
                else{
                    System.out.println("The cache is full, select the replacement technique to use");
                    System.out.println("");
                    System.out.println("A, Least Recently Used");
                    System.out.println("B, First In First Out");
                    System.out.println("C, Random Replacement");
                    System.out.println("");

                    String ss = ne.nextLine();
                    switch (ss.toUpperCase()) {
                        case "A" -> {
                            Cache.get(set_keys).remove(Cache.get(0));
                            System.out.println("it is cache miss, deliver this to the processor " + Mm_lins.get(Mm_keys));
                            Cache.get(set_keys).add(0,Mm_lins.get(Mm_keys));

                        }
                        case "B" -> {
                            Cache.get(set_keys).remove(Cache.get(255));
                            System.out.println("it is cache miss, deliver this to the processor " + Mm_lins.get(Mm_keys));
                            Cache.get(set_keys).add(255,Mm_lins.get(Mm_keys));

                        }
                        case "C" -> {
                            int Cache_indexs = random.nextInt(0, 511);
                            Cache.get(set_keys).remove(Cache.get(Cache_indexs));
                            System.out.println("it is cache miss, deliver this to the processor " + Mm_lins.get(Mm_keys));
                            Cache.get(set_keys).add(Cache_indexs,Mm_lins.get(Mm_keys));
                        }
                        default -> {
                            System.out.println("please try again, A if you went Least Recently Used,B if you went"
                                    + " First In First Out,C if you went Random Replacement");
                        }
                    }
                }
            }

            else{
                System.out.println("it is cache hit, deliver this to the processor" + Mm_lins.get(Mm_keys));
            }

            System.out.println("to test again enter 1 if you don't enter any other keys");
            String m = ne.nextLine();
            if (m.equals("1")){ 
            }else{
                break;
            }
        }
    }
}
