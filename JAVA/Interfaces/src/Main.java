import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        // These are called anonymous classes, as they are an implementation of some interface but do not have names.
        // so anonyomous classes.
        Walk walk = new Walk(){
            @Override
            public void walk(int steps) {
                System.out.println("The steps taken are : "+steps);
            }
        };

        walk.walk(5);


        //Lambda's in java are way to make these anonymous classes for the Functional Interfaces
        //  : are such interfaces where in , there is only one method and
        // we can have method implementation of it in anonymous classes
        // as well as we can also have lambda function.
        // general syntax is : (parameter) -> {body}


        // we can make an interface hold its particular implementation without making its class or anonymous class.
        // provide its method implementation without making class.
        Calculator add = (a,b)-> a+b;
        Calculator sub = (a,b)->a-b;
        Calculator mul = (a,b)->a*b;
        Calculator div = (a,b)->a/b;

        System.out.println(add.calculate(5,5));
        System.out.println(sub.calculate(5,5));
        System.out.println(mul.calculate(5,5));
        System.out.println(div.calculate(5,5));

        // lambdas are used in streams in order to have map filer , sorted collector etc

        // immutable list of integer.
        List<Integer> rollNo = List.of(43,44,45,46,47,48);

        //streams
        //Stream<Integer> rollNoStream = rollNo.stream();

        //Direct Stream operation.

        List<Integer> operatedOn = rollNo.stream()
                .filter((roll)->(roll/10 + roll%10)>9 )
                .map(roll -> roll*2)
                .sorted()
                .collect(Collectors.toList());


       // Intermediate Operations: Transform the stream (e.g., map, filter, sorted)
        // Terminal Operations: End the stream pipeline and produce a result (e.g., collect, forEach).





//        // anonymous class.
//        Walk walk = new Walk() {
//            @Override
//            public void walk(int steps) {
//                System.out.println(steps);
//            }
//        };


        // immutable list of string, implementing class is something internal , not some Arraylist or linkedlist etc.
        List<String> fruits = List.of("Banana", "Kiwi", "Apple");

        Stream<String> stream  = fruits.stream();
        //Stream<String> stream = fruits.parallelstream();

        stream
                .sorted()
                .map(fruit -> fruit.length())
                .filter(length -> length>5)
                .forEach(fruit -> System.out.println(fruit));


        // without creating the stream , we can directly have what we want with the collectors.
        List<Integer> lst = fruits
                .stream()
                .map(fruit -> fruit.length())
                .filter(length -> length>2)
                .collect(Collectors.toList());


        // lambda expression with interfaces.
        Walk walk2 = (steps)->{System.out.println(steps);};

        // we can just write the parameters and then its implementation or return value
        // as we dont need to specify the method name or obj name or anything
        // as there is only one method and java compiler knows its a functional interface.

        Walk walk3 = (steps)->{System.out.println(steps+500);};

    }
}


@FunctionalInterface
interface Calculator{
    public int calculate(int a,int b);
}

