/**
 * Created by Ha on 16. 1. 5..
 */

/*
    page 220 - 전화 단어
    전화번호를 알려줄 때 일곱 자리 번호를 나타내는 단어로 알려주는 경우가 종종 있다. 예를 들어 전화번호가 866-2665라면
    외우기 힘든 일곱 자리 숫자 보다는 "TOOCOOL" 이라는 단어를 알려주는 편이 더 낫다. 하지만 866-2665라는 번호로(대부분 별로
    말이 안되긴 하겠지만) 이 외에도 여러 단어를 만들어낼 수 있다. 전화 버튼의 번호별로 대응되는 글자들은 다음과 같다.

    1
    2 : A B C
    3 : D E F
    4 : G H I
    5 : J K L
    6 : M N O
    7 : P R S
    8 : T U V
    9 : W X Y
    0
    *
    #

    일곱 자리 전화번호를 입력받아서 주어진 수를 표현할 수 있는 모든 가능한 "단어" 또는 글자 조합을 출력하는 루틴을 작성하라. 0과 1 키에는
    글자가 할당되어 있지 않기 때문에 2-9만 글자로 바꿀 수 있디ㅏ. 각 원소가 한 자리 정수로 이루어진 정수 일곱 개짜리 배열이 주어진다.
    루틴으로 들어오는 숫자는 모두 합당한 전화번호라고 가정해도 된다. 다음과 같은 보조 함수를 쓸 수 있다.

    char getCharKey(int telephonekey, int place)

    이 함수에서는 전화 버튼 번호(0-9)와 위치를 나타내는 번호(1, 2 또는 3)를 인자로 받아서 주어진 버튼의 주어진 위치에 대응되는 글자를 반환한다.
    예를 들어 getCharKey(3, 2)를 호출하면 버튼에는 "DEF"라는 글자가 적혀 있고, 그중 두 번째가 E 이므로 'E'가 반환된다.

 */

public class TelephoneNumber {
    private final int MAX_TEL_NUM = 7; // 전화번호 최대길이는 7
    private final int []phoneNum;
    private char []result = new char[MAX_TEL_NUM]; // 문자열 조작이나 가변길이가 아니므로 char형 배열로 선언

    // 생성자에서 입력 전화번호 초기화
    public TelephoneNumber(int[] phoneNum) {
        this.phoneNum = phoneNum;
    }

    // 재귀 함수 wrapper
    public void printWords(){ printWords(0);}

    // 실제 재귀 함수
    private void printWords(int digit){

        // base case - 전화번호 끝에 다다르면 출력 후 반환
        if( digit == MAX_TEL_NUM){
            System.out.println(result);
            return;
        }

        // 각 자리에 대하여 자신이 표현할 수 있는 모든 문자의 조합을 구하고
        // 하위(자신의 오른쪽) 번호들의 조합들을 수행한다.
        for(int i = 1; i <= 3; i++){
            result[digit] = getCharKey(phoneNum[digit], i);
            printWords(digit + 1);

            // 문제에서는 0, 1을 허용안한다 했지만 허용하는 경우에
            // if(phoneNum[digit] == 0 || phoneNum[digit] == 1) return;
        }
    }


    // 전화번호 맵에서 telephonekey 번호의 place 위치의 알파벳을 반환하는 함수
    private char getCharKey(int telephonekey, int place){
        char [][] phonemap = {{},{},{' ','A', 'B', 'C'}, {' ','D', 'E', 'F'}, {' ','G', 'H', 'I'},
                                    {' ','J', 'K', 'L'}, {' ','M', 'N', 'O'},{' ','P', 'R', 'S'},
                                    {' ','T', 'U', 'V'}, {' ','W', 'X', 'Y'}};

        return phonemap[telephonekey][place];
    }

    // 전화번호 문자 반복 버전
    public void printWordIteration(){

        // 문자 초기화
        for(int i = 0; i < phoneNum.length; i++)
            result[i] = getCharKey(phoneNum[i], 1);

        // 무한 반복
        while(true){
            print(); // 출력

            // 전화번호 뒤에서 부터 종주
            for(int i = phoneNum.length - 1; i >= -1; i--){
                if(i == - 1) return; // 전화번호 처음까지 지났다면 종료

                if(result[i] == getCharKey(phoneNum[i], 3)) // 3가지 모든 문자를 순회했다면
                    result[i] = getCharKey(phoneNum[i], 1); // 다시 첫번째 문자로 초기화
                else if(result[i] == getCharKey(phoneNum[i], 1)) { // 첫번째를 수행한 후
                    result[i] = getCharKey(phoneNum[i], 2); // 두번째로 넘어감
                    break; // 출력문으로 간 후 다시 제일 뒤로
                }
                else if(result[i] == getCharKey(phoneNum[i], 2)) { // 두번째를 수행한 후
                    result[i] = getCharKey(phoneNum[i], 3); // 세번째로 넘어감
                    break; // 출력문으로 간 후 다시 제일 뒤로
                }
            }

        }
    }

    // 결과 출력 함수
    private void print(){
        for(int i = 0; i < phoneNum.length; i++){
            System.out.print(result[i]);
        }
        System.out.println();
    }
}
