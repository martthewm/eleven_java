import java.util.ArrayList;


public class AllSorts_TestStudent {
    public static void main(String[] args) {
        Student[] students = new Student[]{
                new Student(1359025, 4.3),
                new Student(2381945, 5),
                new Student(1348912, 3.7),
                new Student(2319388, 4.2),
                new Student(1937322, 3.9)
        };
        Student[] students2 = new Student[]{
                new Student(1355425, 3.3),
                new Student(2128275, 4.1),
                new Student(1292734, 4.3),
                new Student(2355228, 3.8),
                new Student(9162844, 2.9)
        };
        /*for (Student s : students) {
            System.out.println(s);
        }*/
        System.out.println("\nСортировка по ID (Insertion Sort)\n");
        insertionSort(students);

        System.out.println("\nСортировка по GPA (Quick Sort)\n");
        quickSort(students, students.length-1, 0);
        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println("\nСортировка по ID (Merge Sort)\n");

        Student[] allStudents = new Student[students.length + students2.length];
        System.arraycopy(students, 0, allStudents,0,students.length);
        System.arraycopy(students2, 0, allStudents,students.length, students2.length);

        mergeSort(allStudents, allStudents.length);
        for (Student s : allStudents) {
            System.out.println(s);
        }
    }
    private static SortingByGPA sort = new SortingByGPA();

    public static void quickSort(Object[] array, int high, int low){ // Алгоритм сортировки в порядке убывания (!)
        if(array == null || array.length == 0) return;
        if(high <= low) return;

        Object middle = array[(low + high)/2]; // Выбор опорного элемента
        ArrayList<Object> left = new ArrayList<>(); // Разбиение - перераспределение элементов
        ArrayList<Object> right = new ArrayList<>();
        ArrayList<Object> eq = new ArrayList<>();
        for(int i = low; i <= high; i++){
            if(sort.compare(array[i], middle) > 0){
                left.add(array[i]); // (!)  Элементы, меньшие опорного, помещаются за ним, ...
            }
            else if (sort.compare(array[i], middle) < 0)
                right.add(array[i]); // (!) ...остальные элементы - после
            else eq.add(array[i]);
        }
        Object[] leftArr;
        Object[] rightArr;
        if(left.size()>0) { // Рекурсивно применяем первые два шага к двум подмассивам
            leftArr = left.toArray();
            quickSort(leftArr, left.size() - 1, 0);
            System.arraycopy(leftArr, 0, array, low, left.size());
        }
        System.arraycopy(eq.toArray(), 0, array, low+left.size(), eq.size());

        if(right.size() > 0) {
            rightArr = right.toArray();
            quickSort(rightArr, right.size() - 1, 0);
            System.arraycopy(rightArr, 0, array, low+left.size() + eq.size(), right.size());

        }

    }
    public static void insertionSort(Student[] students){

        for (int i = 1; i < students.length; i++) { // На каждом шаге алгоритма выбирается один из элементов входных данных...
            Student current = students[i];
            int j = i-1;
            for(; j >= 0 && current.compareTo(students[j]) < 0; j--) { //...и помещается на нужную позицию в уже отсортированной последовательности
                students[j+1] = students[j];
            }
            students[j+1] = current;
        }
        for (Student s : students) { // Вывод последовательности
            System.out.println(s);
        }



    }

    public static void mergeSort(Student[] students, int n) {
        if (n < 2) { // Любой массив длины 1 - упорядоченный
            return;
        }
        int mid = n / 2; // Сортируемый массив разбивается на две части примерно одинакового размера
        Student[] left = new Student[mid];
        Student[] right = new Student[n - mid];
        System.arraycopy(students, 0, left, 0, mid);
        System.arraycopy(students, mid, right, 0, n - mid);

        mergeSort(left, mid); // Каждая из получившихся частей сортируется отдельно, например — тем же самым алгоритмом
        mergeSort(right, n - mid);

        merge(students, left, right, mid, n - mid); // Два упорядоченных массива половинного размера соединяются в один
    }
    public static void merge(
            Student[] students, Student[] leftArr, Student[] rightArr, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (leftArr[i].compareTo(rightArr[j]) <= 0) {
                students[k++] = leftArr[i++];
            }
            else {
                students[k++] = rightArr[j++];
            }
        }
        while (i < left) {
            students[k++] = leftArr[i++];
        }
        while (j < right) {
            students[k++] = rightArr[j++];
        }
    }
}