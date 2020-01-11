package com.nanchen.coordinatorlayoutdemo;

import java.util.Locale;

/**
 * Created by cy on 2017/9/5.
 */

public class BaseCreditConstant {

    public static final String SSL_STORE_PWD = "dontchange";

    /**
     * 国家ID
     */
    public static class CountryId {

        /**
         * 印尼
         */
        public static final long IN = 1L;

        public static final long MY = 2L;

        public static final long PH = 3L;

        public static final long VN = 4L;
    }

    /**
     * 国家码
     */
    public static class CountryCode {

        /**
         * 印尼
         */
        public static final String IN = "ID";

        public static final String MY = "MY";

        public static final String PH = "PH";

        public static final String VN = "VN";
    }

    /**
     * 语言码
     */
    public static class LanguageCode {

        /**
         * 英文
         */
        public static final String EN = "EN";

        /**
         * 印尼
         */
        public static final String IN = "IN";

        /**
         * 菲律宾只有英语
         */
        public static final String PH = "EN";

        /**
         * 马来
         */
        public static final String MS = "MS";

        /**
         * 越南
         */
        public static final String VN = "VI";
    }

    /**
     * 货币码
     */
    public static class CurrencyCode {

        public static final String IN = "Rp";

        public static final String MY = "RM";

        public static final String PH = "₱";

        public static final String VN = "₫";
    }

    /**
     * 电话国家区码
     */
    public static class PhoneCode {

        // public static final String IN = BaseApplication.getInstance().getString(R.string.phone_code_id);
        //
        // public static final String MY = BaseApplication.getInstance().getString(R.string.phone_code_my);
        //
        // public static final String PH = BaseApplication.getInstance().getString(R.string.phone_code_ph);
        //
        // public static final String VN = BaseApplication.getInstance().getString(R.string.phone_code_vn);
    }

    public static class PhoneMaxLength {

        public static final int IN = 13;

        public static final int MY = 11;

        public static final int PH = 11;

        public static final int VN = 11;
    }

    public static class LocaleType {

        public static final Locale EN = new Locale(LanguageCode.EN, CountryCode.IN);

        public static final Locale IN = new Locale(LanguageCode.IN, CountryCode.IN);

        public static final Locale MY = new Locale(LanguageCode.MS, CountryCode.MY);

        public static final Locale PH = new Locale(LanguageCode.EN, CountryCode.PH);

        public static final Locale VN = new Locale(LanguageCode.VN, CountryCode.VN);
    }

    /**
     * 语言id
     */
    public static class LanguageId {

        /**
         * 英语
         */
        public static final long EN = 102L;

        /**
         * 印尼语
         */
        public static final long IN = 123L;

        /**
         * 越南语
         */
        public static final long VN = 142L;

        /**
         * 马来语
         */
        public static final long MS = 141L;
    }

    /**
     * 货币id
     */
    public static class CurrencyId {

        /**
         * 印尼
         */
        public static final long IN = 4L;

        public static final long MY = 3L;

        public static final long PH = 5L;

        public static final long VN = 6L;
    }

    public static class RegisterAndLogin {

        public static final int CURRENT_TYPE_REGISTER = 1;

        public static final int CURRENT_TYPE_LOGIN = 2;
    }

    public static class SharedPref {

        public static final String LOGIN_BEAN = "login_bean";

        public static final String USER_BEAN = "user_bean";

        public static final String COUNTRY_CODE = "base_country_code";

        public static final String LANGUAGE_CODE = "base_language_code";

        public static final String USER_IS_LOGIN = "user_is_login";

        public static final String MODEL_LOGIN_USERNAME = "model_login_username";

    }

    public static class BundleExtraArg {

        public static final String ARG_CURRENT_TYPE = "current_type";

        public static final String ARG_BUNDLE = "bundle";

    }

    public static class ThirdPartyAccountSource {

        /**
         * Facebook
         */
        public static final int FACEBOOK = 1;

        /**
         * Google
         */
        public static final int GOOGLE = 2;
    }
}
