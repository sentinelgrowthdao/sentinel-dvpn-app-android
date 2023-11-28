package co.sentinel.cosmos.network;

import java.util.concurrent.Executors;

import co.sentinel.cosmos.BuildConfig;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ChannelBuilder {

    private static String grpcSentinelMain = BuildConfig.GRPC_BASE_URL;
    private static int portSentinelMain = BuildConfig.GRPC_PORT;


    public final static int TIME_OUT = 30;

    //Channel for sentinel main
    private static ManagedChannel channel_sentinel_main = null;

    public static ManagedChannel getMainChannel() {
        if (channel_sentinel_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_sentinel_main = ManagedChannelBuilder
                        .forAddress(grpcSentinelMain, portSentinelMain)
                        .usePlaintext()
                        .executor(Executors.newSingleThreadExecutor())
                        .build();
            }
        }
        return channel_sentinel_main;
    }

    public static void resetSentinelMain() {
        if (channel_sentinel_main != null) {
            synchronized (ChannelBuilder.class) {
                channel_sentinel_main.shutdownNow();
                channel_sentinel_main = ManagedChannelBuilder
                        .forAddress(grpcSentinelMain, portSentinelMain)
                        .usePlaintext()
                        .executor(Executors.newSingleThreadExecutor())
                        .build();
            }
        }
    }

    public static void createCustomChannel(String address, int port) {
        synchronized (ChannelBuilder.class) {
            if (channel_sentinel_main != null) {
                channel_sentinel_main.shutdownNow();
            }
            grpcSentinelMain = address;
            portSentinelMain = port;
            channel_sentinel_main = ManagedChannelBuilder
                    .forAddress(grpcSentinelMain, portSentinelMain)
                    .usePlaintext()
                    .executor(Executors.newSingleThreadExecutor())
                    .build();
        }
    }
}
