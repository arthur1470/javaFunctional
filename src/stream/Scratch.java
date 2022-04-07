package stream;

import java.util.Comparator;
import java.util.stream.Stream;

public class Scratch {
    public static void main(String[] args) {
        Integer[] ints = {1, 2, 2, 3, 3, 4, 5};
        System.out.println("***** FOREACH *****");
        Stream.of(ints)
                .forEach(i -> System.out.println(i));

        System.out.println("***** MAP + FOREACH *****");
        Stream.of(ints)
                .map(i -> i + 1)
                .forEach(i -> System.out.println(i));

        System.out.println("***** MAP + FILTER + FOREACH *****");
        Stream.of(ints)
                .map(i -> i + 1)
                .filter(i -> i != 2)
                .forEach(i -> System.out.println(i));

        // A ORDEM ALTERA O RESULTADO (ÓBVIO)
        System.out.println("***** FILTER + MAP + FOREACH *****");
        Stream.of(ints)
                .filter(i -> i != 2)
                .map(i -> i + 1)
                .forEach(i -> System.out.println(i));

        System.out.println("***** FILTER + MAP + REDUCE *****");
        var reduceValue = Stream.of(ints)
                .filter(i -> i != 2)
                .map(i -> i + 1)
                .reduce(0, (initialValue, iteratorValue) -> initialValue + iteratorValue);
        System.out.println(reduceValue);

        System.out.println("***** FILTER + MAP + MAX *****");
        var maxValue = Stream.of(ints)
                .filter(i -> i != 2)
                .map(i -> i + 1)
                .max(Comparator.comparing(i -> i))
                .get();

        System.out.println(maxValue);

        System.out.println("***** FILTER + MAP + MIN *****");
        var minValue = Stream.of(ints)
                .filter(i -> i != 2)
                .map(i -> i + 1)
                .min(Comparator.comparing(i -> i))
                .get();

        System.out.println(minValue);

        // Nesse caso, pula os dois primeiros itens
        System.out.println("***** SKIP *****");
        Stream.of(ints)
                .skip(2)
                .forEach(i -> System.out.println(i));

        // Nesse caso, pula os dois primeiros itens e limita aos 3 primeiros itens
        System.out.println("***** SKIP + LIMIT *****");
        Stream.of(ints)
                .skip(2)
                .limit(3)
                .forEach(i -> System.out.println(i));


        Person homer = new Person("Homer");
        Person bart = new Person("Bart");
        Person lisa = new Person("Lisa");

        homer.children = new Person[] {bart, lisa};

        Person moe = new Person("Moe");
        moe.children = new Person[] {};

        System.out.println("***** FLATMAP VS MAP *****");
        System.out.println("*********");
        System.out.println("***** MAP *****");
        Stream.of(homer, moe)
                .map(p -> Stream.of(p.children))
                .forEach(p -> p.forEach(p2 -> System.out.println(p2.name)));

        System.out.println("***** FLAT MAP *****");
        Stream.of(homer, moe)
                .flatMap(p -> Stream.of(p.children))
                .forEach(p -> System.out.println(p.name));

        System.out.println("***** MATCH *****");
        var allMatch = Stream.of(homer, moe)
                .allMatch(p -> p.children.length == 0);

        var noneMatch = Stream.of(homer, moe)
                .noneMatch(p -> p.children.length == 0);

        var anyMatch = Stream.of(homer, moe)
                .anyMatch(p -> p.children.length == 0);

        System.out.println("All match: " + allMatch);
        System.out.println("None match: " + noneMatch);
        System.out.println("Any match: " + anyMatch);
    }


    static class Person {
        String name;
        Person[] children;

        public Person(String name) {
            this.name = name;
        }
    }
}

