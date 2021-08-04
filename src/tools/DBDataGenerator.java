package tools;


import java.security.SecureRandom;
import cn.edu.hit.sms.utils.DBUtils;

public class DBDataGenerator {
    static private String[] Courses = {
            "High-level Language Programming",
            "PjBL and Technological Innovation",
            "College English",
            "Calculus B(1)",
            "Linear Algebra and Analytic Geometry",
            "Physical Education",
            "Set Theory and Graph Theory",
            "Calculus B(2)",
            "Situation and Policy",
            "Compendium of Chinese Modern and Contemporary History",
            "Algorithmic Design and Analysis",
            "Mathematical Logic",
            "Probability and Statistics",
            "College Physics Experiments B",
            "Digital Logic Design",
            "Data Structures and Algorithms",
            "Biochemistry B",
            "Computer System",
            "Software Construction",
            "Introduction to Information Security",
            "Formal Languages and Automata",
            "Basic principles of Marxism",
    };
    static private String[] Names = {
            "Tess William",
            "Samantha Esther",
            "Zora Chamberlain",
            "Theresa Clarissa",
            "Iris David",
            "Merry Tout",
            "Zachary Nico(l)",
            "Levi Evans",
            "Shirley Sam",
            "Gale Holt",
            "Osborn Gus",
            "Stephanie Grantham",
            "Nydia Barrie",
            "Letitia Wodehous",
            "Prudence Lindberg(h)",
            "Elizabeth Saul",
            "Bonnie Raphael",
            "Marjorie Lena",
            "Fitch Donne",
            "Grace Daniel",
            "Scott Lily",
            "Barbara Woo",
            "lfEllis Webb",
            "Afra Malachi",
            "Elliot Bobby",
            "Jane Dobbin",
            "Carl Whitman",
            "Moira Kingsley",
            "Beau Dunbar",
            "Augustine Eliot",
            "Channing Finger",
            "Caesar Rob",
            "Oliver Robbins",
            "Spring Addison",
            "Hermosa Snow",
            "Bernard Malachi",
            "Kimberley Smith",
            "Albert Jack",
            "Benjamin Sandy",
            "Kerr Sapir",
            "Ken Stephens",
            "Porter Henry",
            "Ruth Alice",
            "Barbara Roy",
            "Ed Bartlett",
            "Tyler Kennedy",
            "Bradley Mac",
            "Millan",
            "Gabriel Philemon",
            "Harley Cumberland",
            "Isidore Becher",
            "Sherry Lloyd",
            "Brook Crofts",
            "Lee Sara(h)",
            "Hamiltion Horace",
            "Roberta Lyly",
            "Genevieve Leigh",
            "Pag Nelson",
            "Clyde Bess",
            "Daphne Juliana",
            "Darren Harry",
            "Jeffrey Dick",
            "Vivien Bentham",
            "Frances Surrey",
            "Bing Timothy",
            "Letitia Benjamin",
            "Teresa Shakespeare",
            "Hannah Berkeley",
            "Fabian Virginia",
            "Maxine Raglan",
            "Elma Job",
            "Kennedy Michelson",
            "Beatrice Zimmerman",
            "Meredith Clapham",
            "Ian Josh",
            "Wright Charley",
            "Carol Willard",
            "Lynn Judd",
            "Henry Grote",
            "Harriet Mc",
            "Carthy",
            "Sabina Washington",
            "Jeff Baker",
            "Roy Mac",
            "Arthur",
            "Byron Montgomery",
            "Sara Habakkuk",
            "Cora Godwin",
            "Chester Dillon",
            "Hulda Thoreau",
            "Liz Roosevelt",
            "Edwiin Bernard",
            "Darcy Hamilton",
            "Charlotte Eugene",
            "Perry Maltz",
            "Kama Pigou",
            "Christine Julius",
            "Coral Paul",
            "Andre Toby",
            "Megan Marcellus",
            "Meroy Belle",
            "Delia Crane",
            "Claire Sawyer",
            "Burnell Ben",
            "Lawrence Melville",
            "Esther Guy",
            "Jenny Bowen",
            "Nelson Oscar",
    };

    static private String[] Majors = {
            "Aerospace Engineering",
            "Electrical Engineering and Automation",
            "Computer Science and Technology",
            "Electronic Information Engineering",
            "Mechanical and Electrical Engineering",
            "Materials Science and Engineering",
            "Energy Science and Engineering",
            "Instrument science and Technology",
            "Chemical Engineering and Chemistry",
            "Environmental Science and Engineering",
    };

    static SecureRandom rand = new SecureRandom();



    public static void main(String args[]) {
        String userid = "S200101";
        String pwd = "123";
        String pwdHash =  SHA256.get(pwd);
        String id = "S200101";
        String name = "XiaoMing";
        String gender = "Male";
        String major = "Computer Science";
//        String sql = String.format("INSERT INTO users (userid, pwdHash, id, name, gender, major) VALUES ('%s', '%s', '%s', '%s', '%s', '%s');", userid, pwdHash, id, name, gender, major);
//        DBUtils.executeUpdate(sql);
//        staffGen();
//        teacherGen();
//        studentGen();
//        teacherAssignment();
        studentScore();
    }


    static void staffGen(){
        String id;
        String pwd;
        String name;
        String gender;
        int userType = 0;
        EasyFileIO EFO = new EasyFileIO("D:\\Users\\VonBrank\\Documents\\GitHub\\HIT-CS33114-final-assignment\\data\\input.txt");
        EFO.appendWriter("-----------------\n");
        EFO.appendWriter("Staff:\n\n");

        for(int i=0; i<5; i++) {
            id = String.format("A%03d", i+1);
            name = Names[i];
            gender = rand.nextInt() % 2 == 0 ? "Male" : "Female";
            pwd = getPassword(8);
            String sql = String.format("INSERT INTO users (userid, pwdHash, id, name, gender, userType) VALUES ('%s', '%s', '%s', '%s', '%s', %d);", id, SHA256.get(pwd.toString()), id, name, gender, userType);
            DBUtils.executeUpdate(sql);

            EFO.appendWriter(String.format("id: %s,\n", id));
            EFO.appendWriter(String.format("pwd: %s,\n", pwd));
            EFO.appendWriter(String.format("name: %s,\n\n", name));
//        System.out.printf("%s %s %s %s%n", id, pwd, name);
        }
        EFO.appendWriter("-----------------\n");
    }

    static String getPassword(int len) {
        String pwdAlphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789._@&+-*/";
        StringBuilder pwd = new StringBuilder();
        for(int i=1; i<=len; i++) {
            pwd.append(pwdAlphabet.charAt(rand.nextInt(70)));
        }
        return pwd.toString();
    }

    static void teacherGen(){
        String id;
        String pwd;
        String name;
        String gender;
        int userType = 1;
        String profession;
        EasyFileIO EFO = new EasyFileIO("D:\\Users\\VonBrank\\Documents\\GitHub\\HIT-CS33114-final-assignment\\data\\input.txt");
        EFO.appendWriter("-----------------\n");
        EFO.appendWriter("Teacher:\n\n");

        for(int i=5; i<20; i++) {
            id = String.format("T%03d", i+1);
            name = Names[i];
            gender = rand.nextInt() % 2 == 0 ? "Male" : "Female";
            pwd = getPassword(8);
            profession = Majors[rand.nextInt(Majors.length)];
            String sql = String.format("INSERT INTO users (userid, pwdHash, id, name, gender, userType, profession) VALUES ('%s', '%s', '%s', '%s', '%s', %d, '%s');", id, SHA256.get(pwd.toString()), id, name, gender, userType, profession);
            DBUtils.executeUpdate(sql);

            EFO.appendWriter(String.format("id: %s,\n", id));
            EFO.appendWriter(String.format("pwd: %s,\n", pwd));
            EFO.appendWriter(String.format("name: %s,\n\n", name));
//        System.out.printf("%s %s %s %s%n", id, pwd, name);
        }
        EFO.appendWriter("-----------------\n");
    }

    static void studentGen() {
        String id;
        String pwd;
        String name;
        String gender;
        int userType = 2;
        String major;
        EasyFileIO EFO = new EasyFileIO("D:\\Users\\VonBrank\\Documents\\GitHub\\HIT-CS33114-final-assignment\\data\\input.txt");
        EFO.appendWriter("-----------------\n");
        EFO.appendWriter("Student:\n\n");

        for(int i=20; i<Names.length; i++) {
            id = String.format("S%03d", i+1);
            name = Names[i];
            gender = rand.nextInt() % 2 == 0 ? "Male" : "Female";
            pwd = getPassword(8);
            major = Majors[rand.nextInt(Majors.length)];
            String sql = String.format("INSERT INTO users (userid, pwdHash, id, name, gender, userType, major) VALUES ('%s', '%s', '%s', '%s', '%s', %d, '%s');", id, SHA256.get(pwd.toString()), id, name, gender, userType, major);
            DBUtils.executeUpdate(sql);

            EFO.appendWriter(String.format("id: %s,\n", id));
            EFO.appendWriter(String.format("pwd: %s,\n", pwd));
            EFO.appendWriter(String.format("name: %s,\n\n", name));
//        System.out.printf("%s %s %s %s%n", id, pwd, name);
        }
        EFO.appendWriter("-----------------\n");
    }

    static void teacherAssignment() {
        EasyFileIO EFO = new EasyFileIO("D:\\Users\\VonBrank\\Documents\\GitHub\\HIT-CS33114-final-assignment\\data\\input.txt");
        EFO.appendWriter("-----------------\n");
        EFO.appendWriter("Course:\n\n");
        for(int i=0; i< Courses.length; i++) {
            String cid = String.format("C%03d", i+1);
            String cname = Courses[i];
            int tidn = rand.nextInt(15) + 6;
            String tid = String.format("T%03d", tidn);
            String tname = Names[tidn-1];
            String sql = String.format("INSERT INTO courses (courseid, coursename, teacherid, teachername) VALUES ('%s', '%s', '%s', '%s');", cid, cname, tid, tname);
            DBUtils.executeUpdate(sql);
            EFO.appendWriter(String.format("courseid: %s,\n", cid));
            EFO.appendWriter(String.format("coursename: %s,\n", cname));
            EFO.appendWriter(String.format("teachername: %s,\n", tid));
            EFO.appendWriter(String.format("teachername: %s,\n\n", tname));
        }
    }

    static void studentScore() {
        EasyFileIO EFO = new EasyFileIO("D:\\Users\\VonBrank\\Documents\\GitHub\\HIT-CS33114-final-assignment\\data\\input.txt");
//        EFO.appendWriter("-----------------\n");
//        EFO.appendWriter("Score:\n\n");
        for(int i=80; i < 89; i++) {
            String sid = String.format("S%03d", i+21);
            String sname = Names[i + 20];
            boolean used[] = new boolean[18];
            for (int j=0; j<18; j++) {
                int cidn = rand.nextInt(18);
                if(used[cidn]) continue;
                used[cidn] = true;
                String cid = String.format("C%03d", cidn+1);
                String cname = Courses[cidn];
                int score = rand.nextInt(41) + 60;
                String sql = String.format("INSERT INTO scores (sid, sname, cid, cname, score) VALUES ('%s', '%s', '%s', '%s', %d);", sid, sname, cid, cname, score);
                DBUtils.executeUpdate(sql);
                EFO.appendWriter(String.format("sid: %s,\n", sid));
                EFO.appendWriter(String.format("sname: %s,\n", sname));
                EFO.appendWriter(String.format("cid: %s,\n", cid));
                EFO.appendWriter(String.format("cname: %s,\n", cname));
                EFO.appendWriter(String.format("score: %d,\n\n", score));



            }

        }
    }

}
