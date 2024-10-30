package com.firstapp.myapplicationtest3;

public class Teacher {
    public String[] TeacherId = {"Mishara","Vidul","Thanusha","Vishmi"};
    public String[] TeacherPassword ={"1234","2543","5896","5698"};
    public String[] TeacherName = {"Mishara Wikramasingha","Vidul Pramitha","Thanusha Witharana","Vishmi radeesha"};

    public int search(String ID, String Password) {
        int i;
        for (i = 0; i < TeacherId.length; i++) {
            if (ID.equals(TeacherId[i]) && Password.equals(TeacherPassword[i])) {
                return i;
            }
        }
        return i = -1;
    }

    public boolean AddAccount(String ID,String Password){
        int i = TeacherId.length;
        TeacherId[i] = ID;
        TeacherId[i] = Password;

        return true;
    }

}
