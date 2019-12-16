package com.moseeker.constant.company;

import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 操作记录应用场景分类
 * @author yehu
 */
public interface LogBusinessType {

    /**
     * 获取操作类型id
     * @return
     */
    int getTypeId();

    /**
     * 获取应用场景id
     * @return
     */
    int getParentId();

    /**
     * 获取操作类型(增删改或未知)
     * @return
     */
    int getOperationtype();

    Map<String,LogBusinessType> container=new HashMap<>();

    static LogBusinessType initFromEnumStr(String enumStr){
        if(CollectionUtils.isEmpty(container)){
            for (Position position : Position.values()) {
                container.put(position.toString(),position);
            }
            for (Recruit recruit : Recruit.values()) {
                container.put(recruit.toString(),recruit);
            }
            for (Setting setting : Setting.values()) {
                container.put(setting.toString(),setting);
            }
        }
        return container.get(enumStr);
    }

    /**
     * 职位相关
     */
    enum Position implements LogBusinessType{
        /**
         * 创建职位
         */
        CREATE{
            @Override
            public int getTypeId() {
                return 4;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.CREATE.getNum();
            }
        },
        /**
         * 置顶职位
         */
        STICKY{
            @Override
            public int getTypeId() {
                return 5;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 取消置顶职位
         */
        CANCEL_STICKY{
            @Override
            public int getTypeId() {
                return 6;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 撤下职位
         */
        RECEDED{
            @Override
            public int getTypeId() {
                return 7;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 恢复职位
         */
        RESUME{
            @Override
            public int getTypeId() {
                return 8;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 编辑职位
         */
        EDIT{
            @Override
            public int getTypeId() {
                return 9;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 同步职位
         */
        SYNCHRONOUS{
            @Override
            public int getTypeId() {
                return 10;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 删除职位
         */
        DELETE{
            @Override
            public int getTypeId() {
                return 11;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.DELETE.getNum();
            }
        },

        /**
         * 分配职位
         */
        ARRANGE{
            @Override
            public int getTypeId() {
                return 12;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 批量置顶
         */
        BATCH_STICKY{
            @Override
            public int getTypeId() {
                return 13;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 批量分配
         */
        BATCH_ARRANGE{
            @Override
            public int getTypeId() {
                return 14;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 批量撤下
         */
        BATCH_RECEDED{
            @Override
            public int getTypeId() {
                return 15;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 批量删除
         */
        BATCH_DELETE{
            @Override
            public int getTypeId() {
                return 16;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.DELETE.getNum();
            }
        },

        /**
         * 批量导入
         */
        BATCH_IMPORT{
            @Override
            public int getTypeId() {
                return 17;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        };

        @Override
        public int getParentId() {
            return 1;
        }
    }

    /**
     *  招聘管理
     */
    enum Recruit implements LogBusinessType{
        /**
         * 批量推荐到用人部门
         */
        BATCH_RECOMMEND{
            @Override
            public int getTypeId() {
                return 18;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 导出excel
         */
        EXCEL_EXPORT{
            @Override
            public int getTypeId() {
                return 19;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 批量收藏
         */
        BATCH_COLLECT{
            @Override
            public int getTypeId() {
                return 20;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.DEFAULT.getNum();
            }
        },

        /**
         * 批量下载简历
         */
        BATCH_DOWNLOAD_PROFILE{
            @Override
            public int getTypeId() {
                return 21;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 批量淘汰
         */
        BATCH_OBSOLETE{
            @Override
            public int getTypeId() {
                return 22;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 批量变更阶段
         */
        BATCH_CHANGE_PROCESS{
            @Override
            public int getTypeId() {
                return 23;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * Offer已发送
         */
        OFFER_SEND{
            @Override
            public int getTypeId() {
                return 48;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 候选人接受Offer
         */
        OFFER_ACCEPT{
            @Override
            public int getTypeId() {
                return 49;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 候选人拒绝Offer
         */
        OFFER_REJECT{
            @Override
            public int getTypeId() {
                return 50;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 候选人入职
         */
        ON_BOARDING{
            @Override
            public int getTypeId() {
                return 51;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 放弃入职
         */
        ABANDON_ON_BOARDING{
            @Override
            public int getTypeId() {
                return 52;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 设置入职日期
         */
        SET_ENTRY_DATE{
            @Override
            public int getTypeId() {
                return 53;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 新增面试安排
         */
        INTERVIEW_ADD{
            @Override
            public int getTypeId() {
                return 54;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.CREATE.getNum();
            }
        },

        /**
         * 编辑面试安排
         */
        INTERVIEW_EDIT{
            @Override
            public int getTypeId() {
                return 55;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 推荐到其他职位
         */
        RECOMMENT_TO_OTHER_POSITION{
            @Override
            public int getTypeId() {
                return 56;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 转发候选人信息
         */
        TRANSMIT_CANDIDATE_INFORMATION{
            @Override
            public int getTypeId() {
                return 57;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 放入人才库
         */
        ADD_TO_TALENT_POOL{
            @Override
            public int getTypeId() {
                return 58;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 人才库批量取消收藏
         */
        TALENT_POOL_BATCH_CANCEL_COLLECT{
            @Override
            public int getTypeId() {
                return 24;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 人才库批量设为公开
         */
        TALENT_POOL_BATCH_SET_PUBLIC{
            @Override
            public int getTypeId() {
                return 25;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 人才库批量设为私有
         */
        TALENT_POOL_BATCH_SET_PRIVATE{
            @Override
            public int getTypeId() {
                return 26;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 人才库批量下载简历
         */
        TALENT_POOL_BATCH_DOWNLOAD_PROFILE{
            @Override
            public int getTypeId() {
                return 27;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 人才库批量导出excel
         */
        TALENT_POOL_BATCH_EXCEL_EXPORT{
            @Override
            public int getTypeId() {
                return 28;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 人才库批量邀请投递
         */
        TALENT_POOL_BATCH_INVITE{
            @Override
            public int getTypeId() {
                return 29;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * HR上传简历到职位
         */
        UPLOAD_TO_POSITION{
            @Override
            public int getTypeId() {
                return 59;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.CREATE.getNum();
            }
        },

        /**
         * HR首次查看了候选人简历
         */
        PROFILE_VIEWED_BY_HR{
            @Override
            public int getTypeId() {
                return 60;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 用人经理首次查看了候选人简历
         */
        PROFILE_VIEWED_BY_MANAGER{
            @Override
            public int getTypeId() {
                return 61;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 用人经理对推荐简历进行反馈
         */
        PROFILE_FEEDBACK_BY_MANAGER{
            @Override
            public int getTypeId() {
                return 62;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        };
        @Override
        public int getParentId() {
            return 2;
        }


    }

    /**
     * 设置中心相关
     */
    enum Setting implements LogBusinessType{
        /**
         * 第三方账号绑定
         */
        THIRD_PARTY_ACCOUNT_BIND{
            @Override
            public int getTypeId() {
                return 30;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.CREATE.getNum();
            }
        },

        /**
         * 第三方账号解绑
         */
        THIRD_PARTY_ACCOUNT_UNBIND{
            @Override
            public int getTypeId() {
                return 31;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.DELETE.getNum();
            }
        },

        /**
         * 第三方账号分配
         */
        THIRD_PARTY_ACCOUNT_ALLOCATION{
            @Override
            public int getTypeId() {
                return 32;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 子账号添加
         */
        SUB_ACCOUNT_ADD{
            @Override
            public int getTypeId() {
                return 33;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.CREATE.getNum();
            }
        },

        /**
         * 子账号换绑
         */
        SUB_ACCOUNT_CHANGE{
            @Override
            public int getTypeId() {
                return 34;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 子账号删除
         */
        SUB_ACCOUNT_DELETE{
            @Override
            public int getTypeId() {
                return 35;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.DELETE.getNum();
            }
        },

        /**
         * 猎头添加
         */
        HEAD_HUNTER_ADD{
            @Override
            public int getTypeId() {
                return 36;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.CREATE.getNum();
            }
        },

        /**
         * 猎头禁用
         */
        HEAD_HUNTER_FORBIDDEN{
            @Override
            public int getTypeId() {
                return 37;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 面试官添加
         */
        INTERVIEWER_ADD{
            @Override
            public int getTypeId() {
                return 38;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.CREATE.getNum();
            }
        },

        /**
         * 面试官删除
         */
        INTERVIEWER_DELETE{
            @Override
            public int getTypeId() {
                return 39;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.DELETE.getNum();
            }
        },

        /**
         * 新增阶段
         */
        PHASE_ADD{
            @Override
            public int getTypeId() {
                return 40;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.CREATE.getNum();
            }
        },

        /**
         * 编辑阶段
         */
        PHASE_EDIT{
            @Override
            public int getTypeId() {
                return 41;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 新增流程
         */
        PROCESS_ADD{
            @Override
            public int getTypeId() {
                return 42;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.CREATE.getNum();
            }
        },

        /**
         * 编辑流程
         */
        PROCESS_EDIT{
            @Override
            public int getTypeId() {
                return 43;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 批量导出候选人
         */
        BATCH_CANDIDATE_EXPORT{
            @Override
            public int getTypeId() {
                return 44;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 批量导入候选人
         */
        BATCH_CANDIDATE_IMPORT{
            @Override
            public int getTypeId() {
                return 45;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        },

        /**
         * 新增通知模版
         */
        NOTICE_TEMPLATE_ADD{
            @Override
            public int getTypeId() {
                return 46;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.CREATE.getNum();
            }
        },

        /**
         * 编辑通知模版
         */
        NOTICE_TEMPLATE_EDIT{
            @Override
            public int getTypeId() {
                return 47;
            }

            @Override
            public int getOperationtype() {
                return LogOperationType.UPDATE.getNum();
            }
        };
        @Override
        public int getParentId() {
            return 3;
        }
    }

}
