import java.util.Comparator;

public class SortingByGPA implements Comparator {
    @Override
    public int compare(Object obj1, Object obj2) {
        if(!(obj1 instanceof Student && obj2 instanceof Student))
            throw new IllegalArgumentException("Не являются объектом Student");

        return (int) (((Student) obj1).getGPA()*10 - ((Student) obj2).getGPA()*10);
    }
}
