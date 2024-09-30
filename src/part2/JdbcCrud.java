package part2;

import part1.common.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JdbcCrud {
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;

    public ArrayList<Members> select() {
        con = JdbcUtil.getConnection();
        String sql = "select id,name,age,gender from members order by id";
        ArrayList<Members> list = null;  //초기값
        try {
            pstmt = con.prepareStatement(sql);  //sql문 파싱
            rs = pstmt.executeQuery();
            list = new ArrayList<>(); //select 성공
            Members mb = null;
            while (rs.next()) {
                mb = new Members();
                mb.setId(rs.getString("ID"));
                mb.setName(rs.getString("name"));
                mb.setAge(rs.getInt("age"));
                mb.setGender(rs.getString("gender"));
                list.add(mb); //배열에 회원추가
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("select 예외 발생");
        } finally {
            close();
        }
        return null;
    }

    public boolean join(Members mb) {
        con = JdbcUtil.getConnection();
        String sql = "insert into members values(?,?,?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);  //파싱1번
            pstmt.setString(1, mb.getId());
            pstmt.setString(2, mb.getPw());
            pstmt.setString(3, mb.getName());
            pstmt.setInt(4, mb.getAge());
            pstmt.setString(5, mb.getGender());
            int result = pstmt.executeUpdate(); //insert,update,delete
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("join 예외 발생");
            e.printStackTrace();
        } finally {
            close();
        }
        return false;
    }

    private void close() {
        JdbcUtil.close(rs);
        JdbcUtil.close(pstmt);
        JdbcUtil.close(con);
    }

    public ArrayList<Members> login(String id, String pw) {
        con = JdbcUtil.getConnection();
        String sql = "select id,pw from members where id=?";
        ArrayList<Members> list = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            Members mb = null;
            if (rs.next()) { //아이디 존재함
                if (rs.getString("id").equals("admin")) {
                    //관리자인 경우
                    if (rs.getString("pw").equals(pw)) {
                      

                        //모든 회원정보를 DB에서 읽어옴
                        //ArrayList에 저장후 리턴

                    }
                } else { //일반인 경우
                    if (rs.getString("pw").equals(pw)) {


                        //일반인 로그인  성공
                        //본인 회원정보를 DB에서 읽어옴
                        //ArrayList에 저장후 리턴
                    }
                }
            }//if 아이디 존재여부
        } catch (SQLException e) {
            System.out.println("login 예외 발생");
        } finally {
            close();
        }
        return null; //예외발생 or id 없을때, 비번이 틀린경우
    }

}
