package Naver;

import KSTU.DBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NaverSQL {
    Connection con;
    ResultSet rs;
    PreparedStatement ps;
    NaverMember nm;


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

    //[회원가입]
    public void join(NaverMember nm){
            try {
                String sql = "INSERT INTO NaverMember values(?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, nm.getnId());
                ps.setString(2, nm.getnPw());
                ps.setString(3, nm.getnName());
                ps.setString(4, nm.getnBirth());
                ps.setString(5, nm.getnGender());
                ps.setString(6,nm.getnEmail());
                ps.setString(7, nm.getnPhone());
                int result = ps.executeUpdate();
                if(result > 0){
                    System.out.println("회원가입 성공!");
                } else {
                    System.out.println("회원가입 실패");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
    public NaverMember login(String nId,String nPw){
        NaverMember nm = new NaverMember();
        try{
            String sql = "SELECT * FROM NaverMember WHERE NID = ? and NPW = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, nId);
            ps.setString(2, nPw);
            rs = ps.executeQuery();
            if (rs.next()) {
                nm = new NaverMember();
                nm.setnId(rs.getString(1 ));
                nm.setnPw(rs.getString(2));
                nm.setnName(rs.getString(3));
                nm.setnBirth(rs.getString(4));
                nm.setnGender(rs.getString(5));
                nm.setnEmail(rs.getString(6));
                nm.setnPhone(rs.getString(7));
            return nm;}
            else{return null;}


        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }
    //회원목록 보기
    public void nmInfo(){
        ArrayList<NaverMember> nmList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM NaverMember";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                //회원 한명의 정보를 담을 수 있는 STU 객체 이기떄문에 while문안에 들어가야만 회원 전체의 정보를 출력할수 있습니다.
                NaverMember nm = new NaverMember(); //nm이라는 각각 새로운 객체를 만들고
                nm.setnId(rs.getString(1));
                nm.setnPw(rs.getString(2));
                nm.setnName(rs.getString(3));
                nm.setnBirth(rs.getString(4));
                nm.setnGender(rs.getString(5));
                nm.setnEmail(rs.getString(6));
                nm.setnPhone(rs.getString(7));
                nmList.add(nm); // nm이라는 객체에 담는다.


            }  for(int i =0; i < nmList.size(); i++){
                System.out.println(nmList.get(i).toString());
            }  //같은표현을 배워보자
              /* for(NaverMember list : nmList){
                       System.out.println(list.getnId());
                       System.out.println(list.getnEmail());
                       System.out.println(list.getnGender());}
*/

        } catch (Exception e) {
            System.out.println("오류가 왜뜨지?");
            throw new RuntimeException(e);
        }

    }//내정보 보기
    public NaverMember myInfo(String nId){

        try { String sql = "SELECT * FROM NaverMember WHERE NID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,nId);
            rs= ps.executeQuery();
            if(rs.next()){
                nm = new NaverMember();
                nm.setnId(rs.getString(1));
                nm.setnPw(rs.getString(2));
                nm.setnName(rs.getString(3));
                nm.setnBirth(rs.getString(4));
                nm.setnGender(rs.getString(5));
                nm.setnEmail(rs.getString(6));
                nm.setnPhone(rs.getString(7));


                System.out.println(nm.toString());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nm;
    }
    public NaverMember myUpdate(String nId){
        try {
            // (1) SQL문 작성
            String sql = "UPDATE NaverMember SET NPW = ?, NNAME = ?, NBIRTH = ?," +
                    "NGENDER = ?, NEMAIL = ?, NPHONE = ? WHERE NID = ?";

            //화면준비
            ps = con.prepareStatement(sql);

            // (3) '?'에 데이터를 입력
            // pstmt.setInt(parameterIndex(? 번호), int 타입의 데이터)
            // pstmt.setString(parameterIndex(? 번호), String 타입의 데이터)

            ps.setString(1, nm.getnPw());
            ps.setString(2, nm.getnName());
            ps.setString(3, nm.getnBirth());
            ps.setString(4, nm.getnGender());
            ps.setString(5, nm.getnEmail());
            ps.setString(6, nm.getnPhone());
            ps.setString(7,nId);



            int result = ps.executeUpdate();


            if(result > 0){
                System.out.println("맴버수정 성공!");

            } else {
                System.out.println("맴버수정 실패");
            }

return nm;
    } catch (Exception e) {
            throw new RuntimeException(e);
        }



        }
    public void myDelete(String nId){

       try {
           String sql = "DELETE FROM NaverMember WHERE nId = ?";
           ps=con.prepareStatement(sql);
           ps.setString(1,nId);
           int result = ps.executeUpdate();
           if(result>0){
               System.out.println("맴버 삭제 성공");

           }else{
               System.out.println("맴버 삭제 실패");
           }

       } catch (Exception e) {
           throw new RuntimeException(e);
       }

    }


    }
