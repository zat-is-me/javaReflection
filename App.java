package constructors;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tatek Ahmed on 2/15/2022
 **/

public class App {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        //finding existing constructor
        printConstructorData(Person.class);
        printConstructorData(Address.class);

        //Dynamic object creation
        Address address = createInstanceWithArguments(Address.class,"First Street", 10);
        Person person = createInstanceWithArguments(Person.class,address,"Ahmed",20);

        System.out.println(person);
    }

    public static <T>T createInstanceWithArguments(Class<T> clazz, Object ... args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Constructor<?> constructor:clazz.getDeclaredConstructors()){
            if(constructor.getParameterTypes().length == args.length){
                return (T)constructor.newInstance(args);
            }
        }
        System.out.println("An appropriate constructor was not found");
        return null;
    }

    //
    public static void printConstructorData(Class<?> clazz) throws NoSuchMethodException {
        Constructor<?> [] constructors = clazz.getDeclaredConstructors();

        System.out.println(String.format("class %s has %d declared constructor",
                clazz.getSimpleName(), constructors.length));

        for (int i = 0; i < constructors.length; i++) {
            Class<?> [] parameterTypes = constructors[i].getParameterTypes();

            List<String> parameterTypeNames = Arrays.stream(parameterTypes)
                    .map(type -> type.getSimpleName())
                    .collect(Collectors.toList());
            System.out.println(parameterTypeNames);
        }
    }
}
