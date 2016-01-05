public class Main {

    public static void main(String[] args) {
        int num[] = {2, 3, 4, 5, 6, 7, 8};
        TelephoneNumber t = new TelephoneNumber(num);
        t.printWords(); // 재귀 버전
        t.printWordIteration(); // 반복 버전
    }

}
