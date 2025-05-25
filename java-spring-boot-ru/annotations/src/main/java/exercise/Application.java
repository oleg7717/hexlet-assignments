package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        Method[] methods = address.getClass().getDeclaredMethods();
        Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(Inspect.class))
                .toList().stream().parallel()
                .forEach(annotatedMethod -> {
                    System.out.println("Method " + annotatedMethod.getName() + " returns a value of type "
                            + annotatedMethod.getReturnType().getSimpleName() + ".");
                });
        // END
    }
}
