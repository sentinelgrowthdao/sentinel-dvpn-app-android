package sentinel.session.v2;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.57.1)",
    comments = "Source: sentinel/session/v2/querier.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryServiceGrpc {

  private QueryServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "sentinel.session.v2.QueryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QuerySessionsRequest,
      sentinel.session.v2.Querier.QuerySessionsResponse> getQuerySessionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QuerySessions",
      requestType = sentinel.session.v2.Querier.QuerySessionsRequest.class,
      responseType = sentinel.session.v2.Querier.QuerySessionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QuerySessionsRequest,
      sentinel.session.v2.Querier.QuerySessionsResponse> getQuerySessionsMethod() {
    io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QuerySessionsRequest, sentinel.session.v2.Querier.QuerySessionsResponse> getQuerySessionsMethod;
    if ((getQuerySessionsMethod = QueryServiceGrpc.getQuerySessionsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQuerySessionsMethod = QueryServiceGrpc.getQuerySessionsMethod) == null) {
          QueryServiceGrpc.getQuerySessionsMethod = getQuerySessionsMethod =
              io.grpc.MethodDescriptor.<sentinel.session.v2.Querier.QuerySessionsRequest, sentinel.session.v2.Querier.QuerySessionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QuerySessions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v2.Querier.QuerySessionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v2.Querier.QuerySessionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QuerySessions"))
              .build();
        }
      }
    }
    return getQuerySessionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QuerySessionsForAccountRequest,
      sentinel.session.v2.Querier.QuerySessionsForAccountResponse> getQuerySessionsForAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QuerySessionsForAccount",
      requestType = sentinel.session.v2.Querier.QuerySessionsForAccountRequest.class,
      responseType = sentinel.session.v2.Querier.QuerySessionsForAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QuerySessionsForAccountRequest,
      sentinel.session.v2.Querier.QuerySessionsForAccountResponse> getQuerySessionsForAccountMethod() {
    io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QuerySessionsForAccountRequest, sentinel.session.v2.Querier.QuerySessionsForAccountResponse> getQuerySessionsForAccountMethod;
    if ((getQuerySessionsForAccountMethod = QueryServiceGrpc.getQuerySessionsForAccountMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQuerySessionsForAccountMethod = QueryServiceGrpc.getQuerySessionsForAccountMethod) == null) {
          QueryServiceGrpc.getQuerySessionsForAccountMethod = getQuerySessionsForAccountMethod =
              io.grpc.MethodDescriptor.<sentinel.session.v2.Querier.QuerySessionsForAccountRequest, sentinel.session.v2.Querier.QuerySessionsForAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QuerySessionsForAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v2.Querier.QuerySessionsForAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v2.Querier.QuerySessionsForAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QuerySessionsForAccount"))
              .build();
        }
      }
    }
    return getQuerySessionsForAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QuerySessionsForNodeRequest,
      sentinel.session.v2.Querier.QuerySessionsForNodeResponse> getQuerySessionsForNodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QuerySessionsForNode",
      requestType = sentinel.session.v2.Querier.QuerySessionsForNodeRequest.class,
      responseType = sentinel.session.v2.Querier.QuerySessionsForNodeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QuerySessionsForNodeRequest,
      sentinel.session.v2.Querier.QuerySessionsForNodeResponse> getQuerySessionsForNodeMethod() {
    io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QuerySessionsForNodeRequest, sentinel.session.v2.Querier.QuerySessionsForNodeResponse> getQuerySessionsForNodeMethod;
    if ((getQuerySessionsForNodeMethod = QueryServiceGrpc.getQuerySessionsForNodeMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQuerySessionsForNodeMethod = QueryServiceGrpc.getQuerySessionsForNodeMethod) == null) {
          QueryServiceGrpc.getQuerySessionsForNodeMethod = getQuerySessionsForNodeMethod =
              io.grpc.MethodDescriptor.<sentinel.session.v2.Querier.QuerySessionsForNodeRequest, sentinel.session.v2.Querier.QuerySessionsForNodeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QuerySessionsForNode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v2.Querier.QuerySessionsForNodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v2.Querier.QuerySessionsForNodeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QuerySessionsForNode"))
              .build();
        }
      }
    }
    return getQuerySessionsForNodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QuerySessionsForSubscriptionRequest,
      sentinel.session.v2.Querier.QuerySessionsForSubscriptionResponse> getQuerySessionsForSubscriptionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QuerySessionsForSubscription",
      requestType = sentinel.session.v2.Querier.QuerySessionsForSubscriptionRequest.class,
      responseType = sentinel.session.v2.Querier.QuerySessionsForSubscriptionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QuerySessionsForSubscriptionRequest,
      sentinel.session.v2.Querier.QuerySessionsForSubscriptionResponse> getQuerySessionsForSubscriptionMethod() {
    io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QuerySessionsForSubscriptionRequest, sentinel.session.v2.Querier.QuerySessionsForSubscriptionResponse> getQuerySessionsForSubscriptionMethod;
    if ((getQuerySessionsForSubscriptionMethod = QueryServiceGrpc.getQuerySessionsForSubscriptionMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQuerySessionsForSubscriptionMethod = QueryServiceGrpc.getQuerySessionsForSubscriptionMethod) == null) {
          QueryServiceGrpc.getQuerySessionsForSubscriptionMethod = getQuerySessionsForSubscriptionMethod =
              io.grpc.MethodDescriptor.<sentinel.session.v2.Querier.QuerySessionsForSubscriptionRequest, sentinel.session.v2.Querier.QuerySessionsForSubscriptionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QuerySessionsForSubscription"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v2.Querier.QuerySessionsForSubscriptionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v2.Querier.QuerySessionsForSubscriptionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QuerySessionsForSubscription"))
              .build();
        }
      }
    }
    return getQuerySessionsForSubscriptionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QuerySessionsForAllocationRequest,
      sentinel.session.v2.Querier.QuerySessionsForAllocationResponse> getQuerySessionsForAllocationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QuerySessionsForAllocation",
      requestType = sentinel.session.v2.Querier.QuerySessionsForAllocationRequest.class,
      responseType = sentinel.session.v2.Querier.QuerySessionsForAllocationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QuerySessionsForAllocationRequest,
      sentinel.session.v2.Querier.QuerySessionsForAllocationResponse> getQuerySessionsForAllocationMethod() {
    io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QuerySessionsForAllocationRequest, sentinel.session.v2.Querier.QuerySessionsForAllocationResponse> getQuerySessionsForAllocationMethod;
    if ((getQuerySessionsForAllocationMethod = QueryServiceGrpc.getQuerySessionsForAllocationMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQuerySessionsForAllocationMethod = QueryServiceGrpc.getQuerySessionsForAllocationMethod) == null) {
          QueryServiceGrpc.getQuerySessionsForAllocationMethod = getQuerySessionsForAllocationMethod =
              io.grpc.MethodDescriptor.<sentinel.session.v2.Querier.QuerySessionsForAllocationRequest, sentinel.session.v2.Querier.QuerySessionsForAllocationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QuerySessionsForAllocation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v2.Querier.QuerySessionsForAllocationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v2.Querier.QuerySessionsForAllocationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QuerySessionsForAllocation"))
              .build();
        }
      }
    }
    return getQuerySessionsForAllocationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QuerySessionRequest,
      sentinel.session.v2.Querier.QuerySessionResponse> getQuerySessionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QuerySession",
      requestType = sentinel.session.v2.Querier.QuerySessionRequest.class,
      responseType = sentinel.session.v2.Querier.QuerySessionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QuerySessionRequest,
      sentinel.session.v2.Querier.QuerySessionResponse> getQuerySessionMethod() {
    io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QuerySessionRequest, sentinel.session.v2.Querier.QuerySessionResponse> getQuerySessionMethod;
    if ((getQuerySessionMethod = QueryServiceGrpc.getQuerySessionMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQuerySessionMethod = QueryServiceGrpc.getQuerySessionMethod) == null) {
          QueryServiceGrpc.getQuerySessionMethod = getQuerySessionMethod =
              io.grpc.MethodDescriptor.<sentinel.session.v2.Querier.QuerySessionRequest, sentinel.session.v2.Querier.QuerySessionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QuerySession"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v2.Querier.QuerySessionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v2.Querier.QuerySessionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QuerySession"))
              .build();
        }
      }
    }
    return getQuerySessionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QueryParamsRequest,
      sentinel.session.v2.Querier.QueryParamsResponse> getQueryParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryParams",
      requestType = sentinel.session.v2.Querier.QueryParamsRequest.class,
      responseType = sentinel.session.v2.Querier.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QueryParamsRequest,
      sentinel.session.v2.Querier.QueryParamsResponse> getQueryParamsMethod() {
    io.grpc.MethodDescriptor<sentinel.session.v2.Querier.QueryParamsRequest, sentinel.session.v2.Querier.QueryParamsResponse> getQueryParamsMethod;
    if ((getQueryParamsMethod = QueryServiceGrpc.getQueryParamsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryParamsMethod = QueryServiceGrpc.getQueryParamsMethod) == null) {
          QueryServiceGrpc.getQueryParamsMethod = getQueryParamsMethod =
              io.grpc.MethodDescriptor.<sentinel.session.v2.Querier.QueryParamsRequest, sentinel.session.v2.Querier.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v2.Querier.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.session.v2.Querier.QueryParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryParams"))
              .build();
        }
      }
    }
    return getQueryParamsMethod;
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
    default void querySessions(sentinel.session.v2.Querier.QuerySessionsRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QuerySessionsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQuerySessionsMethod(), responseObserver);
    }

    /**
     */
    default void querySessionsForAccount(sentinel.session.v2.Querier.QuerySessionsForAccountRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QuerySessionsForAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQuerySessionsForAccountMethod(), responseObserver);
    }

    /**
     */
    default void querySessionsForNode(sentinel.session.v2.Querier.QuerySessionsForNodeRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QuerySessionsForNodeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQuerySessionsForNodeMethod(), responseObserver);
    }

    /**
     */
    default void querySessionsForSubscription(sentinel.session.v2.Querier.QuerySessionsForSubscriptionRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QuerySessionsForSubscriptionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQuerySessionsForSubscriptionMethod(), responseObserver);
    }

    /**
     */
    default void querySessionsForAllocation(sentinel.session.v2.Querier.QuerySessionsForAllocationRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QuerySessionsForAllocationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQuerySessionsForAllocationMethod(), responseObserver);
    }

    /**
     */
    default void querySession(sentinel.session.v2.Querier.QuerySessionRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QuerySessionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQuerySessionMethod(), responseObserver);
    }

    /**
     */
    default void queryParams(sentinel.session.v2.Querier.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQueryParamsMethod(), responseObserver);
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
    public void querySessions(sentinel.session.v2.Querier.QuerySessionsRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QuerySessionsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQuerySessionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void querySessionsForAccount(sentinel.session.v2.Querier.QuerySessionsForAccountRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QuerySessionsForAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQuerySessionsForAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void querySessionsForNode(sentinel.session.v2.Querier.QuerySessionsForNodeRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QuerySessionsForNodeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQuerySessionsForNodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void querySessionsForSubscription(sentinel.session.v2.Querier.QuerySessionsForSubscriptionRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QuerySessionsForSubscriptionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQuerySessionsForSubscriptionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void querySessionsForAllocation(sentinel.session.v2.Querier.QuerySessionsForAllocationRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QuerySessionsForAllocationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQuerySessionsForAllocationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void querySession(sentinel.session.v2.Querier.QuerySessionRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QuerySessionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQuerySessionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryParams(sentinel.session.v2.Querier.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QueryParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQueryParamsMethod(), getCallOptions()), request, responseObserver);
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
    public sentinel.session.v2.Querier.QuerySessionsResponse querySessions(sentinel.session.v2.Querier.QuerySessionsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQuerySessionsMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.session.v2.Querier.QuerySessionsForAccountResponse querySessionsForAccount(sentinel.session.v2.Querier.QuerySessionsForAccountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQuerySessionsForAccountMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.session.v2.Querier.QuerySessionsForNodeResponse querySessionsForNode(sentinel.session.v2.Querier.QuerySessionsForNodeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQuerySessionsForNodeMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.session.v2.Querier.QuerySessionsForSubscriptionResponse querySessionsForSubscription(sentinel.session.v2.Querier.QuerySessionsForSubscriptionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQuerySessionsForSubscriptionMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.session.v2.Querier.QuerySessionsForAllocationResponse querySessionsForAllocation(sentinel.session.v2.Querier.QuerySessionsForAllocationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQuerySessionsForAllocationMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.session.v2.Querier.QuerySessionResponse querySession(sentinel.session.v2.Querier.QuerySessionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQuerySessionMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.session.v2.Querier.QueryParamsResponse queryParams(sentinel.session.v2.Querier.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQueryParamsMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<sentinel.session.v2.Querier.QuerySessionsResponse> querySessions(
        sentinel.session.v2.Querier.QuerySessionsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQuerySessionsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.session.v2.Querier.QuerySessionsForAccountResponse> querySessionsForAccount(
        sentinel.session.v2.Querier.QuerySessionsForAccountRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQuerySessionsForAccountMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.session.v2.Querier.QuerySessionsForNodeResponse> querySessionsForNode(
        sentinel.session.v2.Querier.QuerySessionsForNodeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQuerySessionsForNodeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.session.v2.Querier.QuerySessionsForSubscriptionResponse> querySessionsForSubscription(
        sentinel.session.v2.Querier.QuerySessionsForSubscriptionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQuerySessionsForSubscriptionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.session.v2.Querier.QuerySessionsForAllocationResponse> querySessionsForAllocation(
        sentinel.session.v2.Querier.QuerySessionsForAllocationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQuerySessionsForAllocationMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.session.v2.Querier.QuerySessionResponse> querySession(
        sentinel.session.v2.Querier.QuerySessionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQuerySessionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.session.v2.Querier.QueryParamsResponse> queryParams(
        sentinel.session.v2.Querier.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQueryParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_SESSIONS = 0;
  private static final int METHODID_QUERY_SESSIONS_FOR_ACCOUNT = 1;
  private static final int METHODID_QUERY_SESSIONS_FOR_NODE = 2;
  private static final int METHODID_QUERY_SESSIONS_FOR_SUBSCRIPTION = 3;
  private static final int METHODID_QUERY_SESSIONS_FOR_ALLOCATION = 4;
  private static final int METHODID_QUERY_SESSION = 5;
  private static final int METHODID_QUERY_PARAMS = 6;

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
        case METHODID_QUERY_SESSIONS:
          serviceImpl.querySessions((sentinel.session.v2.Querier.QuerySessionsRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QuerySessionsResponse>) responseObserver);
          break;
        case METHODID_QUERY_SESSIONS_FOR_ACCOUNT:
          serviceImpl.querySessionsForAccount((sentinel.session.v2.Querier.QuerySessionsForAccountRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QuerySessionsForAccountResponse>) responseObserver);
          break;
        case METHODID_QUERY_SESSIONS_FOR_NODE:
          serviceImpl.querySessionsForNode((sentinel.session.v2.Querier.QuerySessionsForNodeRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QuerySessionsForNodeResponse>) responseObserver);
          break;
        case METHODID_QUERY_SESSIONS_FOR_SUBSCRIPTION:
          serviceImpl.querySessionsForSubscription((sentinel.session.v2.Querier.QuerySessionsForSubscriptionRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QuerySessionsForSubscriptionResponse>) responseObserver);
          break;
        case METHODID_QUERY_SESSIONS_FOR_ALLOCATION:
          serviceImpl.querySessionsForAllocation((sentinel.session.v2.Querier.QuerySessionsForAllocationRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QuerySessionsForAllocationResponse>) responseObserver);
          break;
        case METHODID_QUERY_SESSION:
          serviceImpl.querySession((sentinel.session.v2.Querier.QuerySessionRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QuerySessionResponse>) responseObserver);
          break;
        case METHODID_QUERY_PARAMS:
          serviceImpl.queryParams((sentinel.session.v2.Querier.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.session.v2.Querier.QueryParamsResponse>) responseObserver);
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
          getQuerySessionsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.session.v2.Querier.QuerySessionsRequest,
              sentinel.session.v2.Querier.QuerySessionsResponse>(
                service, METHODID_QUERY_SESSIONS)))
        .addMethod(
          getQuerySessionsForAccountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.session.v2.Querier.QuerySessionsForAccountRequest,
              sentinel.session.v2.Querier.QuerySessionsForAccountResponse>(
                service, METHODID_QUERY_SESSIONS_FOR_ACCOUNT)))
        .addMethod(
          getQuerySessionsForNodeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.session.v2.Querier.QuerySessionsForNodeRequest,
              sentinel.session.v2.Querier.QuerySessionsForNodeResponse>(
                service, METHODID_QUERY_SESSIONS_FOR_NODE)))
        .addMethod(
          getQuerySessionsForSubscriptionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.session.v2.Querier.QuerySessionsForSubscriptionRequest,
              sentinel.session.v2.Querier.QuerySessionsForSubscriptionResponse>(
                service, METHODID_QUERY_SESSIONS_FOR_SUBSCRIPTION)))
        .addMethod(
          getQuerySessionsForAllocationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.session.v2.Querier.QuerySessionsForAllocationRequest,
              sentinel.session.v2.Querier.QuerySessionsForAllocationResponse>(
                service, METHODID_QUERY_SESSIONS_FOR_ALLOCATION)))
        .addMethod(
          getQuerySessionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.session.v2.Querier.QuerySessionRequest,
              sentinel.session.v2.Querier.QuerySessionResponse>(
                service, METHODID_QUERY_SESSION)))
        .addMethod(
          getQueryParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.session.v2.Querier.QueryParamsRequest,
              sentinel.session.v2.Querier.QueryParamsResponse>(
                service, METHODID_QUERY_PARAMS)))
        .build();
  }

  private static abstract class QueryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sentinel.session.v2.Querier.getDescriptor();
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
    private final java.lang.String methodName;

    QueryServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
              .addMethod(getQuerySessionsMethod())
              .addMethod(getQuerySessionsForAccountMethod())
              .addMethod(getQuerySessionsForNodeMethod())
              .addMethod(getQuerySessionsForSubscriptionMethod())
              .addMethod(getQuerySessionsForAllocationMethod())
              .addMethod(getQuerySessionMethod())
              .addMethod(getQueryParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
