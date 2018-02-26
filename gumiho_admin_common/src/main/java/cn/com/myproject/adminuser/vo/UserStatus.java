package cn.com.myproject.adminuser.vo;

/**
 *
 */
public enum UserStatus {

    S_0((short)0,"未知"),
    S_1((short)1,"正常"),
    S_2((short)2,"停用"),
    S_3((short)3,"不可删除");

    private short id;
    private String name;

    UserStatus(short id, String name) {
        this.id = id;
        this.name = name;
    }

    public short getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }


    public static String searchId(short id) {
        switch (id) {
            case 1 :
                return S_1.getName();
            case 0 :
                return S_0.getName();
            default:
                return "未知";
        }
    }
}
