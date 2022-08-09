package lesson9;

import lesson9.task.Discount;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Lesson9 {
    public static void main(String[] args) {
        //Дано: папка data, в которой находиться 1000 чеков
        //Чек состоит из: наименования товара, цены, его себестоимости, количества купленного товара, категория клиента
        //Скидки по категориям клиентов: для студентов 5%, для пенсионеров 10%, для врачей 15%

        //Задача №1
        //Из папки data прочитать все чеки, и составить итоговый отчет для генерального директора:
        //Доходы ... Расходы ... Прибыль ...

        //Задача №2
        //Сделать итоговый отчет: товар - сколько куплено
        //Ожидаемый результат:
        //cola - 200 шт
        //milk - 150 шт
        //beer - 100 шт

        //Задача №3
        //Узнать сколько денег потратили покупатели в разрезе по категориям клиентов: обычный, студент, пенсионер, врач
        //Ожидаемый результат:
        //Врач - 240023.00
        //Студент - 5323255.20
        //Обычный - 144422.30
        //Пенсионер - 98550.00

        task1();
        task2();
        task3();


    };


    public static void task1() {
        String path1 = "C:\\Users\\Ruchkov.Denis\\IdeaProjects\\Java_core_2_ruchkov\\data\\";
        double totalincome = 0;
        double totaloutcome = 0;

        for (int i = 1; i < 1_001; i++) {
            String fileName = path1 + i + "_report.txt";
            try {
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String s = bufferedReader.readLine();
                while (bufferedReader.ready()) {
                    String line = bufferedReader.readLine();
                    String[] split = line.split(";");
                    String name = split[4];
                    Discount discount = Discount.valueOf(name);
                    int count = Integer.parseInt(split[3]);
                    int income = Integer.parseInt(split[1]);
                    int outcome = Integer.parseInt(split[2]);
                    totalincome += income * count * discount.getDiscount();
                    totaloutcome += outcome * count;
                };
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        double profit = totalincome - totaloutcome;
        System.out.println("Доходы - " + totalincome + " Расходы - " + totaloutcome + " Прибыль " + profit);


    }
    //Задача №2
    //Сделать итоговый отчет: товар - сколько куплено
    //Ожидаемый результат:
    //cola - 200 шт
    //milk - 150 шт
    //beer - 100 шт
    public static void task2() {
        String path1 = "C:\\Users\\Ruchkov.Denis\\IdeaProjects\\Java_core_2_ruchkov\\data\\";
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 1; i < 1_001; i++) {
            String fileName = path1 + i + "_report.txt";
            try {
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String s = bufferedReader.readLine();
                while (bufferedReader.ready()) {
                    String line = bufferedReader.readLine();
                    String[] split = line.split(";");
                    String name = split[0];
                    int count = Integer.parseInt(split[3]);
                    if (map.containsKey(name)){
                        count = count + map.get(name);
                    }
                    map.put(name, count);

                };
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(map);


    }
    //Задача №3
    //Узнать сколько денег потратили покупатели в разрезе по категориям клиентов: обычный, студент, пенсионер, врач
    //Ожидаемый результат:
    //Врач - 240023.00
    //Студент - 5323255.20
    //Обычный - 144422.30
    //Пенсионер - 98550.00

    public static void task3() {
        String path1 = "C:\\Users\\Ruchkov.Denis\\IdeaProjects\\Java_core_2_ruchkov\\data\\";
        HashMap<String, Double> map = new HashMap<>();

        for (int i = 1; i < 1_001; i++) {
            String fileName = path1 + i + "_report.txt";
            try {
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String s = bufferedReader.readLine();
                while (bufferedReader.ready()) {
                    String line = bufferedReader.readLine();
                    String[] split = line.split(";");
                    String name = split[4];
                    Discount discount = Discount.valueOf(name);
                    double outcome = Integer.parseInt(split[2]) * Integer.parseInt(split[3]) * discount.getDiscount();
                    if (map.containsKey(name)){
                        outcome = outcome + map.get(name);
                    }
                    map.put(name, outcome);

                };
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(map);

    }


}