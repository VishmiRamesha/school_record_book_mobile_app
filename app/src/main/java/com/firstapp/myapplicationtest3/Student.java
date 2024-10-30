package com.firstapp.myapplicationtest3;



public class Student {

    //student profiles
    public String[] StudentId = {"Mishara","Vidul","Thanusha","Vishmi"};
    public String[] StudentPassword ={"1234","2543","5896","5698"};
    public String[] StudentName = {"Mishara Wikramasingha","Vidul Pramitha","Thanusha Witharana","Vishmi radeesha"};

    public String[] StudentContact = {"071 0448098","070 256894","045 0869534","077 256985"};

    public String[] StudentGrade ={"12-S","12-A","12-C","12-T"};

    /////////////Student Time table
    public String[] Mon ={"Sinhala","History","Music","Bucket 2","Mathamatics","Mathamatics","library","Science"};
    public String[] Tue ={"mathamatics","Tamil","History","ICT","PT","Science","Sinhala","Geogrophy"};
    public String[] Wen ={"Sinhala","History","Music","Bucket 2","Mathamatics","Mathamatics","library","Science"};
    public String[] Thu ={"mathamatics","Tamil","History","ICT","PT","Science","Sinhala","Geogrophy"};
    public String[] Fri ={"Sinhala","History","Music","Bucket 2","Mathamatics","Mathamatics","library","Science"};

    /////////////// Student Marks


    public int search(String ID, String Password) {
        int i;
        for (i = 0; i < StudentId.length; i++) {
            if (ID.equals(StudentId[i]) && Password.equals(StudentPassword[i])) {
                return i;
            }
        }
        return i = -1;
    }

    public boolean AddAccount(String ID,String Password){
        int i = StudentId.length;
        StudentId[i] = ID;
        StudentPassword[i] = Password;
        return true;
    }
    public String ViewName(int i){
        return StudentName[i];
    }

    public String ViewID(int i){
        return StudentId[i];
    }

    public String ViewGrade(int i){
        return StudentGrade[i];
    }

    public String ViewContact(int i){
        return StudentContact[i];
    }

    public String EditProfile(int i,String SName,String SGrade,String SContact){
        StudentName[i]=SName;
        StudentGrade[i]=SGrade;
        StudentContact[i]=SContact;
        return SName;
    }




}
