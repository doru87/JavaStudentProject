import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeforeJava8 {

    public static void main(String[] args) {
        Student s1 = new Student("Carstoiu Victor",1,"Resurse Umane");
        Student s2 = new Student("Radu Rodica",2,"Relatii cu Clientii");
        List<Student> lines = Arrays.asList(s1,s2);
        List<Student> result = getFilterOutput(lines, "Radu Rodica");
        for (Student temp : result) {
            System.out.println(temp.getName());    //output : spring, node
        }

    }

    private static List<Student> getFilterOutput(List<Student> lines, String filter) {
        List<Student> result = new ArrayList<>();
        for (Student line : lines) {
            if (!filter.equals(line.getName())) { // we dont like mkyong
                result.add(line);
            }
        }
        return result;
    }

}