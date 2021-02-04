package view;

import domain.Employee;
import domain.Programmer;
import service.NameListService;
import service.TeamException;
import service.TeamService;

/**
 * @Auther: Carl
 * @Date: 2021/02/04/20:26
 * @Description:
 */
public class TeamView {

    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();

    public void enterMainMenu() {
        char menu = 0;
        boolean loopFlag = true;
        while (loopFlag) {

            if (menu != '1') {
                listAllEmployees();
            }

            System.out.print("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：");
            menu = view.TSUtility.readMenuSelection();
            switch (menu) {
                case '1':
                    getTeam();
                    break;

                case '2':
                    addMember();
                    break;

                case '3':
                    deleteMember();
                    break;

                case '4':
                    System.out.println("确认退出？(Y / N)");
                    char check = view.TSUtility.readConfirmSelection();
                    if (check == 'Y') {
                        loopFlag = false;
                    }
                    break;
            }
        }
    }

    /**
     * @Description: 显示所有员工信息
     * @Param: []
     * @return:
     * @Author: Carl
     * @Date: 2021/2/4 20:35
     */
    public void listAllEmployees() {
        System.out.println("\n-------------------------------开发团队调度软件--------------------------------\n");
        Employee[] allEmployees = listSvc.getAllEmployees();
        System.out.println("ID\t姓名\t\t年龄\t工资\t\t职位\t\t状态\t\t奖金\t\t\t股票\t\t\t领用设备");
        for (int i = 0; i < allEmployees.length; i++) {
            System.out.println(allEmployees[i]);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }


    /**
     * @Description: 查看团队列表
     * @Param: []
     * @return:
     * @Author: Carl
     * @Date: 2021/2/4 20:44
     */
    public void getTeam() {
        System.out.println("\n--------------------团队成员列表---------------------\n");
        teamSvc.getTeam();
        Programmer[] team = teamSvc.getTeam();
        if (teamSvc.getTeam() == null || team.length == 0) {
            System.out.println("当前团队无成员！");
        } else {
            System.out.println("TID/ID\t姓名\t\t年龄\t工资\t\t职位\t\t奖金\t\t\t股票\t\t\t");
            for (int i = 0; i < team.length; i++) {
                System.out.println(team[i].getDetailsForTeam());
            }
        }
        System.out.println("-----------------------------------------------------");
    }

    /**
     * @Description: 添加成员
     * @Param: []
     * @return:
     * @Author: Carl
     * @Date: 2021/2/4 20:44
     */
    public void addMember() {
        System.out.println("---------------------添加成员---------------------");
        System.out.println("请输入需要添加的成员编号");
        int id = TSUtility.readInt();
        try {
            Employee employee = listSvc.getEmployee(id);
            teamSvc.addMember(employee);
            System.out.println("添加成功");
        } catch (TeamException e) {
            System.out.println("添加失败，原因：" + e.getMessage());
        }
        TSUtility.readReturn();
    }

    /**
     * @Description: 删除成员
     * @Param: []
     * @return:
     * @Author: Carl
     * @Date: 2021/2/4 20:44
     */
    public void deleteMember() {
        System.out.println("---------------------删除成员---------------------");
        System.out.println("请输入需要删除的成员团队编号");
        int id = TSUtility.readInt();
        System.out.println("确认删除？(Y / N)");
        char check = TSUtility.readConfirmSelection();
        if (check == 'N') {
            return;
        }
        try {
            teamSvc.removeMember(id);
            System.out.println("删除成功");
        } catch (TeamException e) {
            System.out.println("删除失败，原因：" + e.getMessage());
        }
        TSUtility.readReturn();
    }

    public static void main(String[] args) {
        TeamView teamView = new TeamView();
        teamView.enterMainMenu();
    }
}
