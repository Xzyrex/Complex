public class Test {
    public static void main(String[] args) {

        Complex val1 = new Complex(2, 2);
        Complex val2 = new Complex(2, 2);

        System.out.println(val2);
        System.out.println(val1.pow(val2.getReal()));
        System.out.println(val1.pow(2));
    }
}