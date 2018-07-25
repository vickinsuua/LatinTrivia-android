package android.app.my.myapp.models;

    public class User {

        private String _id;
        private String avatar;
        private String contry_code;
        private String phone;
        private String referral_code;
        private double extra_life;
        private double balance;

        /**
         * No args constructor for use in serialization
         *
         */
        public User() {
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
        public User(String id, String avatar, String contryCode, String phone, String referralCode, double extraLife, double balance) {
            super();
            this._id = _id;
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

        public void setId(String _id) {
            this._id = _id;
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

        public double getExtraLife() {
            return extra_life;
        }

        public void setExtraLife(double extra_life) {
            this.extra_life = extra_life;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        @Override
        public String toString() {

            return "User{" +
                    "_id='" + _id + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", contry_code='" + contry_code + '\'' +
                    ", phone='" + phone + '\'' +
                    ", referral_code='" + referral_code + '\'' +
                    ", extra_life=" + extra_life +
                    ", balance=" + balance +
                    '}';
        }
    }
