package co.sentinel.cosmos.utils;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;
import static co.sentinel.cosmos.base.BaseChain.AKASH_MAIN;
import static co.sentinel.cosmos.base.BaseChain.ALTHEA_TEST;
import static co.sentinel.cosmos.base.BaseChain.BAND_MAIN;
import static co.sentinel.cosmos.base.BaseChain.BNB_MAIN;
import static co.sentinel.cosmos.base.BaseChain.BNB_TEST;
import static co.sentinel.cosmos.base.BaseChain.CERTIK_MAIN;
import static co.sentinel.cosmos.base.BaseChain.CERTIK_TEST;
import static co.sentinel.cosmos.base.BaseChain.COSMOS_MAIN;
import static co.sentinel.cosmos.base.BaseChain.COSMOS_TEST;
import static co.sentinel.cosmos.base.BaseChain.CRYPTO_MAIN;
import static co.sentinel.cosmos.base.BaseChain.FETCHAI_MAIN;
import static co.sentinel.cosmos.base.BaseChain.IOV_MAIN;
import static co.sentinel.cosmos.base.BaseChain.IOV_TEST;
import static co.sentinel.cosmos.base.BaseChain.IRIS_MAIN;
import static co.sentinel.cosmos.base.BaseChain.IRIS_TEST;
import static co.sentinel.cosmos.base.BaseChain.KAVA_MAIN;
import static co.sentinel.cosmos.base.BaseChain.KAVA_TEST;
import static co.sentinel.cosmos.base.BaseChain.KI_MAIN;
import static co.sentinel.cosmos.base.BaseChain.MEDI_TEST;
import static co.sentinel.cosmos.base.BaseChain.OKEX_MAIN;
import static co.sentinel.cosmos.base.BaseChain.OK_TEST;
import static co.sentinel.cosmos.base.BaseChain.OSMOSIS_MAIN;
import static co.sentinel.cosmos.base.BaseChain.PERSIS_MAIN;
import static co.sentinel.cosmos.base.BaseChain.RIZON_TEST;
import static co.sentinel.cosmos.base.BaseChain.SECRET_MAIN;
import static co.sentinel.cosmos.base.BaseChain.SENTINEL_MAIN;
import static co.sentinel.cosmos.base.BaseChain.SIF_MAIN;
import static co.sentinel.cosmos.base.BaseConstant.KEY_ALTHEA_PATH;
import static co.sentinel.cosmos.base.BaseConstant.KEY_MEDI_PATH;
import static co.sentinel.cosmos.base.BaseConstant.KEY_RIZON_PATH;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_AKASH;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_ALTHEA;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_ATOM;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_BAND;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_BNB;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_CERTIK;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_COSMOS_TEST;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_CRO;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_DVPN;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_FET;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_IOV;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_IOV_TEST;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_IRIS;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_IRIS_TEST;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_KAVA;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_KI;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_MEDI;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_OK;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_OSMOSIS;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_RIZON;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_SECRET;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_SIF;
import static co.sentinel.cosmos.base.BaseConstant.TOKEN_XPRT;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import co.sentinel.cosmos.R;
import co.sentinel.cosmos.base.BaseChain;
import co.sentinel.cosmos.base.BaseConstant;
import co.sentinel.cosmos.base.BaseData;
import co.sentinel.cosmos.dao.Price;
import co.sentinel.cosmos.model.type.Coin;

public class WDp {
    //show display text with full input amount and to divide deciaml and to show point
    public static SpannableString getDpAmount2(Context c, BigDecimal input, int divideDecimal, int displayDecimal) {
        SpannableString result;
        BigDecimal amount = input.movePointLeft(divideDecimal).setScale(displayDecimal, BigDecimal.ROUND_DOWN);
        result = new SpannableString(getDecimalFormat(displayDecimal).format(amount));
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - displayDecimal, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }

    public static SpannableString getDpString(String input, int point) {
        SpannableString result;
        result = new SpannableString(input);
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - point, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }


    public static SpannableString getPercentDp(BigDecimal input) {
        return getDpString(input.setScale(2, RoundingMode.DOWN).toPlainString() + "%", 3);
    }

    public static SpannableString getCommissionRate(String rate) {
        BigDecimal result = new BigDecimal(rate).multiply(new BigDecimal("100")).setScale(2, RoundingMode.DOWN);
        return getPercentDp(result);
    }

    public static String getPath(BaseChain chain, int position, boolean newBip) {
        if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            return BaseConstant.KEY_BNB_PATH + String.valueOf(position);

        } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            if (newBip) {
                return BaseConstant.KEY_NEW_KAVA_PATH + String.valueOf(position);
            } else {
                return BaseConstant.KEY_PATH + String.valueOf(position);
            }

        } else if (chain.equals(BAND_MAIN)) {
            return BaseConstant.KEY_BAND_PATH + String.valueOf(position);

        } else if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            return BaseConstant.KEY_IOV_PATH + String.valueOf(position);

        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            if (newBip) {
                return ("(Ethermint Type) ") + BaseConstant.KEY_NEW_OK_PATH + String.valueOf(position);
            } else {
                return ("(Tendermint Type) ") + BaseConstant.KEY_NEW_OK_PATH + String.valueOf(position);
            }

        } else if (chain.equals(SECRET_MAIN)) {
            if (newBip) {
                return BaseConstant.KEY_NEW_SECRET_PATH + String.valueOf(position);
            } else {
                return BaseConstant.KEY_PATH + String.valueOf(position);
            }

        } else if (chain.equals(PERSIS_MAIN)) {
            return BaseConstant.KEY_PERSIS_PATH + String.valueOf(position);

        } else if (chain.equals(CRYPTO_MAIN)) {
            return BaseConstant.KEY_CRYPTO_PATH + String.valueOf(position);

        } else if (chain.equals(RIZON_TEST)) {
            return KEY_RIZON_PATH + String.valueOf(position);

        } else if (chain.equals(MEDI_TEST)) {
            return KEY_MEDI_PATH + String.valueOf(position);

        } else if (chain.equals(ALTHEA_TEST)) {
            return KEY_ALTHEA_PATH + String.valueOf(position);

        } else {
            return BaseConstant.KEY_PATH + String.valueOf(position);

        }
    }


    public static DecimalFormat getDecimalFormat(int cnt) {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat decimalformat = (DecimalFormat) formatter;
        decimalformat.setRoundingMode(RoundingMode.DOWN);
        switch (cnt) {
            case 0:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0");
                break;
            case 1:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0");
                break;
            case 2:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00");
                break;
            case 3:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000");
                break;
            case 4:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0000");
                break;
            case 5:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00000");
                break;
            case 6:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000");
                break;
            case 7:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0000000");
                break;
            case 8:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00000000");
                break;
            case 9:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000000");
                break;
            case 10:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0000000000");
                break;
            case 11:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00000000000");
                break;
            case 12:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000000000");
                break;
            case 13:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0000000000000");
                break;
            case 14:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00000000000000");
                break;
            case 15:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000000000000");
                break;
            case 16:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0000000000000000");
                break;
            case 17:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00000000000000000");
                break;
            case 18:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000000000000000");
                break;

            default:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000");
                break;
        }
        return decimalformat;
    }

    public static String getDateformat(Context c, String rawValue) {
        String result = "??";
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_block_time_format));
            SimpleDateFormat myFormat = new SimpleDateFormat(c.getString(R.string.str_dp_date_format));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            result = myFormat.format(blockDateFormat.parse(rawValue));
        } catch (Exception e) {
        }
        ;

        return result;
    }

    public static String getTimeformat(Context c, String rawValue) {
        String result = "??";
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_block_time_format));
            SimpleDateFormat myFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format1));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            result = myFormat.format(blockDateFormat.parse(rawValue));
        } catch (Exception e) {
        }

        return result;
    }

    public static String mainDenom(BaseChain chain) {
        if (chain.equals(COSMOS_MAIN)) {
            return TOKEN_ATOM;
        } else if (chain.equals(IRIS_MAIN)) {
            return TOKEN_IRIS;
        } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            return TOKEN_BNB;
        } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            return TOKEN_KAVA;
        } else if (chain.equals(BAND_MAIN)) {
            return TOKEN_BAND;
        } else if (chain.equals(IOV_MAIN)) {
            return TOKEN_IOV;
        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            return TOKEN_OK;
        } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
            return TOKEN_CERTIK;
        } else if (chain.equals(SECRET_MAIN)) {
            return TOKEN_SECRET;
        } else if (chain.equals(AKASH_MAIN)) {
            return TOKEN_AKASH;
        } else if (chain.equals(PERSIS_MAIN)) {
            return TOKEN_XPRT;
        } else if (chain.equals(SENTINEL_MAIN)) {
            return TOKEN_DVPN;
        } else if (chain.equals(FETCHAI_MAIN)) {
            return TOKEN_FET;
        } else if (chain.equals(CRYPTO_MAIN)) {
            return TOKEN_CRO;
        } else if (chain.equals(SIF_MAIN)) {
            return TOKEN_SIF;
        } else if (chain.equals(KI_MAIN)) {
            return TOKEN_KI;
        } else if (chain.equals(OSMOSIS_MAIN)) {
            return TOKEN_OSMOSIS;
        } else if (chain.equals(COSMOS_TEST)) {
            return TOKEN_COSMOS_TEST;
        } else if (chain.equals(IRIS_TEST)) {
            return TOKEN_IRIS_TEST;
        } else if (chain.equals(IOV_TEST)) {
            return TOKEN_IOV_TEST;
        } else if (chain.equals(RIZON_TEST)) {
            return TOKEN_RIZON;
        } else if (chain.equals(MEDI_TEST)) {
            return TOKEN_MEDI;
        } else if (chain.equals(ALTHEA_TEST)) {
            return TOKEN_ALTHEA;
        }
        return "";
    }

    public static int mainDisplayDecimal(BaseChain chain) {
        if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            return 8;
        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            return 18;
        } else if (chain.equals(FETCHAI_MAIN) || chain.equals(SIF_MAIN)) {
            return 18;
        } else if (chain.equals(CRYPTO_MAIN)) {
            return 8;
        } else {
            return 6;
        }
    }

    public static ArrayList<Coin> getCoins(Object amount) {
        ArrayList<Coin> result = new ArrayList<>();
        try {
            Coin temp = new Gson().fromJson(new Gson().toJson(amount), Coin.class);
            result.add(temp);

        } catch (Exception e) {
        }

        try {
            result = new Gson().fromJson(new Gson().toJson(amount), new TypeToken<List<Coin>>() {
            }.getType());
        } catch (Exception e) {
        }
        return result;
    }

    // Current price

    public static SpannableString dpPerUserCurrencyValue(BaseData baseData, String denom) {
        BigDecimal totalValue = perUserCurrencyValue(baseData, denom);
        final String formatted = baseData.getCurrencySymbol() + " " + getDecimalFormat(3).format(totalValue);
        return dpCurrencyValue(formatted, 3);
    }

    public static BigDecimal perUserCurrencyValue(BaseData baseData, String denom) {
        if (baseData.getCurrency() == 0) {
            return perUsdValue(baseData, denom);
        } else if (baseData.getPrice("usdt") != null) {
            final Price usdtInfo = baseData.getPrice("usdt");
            final BigDecimal usdtPrice = usdtInfo.currencyPrice(baseData.getCurrencyString().toLowerCase());
            return perUsdValue(baseData, denom).multiply(usdtPrice).setScale(3, RoundingMode.DOWN);
        }
        return BigDecimal.ZERO.setScale(3, RoundingMode.DOWN);
    }

    public static SpannableString dpCurrencyValue(String input, int dpPoint) {
        return getDpString(input, dpPoint);
    }

    public static BigDecimal perUsdValue(BaseData baseData, String denom) {
        if (baseData.getPrice(denom) != null) {
            return baseData.getPrice(denom).currencyPrice("usd").setScale(3, RoundingMode.DOWN);
        }
        return BigDecimal.ZERO.setScale(3, RoundingMode.DOWN);
    }

    public static SpannableString dpValueChange(BaseData baseData, String denom) {
        return getDpString(valueChange(baseData, denom).toPlainString() + "% (24h)", 9);
    }

    public static BigDecimal valueChange(BaseData baseData, String denom) {
        if (baseData.getPrice(denom) != null) {
            return baseData.getPrice(denom).priceChange();
        }
        return BigDecimal.ZERO.setScale(2, RoundingMode.FLOOR);
    }
}
