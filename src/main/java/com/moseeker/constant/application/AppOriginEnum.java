package com.moseeker.constant.application;/**
 * Created by zztaiwll on 19/2/20.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @className AppOriginEnum
 * @Description TODO
 * @Author zztaiwll
 * @DATE 19/2/20 下午2:06
 **/
public enum AppOriginEnum {
    PC(1,"PC"),
    QIYEHAO(2,"企业号"),
    JUHEHAO(3,"聚合号"),
    JOB51(4,"51"),
    ZHILIAN(5,"智联"),
    LIEPIN(6,"猎聘"),
    ZHIFUBAO(7,"支付宝"),
    JIANLICHOUQU(8,"简历抽取"),
    EMPLOYEEPROXY(9,"员工代投"),
    PROGRESSIMPORT(10,"程序导入"),
    EMAILAPPLICATION(11,"email申请"),
    ZUIJIADONGFANG(12,"最佳东方"),
    YILANYINGCAI(13,"一览英才"),
    JOBSDB(14,"JobsDB"),
    MINHANG(15,"民航"),
    EMPLOYEERECOMMEND(16,"员工主动推荐"),
    RECOM(17,"内推"),
    JOB58(18,"job58"),
    JIANJIECONNECTIONRECOM(19,"间接内推(联系内推)"),
    JIANJIEINVERTIONRECOM(20,"间接内推(邀请投递)"),
    JIANJIEZHUANFARECOM(21,"间接内推(转发投递)"),
    OLDEMPLOYEE(22,"老员工回聘"),
    EMPLOYEEZHUANGANG(23,"员工转岗"),
    LIETOU(24,"猎头");
    private int number;
    private String name;
    private AppOriginEnum(int number,String name){
        this.number=number;
        this.name=name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
    public static Map<Integer, String> map = new HashMap<>();
    static {
        for (AppOriginEnum appOrigin : AppOriginEnum.values()){
            map.put(appOrigin.getNumber(), appOrigin.getName());
        }
    }
}
