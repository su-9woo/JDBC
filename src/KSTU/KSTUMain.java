package KSTU;

import java.util.Scanner;

public class KSTUMain {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean run = true;
        int menu = 0;
        int kNo = 0;

        KDSTU stu = new KDSTU();
        KSTUSQL sql = new KSTUSQL();

        // DB접속 <== 프로그램 실행시
        sql.connect();


        while(run){
            System.out.println("======================================");
            System.out.println("[1]학생등록\t\t[2]학생목록\t\t[3]학생상세");
            System.out.println("[4]학생수정\t\t[5]학생삭제\t\t[6]종료");
            System.out.println("======================================");
            System.out.print("선택 >> ");
            menu = sc.nextInt();

            switch (menu){
                case 1:
                    System.out.println("학생정보를 입력하세요");
                    stu = new KDSTU();

                    System.out.print("번호 >> ");
                    stu.setkNo(sc.nextInt());

                    System.out.print("비밀번호 >> ");
                    stu.setkPW(sc.next());

                    System.out.print("이름 >> ");
                    stu.setkName(sc.next());

                    System.out.print("나이 >> ");
                    stu.setkAge(sc.nextInt());

                    System.out.print("성별 >> ");
                    stu.setkGender(sc.next());

                    System.out.print("이메일 >> ");
                    stu.setkEmail(sc.next());

                    System.out.print("연락처 >> ");
                    stu.setkPhone(sc.next());

                    sql.stuInsert(stu);
                    break;
                case 2:
                    sql.stuList();
                    break;
                case 3:
                    System.out.print("조회할 학생번호 >> ");
                    kNo = sc.nextInt();
                    System.out.println(sql.stuDetail(kNo));
                    break;
                case 4:
                    System.out.print("수정할 학생번호 >> ");
                    stu = sql.stuDetail(sc.nextInt());
                    // 입력한 학생번호에 대한 학생정보를 모두 가져와서 stu에 담고 시작
                    //정말아름다운 수식이다.






                    System.out.println("======================================");
                    System.out.println("[1]비밀번호\t\t[2]학생이름\t\t[3]나 이");
                    System.out.println("[4]성 별\t\t[5]이메일\t\t[6]연락처");
                    System.out.println("======================================");
                    System.out.print("선택 >> ");
                    menu = sc.nextInt();

                 switch(menu){
                     case 1 :
                         System.out.print("비밀번호 >> ");
                         stu.setkPW(sc.next());
                         System.out.println("수정 후 정보 :" + stu);
                         break;
                     case 2 :
                         System.out.print("이름 >> ");
                         stu.setkName(sc.next());
                         System.out.println("수정 후 정보 :" + stu);
                         break;
                     case 3 :
                         System.out.print("나이 >> ");
                         stu.setkAge(sc.nextInt());
                         System.out.println("수정 후 정보 :" + stu);
                         break;
                     case 4 :
                         System.out.print("성별 >> ");
                         stu.setkGender(sc.next());
                         System.out.println("수정 후 정보 :" + stu);
                         break;
                     case 5 :
                         System.out.print("이메일 >> ");
                         stu.setkEmail(sc.next());
                         System.out.println("수정 후 정보 :" + stu);
                         break;
                     case 6 :
                         System.out.print("연락처 >> ");
                         stu.setkPhone(sc.next());
                         System.out.println("수정 후 정보 :" + stu);
                         break;
                     default:
                         System.out.println("다시 입력!");
                         break;
                 }

                case 5:
                    System.out.print("삭제할 학생번호 >> ");
                    sql.stuDelete(sc.nextInt());
                    break;
                case 6:
                    run = false;    // 필수

                    // DB접속 해제 <== 종료시(선택)
                    sql.conClose();
                    break;
                default:
                    System.out.println("다시 입력하세요.");
                    break;
            }
        }






    }
}
