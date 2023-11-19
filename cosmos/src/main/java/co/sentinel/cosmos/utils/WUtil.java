package co.sentinel.cosmos.utils;

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
import static co.sentinel.cosmos.base.BaseConstant.*;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.sentinel.cosmos.R;
import co.sentinel.cosmos.base.BaseChain;
import co.sentinel.cosmos.base.BaseConstant;
import cosmos.base.v1beta1.CoinOuterClass;

public class WUtil {


    public static String prettyPrinter(Object object) {
        String result = "";
        try {
            result = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e) {
            result = "Print json error";
        }
        return result;
    }

    public static Gson getPresentor() {
        return new GsonBuilder().disableHtmlEscaping().create();
    }

    public static String ByteArrayToHexString(byte[] bytes) {
        final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] integerToBytes(BigInteger s, int length) {
        byte[] bytes = s.toByteArray();

        if (length < bytes.length) {
            byte[] tmp = new byte[length];
            System.arraycopy(bytes, bytes.length - tmp.length, tmp, 0, tmp.length);
            return tmp;
        } else if (length > bytes.length) {
            byte[] tmp = new byte[length];
            System.arraycopy(bytes, 0, tmp, tmp.length - bytes.length, bytes.length);
            return tmp;
        }
        return bytes;
    }

    public static BigDecimal decCoinAmount(List<CoinOuterClass.DecCoin> coins, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        for (CoinOuterClass.DecCoin coin : coins) {
            if (coin.getDenom().equals(denom)) {
                return new BigDecimal(coin.getAmount()).movePointLeft(18).setScale(0, RoundingMode.DOWN);
            }
        }
        return result;
    }
    public static String COSMOS = "asset:atom";

    public static BigDecimal getRealBlockTime(BaseChain chain) {
        if (chain.equals(COSMOS_MAIN) || chain.equals(COSMOS_TEST)) {
            return BLOCK_TIME_COSMOS;

        } else if (chain.equals(IRIS_MAIN) || chain.equals(IRIS_TEST)) {
            return BLOCK_TIME_IRIS;

        } else if (chain.equals(IOV_MAIN)) {
            return BLOCK_TIME_IOV;

        } else if (chain.equals(KAVA_MAIN)) {
            return BLOCK_TIME_KAVA;

        } else if (chain.equals(BAND_MAIN)) {
            return BLOCK_TIME_BAND;

        } else if (chain.equals(CERTIK_MAIN)) {
            return BLOCK_TIME_CERTIK;

        } else if (chain.equals(SECRET_MAIN)) {
            return BLOCK_TIME_SECRET;

        } else if (chain.equals(AKASH_MAIN)) {
            return BLOCK_TIME_AKASH;

        } else if (chain.equals(PERSIS_MAIN)) {
            return BLOCK_TIME_PERSISTENCE;

        } else if (chain.equals(FETCHAI_MAIN)) {
            return BLOCK_TIME_FETCH;

        } else if (chain.equals(CRYPTO_MAIN)) {
            return BLOCK_TIME_CRYPTO;

        } else if (chain.equals(SIF_MAIN)) {
            return BLOCK_TIME_SIF;

        } else if (chain.equals(KI_MAIN)) {
            return BLOCK_TIME_KI;

        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal getRealBlockPerYear(BaseChain chain) {
        if (getRealBlockTime(chain) == BigDecimal.ZERO) {
            return BigDecimal.ZERO;
        }
        return YEAR_SEC.divide(getRealBlockTime(chain), 2, RoundingMode.DOWN);
    }

    public static BigDecimal getEstimateGasAmount(Context c, BaseChain basechain, int txType, int valCnt) {
        BigDecimal result = BigDecimal.ZERO;
        if (basechain.equals(COSMOS_MAIN) || basechain.equals(IRIS_MAIN) || basechain.equals(AKASH_MAIN) || basechain.equals(PERSIS_MAIN) || basechain.equals(CRYPTO_MAIN) ||
                basechain.equals(OSMOSIS_MAIN) || basechain.equals(COSMOS_TEST) || basechain.equals(IRIS_TEST) || basechain.equals(RIZON_TEST) || basechain.equals(ALTHEA_TEST)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(V1_GAS_AMOUNT_LOW);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(V1_GAS_AMOUNT_MID);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(V1_GAS_AMOUNT_MID);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(V1_GAS_AMOUNT_HIGH);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(V1_GAS_AMOUNT_HIGH);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(V1_GAS_AMOUNT_LOW);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(V1_GAS_AMOUNT_LOW);
            }

        } else if (basechain.equals(IOV_MAIN) || basechain.equals(IOV_TEST)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(IOV_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(IOV_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(IOV_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(IOV_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(IOV_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward_kava)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(IOV_GAS_AMOUNT_LOW);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(IOV_GAS_AMOUNT_LOW);
            } else if (txType == CONST_PW_TX_REGISTER_DOMAIN || txType == CONST_PW_TX_REGISTER_ACCOUNT) {
                return new BigDecimal(IOV_GAS_AMOUNT_REGISTER);
            } else if (txType == CONST_PW_TX_DELETE_DOMAIN || txType == CONST_PW_TX_DELETE_ACCOUNT) {
                return new BigDecimal(IOV_GAS_AMOUNT_DELETE);
            } else if (txType == CONST_PW_TX_RENEW_DOMAIN || txType == CONST_PW_TX_RENEW_ACCOUNT) {
                return new BigDecimal(IOV_GAS_AMOUNT_RENEW);
            } else if (txType == CONST_PW_TX_REPLACE_STARNAME) {
                return new BigDecimal(IOV_GAS_AMOUNT_REPLACE);
            }

        } else if (basechain.equals(KAVA_MAIN) || basechain.equals(KAVA_TEST)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(KAVA_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(KAVA_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(KAVA_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(KAVA_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(KAVA_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward_kava)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(KAVA_GAS_AMOUNT_VOTE);
            } else if (txType == CONST_PW_TX_CLAIM_INCENTIVE || txType == CONST_PW_TX_CLAIM_HARVEST_REWARD) {
                return new BigDecimal(KAVA_GAS_AMOUNT_CLAIM_INCENTIVE);
            } else if (txType == CONST_PW_TX_CREATE_CDP || txType == CONST_PW_TX_DEPOSIT_CDP || txType == CONST_PW_TX_WITHDRAW_CDP || txType == CONST_PW_TX_DRAW_DEBT_CDP || txType == CONST_PW_TX_REPAY_CDP) {
                return new BigDecimal(KAVA_GAS_AMOUNT_CDP);
            } else if (txType == CONST_PW_TX_DEPOSIT_HARD || txType == CONST_PW_TX_WITHDRAW_HARD || txType == CONST_PW_TX_BORROW_HARD || txType == CONST_PW_TX_REPAY_HARD) {
                return new BigDecimal(KAVA_GAS_AMOUNT_HARD_POOL);
            } else if (txType == CONST_PW_TX_HTLS_REFUND) {
                return new BigDecimal(KAVA_GAS_AMOUNT_BEP3);
            }

        } else if (basechain.equals(BAND_MAIN)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(BAND_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(BAND_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(BAND_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(BAND_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(BAND_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward_kava)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(BAND_GAS_AMOUNT_ADDRESS_CHANGE);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(BAND_GAS_AMOUNT_VOTE);
            }

        } else if (basechain.equals(OKEX_MAIN) || basechain.equals(OK_TEST)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(OK_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_OK_DEPOSIT || txType == CONST_PW_TX_OK_WITHDRAW) {
                return (new BigDecimal(OK_GAS_AMOUNT_STAKE_MUX).multiply(new BigDecimal("" + valCnt))).add(new BigDecimal(BaseConstant.OK_GAS_AMOUNT_STAKE));
            } else if (txType == CONST_PW_TX_OK_DIRECT_VOTE) {
                return (new BigDecimal(OK_GAS_AMOUNT_VOTE_MUX).multiply(new BigDecimal("" + valCnt))).add(new BigDecimal(BaseConstant.OK_GAS_AMOUNT_VOTE));
            }

        } else if (basechain.equals(CERTIK_MAIN) || basechain.equals(CERTIK_TEST)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(CERTIK_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(CERTIK_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(CERTIK_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(CERTIK_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(CERTIK_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward_kava)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(CERTIK_GAS_AMOUNT_REWARD_ADDRESS_CHANGE);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(CERTIK_GAS_AMOUNT_VOTE);
            }

        } else if (basechain.equals(SECRET_MAIN)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(SECRET_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(SECRET_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(SECRET_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(SECRET_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(SECRET_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward_kava)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(SECRET_GAS_AMOUNT_REWARD_ADDRESS_CHANGE);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(SECRET_GAS_AMOUNT_VOTE);
            }

        } else if (basechain.equals(SENTINEL_MAIN)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(SENTINEL_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(SENTINEL_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(SENTINEL_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(SENTINEL_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(SENTINEL_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward_kava)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(SENTINEL_GAS_AMOUNT_REWARD_ADDRESS_CHANGE);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(SENTINEL_GAS_AMOUNT_VOTE);
            }

        } else if (basechain.equals(FETCHAI_MAIN)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(FETCH_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(FETCH_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(FETCH_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(FETCH_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(FETCH_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(FETCH_GAS_AMOUNT_REWARD_ADDRESS_CHANGE);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(FETCH_GAS_AMOUNT_VOTE);
            }

        } else if (basechain.equals(SIF_MAIN)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(SIF_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(SIF_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(SIF_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(SIF_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(SIF_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(SIF_GAS_AMOUNT_REWARD_ADDRESS_CHANGE);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(SIF_GAS_AMOUNT_VOTE);
            }

        } else if (basechain.equals(KI_MAIN)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(KI_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(KI_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(KI_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(KI_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(KI_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(KI_GAS_AMOUNT_REWARD_ADDRESS_CHANGE);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(KI_GAS_AMOUNT_VOTE);
            }

        } else if (basechain.equals(MEDI_TEST)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(MEDI_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(MEDI_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(MEDI_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(MEDI_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(MEDI_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(MEDI_GAS_AMOUNT_REWARD_ADDRESS_CHANGE);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(MEDI_GAS_AMOUNT_VOTE);
            }

        }
        return result;
    }

    public static BigDecimal getGasRate(BaseChain basechain, int position) {
        if (basechain.equals(COSMOS_MAIN) || basechain.equals(AKASH_MAIN) || basechain.equals(COSMOS_TEST) || basechain.equals(RIZON_TEST) || basechain.equals(ALTHEA_TEST)) {
            if (position == 0) {
                return new BigDecimal(COSMOS_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(COSMOS_GAS_RATE_LOW);
            }
            return new BigDecimal(COSMOS_GAS_RATE_AVERAGE);

        } else if (basechain.equals(IRIS_MAIN) || basechain.equals(IRIS_TEST)) {
            if (position == 0) {
                return new BigDecimal(IRIS_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(IRIS_GAS_RATE_LOW);
            }
            return new BigDecimal(IRIS_GAS_RATE_AVERAGE);

        } else if (basechain.equals(SENTINEL_MAIN)) {
            if (position == 0) {
                return new BigDecimal(SENTINEL_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(SENTINEL_GAS_RATE_LOW);
            }
            return new BigDecimal(SENTINEL_GAS_RATE_AVERAGE);

        } else if (basechain.equals(PERSIS_MAIN)) {
            if (position == 0) {
                return new BigDecimal(PERSIS_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(PERSIS_GAS_RATE_LOW);
            }
            return new BigDecimal(PERSIS_GAS_RATE_AVERAGE);

        } else if (basechain.equals(CRYPTO_MAIN)) {
            if (position == 0) {
                return new BigDecimal(CRYPTO_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(CRYPTO_GAS_RATE_LOW);
            }
            return new BigDecimal(CRYPTO_GAS_RATE_AVERAGE);

        } else if (basechain.equals(OSMOSIS_MAIN)) {
            if (position == 0) {
                return new BigDecimal(OSMOSIS_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(OSMOSIS_GAS_RATE_LOW);
            }
            return new BigDecimal(OSMOSIS_GAS_RATE_AVERAGE);

        } else if (basechain.equals(IOV_MAIN) || basechain.equals(IOV_TEST)) {
            if (position == 0) {
                return new BigDecimal(STARNAME_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(STARNAME_GAS_RATE_LOW);
            }
            return new BigDecimal(STARNAME_GAS_RATE_AVERAGE);
        } else if (basechain.equals(KAVA_MAIN) || basechain.equals(KAVA_TEST)) {
            if (position == 0) {
                return BigDecimal.ZERO.setScale(3);
            } else if (position == 1) {
                return new BigDecimal(KAVA_GAS_RATE_LOW);
            }
            return new BigDecimal(KAVA_GAS_RATE_AVERAGE);

        } else if (basechain.equals(BAND_MAIN)) {
            if (position == 0) {
                return BigDecimal.ZERO.setScale(3);
            } else if (position == 1) {
                return new BigDecimal(BAND_GAS_RATE_LOW);
            }
            return new BigDecimal(BAND_GAS_RATE_AVERAGE);
        } else if (basechain.equals(BNB_MAIN) || basechain.equals(BNB_TEST)) {
            return BigDecimal.ZERO.setScale(3);

        } else if (basechain.equals(OKEX_MAIN) || basechain.equals(OK_TEST)) {
            return new BigDecimal(OK_GAS_RATE_AVERAGE);

        } else if (basechain.equals(CERTIK_MAIN) || basechain.equals(CERTIK_TEST)) {
            return new BigDecimal(CERTIK_GAS_RATE_AVERAGE);

        } else if (basechain.equals(SECRET_MAIN)) {
            return new BigDecimal(SECRET_GAS_FEE_RATE_AVERAGE);

        } else if (basechain.equals(FETCHAI_MAIN)) {
            return new BigDecimal(FETCH_GAS_FEE_RATE_AVERAGE);

        } else if (basechain.equals(SIF_MAIN)) {
            return new BigDecimal(SIF_GAS_FEE_RATE_AVERAGE);

        } else if (basechain.equals(KI_MAIN)) {
            return new BigDecimal(KI_GAS_FEE_RATE_AVERAGE);

        } else if (basechain.equals(MEDI_TEST)) {
            return new BigDecimal(MEDI_GAS_FEE_RATE_AVERAGE);

        }
        return BigDecimal.ZERO.setScale(3);
    }

    public static byte[] HexStringToByteArray(String s) throws IllegalArgumentException {
        int len = s.length();
        if (len % 2 == 1) {
            throw new IllegalArgumentException("Hex string must have even number of characters");
        }
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * token price
     */
    public static String marketPrice(BaseChain basechain) {
        return "usdt" + "," + WDp.mainDenom(basechain);
    }
}
