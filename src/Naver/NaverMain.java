        package Naver;

        import java.util.Scanner;

        public class NaverMain {



            //메인 메뉴
            public static void main(String[] args) {
                boolean run = true;
                int menu = 0;
                int menu1 = 0;
                boolean run1 = true;

                Scanner sc = new Scanner(System.in);
                NaverSQL sql = new NaverSQL();
                NaverMember nm;
               sql.connect();
                while(run) {
                    System.out.println("네이버에 오신것을 환영하오. 잡것들아.");
                    System.out.println("======================================");
                    System.out.println("[1]회원가입\t\t[2]로그인\t\t[3]종료");
                    System.out.println("======================================");
                    System.out.print("선택 >> ");
                    menu = sc.nextInt();
                    switch (menu){
                        case 1:
                            System.out.println("회원정보를 입력하세요");
                            nm = new NaverMember();
                            System.out.println("아이디 >>");
                            nm.setnId(sc.next());
                            System.out.println("비밀번호 >>");
                            nm.setnPw(sc.next());
                            System.out.println("이름 >>");
                            nm.setnName(sc.next());
                            System.out.println("생일 >>");
                            nm.setnBirth(sc.next());
                            System.out.println("성별 >>");
                            nm.setnGender(sc.next());
                            System.out.println("이메일 >>");
                            nm.setnEmail(sc.next());
                            System.out.println("전화번호 >>");
                            nm.setnPhone(sc.next());
                            sql.join(nm);
                            break;
                        case 2:
                            System.out.println("잡것들아 로그인해라 .");
                            System.out.println("======================================");
                            System.out.println("아이디 >>");
                            String loginId = sc.next();
                            System.out.println("비밀번호 >>");
                            String loginPw = sc.next();

                            run1 = true;
                            while(run1) {


                                nm = sql.login(loginId, loginPw);




                                if (nm != null) {
                                    System.out.println("잡것들아 로그인 잘했다!");
                                    System.out.println("======================================");
                                    System.out.println("[1]회원목록\t\t[2]내정보보기 \t\t[3]내정보수정");
                                    System.out.println("[4]탈퇴\t\t[5]로그아웃 ");
                                    System.out.print("선택 >> ");
                                    menu1 = sc.nextInt();


                                    switch (menu1) {
                                        case 1:
                                            sql.nmInfo();
                                            break;
                                        case 2:

                                            nm = sql.myInfo(loginId); //myinfo메서드에 loginid를 넣어 메서드를 출력함
                                            //메서드안에 System.out.println(nm.toString()); 명령어가 포함되어있음
                                           //회원정보를 모두 불러온것을 확인할수있음
                                            break;
                                        case 3:
                                            nm = sql.myInfo(loginId);
                                            if (nm == null) {
                                                System.out.println("회원 정보를 불러오지 못했습니다.");
                                                break;
                                            }
                                            System.out.println("비밀번호 >>");
                                            nm.setnPw(sc.next());
                                            System.out.println("이름 >>");
                                            nm.setnName(sc.next());
                                            System.out.println("생일 >>");
                                            nm.setnBirth(sc.next());
                                            System.out.println("성별 >>");
                                            nm.setnGender(sc.next());
                                            System.out.println("이메일 >>");
                                            nm.setnEmail(sc.next());
                                            System.out.println("전화번호 >>");
                                            nm.setnPhone(sc.next());
                                           //객체변수nm에 각종 값들을 넣으면 그것을
                                            sql.myUpdate(loginId);
                                            //현재 코드에서는 Scanner를 통해 입력받은 값들이 NaverMember 객체 nm의 각 필드에 저장됩니다.
                                            // 이 단계에서는 데이터베이스에 직접 값을 넣는 것이 아니라, 객체 nm에만 값을 저장하는 것입니다.
                                            //
                                            //그 다음, sql.myUpdate(nm); 메서드를 호출하여 nm 객체의 필드 값을 데이터베이스에 업데이트합니다.
                                            //  myUpdate 메서드는 nm 객체의 필드 값을 사용하여 SQL UPDATE 쿼리를 실행합니다.



                                            run1 = false;
                                            System.out.println(" 비밀 번호가 바뀌어 로그아웃 되었습니다. 프로그램을 종료합니다.");



                                            break;
                                        case 4:
                                            System.out.println("정말 탈퇴하시겠습니까? (y/n)");
                                            String checkDelete = sc.next();
                                            switch(checkDelete){
                                                case "y":
                                                case    "Y":
                                                    sql.myDelete(loginId);
                                                    run1 = false;
                                                    break;
                                                case "n":
                                                case "N":
                                                    break;


                                            }

                                            break;
                                        case 5:
                                            System.out.println("로그아웃 되었습니다. 프로그램을 종료합니다.");

                                            sql.conClose();
                                            run1 = false;
                                            break;
                                        default:
                                            System.out.println("잘못입력하셨습니다.");
                                            break;
                                    }


                                }else{
                                    System.out.println("꺼저 로그인도 못하는것아");
                                    break;
                                }


                            }
                            break;

                        case 3:
                            System.out.println("프로그램을 종료합니다.");
                            run =false;
                            sql.conClose();
                            break;


                        default:
                            System.out.println("잘못 입력하셨습니다.");
                            break;
                    }

                }


            }
            // [1] 회원가입 [2] 로그인


            // [1] 회원가입
            //회원 등록하는 작업 실행

            // [2] 로그인
            //아이디와 비밀번호를 입력받아서 모두일치여부 확인 로그인 실패시 다시 메인메뉴로 리턴하게끔

            // [3] 종료

            //로그인 성공시
            //[1] 회원목록 [2] 내정보보기 [3] 내정보수정 [4] 탈퇴 [5] 로그아웃 (아마 종료)와 같은 코드 활용

        }
