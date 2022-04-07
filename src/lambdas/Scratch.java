package lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Scratch {

    public static void main(String[] args) {
        List<String> messages = new ArrayList<>();

        messages.add(" hello ");
        messages.add("Goodbye");
        messages.add("I like your youtube channel!!");

        System.out.println("----------------------------");
        System.out.println("Lambda Method Reference Expressions");
        System.out.println("----------------------------");
        messages.stream()
                .map(Scratch::formatMessage)
                .forEach(System.out::println);

        System.out.println("----------------------------");
        System.out.println("Using Function and Supplier");
        System.out.println("----------------------------");

        Supplier<String> supplier = () -> " Arthur Marques";

//        Function tem dois parametros, entrada e saida, no caso o <String, String>
//        UnaryOperator o parametro de entrada e saida sao os mesmos <String>
//        Function<String, String> mapping = incoming -> {
        UnaryOperator<String> mapping = incoming -> {
            String msg = incoming;

            msg = msg.trim();
            msg += supplier.get();

            return msg;
        };

        messages.stream()
                .map(mapping)
                .forEach(System.out::println);

        System.out.println("----------------------------");
        System.out.println("Using Consumer");
        System.out.println("----------------------------");

        Consumer<String> consumer = incoming -> {
            var msg = incoming;
            msg = msg.trim();
            msg += supplier.get();

            System.out.println(msg);
        };

        messages.stream()
//                .forEach(m -> consumer.accept(m));
                .forEach(consumer);

        messages.forEach(m -> handleMessages(m, consumer));

    }

    static String formatMessage(String incoming) {
        return incoming.trim();
    }

    static void handleMessages(String message, Consumer<String> consumer) {
        consumer.accept(message);
    }
}
