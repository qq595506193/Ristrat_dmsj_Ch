package com.example.tidus.ristrat.bean;

import java.io.Serializable;
import java.util.List;

public class CaseControlBean implements Serializable {


    /**
     * code : 0
     * message : 成功
     * server_params : [{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"1","BED_NUMBER":"01","BED_SEQ":1,"USED_FLAG":1,"PATIENT_ID":"1616086","PATIENT_NAME":"柴有惠","REMINDE_ID":1,"BIRTHDAY":"51","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577846","DOCTOR_NAME":"马玉奎   ","OPERATE_RESULT":20,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":"#05a558","CURRENT_RISK_LEVEL":"9","levlist":[{"CURRENT_RISK_LEVEL":"8","CURRENT_RISK_VALUE":6,"RISK_NAME":"极高危"}]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"1","BED_NUMBER":"02","BED_SEQ":2,"USED_FLAG":1,"PATIENT_ID":"1616087","PATIENT_NAME":"屠过江","REMINDE_ID":2,"BIRTHDAY":"69","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577847","DOCTOR_NAME":"袁丁","OPERATE_RESULT":20,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":"#ff4e6b","CURRENT_RISK_LEVEL":"8","levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"1","BED_NUMBER":"03","BED_SEQ":3,"USED_FLAG":1,"PATIENT_ID":"1616088","PATIENT_NAME":"张连桂","REMINDE_ID":3,"BIRTHDAY":"55","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577848","DOCTOR_NAME":"黄斌","OPERATE_RESULT":20,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":"#05a558","CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"1","BED_NUMBER":"04","BED_SEQ":4,"USED_FLAG":1,"PATIENT_ID":"1616089","PATIENT_NAME":"罗成容","REMINDE_ID":null,"BIRTHDAY":"51","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577849","DOCTOR_NAME":"杨轶","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"1","BED_NUMBER":"05","BED_SEQ":5,"USED_FLAG":1,"PATIENT_ID":"1616090","PATIENT_NAME":"钱乐群","REMINDE_ID":null,"BIRTHDAY":"75","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577850","DOCTOR_NAME":"袁丁","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"1","BED_NUMBER":"06","BED_SEQ":6,"USED_FLAG":1,"PATIENT_ID":"1616091","PATIENT_NAME":"王传斌","REMINDE_ID":null,"BIRTHDAY":"65","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577851","DOCTOR_NAME":"黄斌","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"1","BED_NUMBER":"07","BED_SEQ":7,"USED_FLAG":1,"PATIENT_ID":"1616092","PATIENT_NAME":"陈古荣","REMINDE_ID":null,"BIRTHDAY":"84","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577852","DOCTOR_NAME":"杨轶","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"1","BED_NUMBER":"08","BED_SEQ":8,"USED_FLAG":1,"PATIENT_ID":"1616093","PATIENT_NAME":"蔡成明","REMINDE_ID":null,"BIRTHDAY":"76","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577853","DOCTOR_NAME":"杨轶","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"1","BED_NUMBER":"09","BED_SEQ":9,"USED_FLAG":1,"PATIENT_ID":"1616094","PATIENT_NAME":"杜慧英","REMINDE_ID":null,"BIRTHDAY":"65","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577854","DOCTOR_NAME":"杨轶","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"1","BED_NUMBER":"10","BED_SEQ":10,"USED_FLAG":1,"PATIENT_ID":"1616095","PATIENT_NAME":"刘国安","REMINDE_ID":null,"BIRTHDAY":"64","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577855","DOCTOR_NAME":"曾国军","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"1","BED_NUMBER":"11","BED_SEQ":11,"USED_FLAG":1,"PATIENT_ID":"1616096","PATIENT_NAME":"陈福元","REMINDE_ID":null,"BIRTHDAY":"63","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577856","DOCTOR_NAME":"袁丁","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"1","BED_NUMBER":"12","BED_SEQ":12,"USED_FLAG":1,"PATIENT_ID":"1616097","PATIENT_NAME":"廖朝枢","REMINDE_ID":null,"BIRTHDAY":"81","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577857","DOCTOR_NAME":"黄斌","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"1","BED_NUMBER":"13","BED_SEQ":13,"USED_FLAG":1,"PATIENT_ID":"1616098","PATIENT_NAME":"刘耀全","REMINDE_ID":null,"BIRTHDAY":"60","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577858","DOCTOR_NAME":"黄斌","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"1","BED_NUMBER":"14","BED_SEQ":14,"USED_FLAG":1,"PATIENT_ID":"1616099","PATIENT_NAME":"罗正乾","REMINDE_ID":null,"BIRTHDAY":"78","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577859","DOCTOR_NAME":"黄斌","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"1","BED_NUMBER":"15","BED_SEQ":15,"USED_FLAG":1,"PATIENT_ID":"1616100","PATIENT_NAME":"龙珍哈姆","REMINDE_ID":null,"BIRTHDAY":"49","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577860","DOCTOR_NAME":"袁丁","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"1","BED_NUMBER":"16","BED_SEQ":16,"USED_FLAG":1,"PATIENT_ID":"1616003","PATIENT_NAME":"桑卓","REMINDE_ID":null,"BIRTHDAY":"20","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577763","DOCTOR_NAME":"杨轶","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"1","BED_NUMBER":"17","BED_SEQ":17,"USED_FLAG":1,"PATIENT_ID":"1616004","PATIENT_NAME":"廖蒙惠","REMINDE_ID":null,"BIRTHDAY":"88","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577764","DOCTOR_NAME":"杨轶","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"1","BED_NUMBER":"18","BED_SEQ":18,"USED_FLAG":1,"PATIENT_ID":"1616005","PATIENT_NAME":"王正生","REMINDE_ID":null,"BIRTHDAY":"81","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577765","DOCTOR_NAME":"曾国军","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"1","BED_NUMBER":"19","BED_SEQ":19,"USED_FLAG":1,"PATIENT_ID":"1616006","PATIENT_NAME":"刘兴春","REMINDE_ID":null,"BIRTHDAY":"35","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577766","DOCTOR_NAME":"黄斌","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"1","BED_NUMBER":"20","BED_SEQ":20,"USED_FLAG":1,"PATIENT_ID":"1616007","PATIENT_NAME":"谢树良","REMINDE_ID":null,"BIRTHDAY":"61","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577767","DOCTOR_NAME":"袁丁","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"2","BED_NUMBER":"21","BED_SEQ":21,"USED_FLAG":1,"PATIENT_ID":"1616008","PATIENT_NAME":"宋大禄","REMINDE_ID":null,"BIRTHDAY":"72","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577768","DOCTOR_NAME":"袁丁","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"2","BED_NUMBER":"22","BED_SEQ":22,"USED_FLAG":1,"PATIENT_ID":"1616009","PATIENT_NAME":"张欣","REMINDE_ID":null,"BIRTHDAY":"39","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577769","DOCTOR_NAME":"黄斌","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"2","BED_NUMBER":"23","BED_SEQ":23,"USED_FLAG":1,"PATIENT_ID":"1616010","PATIENT_NAME":"宋平建","REMINDE_ID":null,"BIRTHDAY":"64","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577770","DOCTOR_NAME":"黄斌","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"2","BED_NUMBER":"24","BED_SEQ":24,"USED_FLAG":1,"PATIENT_ID":"1616011","PATIENT_NAME":"钟开英","REMINDE_ID":null,"BIRTHDAY":"66","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577771","DOCTOR_NAME":"黄斌","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"2","BED_NUMBER":"25","BED_SEQ":25,"USED_FLAG":1,"PATIENT_ID":"1616012","PATIENT_NAME":"罗文江","REMINDE_ID":null,"BIRTHDAY":"61","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577772","DOCTOR_NAME":"黄斌","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"2","BED_NUMBER":"26","BED_SEQ":26,"USED_FLAG":1,"PATIENT_ID":"1616013","PATIENT_NAME":"熊学初","REMINDE_ID":null,"BIRTHDAY":"62","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577773","DOCTOR_NAME":"杨轶","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"2","BED_NUMBER":"27","BED_SEQ":27,"USED_FLAG":1,"PATIENT_ID":"1616001","PATIENT_NAME":"布啦","REMINDE_ID":null,"BIRTHDAY":"76","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577761","DOCTOR_NAME":"曾国军","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"2","BED_NUMBER":"28","BED_SEQ":28,"USED_FLAG":1,"PATIENT_ID":"1616002","PATIENT_NAME":"李友珍","REMINDE_ID":null,"BIRTHDAY":"92","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577762","DOCTOR_NAME":"杨轶","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"2","BED_NUMBER":"29","BED_SEQ":29,"USED_FLAG":1,"PATIENT_ID":"1616016","PATIENT_NAME":"刘玉蓉","REMINDE_ID":null,"BIRTHDAY":"54","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577776","DOCTOR_NAME":"袁丁","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]},{"MERCHANT_ID":1400,"SITE_ID":1400,"DEPARTMENT":100,"CARE_UNIT":"2","BED_NUMBER":"30","BED_SEQ":30,"USED_FLAG":1,"PATIENT_ID":"1616017","PATIENT_NAME":"涂怀洪","REMINDE_ID":null,"BIRTHDAY":"58","PATIENT_SEX":"M","IN_DEPT_TIME":"2019-04-02T11:50:03+08:00","IN_DEPT_CODE":"100","IN_DEPT_NAME":"血管外科","VISIT_SQ_NO":"ZY00001577777","DOCTOR_NAME":"杨轶","OPERATE_RESULT":null,"DIAGNOSIS_DISEASE_NAME":null,"REMINDE_LEVEL":null,"REMINDE_COLOR":null,"CURRENT_RISK_LEVEL":null,"levlist":[]}]
     * server_code :
     */

    private String code;
    private String message;
    private String server_code;
    private List<ServerParamsBean> server_params;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getServer_code() {
        return server_code;
    }

    public void setServer_code(String server_code) {
        this.server_code = server_code;
    }

    public List<ServerParamsBean> getServer_params() {
        return server_params;
    }

    public void setServer_params(List<ServerParamsBean> server_params) {
        this.server_params = server_params;
    }

    public static class ServerParamsBean  implements Serializable {
        /**
         * MERCHANT_ID : 1400
         * SITE_ID : 1400
         * DEPARTMENT : 100
         * CARE_UNIT : 1
         * BED_NUMBER : 01
         * BED_SEQ : 1
         * USED_FLAG : 1
         * PATIENT_ID : 1616086
         * PATIENT_NAME : 柴有惠
         * REMINDE_ID : 1
         * BIRTHDAY : 51
         * PATIENT_SEX : M
         * IN_DEPT_TIME : 2019-04-02T11:50:03+08:00
         * IN_DEPT_CODE : 100
         * IN_DEPT_NAME : 血管外科
         * VISIT_SQ_NO : ZY00001577846
         * DOCTOR_NAME : 马玉奎
         * OPERATE_RESULT : 20
         * DIAGNOSIS_DISEASE_NAME : null
         * REMINDE_LEVEL : 10
         * REMINDE_COLOR : #05a558
         * CURRENT_RISK_LEVEL : 9
         * levlist : [{"CURRENT_RISK_LEVEL":"8","CURRENT_RISK_VALUE":6,"RISK_NAME":"极高危"}]
         */

        private int MERCHANT_ID;
        private int SITE_ID;
        private int DEPARTMENT;
        private String CARE_UNIT;
        private String BED_NUMBER;
        private int BED_SEQ;
        private int USED_FLAG;
        private String PATIENT_ID;
        private String PATIENT_NAME;
        private int REMINDE_ID;
        private String BIRTHDAY;
        private String PATIENT_SEX;
        private String IN_DEPT_TIME;
        private String IN_DEPT_CODE;
        private String IN_DEPT_NAME;
        private String VISIT_SQ_NO;
        private String DOCTOR_NAME;
        private int OPERATE_RESULT;
        private Object DIAGNOSIS_DISEASE_NAME;
        private String REMINDE_LEVEL;
        private String REMINDE_COLOR;
        private String CURRENT_RISK_LEVEL;
        private List<LevlistBean> levlist;

        public int getMERCHANT_ID() {
            return MERCHANT_ID;
        }

        public void setMERCHANT_ID(int MERCHANT_ID) {
            this.MERCHANT_ID = MERCHANT_ID;
        }

        public int getSITE_ID() {
            return SITE_ID;
        }

        public void setSITE_ID(int SITE_ID) {
            this.SITE_ID = SITE_ID;
        }

        public int getDEPARTMENT() {
            return DEPARTMENT;
        }

        public void setDEPARTMENT(int DEPARTMENT) {
            this.DEPARTMENT = DEPARTMENT;
        }

        public String getCARE_UNIT() {
            return CARE_UNIT;
        }

        public void setCARE_UNIT(String CARE_UNIT) {
            this.CARE_UNIT = CARE_UNIT;
        }

        public String getBED_NUMBER() {
            return BED_NUMBER;
        }

        public void setBED_NUMBER(String BED_NUMBER) {
            this.BED_NUMBER = BED_NUMBER;
        }

        public int getBED_SEQ() {
            return BED_SEQ;
        }

        public void setBED_SEQ(int BED_SEQ) {
            this.BED_SEQ = BED_SEQ;
        }

        public int getUSED_FLAG() {
            return USED_FLAG;
        }

        public void setUSED_FLAG(int USED_FLAG) {
            this.USED_FLAG = USED_FLAG;
        }

        public String getPATIENT_ID() {
            return PATIENT_ID;
        }

        public void setPATIENT_ID(String PATIENT_ID) {
            this.PATIENT_ID = PATIENT_ID;
        }

        public String getPATIENT_NAME() {
            return PATIENT_NAME;
        }

        public void setPATIENT_NAME(String PATIENT_NAME) {
            this.PATIENT_NAME = PATIENT_NAME;
        }

        public int getREMINDE_ID() {
            return REMINDE_ID;
        }

        public void setREMINDE_ID(int REMINDE_ID) {
            this.REMINDE_ID = REMINDE_ID;
        }

        public String getBIRTHDAY() {
            return BIRTHDAY;
        }

        public void setBIRTHDAY(String BIRTHDAY) {
            this.BIRTHDAY = BIRTHDAY;
        }

        public String getPATIENT_SEX() {
            return PATIENT_SEX;
        }

        public void setPATIENT_SEX(String PATIENT_SEX) {
            this.PATIENT_SEX = PATIENT_SEX;
        }

        public String getIN_DEPT_TIME() {
            return IN_DEPT_TIME;
        }

        public void setIN_DEPT_TIME(String IN_DEPT_TIME) {
            this.IN_DEPT_TIME = IN_DEPT_TIME;
        }

        public String getIN_DEPT_CODE() {
            return IN_DEPT_CODE;
        }

        public void setIN_DEPT_CODE(String IN_DEPT_CODE) {
            this.IN_DEPT_CODE = IN_DEPT_CODE;
        }

        public String getIN_DEPT_NAME() {
            return IN_DEPT_NAME;
        }

        public void setIN_DEPT_NAME(String IN_DEPT_NAME) {
            this.IN_DEPT_NAME = IN_DEPT_NAME;
        }

        public String getVISIT_SQ_NO() {
            return VISIT_SQ_NO;
        }

        public void setVISIT_SQ_NO(String VISIT_SQ_NO) {
            this.VISIT_SQ_NO = VISIT_SQ_NO;
        }

        public String getDOCTOR_NAME() {
            return DOCTOR_NAME;
        }

        public void setDOCTOR_NAME(String DOCTOR_NAME) {
            this.DOCTOR_NAME = DOCTOR_NAME;
        }

        public int getOPERATE_RESULT() {
            return OPERATE_RESULT;
        }

        public void setOPERATE_RESULT(int OPERATE_RESULT) {
            this.OPERATE_RESULT = OPERATE_RESULT;
        }

        public Object getDIAGNOSIS_DISEASE_NAME() {
            return DIAGNOSIS_DISEASE_NAME;
        }

        public void setDIAGNOSIS_DISEASE_NAME(Object DIAGNOSIS_DISEASE_NAME) {
            this.DIAGNOSIS_DISEASE_NAME = DIAGNOSIS_DISEASE_NAME;
        }

        public String getREMINDE_LEVEL() {
            return REMINDE_LEVEL;
        }

        public void setREMINDE_LEVEL(String REMINDE_LEVEL) {
            this.REMINDE_LEVEL = REMINDE_LEVEL;
        }

        public String getREMINDE_COLOR() {
            return REMINDE_COLOR;
        }

        public void setREMINDE_COLOR(String REMINDE_COLOR) {
            this.REMINDE_COLOR = REMINDE_COLOR;
        }

        public String getCURRENT_RISK_LEVEL() {
            return CURRENT_RISK_LEVEL;
        }

        public void setCURRENT_RISK_LEVEL(String CURRENT_RISK_LEVEL) {
            this.CURRENT_RISK_LEVEL = CURRENT_RISK_LEVEL;
        }

        public List<LevlistBean> getLevlist() {
            return levlist;
        }

        public void setLevlist(List<LevlistBean> levlist) {
            this.levlist = levlist;
        }

        public static class LevlistBean  implements Serializable {
            /**
             * CURRENT_RISK_LEVEL : 8
             * CURRENT_RISK_VALUE : 6
             * RISK_NAME : 极高危
             */

            private String CURRENT_RISK_LEVEL;
            private int CURRENT_RISK_VALUE;
            private String RISK_NAME;

            public String getCURRENT_RISK_LEVEL() {
                return CURRENT_RISK_LEVEL;
            }

            public void setCURRENT_RISK_LEVEL(String CURRENT_RISK_LEVEL) {
                this.CURRENT_RISK_LEVEL = CURRENT_RISK_LEVEL;
            }

            public int getCURRENT_RISK_VALUE() {
                return CURRENT_RISK_VALUE;
            }

            public void setCURRENT_RISK_VALUE(int CURRENT_RISK_VALUE) {
                this.CURRENT_RISK_VALUE = CURRENT_RISK_VALUE;
            }

            public String getRISK_NAME() {
                return RISK_NAME;
            }

            public void setRISK_NAME(String RISK_NAME) {
                this.RISK_NAME = RISK_NAME;
            }
        }
    }
}
