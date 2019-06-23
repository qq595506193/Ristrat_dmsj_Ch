package com.example.tidus.ristrat.bean;

import java.util.List;

public class MessageBean {

    /**
     * code : 0
     * message : 成功
     * server_params : {"count":49,"list":[{"USER_ID":202,"MESSAGE_ID":57,"MESSAGE_TITLE":"系统消息","MESSAGE_CONTENT":"患者 王小红（A1678259） 已达到VTE风险评估标准，已延迟30分钟未处理，请及时安排患者进行评估。","SEND_TIME":"20190620095545","MESSAGE_TYPE":20,"MESSAGE_STATUS":1,"OBJECT_PARA":null,"READ_TIME":null,"DELETE_TIME":null,"RN":1},{"USER_ID":202,"MESSAGE_ID":56,"MESSAGE_TITLE":"系统消息","MESSAGE_CONTENT":"患者 王小红（A1678259） 已达到VTE风险评估标准，请及时安排患者进行评估。","SEND_TIME":"20190620095440","MESSAGE_TYPE":20,"MESSAGE_STATUS":1,"OBJECT_PARA":null,"READ_TIME":null,"DELETE_TIME":null,"RN":2},{"USER_ID":202,"MESSAGE_ID":55,"MESSAGE_TITLE":"系统消息","MESSAGE_CONTENT":"患者 柴有惠（1616086） 已达到VTE风险评估标准，已延迟30分钟未处理，请及时安排患者进行评估。","SEND_TIME":"20190620091907","MESSAGE_TYPE":20,"MESSAGE_STATUS":0,"OBJECT_PARA":null,"READ_TIME":null,"DELETE_TIME":null,"RN":3},{"USER_ID":202,"MESSAGE_ID":54,"MESSAGE_TITLE":"系统消息","MESSAGE_CONTENT":"患者 屠过江（1616087） 已达到VTE风险评估标准，已延迟30分钟未处理，请及时安排患者进行评估。","SEND_TIME":"20190619152733","MESSAGE_TYPE":20,"MESSAGE_STATUS":0,"OBJECT_PARA":null,"READ_TIME":null,"DELETE_TIME":null,"RN":4},{"USER_ID":202,"MESSAGE_ID":53,"MESSAGE_TITLE":"系统消息","MESSAGE_CONTENT":"患者 柴有惠（1616086） 已达到VTE风险评估标准，已延迟30分钟未处理，请及时安排患者进行评估。","SEND_TIME":"20190619152732","MESSAGE_TYPE":20,"MESSAGE_STATUS":0,"OBJECT_PARA":null,"READ_TIME":null,"DELETE_TIME":null,"RN":5}]}
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
         * count : 49
         * list : [{"USER_ID":202,"MESSAGE_ID":57,"MESSAGE_TITLE":"系统消息","MESSAGE_CONTENT":"患者 王小红（A1678259） 已达到VTE风险评估标准，已延迟30分钟未处理，请及时安排患者进行评估。","SEND_TIME":"20190620095545","MESSAGE_TYPE":20,"MESSAGE_STATUS":1,"OBJECT_PARA":null,"READ_TIME":null,"DELETE_TIME":null,"RN":1},{"USER_ID":202,"MESSAGE_ID":56,"MESSAGE_TITLE":"系统消息","MESSAGE_CONTENT":"患者 王小红（A1678259） 已达到VTE风险评估标准，请及时安排患者进行评估。","SEND_TIME":"20190620095440","MESSAGE_TYPE":20,"MESSAGE_STATUS":1,"OBJECT_PARA":null,"READ_TIME":null,"DELETE_TIME":null,"RN":2},{"USER_ID":202,"MESSAGE_ID":55,"MESSAGE_TITLE":"系统消息","MESSAGE_CONTENT":"患者 柴有惠（1616086） 已达到VTE风险评估标准，已延迟30分钟未处理，请及时安排患者进行评估。","SEND_TIME":"20190620091907","MESSAGE_TYPE":20,"MESSAGE_STATUS":0,"OBJECT_PARA":null,"READ_TIME":null,"DELETE_TIME":null,"RN":3},{"USER_ID":202,"MESSAGE_ID":54,"MESSAGE_TITLE":"系统消息","MESSAGE_CONTENT":"患者 屠过江（1616087） 已达到VTE风险评估标准，已延迟30分钟未处理，请及时安排患者进行评估。","SEND_TIME":"20190619152733","MESSAGE_TYPE":20,"MESSAGE_STATUS":0,"OBJECT_PARA":null,"READ_TIME":null,"DELETE_TIME":null,"RN":4},{"USER_ID":202,"MESSAGE_ID":53,"MESSAGE_TITLE":"系统消息","MESSAGE_CONTENT":"患者 柴有惠（1616086） 已达到VTE风险评估标准，已延迟30分钟未处理，请及时安排患者进行评估。","SEND_TIME":"20190619152732","MESSAGE_TYPE":20,"MESSAGE_STATUS":0,"OBJECT_PARA":null,"READ_TIME":null,"DELETE_TIME":null,"RN":5}]
         */

        private int count;
        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * USER_ID : 202
             * MESSAGE_ID : 57
             * MESSAGE_TITLE : 系统消息
             * MESSAGE_CONTENT : 患者 王小红（A1678259） 已达到VTE风险评估标准，已延迟30分钟未处理，请及时安排患者进行评估。
             * SEND_TIME : 20190620095545
             * MESSAGE_TYPE : 20
             * MESSAGE_STATUS : 1
             * OBJECT_PARA : null
             * READ_TIME : null
             * DELETE_TIME : null
             * RN : 1
             */

            private int USER_ID;
            private int MESSAGE_ID;
            private String MESSAGE_TITLE;
            private String MESSAGE_CONTENT;
            private String SEND_TIME;
            private int MESSAGE_TYPE;
            private int MESSAGE_STATUS;
            private Object OBJECT_PARA;
            private Object READ_TIME;
            private Object DELETE_TIME;
            private int RN;

            public int getUSER_ID() {
                return USER_ID;
            }

            public void setUSER_ID(int USER_ID) {
                this.USER_ID = USER_ID;
            }

            public int getMESSAGE_ID() {
                return MESSAGE_ID;
            }

            public void setMESSAGE_ID(int MESSAGE_ID) {
                this.MESSAGE_ID = MESSAGE_ID;
            }

            public String getMESSAGE_TITLE() {
                return MESSAGE_TITLE;
            }

            public void setMESSAGE_TITLE(String MESSAGE_TITLE) {
                this.MESSAGE_TITLE = MESSAGE_TITLE;
            }

            public String getMESSAGE_CONTENT() {
                return MESSAGE_CONTENT;
            }

            public void setMESSAGE_CONTENT(String MESSAGE_CONTENT) {
                this.MESSAGE_CONTENT = MESSAGE_CONTENT;
            }

            public String getSEND_TIME() {
                return SEND_TIME;
            }

            public void setSEND_TIME(String SEND_TIME) {
                this.SEND_TIME = SEND_TIME;
            }

            public int getMESSAGE_TYPE() {
                return MESSAGE_TYPE;
            }

            public void setMESSAGE_TYPE(int MESSAGE_TYPE) {
                this.MESSAGE_TYPE = MESSAGE_TYPE;
            }

            public int getMESSAGE_STATUS() {
                return MESSAGE_STATUS;
            }

            public void setMESSAGE_STATUS(int MESSAGE_STATUS) {
                this.MESSAGE_STATUS = MESSAGE_STATUS;
            }

            public Object getOBJECT_PARA() {
                if (OBJECT_PARA != null) {
                    return OBJECT_PARA;
                }
                return "";
            }

            public void setOBJECT_PARA(Object OBJECT_PARA) {
                this.OBJECT_PARA = OBJECT_PARA;
            }

            public Object getREAD_TIME() {
                if (READ_TIME != null) {
                    return READ_TIME;
                }
                return "";
            }

            public void setREAD_TIME(Object READ_TIME) {
                this.READ_TIME = READ_TIME;
            }

            public Object getDELETE_TIME() {
                if (DELETE_TIME != null) {
                    return DELETE_TIME;
                }
                return "";
            }

            public void setDELETE_TIME(Object DELETE_TIME) {
                this.DELETE_TIME = DELETE_TIME;
            }

            public int getRN() {
                return RN;
            }

            public void setRN(int RN) {
                this.RN = RN;
            }
        }
    }
}
