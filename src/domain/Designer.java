package domain;

/**
 * @Auther: Carl
 * @Date: 2021/02/04/12:38
 * @Description:
 */
public class Designer extends Programmer {

    private double bonus;       //奖金

    public Designer() {
        super();
    }

    public Designer(int id, String name, int age, double salary, Equipment equipment, double bonus) {
        super(id, name, age, salary, equipment);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return super.getDetails() + "\t设计师\t" + getStatus() + "\t" + bonus + "\t\t\t\t\t" + getEquipment().getDescription();
    }

    @Override
    public String getDetailsForTeam() {
        return getMemberId() + "/" + getId() + " \t" + getName() + " \t" + getAge() + "\t" + getSalary() +
                "\t设计师\t" + bonus;
    }
}
