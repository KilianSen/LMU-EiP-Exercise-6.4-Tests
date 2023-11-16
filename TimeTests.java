public class TimeTests {
    public static void main(String[] args) {
        test_create();
        test_get_secs();
        test_get_minutes();
        test_get_hours();
        test_equality();
        test_clone();
        test_adding();
        test_tick();
        test_timeDifference();
    }
    public static void assertEqual(Object a, Object b) {
        if (!a.equals(b)) {
            throw new AssertionError("The two objects are not equal!");
        }
    }
    public static void assertNotEqual(Object a, Object b) {
        if (a.equals(b)) {
            throw new AssertionError("The two objects are equal!");
        }
    }

    static void test_create() {
        new Time();
        new Time(1);
        new Time(1,1);
        new Time(1,1,1);
    }

    static void test_get_secs() {
        assertEqual(new Time(1,0,3).getSeconds(), 3);
        assertNotEqual(new Time(1,0,3).getSeconds(), 7);
        assertEqual(new Time(5,43,7).getSeconds(), 7);
    }
    static void test_get_minutes() {
        assertEqual(new Time(1,2,3).getMinutes(), 2);
        assertEqual(new Time(12,0,0).getMinutes(), 0);
        assertEqual(new Time(12,65,0).getMinutes(), 0);
    }
    static void test_get_hours() {
        assertEqual(new Time(1,2,3).getHours(), 1);
        assertEqual(new Time(24,0,0).getHours(), 0);
        assertEqual(new Time(12,65,0).getHours() ,12);
    }

    static void test_clone() {
        Time t1 = new Time(1,1,1);
        Time t2 = t1.clone();

        assertEqual(t1.isEqualTo(t2), true);
        t2.tick();
        assertEqual(!t1.isEqualTo(t2), true);
    }

    static void test_equality() {
        assert new Time(12,11,10).isEqualTo(new Time(12,11,10));
        assert new Time(12,99,10).isEqualTo(new Time(12,0,10));
        assert !(new Time(15,15,15).isEqualTo(new Time(15,12,12)));
    }

    static void test_adding() {
        Time t1 = new Time(12,12,12);
        Time t2 = new Time(1,1,1);

        Time tr = new Time(13,13,13);

        t1.add(t2);
        assertEqual(tr.isEqualTo(t1), true);

        Time t3 = new Time(22,23,59);
        Time t4 = new Time(12,4,59);

        Time tr2 = new Time(10,28,58);

        t3.add(t4);
        assertEqual(tr2.isEqualTo(t3), true);
    }

    static void test_tick() {
        Time t1 = new Time(12,45,34);
        t1.tick();
        Time t2 = new Time(12,45,35);
        assertEqual(t2.isEqualTo(t1), true);
        t1.tick();
        Time t3 = new Time(12,45,36);
        assertEqual(t3.isEqualTo(t1), true);

        Time t4 = new Time(23,59,59);
        t4.tick();
        assertEqual(new Time(0).isEqualTo(t4), true);
    }

    static void test_timeDifference() {
        Time t1 = new Time(12,12,12);
        Time t2 = new Time(1,1,1);
        assertEqual(new Time(12,48,49).isEqualTo(t1.differenceTo(t2)), true);

        Time t3 = new Time(3,45,42);
        Time t4 = new Time(13,14,15);
        assertEqual(new Time(9,28,33).isEqualTo(t3.differenceTo(t4)), true);

        Time t5 = new Time(0,12,3);
        Time t6 = new Time(14,14,15);
        assertEqual(new Time(14,2,12).isEqualTo(t5.differenceTo(t6)), true);

        Time t7 = new Time(23,55,4);
        Time t8 = new Time(5,23,55);
        assertEqual(new Time(5,28,51).isEqualTo(t7.differenceTo(t8)), true);
    }
}
