package p10_GroupByGroup;

import com.sun.javafx.geom.Edge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Person> ppl = new ArrayList<>();
        String[] input = reader.readLine().split(" ");
        while(!input[0].equals("END")){
            Person temp = new Person(input[0], input[1], Integer.parseInt(input[2]));
            ppl.add(temp);
            input = reader.readLine().split(" ");
        }
        //HashMap<Integer, ArrayList<Person>> groups =
                ppl.stream().collect(Collectors.groupingBy(person -> person.getGroup()))
                        .entrySet().stream().forEach(pair -> {
                    System.out.print(pair.getKey() + " - ");
                    for (int i = 0; i < pair.getValue().size(); i++) {
                        if (i == pair.getValue().size() - 1){
                            System.out.println(pair.getValue().get(i).getName());
                        } else {
                            System.out.print(pair.getValue().get(i).getName() + ", ");
                        }
                    }
                });

    }
}

class Person {
    private String firstName;
    private String lastName;
    private Integer group;

    public Person(String firstName, String lastName, Integer group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public Integer getGroup() {
        return group;
    }
}
