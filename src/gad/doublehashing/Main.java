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
        System.out.println(dh.hashTick(7));
        System.out.println(dh.hashTick(6));
        System.out.println(dh.hashTick(12));
        System.out.println(dh.hashTick(1));
        System.out.println(dh.hashTick(-2));
        System.out.println(dh.hashTick(0));
        System.out.println(dh.hashTick(5));
        System.out.println(dh.hashTick(14));
        System.out.println(dh.hashTick(533));
        System.out.println(dh.hashTick(312));
        System.out.println(dh.hashTick(54));
        System.out.println(dh.hashTick(958));
        System.out.println(dh.hashTick(8));
        System.out.println(dh.hashTick(49));
    }
}
