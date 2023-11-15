import java.util.Comparator;

public class Student implements Comparable {

    private int ID;
    private double GPA;

    public Student(int ID, double GPA) {
        this.ID = ID;
        this.GPA = GPA;
    }


    public int getIdNum() {
        return ID;
    }

    public void setIdNum(int ID) {
        this.ID = ID;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(int GPA) {
        this.GPA = GPA;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + ID +
                ", GPA=" + GPA +
                '}';
    }

    @Override
    public int compareTo(Object obj) {
        if(!(obj instanceof Student))
            throw new IllegalArgumentException("Не является объектом Student");
        // >0 -> this.ID is bigger
        return this.ID - ((Student) obj).ID;
    }
}