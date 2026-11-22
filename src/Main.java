import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = List.of("Ivan", "Maria", "Peter", "Anna", "Oleg");
        System.out.println("Завдання 1: " + StreamTasks.oddIndexedNames(names));

        List<String> words = List.of("ivan", "peter", "anna", "maria");
        System.out.println("Завдання 2: " + StreamTasks.upperCaseSortedDesc(words));

        String[] input = {"1, 2, 0", "4, 5"};
        System.out.println("Завдання 3: " + StreamTasks.extractAndSortNumbers(input));

        long a = 25214903917L;
        long c = 11L;
        long m = 1L << 48;
        List<Long> randoms = StreamTasks.lcgStream(0L, a, c, m)
                .limit(10)
                .toList();
        System.out.println("Завдання 4: " + randoms);

        Stream<String> s1 = Stream.of("A", "B", "C", "D");
        Stream<String> s2 = Stream.of("1", "2", "3");
        List<String> zipped = StreamTasks.zip(s1, s2).toList();
        System.out.println("Завдання 5: " + zipped);
    }
}