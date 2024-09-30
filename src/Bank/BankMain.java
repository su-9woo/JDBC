package Bank;

import java.util.Scanner;

public class BankMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        int menu = 0;
        int amount1 = 0;
        int balance = 0;
        Client ct = new Client();
        BankSQL sql = new BankSQL();
        System.out.println("ICIA 은행에 오신 것을 환영합니다.");
        System.out.println("무엇을 도와드릴까요?");
        sql.connect();
        while(run) {
            System.out.println("=============================");
            System.out.println("[1] 생성\t\t[2]입금\t\t[3]출금");
            System.out.println("[4]송금\t\t[5]조회\t\t[6]종료");
            System.out.println("==============================");
            System.out.println();

        menu = sc.nextInt();
            switch (menu) {

                case 1:
                    // 고객번호(자동)
                    // int cNum = sql.clientNumber();

                    ct.setcNum(sql.clientNumber());
                    //고객이름(작성)
                    System.out.println("고객이름 >>");
                    ct.setcName(sc.next());
                    //계좌번호(자동)
/*                    String cAccount = sql.accountNumber();
                    System.out.println("계좌번호 조회 : "+ cAccount);
                   */
                    ct.setcAccount(sql.accountNumber());
                    //잔액 : 0 원
                    ct.setBalance(0);
                    sql.createAccount(ct);


                    //잔액 : 0원

                    break;
                case 2:
                    // 입금할 계좌 번호 입력후 존재 유무 확인
                    System.out.println("계좌번호 입력 >> ");
                    String cAccount = sc.next();
                    //계좌번호 유무 확인
                    boolean checked = sql.createAccount(cAccount);

                    if (checked) {
                        System.out.println("계좌 존재");
                        System.out.print("입금할 금액 >>");
                        int amout = sc.nextInt();
                        sql.deposit(cAccount, amout);


                    } else {
                        System.out.println("계좌가 존재하지 않습니다.");
                    }


                    break;
                case 3:
                    System.out.println("계좌번호 입력 >> ");
                    String cAccount1 = sc.next();
                    //계좌번호 유무 확인
                    boolean checked1 = sql.createAccount(cAccount1);

                    if (checked1) {
                        System.out.println("계좌 존재합니다");
                        System.out.print("출금할 금액 >>");
                        amount1 = sc.nextInt();
                        sql.Withdrawal(cAccount1, amount1);
                        System.out.println("출금성공!");


                    } else {

                        System.out.println("출금 실패");
                        System.out.println("계좌가 존재하지 않습니다.");
                    }
                    //해당 계좌의 잔액 확인
                    balance = sql.checkBalance(cAccount1);
                    amount1 = 0;
                    if (balance >= amount1) {
                        sql.Withdrawal(cAccount1, amount1);
                        System.out.println("출금성공!");
                    } else {
                        System.out.println("출금실패!");
                        System.out.println("잔액이 " + (amount1 - balance) + "원 부족합니다.");

                    }
                    break;
                case 4:
                    System.out.print("보내는 분 계좌 >>");
                    String sAccount = sc.next();
                    if(sql.checkAccount(sAccount)) {
                        System.out.print("받는 분계좌 >>");
                        String rAccount = sc.next();
                        if (sql.checkAccount(rAccount)) {
                            System.out.println("송금할 금액>>>");
                            amount1 = sc.nextInt();

                            balance = sql.checkBalance(sAccount);
                            if (balance >= amount1) {
                                sql.Withdrawal(sAccount, amount1);
                                sql.deposit(rAccount, amount1);
                                System.out.println("송금 성공");

                            } else {
                                System.out.println("잔액이 " + (amount1 - balance) + "원 부족합니다.");

                            }
                        } else {
                            System.out.println("받는분 계좌가 정확하지 않습니다.");
                        }
                        }else{
                            System.out.println("보내는분 계좌가 정확하지 않습니다.");
                        }

                    break;
                case 5:
                    System.out.print("계좌번호 입력 >> ");
                    cAccount = sc.next();
                    checked = sql.checkAccount(cAccount);

                    if(checked){
                        balance = sql.checkBalance(cAccount);
                        System.out.println("현재 잔액 : " + balance + "원");
                    } else {
                        System.out.println("계좌가 존재하지 않습니다.");
                    }

                    break;
                case 6:
                    System.out.println("이용해 주셔서 감사합니다.");
                    run = false;
                    sql.conClose();
                    break;
                default:
                    System.out.println("다시 입력하세요");
                    break;
            }
        }


    }




}
