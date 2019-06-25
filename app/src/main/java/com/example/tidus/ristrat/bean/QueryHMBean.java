package com.example.tidus.ristrat.bean;

import java.util.List;

public class QueryHMBean {

    /**
     * code : 0
     * message : 成功
     * server_params : {"WPG":"0","LIST":[{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY010000382104","REMINDE_ID":4,"PATIENT_ID":"A1678256","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190618175427","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY010000382105","REMINDE_ID":5,"PATIENT_ID":"A1678257","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190618175427","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY010000382106","REMINDE_ID":6,"PATIENT_ID":"A1678258","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190618175427","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY010000382111","REMINDE_ID":7,"PATIENT_ID":"A1678259","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190618175427","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY010000382112","REMINDE_ID":8,"PATIENT_ID":"A1678260","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190618175427","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY110000382112","REMINDE_ID":13,"PATIENT_ID":"A1678259","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190620095440","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY010000382141","REMINDE_ID":10,"PATIENT_ID":"A1678262","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190618175427","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY010000382142","REMINDE_ID":11,"PATIENT_ID":"A1678263","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190618175427","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY010000382143","REMINDE_ID":12,"PATIENT_ID":"A1678264","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190618175427","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY010000382113","REMINDE_ID":9,"PATIENT_ID":"A1678261","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190618175427","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY00001577848","REMINDE_ID":3,"PATIENT_ID":"1616088","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190614164800","OPERATE_RESULT":20,"USER_ID":202,"OPERATE_TIME":"20190614164800","REMINDE_TYPE":"20190619153031","REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY00001577847","REMINDE_ID":2,"PATIENT_ID":"1616087","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190614164800","OPERATE_RESULT":20,"USER_ID":202,"OPERATE_TIME":"20190614164800","REMINDE_TYPE":"20190619153031","REMINDE_LEVEL":"10","REMINDE_COLOR":null}]}
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

    public static class ServerParamsBean {
        /**
         * WPG : 0
         * LIST : [{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY010000382104","REMINDE_ID":4,"PATIENT_ID":"A1678256","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190618175427","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY010000382105","REMINDE_ID":5,"PATIENT_ID":"A1678257","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190618175427","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY010000382106","REMINDE_ID":6,"PATIENT_ID":"A1678258","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190618175427","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY010000382111","REMINDE_ID":7,"PATIENT_ID":"A1678259","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190618175427","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY010000382112","REMINDE_ID":8,"PATIENT_ID":"A1678260","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190618175427","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY110000382112","REMINDE_ID":13,"PATIENT_ID":"A1678259","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190620095440","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY010000382141","REMINDE_ID":10,"PATIENT_ID":"A1678262","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190618175427","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY010000382142","REMINDE_ID":11,"PATIENT_ID":"A1678263","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190618175427","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY010000382143","REMINDE_ID":12,"PATIENT_ID":"A1678264","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190618175427","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY010000382113","REMINDE_ID":9,"PATIENT_ID":"A1678261","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190618175427","OPERATE_RESULT":20,"USER_ID":null,"OPERATE_TIME":null,"REMINDE_TYPE":null,"REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY00001577848","REMINDE_ID":3,"PATIENT_ID":"1616088","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190614164800","OPERATE_RESULT":20,"USER_ID":202,"OPERATE_TIME":"20190614164800","REMINDE_TYPE":"20190619153031","REMINDE_LEVEL":"10","REMINDE_COLOR":null},{"MERCHANT_ID":1400,"SITE_ID":1400,"VISIT_SQ_NO":"ZY00001577847","REMINDE_ID":2,"PATIENT_ID":"1616087","PATIENT_TYPE":"Patient","REMINDE_TIME":"20190614164800","OPERATE_RESULT":20,"USER_ID":202,"OPERATE_TIME":"20190614164800","REMINDE_TYPE":"20190619153031","REMINDE_LEVEL":"10","REMINDE_COLOR":null}]
         */

        private String WPG;
        private List<LISTBean> LIST;

        public String getWPG() {
            return WPG;
        }

        public void setWPG(String WPG) {
            this.WPG = WPG;
        }

        public List<LISTBean> getLIST() {
            return LIST;
        }

        public void setLIST(List<LISTBean> LIST) {
            this.LIST = LIST;
        }

        public static class LISTBean {
            /**
             * MERCHANT_ID : 1400
             * SITE_ID : 1400
             * VISIT_SQ_NO : ZY010000382104
             * REMINDE_ID : 4
             * PATIENT_ID : A1678256
             * PATIENT_TYPE : Patient
             * REMINDE_TIME : 20190618175427
             * OPERATE_RESULT : 20
             * USER_ID : null
             * OPERATE_TIME : null
             * REMINDE_TYPE : null
             * REMINDE_LEVEL : 10
             * REMINDE_COLOR : null
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
            private Object REMINDE_TYPE;
            private String REMINDE_LEVEL;
            private Object REMINDE_COLOR;

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

            public Object getREMINDE_TYPE() {
                return REMINDE_TYPE;
            }

            public void setREMINDE_TYPE(Object REMINDE_TYPE) {
                this.REMINDE_TYPE = REMINDE_TYPE;
            }

            public String getREMINDE_LEVEL() {
                return REMINDE_LEVEL;
            }

            public void setREMINDE_LEVEL(String REMINDE_LEVEL) {
                this.REMINDE_LEVEL = REMINDE_LEVEL;
            }

            public Object getREMINDE_COLOR() {
                return REMINDE_COLOR;
            }

            public void setREMINDE_COLOR(Object REMINDE_COLOR) {
                this.REMINDE_COLOR = REMINDE_COLOR;
            }
        }
    }
}
