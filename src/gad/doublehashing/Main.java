package gad.doublehashing;

public final class Main {
    private Main() {

    }

    public static void main(String[] args) {
        DoubleHashTable<Integer, String> table = new DoubleHashTable<>(17, new IntHashableFactory());

        table.insert(42, "Hallo");
        table.insert(69, "Welt");

        System.out.println(table.find(42));

        DoubleHashInt dh = new DoubleHashInt(3);
        System.out.println(dh.hash(42));
        System.out.println(dh.hashTick(42));

    }
}
