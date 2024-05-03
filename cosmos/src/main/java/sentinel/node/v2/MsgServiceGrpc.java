package sentinel.node.v2;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.3)",
    comments = "Source: sentinel/node/v2/msg.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgServiceGrpc {

  private MsgServiceGrpc() {}

  public static final String SERVICE_NAME = "sentinel.node.v2.MsgService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sentinel.node.v2.Msg.MsgRegisterRequest,
      sentinel.node.v2.Msg.MsgRegisterResponse> getMsgRegisterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgRegister",
      requestType = sentinel.node.v2.Msg.MsgRegisterRequest.class,
      responseType = sentinel.node.v2.Msg.MsgRegisterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.node.v2.Msg.MsgRegisterRequest,
      sentinel.node.v2.Msg.MsgRegisterResponse> getMsgRegisterMethod() {
    io.grpc.MethodDescriptor<sentinel.node.v2.Msg.MsgRegisterRequest, sentinel.node.v2.Msg.MsgRegisterResponse> getMsgRegisterMethod;
    if ((getMsgRegisterMethod = MsgServiceGrpc.getMsgRegisterMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgRegisterMethod = MsgServiceGrpc.getMsgRegisterMethod) == null) {
          MsgServiceGrpc.getMsgRegisterMethod = getMsgRegisterMethod =
              io.grpc.MethodDescriptor.<sentinel.node.v2.Msg.MsgRegisterRequest, sentinel.node.v2.Msg.MsgRegisterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgRegister"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.node.v2.Msg.MsgRegisterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.node.v2.Msg.MsgRegisterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgRegister"))
              .build();
        }
      }
    }
    return getMsgRegisterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.node.v2.Msg.MsgUpdateDetailsRequest,
      sentinel.node.v2.Msg.MsgUpdateDetailsResponse> getMsgUpdateDetailsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgUpdateDetails",
      requestType = sentinel.node.v2.Msg.MsgUpdateDetailsRequest.class,
      responseType = sentinel.node.v2.Msg.MsgUpdateDetailsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.node.v2.Msg.MsgUpdateDetailsRequest,
      sentinel.node.v2.Msg.MsgUpdateDetailsResponse> getMsgUpdateDetailsMethod() {
    io.grpc.MethodDescriptor<sentinel.node.v2.Msg.MsgUpdateDetailsRequest, sentinel.node.v2.Msg.MsgUpdateDetailsResponse> getMsgUpdateDetailsMethod;
    if ((getMsgUpdateDetailsMethod = MsgServiceGrpc.getMsgUpdateDetailsMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgUpdateDetailsMethod = MsgServiceGrpc.getMsgUpdateDetailsMethod) == null) {
          MsgServiceGrpc.getMsgUpdateDetailsMethod = getMsgUpdateDetailsMethod =
              io.grpc.MethodDescriptor.<sentinel.node.v2.Msg.MsgUpdateDetailsRequest, sentinel.node.v2.Msg.MsgUpdateDetailsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgUpdateDetails"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.node.v2.Msg.MsgUpdateDetailsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.node.v2.Msg.MsgUpdateDetailsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgUpdateDetails"))
              .build();
        }
      }
    }
    return getMsgUpdateDetailsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.node.v2.Msg.MsgUpdateStatusRequest,
      sentinel.node.v2.Msg.MsgUpdateStatusResponse> getMsgUpdateStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgUpdateStatus",
      requestType = sentinel.node.v2.Msg.MsgUpdateStatusRequest.class,
      responseType = sentinel.node.v2.Msg.MsgUpdateStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.node.v2.Msg.MsgUpdateStatusRequest,
      sentinel.node.v2.Msg.MsgUpdateStatusResponse> getMsgUpdateStatusMethod() {
    io.grpc.MethodDescriptor<sentinel.node.v2.Msg.MsgUpdateStatusRequest, sentinel.node.v2.Msg.MsgUpdateStatusResponse> getMsgUpdateStatusMethod;
    if ((getMsgUpdateStatusMethod = MsgServiceGrpc.getMsgUpdateStatusMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgUpdateStatusMethod = MsgServiceGrpc.getMsgUpdateStatusMethod) == null) {
          MsgServiceGrpc.getMsgUpdateStatusMethod = getMsgUpdateStatusMethod =
              io.grpc.MethodDescriptor.<sentinel.node.v2.Msg.MsgUpdateStatusRequest, sentinel.node.v2.Msg.MsgUpdateStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgUpdateStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.node.v2.Msg.MsgUpdateStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.node.v2.Msg.MsgUpdateStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgUpdateStatus"))
              .build();
        }
      }
    }
    return getMsgUpdateStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.node.v2.Msg.MsgSubscribeRequest,
      sentinel.node.v2.Msg.MsgSubscribeResponse> getMsgSubscribeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgSubscribe",
      requestType = sentinel.node.v2.Msg.MsgSubscribeRequest.class,
      responseType = sentinel.node.v2.Msg.MsgSubscribeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.node.v2.Msg.MsgSubscribeRequest,
      sentinel.node.v2.Msg.MsgSubscribeResponse> getMsgSubscribeMethod() {
    io.grpc.MethodDescriptor<sentinel.node.v2.Msg.MsgSubscribeRequest, sentinel.node.v2.Msg.MsgSubscribeResponse> getMsgSubscribeMethod;
    if ((getMsgSubscribeMethod = MsgServiceGrpc.getMsgSubscribeMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgSubscribeMethod = MsgServiceGrpc.getMsgSubscribeMethod) == null) {
          MsgServiceGrpc.getMsgSubscribeMethod = getMsgSubscribeMethod =
              io.grpc.MethodDescriptor.<sentinel.node.v2.Msg.MsgSubscribeRequest, sentinel.node.v2.Msg.MsgSubscribeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgSubscribe"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.node.v2.Msg.MsgSubscribeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.node.v2.Msg.MsgSubscribeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgSubscribe"))
              .build();
        }
      }
    }
    return getMsgSubscribeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MsgServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgServiceStub>() {
        @java.lang.Override
        public MsgServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgServiceStub(channel, callOptions);
        }
      };
    return MsgServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MsgServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgServiceBlockingStub>() {
        @java.lang.Override
        public MsgServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgServiceBlockingStub(channel, callOptions);
        }
      };
    return MsgServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MsgServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgServiceFutureStub>() {
        @java.lang.Override
        public MsgServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgServiceFutureStub(channel, callOptions);
        }
      };
    return MsgServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void msgRegister(sentinel.node.v2.Msg.MsgRegisterRequest request,
        io.grpc.stub.StreamObserver<sentinel.node.v2.Msg.MsgRegisterResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMsgRegisterMethod(), responseObserver);
    }

    /**
     */
    default void msgUpdateDetails(sentinel.node.v2.Msg.MsgUpdateDetailsRequest request,
        io.grpc.stub.StreamObserver<sentinel.node.v2.Msg.MsgUpdateDetailsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMsgUpdateDetailsMethod(), responseObserver);
    }

    /**
     */
    default void msgUpdateStatus(sentinel.node.v2.Msg.MsgUpdateStatusRequest request,
        io.grpc.stub.StreamObserver<sentinel.node.v2.Msg.MsgUpdateStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMsgUpdateStatusMethod(), responseObserver);
    }

    /**
     */
    default void msgSubscribe(sentinel.node.v2.Msg.MsgSubscribeRequest request,
        io.grpc.stub.StreamObserver<sentinel.node.v2.Msg.MsgSubscribeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMsgSubscribeMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service MsgService.
   */
  public static abstract class MsgServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MsgServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service MsgService.
   */
  public static final class MsgServiceStub
      extends io.grpc.stub.AbstractAsyncStub<MsgServiceStub> {
    private MsgServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgServiceStub(channel, callOptions);
    }

    /**
     */
    public void msgRegister(sentinel.node.v2.Msg.MsgRegisterRequest request,
        io.grpc.stub.StreamObserver<sentinel.node.v2.Msg.MsgRegisterResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMsgRegisterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgUpdateDetails(sentinel.node.v2.Msg.MsgUpdateDetailsRequest request,
        io.grpc.stub.StreamObserver<sentinel.node.v2.Msg.MsgUpdateDetailsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMsgUpdateDetailsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgUpdateStatus(sentinel.node.v2.Msg.MsgUpdateStatusRequest request,
        io.grpc.stub.StreamObserver<sentinel.node.v2.Msg.MsgUpdateStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMsgUpdateStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgSubscribe(sentinel.node.v2.Msg.MsgSubscribeRequest request,
        io.grpc.stub.StreamObserver<sentinel.node.v2.Msg.MsgSubscribeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMsgSubscribeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service MsgService.
   */
  public static final class MsgServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MsgServiceBlockingStub> {
    private MsgServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public sentinel.node.v2.Msg.MsgRegisterResponse msgRegister(sentinel.node.v2.Msg.MsgRegisterRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMsgRegisterMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.node.v2.Msg.MsgUpdateDetailsResponse msgUpdateDetails(sentinel.node.v2.Msg.MsgUpdateDetailsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMsgUpdateDetailsMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.node.v2.Msg.MsgUpdateStatusResponse msgUpdateStatus(sentinel.node.v2.Msg.MsgUpdateStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMsgUpdateStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.node.v2.Msg.MsgSubscribeResponse msgSubscribe(sentinel.node.v2.Msg.MsgSubscribeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMsgSubscribeMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service MsgService.
   */
  public static final class MsgServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<MsgServiceFutureStub> {
    private MsgServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.node.v2.Msg.MsgRegisterResponse> msgRegister(
        sentinel.node.v2.Msg.MsgRegisterRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMsgRegisterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.node.v2.Msg.MsgUpdateDetailsResponse> msgUpdateDetails(
        sentinel.node.v2.Msg.MsgUpdateDetailsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMsgUpdateDetailsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.node.v2.Msg.MsgUpdateStatusResponse> msgUpdateStatus(
        sentinel.node.v2.Msg.MsgUpdateStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMsgUpdateStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.node.v2.Msg.MsgSubscribeResponse> msgSubscribe(
        sentinel.node.v2.Msg.MsgSubscribeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMsgSubscribeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MSG_REGISTER = 0;
  private static final int METHODID_MSG_UPDATE_DETAILS = 1;
  private static final int METHODID_MSG_UPDATE_STATUS = 2;
  private static final int METHODID_MSG_SUBSCRIBE = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_MSG_REGISTER:
          serviceImpl.msgRegister((sentinel.node.v2.Msg.MsgRegisterRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.node.v2.Msg.MsgRegisterResponse>) responseObserver);
          break;
        case METHODID_MSG_UPDATE_DETAILS:
          serviceImpl.msgUpdateDetails((sentinel.node.v2.Msg.MsgUpdateDetailsRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.node.v2.Msg.MsgUpdateDetailsResponse>) responseObserver);
          break;
        case METHODID_MSG_UPDATE_STATUS:
          serviceImpl.msgUpdateStatus((sentinel.node.v2.Msg.MsgUpdateStatusRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.node.v2.Msg.MsgUpdateStatusResponse>) responseObserver);
          break;
        case METHODID_MSG_SUBSCRIBE:
          serviceImpl.msgSubscribe((sentinel.node.v2.Msg.MsgSubscribeRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.node.v2.Msg.MsgSubscribeResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getMsgRegisterMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.node.v2.Msg.MsgRegisterRequest,
              sentinel.node.v2.Msg.MsgRegisterResponse>(
                service, METHODID_MSG_REGISTER)))
        .addMethod(
          getMsgUpdateDetailsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.node.v2.Msg.MsgUpdateDetailsRequest,
              sentinel.node.v2.Msg.MsgUpdateDetailsResponse>(
                service, METHODID_MSG_UPDATE_DETAILS)))
        .addMethod(
          getMsgUpdateStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.node.v2.Msg.MsgUpdateStatusRequest,
              sentinel.node.v2.Msg.MsgUpdateStatusResponse>(
                service, METHODID_MSG_UPDATE_STATUS)))
        .addMethod(
          getMsgSubscribeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.node.v2.Msg.MsgSubscribeRequest,
              sentinel.node.v2.Msg.MsgSubscribeResponse>(
                service, METHODID_MSG_SUBSCRIBE)))
        .build();
  }

  private static abstract class MsgServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sentinel.node.v2.Msg.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MsgService");
    }
  }

  private static final class MsgServiceFileDescriptorSupplier
      extends MsgServiceBaseDescriptorSupplier {
    MsgServiceFileDescriptorSupplier() {}
  }

  private static final class MsgServiceMethodDescriptorSupplier
      extends MsgServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MsgServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MsgServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MsgServiceFileDescriptorSupplier())
              .addMethod(getMsgRegisterMethod())
              .addMethod(getMsgUpdateDetailsMethod())
              .addMethod(getMsgUpdateStatusMethod())
              .addMethod(getMsgSubscribeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
