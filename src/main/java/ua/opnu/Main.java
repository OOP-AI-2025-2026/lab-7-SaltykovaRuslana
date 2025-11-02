package ua.opnu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static List<Student> filterStudents(Student[] students, Predicate<Student> predicate) {
      List<Student> result = new ArrayList<>();
      for (Student s : students) {
          if (predicate.test(s)) {
              result.add(s);
          }
      }
      return result;

    }

    public static <T> List<T> filterByTwoPredicates(T[] array, Predicate<T> p1, Predicate<T> p2) {
      List<T> result = new ArrayList<>();
      for (T item : array) {
          if (p1.test(item) && p2.test(item)) {
              result.add(item);
          }
      }
      return result;
    }

    public static <T> void forEach(T[] input, Consumer<T> action) {
        for (T i : input) {
            action.accept(i);
        }
    }

    public static <T> void processIf(T[] array, Predicate<T> predicate, Consumer<T> consumer) {
       for (T item : array) {
           if (predicate.test(item)) {
               consumer.accept(item);
           }
       }
    }

    public static Integer[] processArray(Integer[] input, Function<Integer, Integer> function) {
       Integer[] result = new Integer[input.length];
       for (int i = 0; i < input.length; i++) {
           result[i] = function.apply(input[i]);
       }
       return result;
    }

    public static String[] stringfy(int[] input, Function<Integer, String> function) {
        String[] result = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = function.apply(input[i]);
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println("Завдання 1: Предикат для простих чисел");
        Predicate<Integer> isPrime = n -> {
            if (n <= 1) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        };
        System.out.println("Чи є 7 простим числом?" + isPrime.test(7));
        System.out.println("Чи є 10 простим числом?" + isPrime.test(10));

        System.out.println("Завдання 2: Фільтрація студентів із заборгованостями");
        Student[] students = {
                new Student("Іванов Іван", "ICS100", new int[]{80, 75, 95}),
                new Student("Путров Петро", "ICS 100", new int []{60, 70, 50}),
                new Student("Колісніченко Максим", "ICS 103", new int[]{100, 90, 95}),
                new Student("Коваль Діана", "ICS 105", new int[]{40,59,65})
        };

        Predicate<Student> hasNoDebt = student -> {
            for (int mark : student.getMarks()) {
                if(mark < 60) {
                    return false;
                }
            }
            return true;
        };

        List<Student> goodStudents = filterStudents(students, hasNoDebt);
        System.out.println("Студенти без заборгованостей:" + goodStudents);

        System.out.println("Завдання 3: Фільтрація за двома предиктами");
        Predicate<Student> inGroupICS100 = student -> student.getGroup().equals("ICS100");

        List<Student> goodStudentInICS100 = filterByTwoPredicates(students, hasNoDebt, inGroupICS100);
        System.out.println("Відмінники з группи ICS100: " + goodStudentInICS100);

        System.out.println("Завдання 4: Consumer для студентів");
        Consumer<Student> printName = student -> System.out.println(student.getName());

        System.out.println("Список всіх студентів ( через forEach :");
        forEach(students, printName);

        System.out.println("Завдання 5: Метод з Predicate та Consumer");
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Predicate<Integer> isEven = n -> n % 2 == 0;

        Consumer<Integer> printNumber = n -> System.out.println("Знайдено парне число: " + n);

        processIf(numbers, isEven, printNumber);

        System.out.println("Завдання 6: Function для 2^n");
        Function<Integer, Integer> powerOfTwo = n -> (int) Math.pow(2, n);

        Integer[] baseNumber = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9,};
        Integer[] powers = processArray(baseNumber, powerOfTwo);

        System.out.println("Числа:" + Arrays.toString(baseNumber));
        System.out.println("2^n: " + Arrays.toString(powers));

        System.out.println("Завдання 7: Function для \"stringfy\" чисел");
        Function<Integer, String> intToString = n -> {
            switch (n) {
                case 0: return "нуль";
                case 1: return "один";
                case 2: return "два";
                case 3: return "три";
                case 4: return "чотири";
                case 5: return "п'ять";
                case 6: return "шість";
                case 7: return "сім";
                case 8: return "вісім";
                case 9: return "дев'ять";
                default: return "невідомо";
            }
        };

        int[] digitArray = {9, 0, 2, 1, 0, 5, 7, 3, 8, 4};
        String[] stringArray = stringfy(digitArray, intToString);

        System.out.println("Початковий масив: " + Arrays.toString(digitArray));
        System.out.println("Масив рядків: " + Arrays.toString(stringArray));

    }
}
