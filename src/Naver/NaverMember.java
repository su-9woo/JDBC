package Naver;

public class NaverMember {

    //필드

    private String nId;
    private String nPw;
    private String nName;
    private String nBirth;
    private String nGender;
    private String nEmail;
    private String nPhone;
    //생성자

    public NaverMember() {
        this.nId = nId;
        this.nPw = nPw;
        this.nName = nName;
        this.nBirth = nBirth;
        this.nGender = nGender;
        this.nEmail = nEmail;
        this.nPhone = nPhone;
    }


    //메소드 getter setter 만들기

    public String getnId() {
        return nId;
    }

    public void setnId(String nId) {
        this.nId = nId;
    }

    public String getnPw() {
        return nPw;
    }

    public void setnPw(String nPw) {
        this.nPw = nPw;
    }

    public String getnName() {
        return nName;
    }

    public void setnName(String nName) {
        this.nName = nName;
    }

    public String getnBirth() {
        return nBirth;
    }

    public void setnBirth(String nBirth) {
        this.nBirth = nBirth;
    }

    public String getnGender() {
        return nGender;
    }

    public void setnGender(String nGender) {
        this.nGender = nGender;
    }

    public String getnEmail() {
        return nEmail;
    }

    public void setnEmail(String nEmail) {
        this.nEmail = nEmail;
    }

    public String getnPhone() {
        return nPhone;
    }

    public void setnPhone(String nPhone) {
        this.nPhone = nPhone;
    }


    //DB 테이블 만들기 to string 만들기


    @Override
    public String toString() {
        return "NaverMember{" +
                "nId='" + nId + '\'' +
                ", nPw='" + nPw + '\'' +
                ", nName='" + nName + '\'' +
                ", nBirth='" + nBirth + '\'' +
                ", nGender='" + nGender + '\'' +
                ", nEmail='" + nEmail + '\'' +
                ", nPhone='" + nPhone + '\'' +
                '}';
    }
}
