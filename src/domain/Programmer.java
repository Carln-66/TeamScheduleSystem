package domain;

import service.Status;

/**
 * @Auther: Carl
 * @Date: 2021/02/04/12:22
 * @Description:
 */
public class Programmer extends Employee {

    private int memberId;   //开发团队的ID
    private Status status = Status.FREE;
    private Equipment equipment;

    public Programmer() {
        super();
    }

    public Programmer(int id, String name, int age, double salary, Equipment equipment) {
        super(id, name, age, salary);
        this.equipment = equipment;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return super.toString() + "\t程序员\t" + status + "\t" + "\t\t\t\t\t\t" + equipment.getDescription();
    }

    public String getDetailsForTeam() {
        return memberId + "/" + getId() + " \t" + getName() + " \t" + getAge() + "\t" + getSalary() + "\t程序员";
    }
}
