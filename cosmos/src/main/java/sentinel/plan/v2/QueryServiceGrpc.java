package sentinel.plan.v2;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.3)",
    comments = "Source: sentinel/plan/v2/querier.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryServiceGrpc {

  private QueryServiceGrpc() {}

  public static final String SERVICE_NAME = "sentinel.plan.v2.QueryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sentinel.plan.v2.Querier.QueryPlansRequest,
      sentinel.plan.v2.Querier.QueryPlansResponse> getQueryPlansMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryPlans",
      requestType = sentinel.plan.v2.Querier.QueryPlansRequest.class,
      responseType = sentinel.plan.v2.Querier.QueryPlansResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.plan.v2.Querier.QueryPlansRequest,
      sentinel.plan.v2.Querier.QueryPlansResponse> getQueryPlansMethod() {
    io.grpc.MethodDescriptor<sentinel.plan.v2.Querier.QueryPlansRequest, sentinel.plan.v2.Querier.QueryPlansResponse> getQueryPlansMethod;
    if ((getQueryPlansMethod = QueryServiceGrpc.getQueryPlansMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryPlansMethod = QueryServiceGrpc.getQueryPlansMethod) == null) {
          QueryServiceGrpc.getQueryPlansMethod = getQueryPlansMethod =
              io.grpc.MethodDescriptor.<sentinel.plan.v2.Querier.QueryPlansRequest, sentinel.plan.v2.Querier.QueryPlansResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryPlans"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v2.Querier.QueryPlansRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v2.Querier.QueryPlansResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryPlans"))
              .build();
        }
      }
    }
    return getQueryPlansMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.plan.v2.Querier.QueryPlansForProviderRequest,
      sentinel.plan.v2.Querier.QueryPlansForProviderResponse> getQueryPlansForProviderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryPlansForProvider",
      requestType = sentinel.plan.v2.Querier.QueryPlansForProviderRequest.class,
      responseType = sentinel.plan.v2.Querier.QueryPlansForProviderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.plan.v2.Querier.QueryPlansForProviderRequest,
      sentinel.plan.v2.Querier.QueryPlansForProviderResponse> getQueryPlansForProviderMethod() {
    io.grpc.MethodDescriptor<sentinel.plan.v2.Querier.QueryPlansForProviderRequest, sentinel.plan.v2.Querier.QueryPlansForProviderResponse> getQueryPlansForProviderMethod;
    if ((getQueryPlansForProviderMethod = QueryServiceGrpc.getQueryPlansForProviderMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryPlansForProviderMethod = QueryServiceGrpc.getQueryPlansForProviderMethod) == null) {
          QueryServiceGrpc.getQueryPlansForProviderMethod = getQueryPlansForProviderMethod =
              io.grpc.MethodDescriptor.<sentinel.plan.v2.Querier.QueryPlansForProviderRequest, sentinel.plan.v2.Querier.QueryPlansForProviderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryPlansForProvider"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v2.Querier.QueryPlansForProviderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v2.Querier.QueryPlansForProviderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryPlansForProvider"))
              .build();
        }
      }
    }
    return getQueryPlansForProviderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.plan.v2.Querier.QueryPlanRequest,
      sentinel.plan.v2.Querier.QueryPlanResponse> getQueryPlanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryPlan",
      requestType = sentinel.plan.v2.Querier.QueryPlanRequest.class,
      responseType = sentinel.plan.v2.Querier.QueryPlanResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.plan.v2.Querier.QueryPlanRequest,
      sentinel.plan.v2.Querier.QueryPlanResponse> getQueryPlanMethod() {
    io.grpc.MethodDescriptor<sentinel.plan.v2.Querier.QueryPlanRequest, sentinel.plan.v2.Querier.QueryPlanResponse> getQueryPlanMethod;
    if ((getQueryPlanMethod = QueryServiceGrpc.getQueryPlanMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryPlanMethod = QueryServiceGrpc.getQueryPlanMethod) == null) {
          QueryServiceGrpc.getQueryPlanMethod = getQueryPlanMethod =
              io.grpc.MethodDescriptor.<sentinel.plan.v2.Querier.QueryPlanRequest, sentinel.plan.v2.Querier.QueryPlanResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryPlan"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v2.Querier.QueryPlanRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.plan.v2.Querier.QueryPlanResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryPlan"))
              .build();
        }
      }
    }
    return getQueryPlanMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static QueryServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryServiceStub>() {
        @java.lang.Override
        public QueryServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryServiceStub(channel, callOptions);
        }
      };
    return QueryServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static QueryServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryServiceBlockingStub>() {
        @java.lang.Override
        public QueryServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryServiceBlockingStub(channel, callOptions);
        }
      };
    return QueryServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static QueryServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryServiceFutureStub>() {
        @java.lang.Override
        public QueryServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryServiceFutureStub(channel, callOptions);
        }
      };
    return QueryServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void queryPlans(sentinel.plan.v2.Querier.QueryPlansRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v2.Querier.QueryPlansResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQueryPlansMethod(), responseObserver);
    }

    /**
     */
    default void queryPlansForProvider(sentinel.plan.v2.Querier.QueryPlansForProviderRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v2.Querier.QueryPlansForProviderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQueryPlansForProviderMethod(), responseObserver);
    }

    /**
     */
    default void queryPlan(sentinel.plan.v2.Querier.QueryPlanRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v2.Querier.QueryPlanResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQueryPlanMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service QueryService.
   */
  public static abstract class QueryServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return QueryServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service QueryService.
   */
  public static final class QueryServiceStub
      extends io.grpc.stub.AbstractAsyncStub<QueryServiceStub> {
    private QueryServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryServiceStub(channel, callOptions);
    }

    /**
     */
    public void queryPlans(sentinel.plan.v2.Querier.QueryPlansRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v2.Querier.QueryPlansResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQueryPlansMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryPlansForProvider(sentinel.plan.v2.Querier.QueryPlansForProviderRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v2.Querier.QueryPlansForProviderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQueryPlansForProviderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryPlan(sentinel.plan.v2.Querier.QueryPlanRequest request,
        io.grpc.stub.StreamObserver<sentinel.plan.v2.Querier.QueryPlanResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQueryPlanMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service QueryService.
   */
  public static final class QueryServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<QueryServiceBlockingStub> {
    private QueryServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public sentinel.plan.v2.Querier.QueryPlansResponse queryPlans(sentinel.plan.v2.Querier.QueryPlansRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQueryPlansMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.plan.v2.Querier.QueryPlansForProviderResponse queryPlansForProvider(sentinel.plan.v2.Querier.QueryPlansForProviderRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQueryPlansForProviderMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.plan.v2.Querier.QueryPlanResponse queryPlan(sentinel.plan.v2.Querier.QueryPlanRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQueryPlanMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service QueryService.
   */
  public static final class QueryServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<QueryServiceFutureStub> {
    private QueryServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.plan.v2.Querier.QueryPlansResponse> queryPlans(
        sentinel.plan.v2.Querier.QueryPlansRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQueryPlansMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.plan.v2.Querier.QueryPlansForProviderResponse> queryPlansForProvider(
        sentinel.plan.v2.Querier.QueryPlansForProviderRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQueryPlansForProviderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.plan.v2.Querier.QueryPlanResponse> queryPlan(
        sentinel.plan.v2.Querier.QueryPlanRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQueryPlanMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_PLANS = 0;
  private static final int METHODID_QUERY_PLANS_FOR_PROVIDER = 1;
  private static final int METHODID_QUERY_PLAN = 2;

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
        case METHODID_QUERY_PLANS:
          serviceImpl.queryPlans((sentinel.plan.v2.Querier.QueryPlansRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.plan.v2.Querier.QueryPlansResponse>) responseObserver);
          break;
        case METHODID_QUERY_PLANS_FOR_PROVIDER:
          serviceImpl.queryPlansForProvider((sentinel.plan.v2.Querier.QueryPlansForProviderRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.plan.v2.Querier.QueryPlansForProviderResponse>) responseObserver);
          break;
        case METHODID_QUERY_PLAN:
          serviceImpl.queryPlan((sentinel.plan.v2.Querier.QueryPlanRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.plan.v2.Querier.QueryPlanResponse>) responseObserver);
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
          getQueryPlansMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.plan.v2.Querier.QueryPlansRequest,
              sentinel.plan.v2.Querier.QueryPlansResponse>(
                service, METHODID_QUERY_PLANS)))
        .addMethod(
          getQueryPlansForProviderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.plan.v2.Querier.QueryPlansForProviderRequest,
              sentinel.plan.v2.Querier.QueryPlansForProviderResponse>(
                service, METHODID_QUERY_PLANS_FOR_PROVIDER)))
        .addMethod(
          getQueryPlanMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.plan.v2.Querier.QueryPlanRequest,
              sentinel.plan.v2.Querier.QueryPlanResponse>(
                service, METHODID_QUERY_PLAN)))
        .build();
  }

  private static abstract class QueryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sentinel.plan.v2.Querier.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("QueryService");
    }
  }

  private static final class QueryServiceFileDescriptorSupplier
      extends QueryServiceBaseDescriptorSupplier {
    QueryServiceFileDescriptorSupplier() {}
  }

  private static final class QueryServiceMethodDescriptorSupplier
      extends QueryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    QueryServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (QueryServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new QueryServiceFileDescriptorSupplier())
              .addMethod(getQueryPlansMethod())
              .addMethod(getQueryPlansForProviderMethod())
              .addMethod(getQueryPlanMethod())
              .build();
        }
      }
    }
    return result;
  }
}
