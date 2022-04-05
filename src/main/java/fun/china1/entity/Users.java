package fun.china1.entity;

public class Users {
    private String username;
    private Long gender;
    private String num;
    private Integer admin;

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    private int finish;
    public Users(String username, Long gender, String num,int finish,Integer admin) {
        this.username = username;
        this.gender = gender;
        this.num = num;
        this.finish=finish;
        this.admin=admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
