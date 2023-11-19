package co.sentinel.cosmos.base;

import java.math.BigDecimal;

public class BaseConstant {
    public final static boolean IS_SHOTimber              = false;
    public final static boolean SUPPORT_MOONPAY         = true;
    public final static boolean SUPPORT_BEP3_SWAP       = true;
    public final static String LOG_TAG                  = "Cosmostation";

    public final static String DB_NAME                  = "WannaBit";
    public final static int DB_VERSION                  = 5;
    public final static String DB_TABLE_PASSWORD        = "paswd";
    public final static String DB_TABLE_ACCOUNT         = "accnt";
    public final static String DB_TABLE_BALANCE         = "balan";
    public final static String DB_TABLE_BONDING         = "bondi";
    public final static String DB_TABLE_UNBONDING       = "unbond";
    public final static String DB_TABLE_PRICE           = "price";

    public final static String PRE_USER_ID                  = "PRE_USER_ID";
    public final static String PRE_SELECTED_CHAIN           = "PRE_SELECTED_CHAIN";
    public final static String PRE_VALIDATOR_SORTING        = "PRE_VALIDATOR_SORTING";
    public final static String PRE_MY_VALIDATOR_SORTING     = "PRE_MY_VALIDATOR_SORTING";
    public final static String PRE_CURRENCY                 = "PRE_CURRENCY";
    public final static String PRE_MARKET                   = "PRE_MARKET";
    public final static String PRE_USING_APP_LOCK           = "PRE_USING_APP_LOCK";
    public final static String PRE_USING_FINGERPRINT        = "PRE_USING_FINGERPRINT";
    public final static String PRE_APP_LOCK_TIME            = "PRE_APP_LOCK_TIME";
    public final static String PRE_APP_LOCK_LEAVE_TIME      = "PRE_APP_LOCK_LEAVE_TIME";
    public final static String PRE_TOKEN_SORTING            = "PRE_TOKEN_SORTING";
    public final static String PRE_ACCOUNT_ORDER            = "PRE_ACCOUNT_ORDER";
    public final static String PRE_FCM_TOKEN                = "PRE_FCM_TOKEN";
    public final static String PRE_KAVA_TESTNET_WARN        = "PRE_KAVA_TESTNET_WARN";
    public final static String PRE_EVENT_HIDE               = "PRE_EVENT_HIDE";


    public final static int TASK_INIT_PW                                = 2000;
    public final static int TASK_INIT_ACCOUNT                           = 2002;
    public final static int TASK_INIT_EMPTY_ACCOUNT                     = 2003;
    public final static int TASK_PASSWORD_CHECK                         = 2015;
    public final static int TASK_OVERRIDE_ACCOUNT                       = 2019;
    public final static int TASK_DELETE_USER                            = 2024;
    public final static int TASK_CHECK_MNEMONIC                         = 2025;
    //gRPC
    public final static int TASK_GRPC_FETCH_BALANCE                     = 4001;
    public final static int TASK_GRPC_FETCH_BONDED_VALIDATORS           = 4002;
    public final static int TASK_GRPC_FETCH_UNBONDED_VALIDATORS         = 4003;
    public final static int TASK_GRPC_FETCH_UNBONDING_VALIDATORS        = 4004;
    public final static int TASK_GRPC_FETCH_DELEGATIONS                 = 4005;
    public final static int TASK_GRPC_FETCH_UNDELEGATIONS               = 4006;
    public final static int TASK_GRPC_FETCH_ALL_REWARDS                 = 4007;
    public final static int TASK_GRPC_FETCH_NODE_INFO                   = 4024;
    public final static int TASK_GRPC_FETCH_AUTH                        = 4025;
    public final static int TASK_GRPC_BROAD_SEND                        = 4303;


    public final static int CONST_PW_TX_SIMPLE_SEND                         = 5003;
    public final static int CONST_PW_TX_SIMPLE_DELEGATE                     = 5004;
    public final static int CONST_PW_TX_SIMPLE_UNDELEGATE                   = 5005;
    public final static int CONST_PW_TX_SIMPLE_REWARD                       = 5006;
    public final static int CONST_PW_DELETE_ACCOUNT                         = 5007;
    public final static int CONST_PW_CHECK_MNEMONIC                         = 5008;
    public final static int CONST_PW_TX_SIMPLE_REDELEGATE                   = 5009;
    public final static int CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS        = 5010;
    public final static int CONST_PW_TX_REINVEST                            = 5011;
    public final static int CONST_PW_TX_VOTE                                = 5012;
    public final static int CONST_PW_TX_CREATE_CDP                          = 5013;
    public final static int CONST_PW_TX_REPAY_CDP                           = 5014;
    public final static int CONST_PW_TX_DRAW_DEBT_CDP                       = 5015;
    public final static int CONST_PW_TX_DEPOSIT_CDP                         = 5016;
    public final static int CONST_PW_TX_WITHDRAW_CDP                        = 5017;
//    public final static int CONST_PW_TX_HTLS_SWAP                           = 5018;
    public final static int CONST_PW_TX_HTLS_REFUND                         = 5019;
    public final static int CONST_PW_TX_CLAIM_INCENTIVE                     = 5020;
    public final static int CONST_PW_TX_OK_DEPOSIT                          = 5021;
    public final static int CONST_PW_TX_OK_WITHDRAW                         = 5022;
    public final static int CONST_PW_TX_OK_DIRECT_VOTE                      = 5023;
    public final static int CONST_PW_TX_REGISTER_DOMAIN                     = 5024;
    public final static int CONST_PW_TX_REGISTER_ACCOUNT                    = 5025;
    public final static int CONST_PW_TX_DELETE_DOMAIN                       = 5026;
    public final static int CONST_PW_TX_DELETE_ACCOUNT                      = 5027;
    public final static int CONST_PW_TX_RENEW_DOMAIN                        = 5028;
    public final static int CONST_PW_TX_RENEW_ACCOUNT                       = 5029;
    public final static int CONST_PW_TX_REPLACE_STARNAME                    = 5030;
    public final static int CONST_PW_TX_DEPOSIT_HARD                        = 5031;
    public final static int CONST_PW_TX_WITHDRAW_HARD                       = 5032;
    public final static int CONST_PW_TX_CLAIM_HARVEST_REWARD                = 5033;
    public final static int CONST_PW_TX_BORROW_HARD                         = 5034;
    public final static int CONST_PW_TX_REPAY_HARD                          = 5035;


    public final static int ERROR_CODE_UNKNOWN              = 8000;
    public final static int ERROR_CODE_NETWORK              = 8001;
    public final static int ERROR_CODE_INVALID_PASSWORD     = 8002;
    public final static int ERROR_CODE_TIMEOUT              = 8003;
    public final static int ERROR_CODE_BROADCAST            = 8004;


    public final static String TOKEN_ATOM           = "uatom";
    public final static String TOKEN_IRIS           = "uiris";
    public final static String TOKEN_IRIS_ATTO      = "iris-atto";
    public final static String TOKEN_BNB            = "BNB";
    public final static String TOKEN_KAVA           = "ukava";
    public final static String TOKEN_HARD           = "hard";
    public final static String TOKEN_IOV            = "uiov";
    public final static String TOKEN_CERTIK         = "uctk";
    public final static String TOKEN_BAND           = "uband";
    public final static String TOKEN_AKASH          = "uakt";
    public final static String TOKEN_SECRET         = "uscrt";
    public final static String TOKEN_OK             = "okt";
    public final static String TOKEN_OK_OKB         = "okb";
    public final static String TOKEN_XPRT           = "uxprt";
    public final static String TOKEN_DVPN           = "udvpn";
    public final static String TOKEN_FET            = "afet";
    public final static String TOKEN_CRO            = "basecro";
    public final static String TOKEN_SIF            = "rowan";
    public final static String TOKEN_KI             = "uxki";
    public final static String TOKEN_RIZON          = "uatolo";
    public final static String TOKEN_MEDI           = "umed";
    public final static String TOKEN_ALTHEA         = "ualtg";
    public final static String TOKEN_OSMOSIS        = "uosmo";
    public final static String TOKEN_ION            = "uion";

    public final static String TOKEN_IOV_TEST       = "uvoi";
    public final static String TOKEN_COSMOS_TEST    = "umuon";
    public final static String TOKEN_IRIS_TEST      = "ubif";


    //HTLC swap support Token Types
    public final static String  TOKEN_HTLC_BINANCE_BNB               = "BNB";
    public final static String  TOKEN_HTLC_KAVA_BNB                  = "bnb";
    public final static String  TOKEN_HTLC_BINANCE_BTCB             = "BTCB-1DE";
    public final static String  TOKEN_HTLC_KAVA_BTCB                = "btcb";
    public final static String  TOKEN_HTLC_BINANCE_XRPB             = "XRP-BF2";
    public final static String  TOKEN_HTLC_KAVA_XRPB                = "xrpb";
    public final static String  TOKEN_HTLC_BINANCE_BUSD             = "BUSD-BD1";
    public final static String  TOKEN_HTLC_KAVA_BUSD                = "busd";

    public final static String TOKEN_HTLC_BINANCE_TEST_BNB          = "BNB";
    public final static String TOKEN_HTLC_BINANCE_TEST_BTC          = "BTCB-101";
    public final static String TOKEN_HTLC_KAVA_TEST_BNB             = "bnb";
    public final static String TOKEN_HTLC_KAVA_TEST_BTC             = "btcb";


    //TODO HardCoding!!
    public final static long COSMOS_UNBONDING_TIME = 1814400000;
    public final static long COSMOS_UNBONDING_DAY = 3;


    public final static String KEY_PATH             = "44'/118'/0'/0/";
    public final static String KEY_BNB_PATH         = "44'/714'/0'/0/";
    public final static String KEY_IOV_PATH         = "44'/234'/0'/0/";
    public final static String KEY_NEW_KAVA_PATH    = "44'/459'/0'/0/";
    public final static String KEY_BAND_PATH        = "44'/494'/0'/0/";
    public final static String KEY_NEW_OK_PATH      = "44'/996'/0'/0/";
    public final static String KEY_NEW_SECRET_PATH  = "44'/529'/0'/0/";
    public final static String KEY_PERSIS_PATH      = "44'/750'/0'/0/";
    public final static String KEY_CRYPTO_PATH      = "44'/394'/0'/0/";
    public final static String KEY_RIZON_PATH       = "44'/1217'/0'/0/";
    public final static String KEY_MEDI_PATH        = "44'/371'/0'/0/";
    public final static String KEY_ALTHEA_PATH      = "44'/60'/0'/0/";
    public final static String characterFilter      = "[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\s]";


    public final static long CONSTANT_S = 1000l;
    public final static long CONSTANT_10S = CONSTANT_S * 10;
    public final static long CONSTANT_30S = CONSTANT_S * 30;
    public final static long CONSTANT_M = CONSTANT_S * 60;
    public final static long CONSTANT_H = CONSTANT_M * 60;
    public final static long CONSTANT_D = CONSTANT_H * 24;


    public final static int MEMO_ATOM = 255;
    public final static int MEMO_BNB = 100;





    public final static String FEE_BNB_SEND                             = "0.000075";

    public final static String KAVA_GAS_RATE_LOW                        = "0.0025";
    public final static String KAVA_GAS_RATE_AVERAGE                    = "0.025";
    public final static String KAVA_GAS_AMOUNT_SEND                     = "300000";
    public final static String KAVA_GAS_AMOUNT_STAKE                    = "800000";
    public final static String KAVA_GAS_AMOUNT_REINVEST                 = "800000";
    public final static String KAVA_GAS_AMOUNT_REDELEGATE               = "800000";
    public final static String KAVA_GAS_AMOUNT_VOTE                     = "300000";
    public final static String KAVA_GAS_AMOUNT_CLAIM_INCENTIVE          = "800000";
    public final static String KAVA_GAS_AMOUNT_CDP                      = "2000000";
    public final static String KAVA_GAS_AMOUNT_HARD_POOL                = "800000";
    public final static String KAVA_GAS_AMOUNT_BEP3                     = "500000";

    public final static String BAND_GAS_AMOUNT_SEND                     = "100000";
    public final static String BAND_GAS_AMOUNT_STAKE                    = "200000";
    public final static String BAND_GAS_AMOUNT_REDELEGATE               = "240000";
    public final static String BAND_GAS_AMOUNT_REINVEST                 = "220000";
    public final static String BAND_GAS_AMOUNT_ADDRESS_CHANGE           = "100000";
    public final static String BAND_GAS_AMOUNT_VOTE                     = "100000";

//    public final static String IOV_GAS_RATE_AVERAGE                     = "1.00";
    public final static String IOV_GAS_AMOUNT_SEND                      = "100000";
    public final static String IOV_GAS_AMOUNT_STAKE                     = "200000";
    public final static String IOV_GAS_AMOUNT_REDELEGATE                = "300000";
    public final static String IOV_GAS_AMOUNT_REINVEST                  = "300000";
    public final static String IOV_GAS_AMOUNT_LOW                       = "100000";
    public final static String IOV_GAS_AMOUNT_REGISTER                  = "300000";
    public final static String IOV_GAS_AMOUNT_DELETE                    = "150000";
    public final static String IOV_GAS_AMOUNT_RENEW                     = "300000";
    public final static String IOV_GAS_AMOUNT_REPLACE                   = "300000";

    public final static String OK_GAS_RATE_AVERAGE                      = "0.000000001";
    public final static String OK_GAS_AMOUNT_SEND                       = "200000";
    public final static String OK_GAS_AMOUNT_STAKE                      = "200000";
    public final static String OK_GAS_AMOUNT_STAKE_MUX                  = "20000";
    public final static String OK_GAS_AMOUNT_VOTE                       = "200000";
    public final static String OK_GAS_AMOUNT_VOTE_MUX                   = "50000";

    public final static String CERTIK_GAS_RATE_AVERAGE                  = "0.05";
    public final static String CERTIK_GAS_AMOUNT_SEND                   = "100000";
    public final static String CERTIK_GAS_AMOUNT_STAKE                  = "200000";
    public final static String CERTIK_GAS_AMOUNT_REDELEGATE             = "300000";
    public final static String CERTIK_GAS_AMOUNT_REINVEST               = "300000";
    public final static String CERTIK_GAS_AMOUNT_REWARD_ADDRESS_CHANGE  = "100000";
    public final static String CERTIK_GAS_AMOUNT_VOTE                   = "100000";

    public final static String SECRET_GAS_FEE_RATE_AVERAGE              = "0.25";
    public final static String SECRET_GAS_AMOUNT_SEND                   = "80000";
    public final static String SECRET_GAS_AMOUNT_STAKE                  = "200000";
    public final static String SECRET_GAS_AMOUNT_REDELEGATE             = "300000";
    public final static String SECRET_GAS_AMOUNT_REINVEST               = "350000";
    public final static String SECRET_GAS_AMOUNT_REWARD_ADDRESS_CHANGE  = "80000";
    public final static String SECRET_GAS_AMOUNT_VOTE                   = "100000";

    public final static String SENTINEL_GAS_AMOUNT_SEND                   = "100000";
    public final static String SENTINEL_GAS_AMOUNT_STAKE                  = "200000";
    public final static String SENTINEL_GAS_AMOUNT_REDELEGATE             = "300000";
    public final static String SENTINEL_GAS_AMOUNT_REINVEST               = "350000";
    public final static String SENTINEL_GAS_AMOUNT_REWARD_ADDRESS_CHANGE  = "100000";
    public final static String SENTINEL_GAS_AMOUNT_VOTE                   = "100000";

    public final static String FETCH_GAS_FEE_RATE_AVERAGE               = "0.00";
    public final static String FETCH_GAS_AMOUNT_SEND                    = "100000";
    public final static String FETCH_GAS_AMOUNT_STAKE                   = "200000";
    public final static String FETCH_GAS_AMOUNT_REDELEGATE              = "300000";
    public final static String FETCH_GAS_AMOUNT_REINVEST                = "350000";
    public final static String FETCH_GAS_AMOUNT_REWARD_ADDRESS_CHANGE   = "100000";
    public final static String FETCH_GAS_AMOUNT_VOTE                    = "100000";

    public final static String SIF_GAS_FEE_RATE_AVERAGE                 = "0.50";
    public final static String SIF_GAS_AMOUNT_SEND                      = "100000";
    public final static String SIF_GAS_AMOUNT_STAKE                     = "200000";
    public final static String SIF_GAS_AMOUNT_REDELEGATE                = "300000";
    public final static String SIF_GAS_AMOUNT_REINVEST                  = "350000";
    public final static String SIF_GAS_AMOUNT_REWARD_ADDRESS_CHANGE     = "100000";
    public final static String SIF_GAS_AMOUNT_VOTE                      = "100000";

    public final static String KI_GAS_FEE_RATE_AVERAGE                  = "0.025";
    public final static String KI_GAS_AMOUNT_SEND                       = "100000";
    public final static String KI_GAS_AMOUNT_STAKE                      = "200000";
    public final static String KI_GAS_AMOUNT_REDELEGATE                 = "300000";
    public final static String KI_GAS_AMOUNT_REINVEST                   = "350000";
    public final static String KI_GAS_AMOUNT_REWARD_ADDRESS_CHANGE      = "100000";
    public final static String KI_GAS_AMOUNT_VOTE                       = "100000";

    public final static String MEDI_GAS_FEE_RATE_AVERAGE                = "0.025";
    public final static String MEDI_GAS_AMOUNT_SEND                     = "100000";
    public final static String MEDI_GAS_AMOUNT_STAKE                    = "200000";
    public final static String MEDI_GAS_AMOUNT_REDELEGATE               = "300000";
    public final static String MEDI_GAS_AMOUNT_REINVEST                 = "350000";
    public final static String MEDI_GAS_AMOUNT_REWARD_ADDRESS_CHANGE    = "100000";
    public final static String MEDI_GAS_AMOUNT_VOTE                     = "100000";


    public final static String COSMOS_GAS_RATE_TINY                     = "0.00025";
    public final static String COSMOS_GAS_RATE_LOW                      = "0.0025";
    public final static String COSMOS_GAS_RATE_AVERAGE                  = "0.025";

    public final static String IRIS_GAS_RATE_TINY                       = "0.002";
    public final static String IRIS_GAS_RATE_LOW                        = "0.02";
    public final static String IRIS_GAS_RATE_AVERAGE                    = "0.2";

    public final static String SENTINEL_GAS_RATE_TINY                   = "0.01";
    public final static String SENTINEL_GAS_RATE_LOW                    = "0.1";
    public final static String SENTINEL_GAS_RATE_AVERAGE                = "0.1";

    public final static String PERSIS_GAS_RATE_TINY                     = "0.000";
    public final static String PERSIS_GAS_RATE_LOW                      = "0.000";
    public final static String PERSIS_GAS_RATE_AVERAGE                  = "0.000";

    public final static String CRYPTO_GAS_RATE_TINY                     = "0.025";
    public final static String CRYPTO_GAS_RATE_LOW                      = "0.05";
    public final static String CRYPTO_GAS_RATE_AVERAGE                  = "0.075";

    public final static String OSMOSIS_GAS_RATE_TINY                    = "0.000";
    public final static String OSMOSIS_GAS_RATE_LOW                     = "0.0025";
    public final static String OSMOSIS_GAS_RATE_AVERAGE                 = "0.025";

    public final static String BAND_GAS_RATE_TINY                       = "0.000";
    public final static String BAND_GAS_RATE_LOW                        = "0.0025";
    public final static String BAND_GAS_RATE_AVERAGE                    = "0.025";

    public final static String STARNAME_GAS_RATE_TINY                   = "0.10";
    public final static String STARNAME_GAS_RATE_LOW                    = "1.00";
    public final static String STARNAME_GAS_RATE_AVERAGE                = "1.00";

    public final static String V1_GAS_AMOUNT_LOW                        = "100000";
    public final static String V1_GAS_AMOUNT_MID                        = "200000";
    public final static String V1_GAS_AMOUNT_HIGH                       = "300000";


    public final static BigDecimal DAY_SEC = new BigDecimal("86400");
    public final static BigDecimal MONTH_SEC = DAY_SEC.multiply(new BigDecimal("30"));
    public final static BigDecimal YEAR_SEC = DAY_SEC.multiply(new BigDecimal("365"));

    public final static BigDecimal BLOCK_TIME_COSMOS = new BigDecimal("7.3266");
    public final static BigDecimal BLOCK_TIME_IRIS = new BigDecimal("6.6094");
    public final static BigDecimal BLOCK_TIME_IOV = new BigDecimal("6.1838");
    public final static BigDecimal BLOCK_TIME_KAVA = new BigDecimal("6.5700");
    public final static BigDecimal BLOCK_TIME_BAND = new BigDecimal("3.01");
    public final static BigDecimal BLOCK_TIME_CERTIK = new BigDecimal("5.75");
    public final static BigDecimal BLOCK_TIME_SECRET = new BigDecimal("5.96");
    public final static BigDecimal BLOCK_TIME_AKASH = new BigDecimal("6.1908");
    public final static BigDecimal BLOCK_TIME_PERSISTENCE = new BigDecimal("5.6381");
    public final static BigDecimal BLOCK_TIME_FETCH = new BigDecimal("5.8956");
    public final static BigDecimal BLOCK_TIME_CRYPTO = new BigDecimal("6.4383");
    public final static BigDecimal BLOCK_TIME_SIF = new BigDecimal("5.6577");
    public final static BigDecimal BLOCK_TIME_KI = new BigDecimal("5.6524");


    // SOLAR ADDED ERRORS
    public final static int ERROR_INSUFFICIENT_FUNDS = 5;
}
