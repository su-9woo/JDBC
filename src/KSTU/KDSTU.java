package KSTU;

public class KDSTU {
    //필드
    private int kNo; // 번호
    private String kPW; // 비밀번호
    private String kName; //이름
    private int kAge; //나이
    private String kGender; //성별
    private String kEmail; //이메일
    private String kPhone;// 연락처

  //KSTU 테이블 만들기 !


    //생성자 : 기본생성자(생략)

    //메소드 : getter,setter,toString
    //마우스 오른쪽 생성 클릭
    public int getkNo() {
        return kNo;
    }

    public void setkNo(int kNo) {
        this.kNo = kNo;
    }

    public String getkPW() {
        return kPW;
    }

    public void setkPW(String kPW) {
        this.kPW = kPW;
    }

    public String getkName() {
        return kName;
    }

    public void setkName(String kName) {
        this.kName = kName;
    }

    public int getkAge() {
        return kAge;
    }

    public void setkAge(int kAge) {
        this.kAge = kAge;
    }

    public String getkGender() {
        return kGender;
    }

    public void setkGender(String kGender) {
        this.kGender = kGender;
    }

    public String getkEmail() {
        return kEmail;
    }

    public void setkEmail(String kEmail) {
        this.kEmail = kEmail;
    }

    public String getkPhone() {
        return kPhone;
    }

    public void setkPhone(String kPhone) {
        this.kPhone = kPhone;
    }

    @Override
    public String toString() {
        return "KDSTU[" +"바보들"+
                "번호 :" + kNo +
                ", 비밀번호 :'" + kPW + '\'' +
                ", 이름 :" + kName + '\'' +
                ", 나이:" + kAge +
                ", 성별:" + kGender + '\'' +
                ", 이메일:" + kEmail + '\'' +
                ", 연락처:" + kPhone + '\'' +
                "]";
    }
}

