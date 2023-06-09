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

        DoubleHashable dh = new DoubleHashInt(7);
        System.out.println(dh.hash(7));
        System.out.println(dh.hash(6));
        System.out.println(dh.hash(12));
        System.out.println(dh.hash(1));
        System.out.println(dh.hash(-2));
        System.out.println(dh.hashTick(7));
        System.out.println(dh.hashTick(6));
        System.out.println(dh.hashTick(12));
        System.out.println(dh.hashTick(1));
        System.out.println(dh.hashTick(-2));
    }
}
