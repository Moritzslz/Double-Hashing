package gad.doublehashing;

public final class Main {
    private Main() {

    }

    public static void main(String[] args) {
        DoubleHashTable<Integer, String> table = new DoubleHashTable<>(17, new IntHashableFactory());

        table.insert(42, "Hallo");
        table.insert(69, "Welt");

        System.out.println(table.find(42));
        System.out.println(table.find(69));


        DoubleHashTable<Integer, Integer> table1 = new DoubleHashTable<>(50053, new IntHashableFactory());
        for(int i = 0; i < 7000; i++) {
            table1.insert(i*10, i);
        }
        for (int i = 0; i < 50000000; i++) {
            table1.find(i*10);
        }
    }
}
