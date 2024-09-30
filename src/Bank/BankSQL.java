package Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankSQL {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public void connect(){
        con = DBC.DBConnect();

    }
    public  void conClose(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int clientNumber() {
        int cNum = 0;
        System.out.println("고객번호확인" + cNum);
        //BCLIENT 테이블에서 MAX(CNUM)을 조회
        String sql = "SELECT MAX(CNUM) FROM BCLIENT";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                cNum = rs.getInt(1);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return cNum + 1; //메소드타입의 변수를만들고 리턴값대신애 넣는다면 메소드를 사용한게되겟죠 void값이 아니더라도
        //고객정보는 cNum의 + 1입니다!

    }

    public String accountNumber() {

        String cAccount = null;

        //카카오뱅크 계좌번호 : 3333-XX(2자리)-XXXXXXX(7자리)
        //Math.random()메소드 사용해서 무작위 숫자 생성후 원래 계좌랑 비교해서 같으면 생성취소 이런느낌으로도 가능하겠지 나중엔
        // 3333-XX-XXXXXXX
        // 0부터 9까지 => (int)(Math.random()*9)
        cAccount = "3333-";
        for(int i=1; i<=2; i++){
            cAccount+= (int)(Math.random()*9);
        //3333-xx두개 추가됨
        }
        cAccount+="-";
        //3333-XX-
        for(int i=1; i<=7; i++){
            cAccount += (int)(Math.random()*9);
            //3333-xx-xxxxxxx
            //우선 중복 걱정x

        }
        return cAccount;

    }

    public void createAccount(Client ct) {

        try {
            String sql = "INSERT INTO BCLIENT VALUES(?,?,?,?)";
            ps = con.prepareStatement(sql);

            // [3] ?에 값을넣자.
            ps.setInt(1,ct.getcNum());
            ps.setString(2,ct.getcName());
            ps.setString(3,ct.getcAccount());
            ps.setInt(4,ct.getBalance());
            // 고객정보,고객이름,고객계좌,고객잔액
            int result = ps.executeUpdate();
            if(result>0){
                System.out.println("계좌생성 성공!");
                System.out.println("고객번호 : " + ct.getcNum());
                System.out.println("계좌번호 : " + ct.getcAccount());
                System.out.println();
                //
            }else{
                System.out.println("계좌생성 실패!");

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public boolean createAccount(String cAccount) {
        boolean checked = false;
        try {
            String sql = "SELECT * FROM BCLIENT WHERE CACCOUNT = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,cAccount);
            rs = ps.executeQuery();
            checked = rs.next();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return checked;
    }

    public void deposit(String cAccount, int amout) {

        String sql = "UPDATE BCLIENT SET BALANCE = BALANCE + ? WHERE CACCOUNT = ?";
        //원래있던 잔액인 BALANCE에 ?가 추가된다 CACCOUT가 ?인 계좌에서


        try {

            //(1) sql문 작성

            //(2) DB준비

            ps = con.prepareStatement(sql);


            //(3)   Main에서 가져온 amount와 cAccount를 가져와서 쿼리문에 '?'에 입력한다. .'?' 데이터 입력
            ps.setInt(1,amout);
            ps.setString(2,cAccount);

            //(4) 실행
            int result = ps.executeUpdate();


            //(5) 결과

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void Withdrawal(String cAccount1, int amout1) {

        String sql = "UPDATE BCLIENT SET BALANCE = BALANCE - ? WHERE CACCOUNT = ?";
        //원래있던 잔액인 BALANCE에 ?가 추가된다 CACCOUT가 ?인 계좌에서


        try {

            //(1) sql문 작성

            //(2) DB준비

            ps = con.prepareStatement(sql);


            //(3)   Main에서 가져온 amount와 cAccount를 가져와서 쿼리문에 '?'에 입력한다. .'?' 데이터 입력
            ps.setInt(1,amout1);
            ps.setString(2,cAccount1);

            //(4) 실행
            int result = ps.executeUpdate();


            //(5) 결과

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int checkBalance(String cAccount1) {
        int balance = 0;
          try {

              //(1) sql문 작성
              String sql = "SELECT BALANCE FROM BCLIENT WHERE CACCOUNT = ?";

                      //(2) DB준비

                      ps = con.prepareStatement(sql);


                      //(3) '?' 데이터 입력
                      ps.setString(1,cAccount1);
                      //(4) 실행
              rs = ps.executeQuery();

                      //(5) 결과
                      if(rs.next()){
                          balance =rs.getInt(1);
                      }
                  } catch (SQLException e) {
                      throw new RuntimeException(e);
                  }
        return balance ;
    }
    public boolean checkAccount(String cAccount) {
        boolean checked = false;
        try {
            String sql = "SELECT * FROM BCLIENT WHERE CACCOUNT = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, cAccount);
            rs = ps.executeQuery();
            checked = rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return checked;
    }
}

