package service;

import domain.PC;
import domain.*;

import static service.Data.*;
/**
 * @Auther: Carl
 * @Date: 2021/02/04/12:45
 * @Description: 负责将Data中的数据封装到Employee[]数组中，同时提供相关操作Employee[]的方法。
 */
public class NameListService {
    private Employee[] employees;

    public NameListService() {
        employees = new Employee[EMPLOYEES.length];

        for (int i = 0; i < employees.length; i++) {

            //获取员工类型
            int type = Integer.parseInt(EMPLOYEES[i][0]);

            //获取Employee的4个基本信息
            int id = Integer.parseInt(EMPLOYEES[i][1]);
            String name = EMPLOYEES[i][2];
            int age = Integer.parseInt(EMPLOYEES[i][3]);
            double salary = Double.parseDouble(EMPLOYEES[i][4]);

            /**
             * @Description: 将员工按职位分类，分别获取他们所存储的元素
             * @Param: []
             * @return:
             * @Author: Carl
             * @Date: 2021/2/4 14:31
             */
            Equipment equipment;
            double bonus;
            int stock;
            switch (type) {
                case EMPLOYEE:
                    employees[i] = new Employee(id, name, age, salary);
                    break;
                case PROGRAMMER:
                    equipment = creatEquipment(i);
                    employees[i] = new domain.Programmer(id, name, age, salary, equipment);
                    break;
                case DESIGNER:
                    equipment = creatEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    employees[i] = new Designer(id, name, age, salary, equipment, bonus);
                    break;
                case ARCHITECT:
                    equipment = creatEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    stock = Integer.parseInt(EMPLOYEES[i][6]);
                    employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
                    break;
            }
        }
    }


    /**
     * @Description: 获取指定index上的员工的设备信息
     * @Param: [index]
     * @return:
     * @Author: Carl
     * @Date: 2021/2/4 13:19
     */
    private Equipment creatEquipment(int index) {
        int type = Integer.parseInt(EQUIPMENTS[index][0]);
        String modelOrName = EQUIPMENTS[index][1];
        switch (type) {
            case PC:
                return new PC(modelOrName, EQUIPMENTS[index][2]);
            case NOTEBOOK:
                return new Notebook(modelOrName, Double.parseDouble(EQUIPMENTS[index][2]));
            case PRINTER:
                return new Printer(modelOrName, EQUIPMENTS[index][2]);
        }
        return null;
    }

    /**
     * @Description: 获取当前所有员工
     * @Param: []
     * @return:
     * @Author: Carl
     * @Date: 2021/2/4 14:34
     */
    public Employee[] getAllEmployees() {
        return employees;
    }

    /**
     * @Description: 获取指定ID的员工
     * @Param: [id]
     * @return:
     * @Author: Carl
     * @Date: 2021/2/4 14:35
     */
    public Employee getEmployee(int id) throws TeamException {
        for (int i = 0; i < employees.length; i++){
            if (employees[i].getId() == id){        //如果Id初始用Integer定义，则一定要使用equals判断！
                return employees[i];
            }
        }
        throw new TeamException("找不到指定的员工");
    }
}
