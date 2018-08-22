package app.my.myapp.models;

import com.google.gson.annotations.SerializedName;

public class User {

        @SerializedName("_id")
        private String _id;
        @SerializedName("nickname")
        private String nickname;
        @SerializedName("avatar")
        private String avatar;
        @SerializedName("contry_code")
        private String contry_code;
        @SerializedName("phone")
        private String phone;
        @SerializedName("referral_code")
        private String referral_code;
        @SerializedName("extra_life")
        private int extra_life;
        @SerializedName("balance")
        private int balance;



        public User( String avatar, String nickname, String referral_code) {
            super();
            this.avatar = avatar;
            this.nickname = nickname;
            this.referral_code = referral_code;

        }

        /**
         *
         * @param id
         * @param balance
         * @param phone
         * @param referralCode
         * @param contryCode
         * @param avatar
         * @param extraLife
         */
        public User(String id, String nickname, String avatar, String contryCode, String phone, String referralCode, int extraLife, int balance) {
            super();
            this._id = _id;
            this.nickname = nickname;
            this.avatar = avatar;
            this.contry_code = contryCode;
            this.phone = phone;
            this.referral_code = referralCode;
            this.extra_life = extraLife;
            this.balance = balance;
        }

        public String getId() {
            return _id;
        }


        public String getNickname() {
        return nickname;
    }

        public void setNickname(String nickname) {
        this.nickname = nickname;
    }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getContryCode() {
            return contry_code;
        }

        public void setContryCode(String contry_code) {
            this.contry_code = contry_code;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getReferralCode() {
            return referral_code;
        }

        public void setReferralCode(String referral_code) {
            this.referral_code = referral_code;
        }

        public int getExtraLife() {
            return extra_life;
        }

        public void setExtraLife(int extra_life) {
            this.extra_life = extra_life;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        private User user;

        public User getUser() {
        return user;
    }

        public void setUsers(User user) {
        this.user = user;
    }

        @Override
        public String toString() {

            return "User{" +
                    "_id='" + _id + '\'' +
                    "nickname='" + nickname + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", contry_code='" + contry_code + '\'' +
                    ", phone='" + phone + '\'' +
                    ", referral_code='" + referral_code + '\'' +
                    ", extra_life=" + extra_life +
                    ", balance=" + balance +
                    '}';
        }
    }
