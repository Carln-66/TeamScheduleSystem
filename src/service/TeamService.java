package service;

import domain.Architect;
import domain.Designer;
import domain.Employee;
import domain.Programmer;

/**
 * @Auther: Carl
 * @Date: 2021/02/04/16:58
 * @Description: 关于开发团队成员的管理：添加、删除等。
 */
public class TeamService {

    private static int counter = 1;     //给memberId赋值

    private final int MAX_MEMBER = 5;   //限制开发团队人数
    private final Programmer[] team = new Programmer[MAX_MEMBER];     //保存开发团队成员
    private int total;          //记录开发团队中实际的人数

    public TeamService() {
    }

    /**
     * @Description: 获取开发团队中的成员
     * @Param: []
     * @return:
     * @Author: Carl
     * @Date: 2021/2/4 17:26
     */
    public Programmer[] getTeam() {
        Programmer[] team = new Programmer[total];
        for (int i = 0; i < team.length; i++) {
            team[i] = this.team[i];
        }
        return team;
    }

    /**
     * @Description: 为开发团队添加成员
     * @Param: []
     * @return:
     * @Author: Carl
     * @Date: 2021/2/4 17:33
     */
    public void addMember(Employee e) throws TeamException {
//        成员已满，无法添加
        if (total >= MAX_MEMBER) {
            throw new TeamException("成员已满，无法添加");
        }
//        该成员不是开发人员，无法添加
        if (!(e instanceof Programmer)) {
            throw new TeamException("该成员不是开发人员，无法添加");
        }
//        该员工已在本开发团队中
        if (isExist(e)) {
            throw new TeamException("当前员工已在本开发团队中");
        }
//        该员工已是某团队成员
//        该员正在休假，无法添加
        Programmer p = (Programmer) e;
        if ("BUSY".equalsIgnoreCase(p.getStatus().getNAME())) {
            throw new TeamException("该员工已在其他开发团队中");
        } else if ("VOCATION".equalsIgnoreCase(((Programmer) e).getStatus().getNAME()))
            throw new TeamException("该员工正在休假中");
//        团队中至多只能有一名架构师
//        团队中至多只能有两名设计师
//        团队中至多只能有三名程序员

        //获取team已有成员中架构师，设计师，程序员的个数
        int numOfArch = 0, numOfDes = 0, numOfPro = 0;
        for (int i = 0; i < total; i++) {
            if (team[i] instanceof Architect) {
                numOfArch++;
            } else if (team[i] instanceof Designer) {
                numOfDes++;
            } else if (team[i] instanceof Programmer) {
                numOfPro++;
            }
        }
        if (p instanceof Architect) {
            if (numOfArch >= 1) {
                throw new TeamException("团队中至多只能有一名架构师");
            }
        } else if (p instanceof Designer) {
            if (numOfDes >= 2) {
                throw new TeamException("团队中至多只能有两名设计师");
            }
        } else if (p instanceof Programmer) {
            if (numOfPro >= 3) {
                throw new TeamException("团队中至多只能有三名程序员");
            }
        }

        //p的属性赋值
        p.setStatus(Status.BUSY);
        p.setMemberId(counter++);
        //将p(或e)添加到现有的团队中
        team[total++] = p;
    }


    /**
     * @Description: 判断该员工是否存在于当前团队中
     * @Param: [e]
     * @return:
     * @Author: Carl
     * @Date: 2021/2/4 19:50
     */
    private boolean isExist(Employee e) {
        for (int i = 0; i < total; i++) {
            if (team[i].getId() == e.getId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @Description: 删除团队中指定Id的成员
     * @Param: []
     * @return:
     * @Author: Carl
     * @Date: 2021/2/4 20:00
     */
    public void removeMember(int memberId) throws TeamException {
        int i = 0;
        for (; i < total; i++) {
            if (team[i].getMemberId() == memberId) {
                team[i].setStatus(Status.FREE);
                break;
            }
        }
        if (i == total) {
            throw new TeamException("未找到指定员工");
        }

        for (int j = i; j < total - 1; j++) {
            team[j] = team[j + 1];
        }
        team[total - 1] = null;
        total--;


    }

}
