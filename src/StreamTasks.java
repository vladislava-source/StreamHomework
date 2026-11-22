import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamTasks {

    // Завдання 1: рядки за непарними індексами (1, 3, ...) у форматі "1. Name, 3. Name"
    public static String oddIndexedNames(List<String> names) {
        return IntStream.range(0, names.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(i -> i + ". " + names.get(i))
                .collect(Collectors.joining(", "));
    }

    // Завдання 2: верхній регістр і сортування за спаданням (від Z до A)
    public static List<String> upperCaseSortedDesc(List<String> items) {
        return items.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    // Завдання 3: дістати всі числа з масиву рядків і вивести відсортованими через ", "
    public static String extractAndSortNumbers(String[] array) {
        return Arrays.stream(array)
                .flatMap(s -> Arrays.stream(s.split(",")))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    // Завдання 4: лінійний конгруентний генератор через Stream.iterate (без Math.random)
    public static Stream<Long> lcgStream(long seed, long a, long c, long m) {
        java.math.BigInteger bigA = java.math.BigInteger.valueOf(a);
        java.math.BigInteger bigC = java.math.BigInteger.valueOf(c);
        java.math.BigInteger bigM = java.math.BigInteger.valueOf(m);
        long start = java.math.BigInteger.valueOf(seed).mod(bigM).longValue();

        return Stream.iterate(
                start,
                x -> java.math.BigInteger.valueOf(x)
                        .multiply(bigA)
                        .add(bigC)
                        .mod(bigM)
                        .longValue()
        );
    }

    // Завдання 5: «перемішування» двох стрімів до завершення коротшого
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> it1 = first.iterator();
        Iterator<T> it2 = second.iterator();

        Iterator<T> zippedIterator = new Iterator<>() {
            private boolean takeFirst = true;

            @Override
            public boolean hasNext() {
                if (takeFirst) {
                    return it1.hasNext() && it2.hasNext();
                }
                return it2.hasNext();
            }

            @Override
            public T next() {
                if (takeFirst) {
                    takeFirst = false;
                    return it1.next();
                } else {
                    takeFirst = true;
                    return it2.next();
                }
            }
        };

        Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(
                zippedIterator,
                Spliterator.ORDERED
        );
        return StreamSupport.stream(spliterator, false);
    }
}