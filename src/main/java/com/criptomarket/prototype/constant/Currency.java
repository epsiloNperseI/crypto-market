package com.criptomarket.prototype.constant;

import java.lang.reflect.Field;

public final class Currency {

    //currency
    public static final String US_DOLLAR = "USD";
    public static final String EURO = "EUR";
    public static final String AUSTRALIAN_DOLLAR = "AUD";
    public static final String SWISS_FRANC = "CHF";
    public static final String SWEDISH_KRONA = "SEK";
    public static final String POUND_STERLING = "GBP";
    public static final String DANISH_KRONE = "DKK";
    public static final String RUSSIAN_RUBLE = "RUB";
    public static final String CANADIAN_DOLLAR = "CAD";

    // crypto
    public final static String BITCOIN = "BTC";
    public final static String LITECOIN = "LTC";
    public final static String ETHEREUM = "ETH";
    public final static String TETHER = "USDT";
    public final static String BINANCE_COIN = "BNB";
    public final static String USDC = "USDC";
    public final static String PAXOS_GOLD = "PAXG";
    public final static String MAKER = "MKR";
    public final static String MONERO = "XMR";
    public final static String AAVE = "AAVE";
    public final static String KSM = "KSM";
    public final static String ZCASH = "ZEC";
    public final static String EGLD = "EGLD";
    public final static String COMP = "COMP";

    public static String getForUpdate() throws IllegalAccessException {
        StringBuilder filter = new StringBuilder();
        Field[] fields = Currency.class.getFields();
        for (int i = 0; i <= fields.length - 1; i++) {
            filter.append(fields[i].get(Currency.class));
            if (fields.length - 1 != i) {
                filter.append(",");
            }
        }

        return filter.toString();
    }
}
