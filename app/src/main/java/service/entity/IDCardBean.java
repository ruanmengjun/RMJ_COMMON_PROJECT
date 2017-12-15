package service.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 2017/11/21.
 */

public class IDCardBean {

    /**
     * race : 汉
     * name : 张三
     * time_used : 1144
     * gender : 男
     * id_card_number : 110xxxxxx123456789
     * request_id : 1505962912,9066edb8-719e-4ee3-8eaf-312b863fcce3
     * birthday : {"year":"1989","day":"3","month":"4"}
     * legality : {"Edited":0,"Photocopy":0,"ID Photo":1,"Screen":0,"Temporary ID Photo":0}
     * address : 北京市海淀区xxxxx
     * head_rect : {"rt":{"y":0.19160387,"x":0.89415807},"lt":{"y":0.19160387,"x":0.59965634},"lb":{"y":0.712056,"x":0.59965634},"rb":{"y":0.71905273,"x":0.89415807}}
     * side : front
     */

    private String race;
    private String name;
    private int time_used;
    private String gender;
    private String id_card_number;
    private String request_id;
    private BirthdayBean birthday;
    private LegalityBean legality;
    private String address;
    private HeadRectBean head_rect;
    private String side;
    private String valid_date;
    private String issued_by;

    public String getValid_date() {
        return valid_date;
    }

    public void setValid_date(String valid_date) {
        this.valid_date = valid_date;
    }

    public String getIssued_by() {
        return issued_by;
    }

    public void setIssued_by(String issued_by) {
        this.issued_by = issued_by;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId_card_number() {
        return id_card_number;
    }

    public void setId_card_number(String id_card_number) {
        this.id_card_number = id_card_number;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public BirthdayBean getBirthday() {
        return birthday;
    }

    public void setBirthday(BirthdayBean birthday) {
        this.birthday = birthday;
    }

    public LegalityBean getLegality() {
        return legality;
    }

    public void setLegality(LegalityBean legality) {
        this.legality = legality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HeadRectBean getHead_rect() {
        return head_rect;
    }

    public void setHead_rect(HeadRectBean head_rect) {
        this.head_rect = head_rect;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public static class BirthdayBean {
        /**
         * year : 1989
         * day : 3
         * month : 4
         */

        private String year;
        private String day;
        private String month;

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }
    }

    public static class LegalityBean {
        /**
         * Edited : 0
         * Photocopy : 0
         * ID Photo : 1
         * Screen : 0
         * Temporary ID Photo : 0
         */

        private int Edited;
        private int Photocopy;
        @SerializedName("ID Photo")
        private int _$IDPhoto49; // FIXME check this code
        private int Screen;
        @SerializedName("Temporary ID Photo")
        private int _$TemporaryIDPhoto227; // FIXME check this code

        public int getEdited() {
            return Edited;
        }

        public void setEdited(int Edited) {
            this.Edited = Edited;
        }

        public int getPhotocopy() {
            return Photocopy;
        }

        public void setPhotocopy(int Photocopy) {
            this.Photocopy = Photocopy;
        }

        public int get_$IDPhoto49() {
            return _$IDPhoto49;
        }

        public void set_$IDPhoto49(int _$IDPhoto49) {
            this._$IDPhoto49 = _$IDPhoto49;
        }

        public int getScreen() {
            return Screen;
        }

        public void setScreen(int Screen) {
            this.Screen = Screen;
        }

        public int get_$TemporaryIDPhoto227() {
            return _$TemporaryIDPhoto227;
        }

        public void set_$TemporaryIDPhoto227(int _$TemporaryIDPhoto227) {
            this._$TemporaryIDPhoto227 = _$TemporaryIDPhoto227;
        }
    }

    public static class HeadRectBean {
        /**
         * rt : {"y":0.19160387,"x":0.89415807}
         * lt : {"y":0.19160387,"x":0.59965634}
         * lb : {"y":0.712056,"x":0.59965634}
         * rb : {"y":0.71905273,"x":0.89415807}
         */

        private RtBean rt;
        private LtBean lt;
        private LbBean lb;
        private RbBean rb;

        public RtBean getRt() {
            return rt;
        }

        public void setRt(RtBean rt) {
            this.rt = rt;
        }

        public LtBean getLt() {
            return lt;
        }

        public void setLt(LtBean lt) {
            this.lt = lt;
        }

        public LbBean getLb() {
            return lb;
        }

        public void setLb(LbBean lb) {
            this.lb = lb;
        }

        public RbBean getRb() {
            return rb;
        }

        public void setRb(RbBean rb) {
            this.rb = rb;
        }

        public static class RtBean {
            /**
             * y : 0.19160387
             * x : 0.89415807
             */

            private double y;
            private double x;

            public double getY() {
                return y;
            }

            public void setY(double y) {
                this.y = y;
            }

            public double getX() {
                return x;
            }

            public void setX(double x) {
                this.x = x;
            }
        }

        public static class LtBean {
            /**
             * y : 0.19160387
             * x : 0.59965634
             */

            private double y;
            private double x;

            public double getY() {
                return y;
            }

            public void setY(double y) {
                this.y = y;
            }

            public double getX() {
                return x;
            }

            public void setX(double x) {
                this.x = x;
            }
        }

        public static class LbBean {
            /**
             * y : 0.712056
             * x : 0.59965634
             */

            private double y;
            private double x;

            public double getY() {
                return y;
            }

            public void setY(double y) {
                this.y = y;
            }

            public double getX() {
                return x;
            }

            public void setX(double x) {
                this.x = x;
            }
        }

        public static class RbBean {
            /**
             * y : 0.71905273
             * x : 0.89415807
             */

            private double y;
            private double x;

            public double getY() {
                return y;
            }

            public void setY(double y) {
                this.y = y;
            }

            public double getX() {
                return x;
            }

            public void setX(double x) {
                this.x = x;
            }
        }
    }
}
