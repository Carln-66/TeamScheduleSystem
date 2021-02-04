package domain;

/**
 * @Auther: Carl
 * @Date: 2021/02/04/12:40
 * @Description:
 */
public class Architect extends Designer {

    private int stock;      //股份

    public Architect() {

    }

    public Architect(int id, String name, int age, double salary, Equipment equipment, double bonus, int stock) {
        super(id, name, age, salary, equipment, bonus);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return super.getDetails() + "\t架构师\t" + getStatus() + "\t" + getBonus() + "\t\t" + stock + "\t\t" + getEquipment().getDescription();
    }

    @Override
    public String getDetailsForTeam() {
        return getMemberId() + "/" + getId() + " \t" + getName() + " \t" + getAge() + "\t" + getSalary() +
                "\t架构师\t" + getBonus() + "\t\t" + stock;
    }
}
