package com.moseeker.constant.company;

import sun.rmi.runtime.Log;

/**
 * 操作记录应用场景分类
 * @author yehu
 */
public interface LogBusinessType {

    /**
     * 职位相关
     */
    enum Position implements LogBusinessType{
        /**
         * 创建职位
         */
        CREATE(4,LogOperationType.CREATE.getNum()),
        /**
         * 置顶职位
         */
        STICKY(5,LogOperationType.UPDATE.getNum()),

        /**
         * 取消置顶职位
         */
        CANCEL_STICKY(6,LogOperationType.UPDATE.getNum()),

        /**
         * 撤下职位
         */
        RECEDED(7, LogOperationType.UPDATE.getNum()),

        /**
         * 恢复职位
         */
        RESUME(8,LogOperationType.UPDATE.getNum()),

        /**
         * 编辑职位
         */
        EDIT(9,LogOperationType.UPDATE.getNum()),

        /**
         * 同步职位
         */
        SYNCHRONOUS(10,LogOperationType.UPDATE.getNum()),

        /**
         * 删除职位
         */
        DELETE(11,LogOperationType.DELETE.getNum()),

        /**
         * 分配职位
         */
        ARRANGE(12,LogOperationType.UPDATE.getNum()),

        /**
         * 批量置顶
         */
        BATCH_STICKY(13,LogOperationType.UPDATE.getNum()),

        /**
         * 批量分配
         */
        BATCH_ARRANGE(14,LogOperationType.UPDATE.getNum()),

        /**
         * 批量撤下
         */
        BATCH_RECEDED(15,LogOperationType.UPDATE.getNum()),

        /**
         * 批量删除
         */
        BATCH_DELETE(16,LogOperationType.DELETE.getNum()),

        /**
         * 批量导入
         */
        BATCH_IMPORT(17,LogOperationType.UPDATE.getNum())
        ;
        private Integer typeId;

        private Integer operationLogType;

        public Integer getTypeId() {
            return typeId;
        }

        public void setTypeId(Integer typeId) {
            this.typeId = typeId;
        }

        public Integer getOperationLogType() {
            return operationLogType;
        }

        public void setOperationLogType(Integer operationLogType) {
            this.operationLogType = operationLogType;
        }

        Position(Integer typeId, Integer operationLogType) {
            this.typeId = typeId;
            this.operationLogType = operationLogType;
        }
    }

    /**
     *  招聘管理
     */
    enum Recruit implements LogBusinessType{
        /**
         * 批量推荐到用人部门
         */
        BATCH_RECOMMEND(18,LogOperationType.UPDATE.getNum()),

        /**
         * 导出excel
         */
        EXCEL_EXPORT(19,LogOperationType.UPDATE.getNum()),

        /**
         * 批量收藏
         */
        BATCH_COLLECT(20,LogOperationType.DEFAULT.getNum()),

        /**
         * 批量下载简历
         */
        BATCH_DOWNLOAD_PROFILE(21,LogOperationType.UPDATE.getNum()),

        /**
         * 批量淘汰
         */
        BATCH_OBSOLETE(22,LogOperationType.UPDATE.getNum()),

        /**
         * 批量变更阶段
         */
        BATCH_CHANGE_PROCESS(23,LogOperationType.UPDATE.getNum()),

        /**
         * 人才库批量取消收藏
         */
        TALENT_POOL_BATCH_CANCEL_COLLECT(24,LogOperationType.UPDATE.getNum()),

        /**
         * 人才库批量设为公开
         */
        TALENT_POOL_BATCH_SET_PUBLIC(25,LogOperationType.UPDATE.getNum()),

        /**
         * 人才库批量设为私有
         */
        TALENT_POOL_BATCH_SET_PRIVATE(26,LogOperationType.UPDATE.getNum()),

        /**
         * 人才库批量下载简历
         */
        TALENT_POOL_BATCH_DOWNLOAD_PROFILE(27,LogOperationType.UPDATE.getNum()),

        /**
         * 人才库批量导出excel
         */
        TALENT_POOL_BATCH_EXCEL_EXPORT(28,LogOperationType.UPDATE.getNum()),

        /**
         * 人才库批量邀请投递
         */
        TALENT_POOL_BATCH_INVITE(29,LogOperationType.UPDATE.getNum())
        ;
        private Integer typeId;

        private Integer operationLogType;

        public Integer getTypeId() {
            return typeId;
        }

        public void setTypeId(Integer typeId) {
            this.typeId = typeId;
        }

        public Integer getOperationLogType() {
            return operationLogType;
        }

        public void setOperationLogType(Integer operationLogType) {
            this.operationLogType = operationLogType;
        }

        Recruit(Integer typeId, Integer operationLogType) {
            this.typeId = typeId;
            this.operationLogType = operationLogType;
        }
    }

    /**
     * 设置中心相关
     */
    enum Setting implements LogBusinessType{
        /**
         * 第三方账号绑定
         */
        THIRD_PARTY_ACCOUNT_BIND(30,LogOperationType.CREATE.getNum()),

        /**
         * 第三方账号解绑
         */
        THIRD_PARTY_ACCOUNT_UNBIND(31,LogOperationType.DELETE.getNum()),

        /**
         * 第三方账号分配
         */
        THIRD_PARTY_ACCOUNT_ALLOCATION(32,LogOperationType.UPDATE.getNum()),

        /**
         * 子账号添加
         */
        SUB_ACCOUNT_ADD(33,LogOperationType.CREATE.getNum()),

        /**
         * 子账号换绑
         */
        SUB_ACCOUNT_CHANGE(34,LogOperationType.UPDATE.getNum()),

        /**
         * 子账号删除
         */
        SUB_ACCOUNT_DELETE(35,LogOperationType.DELETE.getNum()),

        /**
         * 猎头添加
         */
        HEAD_HUNTER_ADD(36,LogOperationType.CREATE.getNum()),

        /**
         * 猎头禁用
         */
        HEAD_HUNTER_FORBIDDEN(37,LogOperationType.UPDATE.getNum()),

        /**
         * 面试官添加
         */
        INTERVIEWER_ADD(38,LogOperationType.CREATE.getNum()),

        /**
         * 面试官删除
         */
        INTERVIEWER_DELETE(39,LogOperationType.DELETE.getNum()),

        /**
         * 新增阶段
         */
        PHASE_ADD(40,LogOperationType.CREATE.getNum()),

        /**
         * 编辑阶段
         */
        PHASE_EDIT(41,LogOperationType.UPDATE.getNum()),

        /**
         * 新增流程
         */
        PROCESS_ADD(42,LogOperationType.CREATE.getNum()),

        /**
         * 编辑流程
         */
        PROCESS_EDIT(43,LogOperationType.UPDATE.getNum()),

        /**
         * 批量导出候选人
         */
        BATCH_CANDIDATE_EXPORT(44,LogOperationType.UPDATE.getNum()),

        /**
         * 批量导入候选人
         */
        BATCH_CANDIDATE_IMPORT(45,LogOperationType.UPDATE.getNum()),

        /**
         * 新增通知模版
         */
        NOTICE_TEMPLATE_ADD(46,LogOperationType.CREATE.getNum()),

        /**
         * 编辑通知模版
         */
        NOTICE_TEMPLATE_EDIT(47,LogOperationType.UPDATE.getNum())
        ;
        private Integer typeId;

        private Integer operationLogType;

        public Integer getTypeId() {
            return typeId;
        }

        public void setTypeId(Integer typeId) {
            this.typeId = typeId;
        }

        public Integer getOperationLogType() {
            return operationLogType;
        }

        public void setOperationLogType(Integer operationLogType) {
            this.operationLogType = operationLogType;
        }

        Setting(Integer typeId, Integer operationLogType) {
            this.typeId = typeId;
            this.operationLogType = operationLogType;
        }
    }

}
