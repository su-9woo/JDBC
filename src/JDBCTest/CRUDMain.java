package JDBCTest;

import java.util.Scanner;

public class CRUDMain {

    public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);
            boolean run = true;
            int menu = 0;
           CRUD crud = new CRUD();
           //클레스 객체변수 = new 객체(생성자);
        while(run){
            System.out.println("===========================================");
            System.out.println("[1]connect\t\t[2]insert\t\t[3]select");
            System.out.println("[4]update\t\t[5]delete\t\t[6]close");
            System.out.println("\\t\\t[7]emp");
            System.out.println("===========================================");
            System.out.print("선택 >> ");
            menu = sc.nextInt();
            switch (menu){
                case 1: //DB접속 // connect를 하지않고 다른 메서드를 실행하면 오류발생
                    crud.connect();
                break;
                case 2:
                    crud.insert();
                    break;
                case 3:
                    crud.select();
                    break;
                case 4:
                    crud.update();
                    break;
                case 5:
                    crud.delete();
                    break;
                case 6:
                    break;
                case 7:
                    crud.selectEMP();
                    break;
                default:
                    System.out.println("다시 입력하세요.");

                break;

            }
        }








    }
}
