package Wielodziedziczenie;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws Exception {
        StaffMember staffMember = new StaffMember(LocalDateTime.of(2024, 8,12,5,12), 10000.0);
        Employee employee = new Employee("ala@wp.pl", 10000.0);
        employee.setRegistrationDate(LocalDateTime.of(2024, 8,12,5,12));
        System.out.println(staffMember.getIncome());
        System.out.println(staffMember.getDefaultIncome());
        System.out.println("==============");
        System.out.println(employee.getIncome());
        System.out.println(employee.getDefaultIncome());
        System.out.println();
        ObjectPlus.showExtent(StaffMember.class);
        staffMember.destroy();
        ObjectPlus.showExtent(StaffMember.class);
    }
}
