package com.example.tidus.ristrat.bean;

import java.io.Serializable;
import java.util.List;

public class QueryHMBean implements Serializable {


    /**
     * code : 0
     * message : 成功
     * server_params : {"tixingLIST":[],"LIST":[{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY000040000003","REMINDE_ID":49,"PATIENT_ID":"1616088","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190626115114","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":"VTE评估监控","REMINDE_LEVEL":"10","REMINDE_COLOR":"#05a558"},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY000040000004","REMINDE_ID":50,"PATIENT_ID":"1616088","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190627092206","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":"VTE评估监控","REMINDE_LEVEL":"20","REMINDE_COLOR":"#ff4e6b"}]}
     * server_code :
     */

    private String code;
    private String message;
    private ServerParamsBean server_params;
    private String server_code;

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

    public ServerParamsBean getServer_params() {
        return server_params;
    }

    public void setServer_params(ServerParamsBean server_params) {
        this.server_params = server_params;
    }

    public String getServer_code() {
        return server_code;
    }

    public void setServer_code(String server_code) {
        this.server_code = server_code;
    }

    public static class ServerParamsBean  implements Serializable{
        private List<?> tixingLIST;
        private List<LISTBean> LIST;

        public List<?> getTixingLIST() {
            return tixingLIST;
        }

        public void setTixingLIST(List<?> tixingLIST) {
            this.tixingLIST = tixingLIST;
        }

        public List<LISTBean> getLIST() {
            return LIST;
        }

        public void setLIST(List<LISTBean> LIST) {
            this.LIST = LIST;
        }

        public static class LISTBean  implements Serializable{
            /**
             * MERCHANT_ID : 1400
             * SITE_ID : 1400
             * VISIT_SQ_NO : ZY000040000003
             * REMINDE_ID : 49
             * PATIENT_ID : 1616088
             * PATIENT_TYPE : Patient
             * REMINDE_TIME : 20190626115114
             * OPERATE_RESULT : 20
             * USER_ID : null
             * OPERATE_TIME : null
             * REMINDE_TYPE : VTE评估监控
             * REMINDE_LEVEL : 10
             * REMINDE_COLOR : #05a558
             */

            private int MERCHANT_ID;
            private int SITE_ID;
            private String VISIT_SQ_NO;
            private int REMINDE_ID;
            private String PATIENT_ID;
            private String PATIENT_TYPE;
            private String REMINDE_TIME;
            private int OPERATE_RESULT;
            private Object USER_ID;
            private Object OPERATE_TIME;
            private String REMINDE_TYPE;
            private String REMINDE_LEVEL;
            private String REMINDE_COLOR;

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

            public String getVISIT_SQ_NO() {
                return VISIT_SQ_NO;
            }

            public void setVISIT_SQ_NO(String VISIT_SQ_NO) {
                this.VISIT_SQ_NO = VISIT_SQ_NO;
            }

            public int getREMINDE_ID() {
                return REMINDE_ID;
            }

            public void setREMINDE_ID(int REMINDE_ID) {
                this.REMINDE_ID = REMINDE_ID;
            }

            public String getPATIENT_ID() {
                return PATIENT_ID;
            }

            public void setPATIENT_ID(String PATIENT_ID) {
                this.PATIENT_ID = PATIENT_ID;
            }

            public String getPATIENT_TYPE() {
                return PATIENT_TYPE;
            }

            public void setPATIENT_TYPE(String PATIENT_TYPE) {
                this.PATIENT_TYPE = PATIENT_TYPE;
            }

            public String getREMINDE_TIME() {
                return REMINDE_TIME;
            }

            public void setREMINDE_TIME(String REMINDE_TIME) {
                this.REMINDE_TIME = REMINDE_TIME;
            }

            public int getOPERATE_RESULT() {
                return OPERATE_RESULT;
            }

            public void setOPERATE_RESULT(int OPERATE_RESULT) {
                this.OPERATE_RESULT = OPERATE_RESULT;
            }

            public Object getUSER_ID() {
                return USER_ID;
            }

            public void setUSER_ID(Object USER_ID) {
                this.USER_ID = USER_ID;
            }

            public Object getOPERATE_TIME() {
                return OPERATE_TIME;
            }

            public void setOPERATE_TIME(Object OPERATE_TIME) {
                this.OPERATE_TIME = OPERATE_TIME;
            }

            public String getREMINDE_TYPE() {
                return REMINDE_TYPE;
            }

            public void setREMINDE_TYPE(String REMINDE_TYPE) {
                this.REMINDE_TYPE = REMINDE_TYPE;
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
        }
    }
}
