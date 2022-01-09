package org.undp.incompetent.models;


import java.io.Serializable;

public class UserFullNameRequestEntity implements Serializable {
    private String incompetentSurname;
    private String incompetentFirstname;
    private String incompetentPatronymic;

    public String getIncompetentSurname() {
        return incompetentSurname;
    }

    public void setIncompetentSurname(String incompetentSurname) {
        this.incompetentSurname = incompetentSurname;
    }

    public String getIncompetentFirstname() {
        return incompetentFirstname;
    }

    public void setIncompetentFirstname(String incompetentFirstname) {
        this.incompetentFirstname = incompetentFirstname;
    }

    public String getIncompetentPatronymic() {
        return incompetentPatronymic;
    }

    public void setIncompetentPatronymic(String incompetentPatronymic) {
        this.incompetentPatronymic = incompetentPatronymic;
    }
}
