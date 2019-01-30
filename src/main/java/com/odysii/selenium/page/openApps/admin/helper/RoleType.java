package com.odysii.selenium.page.openApps.admin.helper;

public enum RoleType {
     ROLE_1(1),ROLE_2(2),ROLE_3(3),ROLE_4(4),ROLE_5(5),ROLE_6(6),ROLE_7(7),ROLE_8(8);

    private int index;
    public int getIndex() {
        return index;
    }
    RoleType(int index) {
        this.index = index;
    }
}
