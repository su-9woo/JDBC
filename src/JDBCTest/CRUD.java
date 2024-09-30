package JDBCTest;

import java.sql.*;

public class CRUD {

    /*
    C(Crate) : 생성
    R(Read)  : 조회
    U(Update) : 수정
    D(Delete) : 삭제
     */
  //DB에 접속하기 위한 Connection 객체 생성
    Connection con; // = null;값이 들어가있다.

    // Java에서 작성한 sql문을 DB로 전달하기 위한 Statement 객체 st 생성
    Statement st;

    //DB에서 실행된 결과를 저장하는 Result 객체 rs 생성
    ResultSet rs;

    // [1] DB에 접속하기 위한 메소드
    public void connect(){
        con = DBConnection.DBCnnect(); // DBConnect라는 메소드의 기본값을 객체 con에 가져옴

    }
    //[2] C :DB에 데이터를 저장하기 위한 메소드
    public void insert(){


            //(2) SQL문 작성
            String sql = "INSERT INTO JDBCT VALUES('JAVA',21)";
            String cud = "insert";
            CUD(sql,cud);


    }
    // [3] R: DB에 있는 데이터를 조회하기 위한 메소드
    public void select(){

        try {
            //(1) 화면준비
            st = con.createStatement();
            //(2) sql문 작성
            String sql = "SELECT * FROM JDBCT";

            //(3) 실행
            rs = st.executeQuery(sql);
            //검색한 결과를 담기 위한 변수 rs(ResultSet 타입)
            // rs에 결과를 담을떄는 stmt.excuteQuery(sql)을 사용한다.

            //insert(C),update(u),delete(D) => st.excuteUpdate(sql) : int result
            //select(rs):
            //(4) 실행결과
            //rs.next() : boolean 타입, 데이터의 갯수만큼 반복문을 실행할떄 while문이나 for문에 넣어준다.
            //데이터가 존재하면 true, 존재하지 않으면 false
            //ex ) 데이터가 5개존재하면 5번동안 true, 6번쨰부터 false값을 갖게 된다.
            while(rs.next()){
                System.out.println("DATA1 : " + rs.getString(1)+ ", DATA2:" + rs.getInt(2));
            }

            //rs.getString(1): 1은 첫번째 컬럼, getString()=>컬럼의 데이터타입이 문자열(NVARCHAR2)일때
            //rs.getInt(2): 1은 두번째 컬럼, getInt()=>컬럼의 데이터타입이 정수형(NUMBER)일때
            //실수형일때는 getDouble()

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //[4] U :DB에 있는 데이터를 변경하기 위한 메소드
public void update(){



        // (2)sql문 작성
        String sql = "UPDATE JDBCT SET DATA2 = 17 WHERE DATA1 = 'JAVA'";
        //컬럼 1의 값이 JAVA인 행의 컬럼2의 값을 17로 바꾸겠따!
        String cud = "update";
        CUD(sql,cud);

}
// [5] D = DB에 있는 데이터를 삭제하기 위한 메소드
public void delete(){

      //  (2)
        String sql = "DELETE FROM JDBCT WHERE DATA2 = 17";
        String cud = "delete";
        CUD(sql,cud);


}
public void CUD(String sql,String cud){
    try {
        //  (1)화면준비
        st= con.createStatement();

        //  (3) 실행
        int result = st.executeUpdate(sql);


        //  (4) 결과
        if(result > 0){
            System.out.println(cud + "CUD 성공!");
        }else{
            System.out.println(cud + "CUD 실패!");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

}
//[6]DB접속 해제
    public void close() {
        try {
            con.close();
            System.out.println("DB접속 해제!");
        } catch (SQLException e) {
            System.out.println("DB접속 실패");
            throw new RuntimeException(e);

        }
    }


    public void selectEMP() {
        try {
            //(1) 화면준비
            st = con.createStatement();

            //(2) sql문 실행
            String sql = "SELECT * FROM EMP";

            // (3) 실행


            //(4) 실행결과 보여주기
            while(rs.next()){
                rs = st.executeQuery(sql);
                int EMPNO = rs.getInt(1);
                String ENAME = rs.getString(2);
                String JOB = rs.getString(3);
                int MGR =rs.getInt(4);
                Date HIREDATE = rs.getDate(5);
                int SAL = rs.getInt(6);
                int COMM = rs.getInt(7);
                int DEPTNO = rs.getInt(8);

                System.out.println("EMPNO :" + EMPNO + "ENAME :" +ENAME +
                        "JOB :" + JOB+"MGR :" +MGR + "HIREDATE :"+HIREDATE
                    +"SAL :" +SAL +"COMM :"+ COMM +"DEPTNO : " +DEPTNO );

/*                System.out.print("EMPNO:"+EMPNO);
                System.out.print("ENAME:"+ENAME);
                System.out.print("JOB"+JOB);
                System.out.print("MGR"+MGR);
                System.out.print("HIREDATE"+HIREDATE);
                System.out.print("SAL"+SAL);
                System.out.print("COMM"+COMM);
                System.out.print("DEPTNO"+DEPTNO);*/
            }



        } catch (SQLException e) {
            System.out.println("EMP 테이블 조회실패");
            throw new RuntimeException(e);

        }

    }


}
