package KSTU;

import java.sql.*;
import java.util.ArrayList;

public class KSTUSQL {

    Connection con;         // DB접속
    // Statement stmt;      // SQL문 작성
    ResultSet rs;           // DB결과

    PreparedStatement pstmt;
    // SQL문을 DB로 전달하기 위한 객체
    // Statement stmt 대신 사용
    // '?' 를 데이터로 인식

    // [0-1] DB접속 메소드
    public void connect(){
        con = DBC.DBConnect();
    }

    // [0-2] DB해제 메소드
    public void conClose(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // [1] 학생등록 : stuInsert
    public void stuInsert(KDSTU stu){


        try {
            // (1) SQL문 작성
            String sql = "INSERT INTO KSTU VALUES(?, ?, ?, ?, ?, ?, ?)";

            // (2) 화면준비
            // stmt = con.createStatement();
            pstmt = con.prepareStatement(sql);

            // (3) '?'에 데이터를 입력
            // pstmt.setInt(parameterIndex(? 번호), int 타입의 데이터)
            // pstmt.setString(parameterIndex(? 번호), String 타입의 데이터)
            pstmt.setInt(1, stu.getkNo());
            pstmt.setString(2, stu.getkPW());
            pstmt.setString(3, stu.getkName());
            pstmt.setInt(4, stu.getkAge());
            pstmt.setString(5, stu.getkGender());
            pstmt.setString(6, stu.getkEmail());
            pstmt.setString(7, stu.getkPhone());

            // (4) 실행
            int result = pstmt.executeUpdate();

            // (5) 결과(선택)
            if(result > 0){
                System.out.println("학생등록 성공!");
            } else {
                System.out.println("학생등록 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // [2] 학생목록
    public void stuList(){
        //학생 한명의 정보를 담을 수 있는 STU 객체

        //학생목록을 만들 수 있는 kdList
        ArrayList<KDSTU> kdList = new ArrayList<>();
        /*
        collection : (1) Set (2) Map (3) List
        Array(배열),List(목록) => 여러 데이터를 저장하는데 사용되는 구조
        -Array : 크기가 고정되어 있다.
        :기본타입(정수형,실수형,문자형,논리형)

        List:크기가 변한다.
        :참조형 (객체)

        List,ArrayList 비교 : List는 인터페이스, ArrayList는 List의 구현 클레스

         */
        try {
            // (1) SQL문 작성
            String sql = "SELECT * FROM KSTU";

            // (2) 화면준비
            pstmt = con.prepareStatement(sql);

            // (3) '?' 안에 데이터 입력, '?'가 없으면 패스

            // (4) 실행
            rs = pstmt.executeQuery();

            // (5) 결과
            while(rs.next()){

                //학생 한명의 정보를 담을 수 있는 STU 객체 이기떄문에 while문안에 들어가야만 학생 전체의 정보를 출력할수 있습니다.
                KDSTU stu = new KDSTU();
/*                System.out.print("번호 : " + rs.getInt(1));
                System.out.print("\t| 이름 : " + rs.getString(3));
                System.out.println("\t| 성별 : " + rs.getString(5));*/
                stu.setkNo((rs.getInt(1)));
                stu.setkPW(rs.getString(2));
                stu.setkName(rs.getString(3));
                stu.setkAge(rs.getInt(4));
                stu.setkGender(rs.getString(5));
                stu.setkEmail(rs.getString(6));
                stu.setkPhone(rs.getString(7));
                kdList.add(stu);
            }
            for(int i = 0; i <kdList.size(); i++){
 /*               System.out.print(" 번호 :" + kdList.get(i).getkNo());
                System.out.print(" | 이름 :" + kdList.get(i).getkName());
                System.out.println(" |성별 :" + kdList.get(i).getkGender());
*/
                System.out.println(kdList.get(i).toString());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // [3] 학생상세보기
    public KDSTU stuDetail(int kNo){

        KDSTU stu = new KDSTU();

        try {
            // (1) SQL문 작성
            String sql = "SELECT * FROM KSTU WHERE KNO = ?";

            // (2) 화면준비
            pstmt = con.prepareStatement(sql);

            // (3) '?' 안에 데이터 입력, '?'가 없으면 패스
            pstmt.setInt(1, kNo);

            // (4) 실행
            rs = pstmt.executeQuery();

            // (5) 결과
            if(rs.next()){
/*                System.out.print("번호 : " + rs.getInt(1));
                System.out.print("\t| 비밀번호 : " + rs.getString(2));
                System.out.print("\t| 이름 : " + rs.getString(3));
                System.out.println("\t| 나이 : " + rs.getInt(4));
                System.out.print("성별 : " + rs.getString(5));
                System.out.print("\t| 이메일 : " + rs.getString(6));
                System.out.println("\t| 연락처 : " + rs.getString(7));*/
                stu.setkNo((rs.getInt(1)));
                stu.setkPW(rs.getString(2));
                stu.setkName(rs.getString(3));
                stu.setkAge(rs.getInt(4));
                stu.setkGender(rs.getString(5));
                stu.setkEmail(rs.getString(6));
                stu.setkPhone(rs.getString(7));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
   return stu;
    }

    // [4] 학생수정
    public void stuUpdate(KDSTU stu){
        try {
            // (1) SQL문 작성
            String sql = "UPDATE KSTU SET KPW = ?, KNAME = ?, KAGE = ?," +
                    "KGENDER = ?, KEMAIL = ?, KPHONE = ? WHERE KNO = ?";

            // (2) 화면준비
            // stmt = con.createStatement();
            pstmt = con.prepareStatement(sql);

            // (3) '?'에 데이터를 입력
            // pstmt.setInt(parameterIndex(? 번호), int 타입의 데이터)
            // pstmt.setString(parameterIndex(? 번호), String 타입의 데이터)
            pstmt.setString(1, stu.getkPW());
            pstmt.setString(2, stu.getkName());
            pstmt.setInt(3, stu.getkAge());
            pstmt.setString(4, stu.getkGender());
            pstmt.setString(5, stu.getkEmail());
            pstmt.setString(6, stu.getkPhone());
            pstmt.setInt(7, stu.getkNo());

            // (4) 실행
            int result = pstmt.executeUpdate();

            // (5) 결과(선택)
            if(result > 0){
                System.out.println("학생수정 성공!");
            } else {
                System.out.println("학생수정 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // [5] 학생삭제
    public void stuDelete(int kNo){
        try {
            // (1) SQL문 작성
            String sql = "DELETE FROM KSTU WHERE KNO = ?";

            // (2) 화면준비
            // stmt = con.createStatement();
            pstmt = con.prepareStatement(sql);

            // (3) '?'에 데이터를 입력
            pstmt.setInt(1, kNo);

            // (4) 실행
            int result = pstmt.executeUpdate();

            // (5) 결과(선택)
            if(result > 0){
                System.out.println("학생삭제 성공!");
            } else {
                System.out.println("학생삭제 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void stuInfo(KDSTU stu) {
    }
}









