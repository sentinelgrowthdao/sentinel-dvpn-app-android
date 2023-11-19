package sentinel.subscription.v2;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.57.1)",
    comments = "Source: sentinel/subscription/v2/querier.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryServiceGrpc {

  private QueryServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "sentinel.subscription.v2.QueryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QuerySubscriptionsRequest,
      sentinel.subscription.v2.Querier.QuerySubscriptionsResponse> getQuerySubscriptionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QuerySubscriptions",
      requestType = sentinel.subscription.v2.Querier.QuerySubscriptionsRequest.class,
      responseType = sentinel.subscription.v2.Querier.QuerySubscriptionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QuerySubscriptionsRequest,
      sentinel.subscription.v2.Querier.QuerySubscriptionsResponse> getQuerySubscriptionsMethod() {
    io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QuerySubscriptionsRequest, sentinel.subscription.v2.Querier.QuerySubscriptionsResponse> getQuerySubscriptionsMethod;
    if ((getQuerySubscriptionsMethod = QueryServiceGrpc.getQuerySubscriptionsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQuerySubscriptionsMethod = QueryServiceGrpc.getQuerySubscriptionsMethod) == null) {
          QueryServiceGrpc.getQuerySubscriptionsMethod = getQuerySubscriptionsMethod =
              io.grpc.MethodDescriptor.<sentinel.subscription.v2.Querier.QuerySubscriptionsRequest, sentinel.subscription.v2.Querier.QuerySubscriptionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QuerySubscriptions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QuerySubscriptionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QuerySubscriptionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QuerySubscriptions"))
              .build();
        }
      }
    }
    return getQuerySubscriptionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountRequest,
      sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountResponse> getQuerySubscriptionsForAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QuerySubscriptionsForAccount",
      requestType = sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountRequest.class,
      responseType = sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountRequest,
      sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountResponse> getQuerySubscriptionsForAccountMethod() {
    io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountRequest, sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountResponse> getQuerySubscriptionsForAccountMethod;
    if ((getQuerySubscriptionsForAccountMethod = QueryServiceGrpc.getQuerySubscriptionsForAccountMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQuerySubscriptionsForAccountMethod = QueryServiceGrpc.getQuerySubscriptionsForAccountMethod) == null) {
          QueryServiceGrpc.getQuerySubscriptionsForAccountMethod = getQuerySubscriptionsForAccountMethod =
              io.grpc.MethodDescriptor.<sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountRequest, sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QuerySubscriptionsForAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QuerySubscriptionsForAccount"))
              .build();
        }
      }
    }
    return getQuerySubscriptionsForAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeRequest,
      sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeResponse> getQuerySubscriptionsForNodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QuerySubscriptionsForNode",
      requestType = sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeRequest.class,
      responseType = sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeRequest,
      sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeResponse> getQuerySubscriptionsForNodeMethod() {
    io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeRequest, sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeResponse> getQuerySubscriptionsForNodeMethod;
    if ((getQuerySubscriptionsForNodeMethod = QueryServiceGrpc.getQuerySubscriptionsForNodeMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQuerySubscriptionsForNodeMethod = QueryServiceGrpc.getQuerySubscriptionsForNodeMethod) == null) {
          QueryServiceGrpc.getQuerySubscriptionsForNodeMethod = getQuerySubscriptionsForNodeMethod =
              io.grpc.MethodDescriptor.<sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeRequest, sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QuerySubscriptionsForNode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QuerySubscriptionsForNode"))
              .build();
        }
      }
    }
    return getQuerySubscriptionsForNodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanRequest,
      sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanResponse> getQuerySubscriptionsForPlanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QuerySubscriptionsForPlan",
      requestType = sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanRequest.class,
      responseType = sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanRequest,
      sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanResponse> getQuerySubscriptionsForPlanMethod() {
    io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanRequest, sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanResponse> getQuerySubscriptionsForPlanMethod;
    if ((getQuerySubscriptionsForPlanMethod = QueryServiceGrpc.getQuerySubscriptionsForPlanMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQuerySubscriptionsForPlanMethod = QueryServiceGrpc.getQuerySubscriptionsForPlanMethod) == null) {
          QueryServiceGrpc.getQuerySubscriptionsForPlanMethod = getQuerySubscriptionsForPlanMethod =
              io.grpc.MethodDescriptor.<sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanRequest, sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QuerySubscriptionsForPlan"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QuerySubscriptionsForPlan"))
              .build();
        }
      }
    }
    return getQuerySubscriptionsForPlanMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QuerySubscriptionRequest,
      sentinel.subscription.v2.Querier.QuerySubscriptionResponse> getQuerySubscriptionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QuerySubscription",
      requestType = sentinel.subscription.v2.Querier.QuerySubscriptionRequest.class,
      responseType = sentinel.subscription.v2.Querier.QuerySubscriptionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QuerySubscriptionRequest,
      sentinel.subscription.v2.Querier.QuerySubscriptionResponse> getQuerySubscriptionMethod() {
    io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QuerySubscriptionRequest, sentinel.subscription.v2.Querier.QuerySubscriptionResponse> getQuerySubscriptionMethod;
    if ((getQuerySubscriptionMethod = QueryServiceGrpc.getQuerySubscriptionMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQuerySubscriptionMethod = QueryServiceGrpc.getQuerySubscriptionMethod) == null) {
          QueryServiceGrpc.getQuerySubscriptionMethod = getQuerySubscriptionMethod =
              io.grpc.MethodDescriptor.<sentinel.subscription.v2.Querier.QuerySubscriptionRequest, sentinel.subscription.v2.Querier.QuerySubscriptionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QuerySubscription"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QuerySubscriptionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QuerySubscriptionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QuerySubscription"))
              .build();
        }
      }
    }
    return getQuerySubscriptionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryAllocationsRequest,
      sentinel.subscription.v2.Querier.QueryAllocationsResponse> getQueryAllocationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryAllocations",
      requestType = sentinel.subscription.v2.Querier.QueryAllocationsRequest.class,
      responseType = sentinel.subscription.v2.Querier.QueryAllocationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryAllocationsRequest,
      sentinel.subscription.v2.Querier.QueryAllocationsResponse> getQueryAllocationsMethod() {
    io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryAllocationsRequest, sentinel.subscription.v2.Querier.QueryAllocationsResponse> getQueryAllocationsMethod;
    if ((getQueryAllocationsMethod = QueryServiceGrpc.getQueryAllocationsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryAllocationsMethod = QueryServiceGrpc.getQueryAllocationsMethod) == null) {
          QueryServiceGrpc.getQueryAllocationsMethod = getQueryAllocationsMethod =
              io.grpc.MethodDescriptor.<sentinel.subscription.v2.Querier.QueryAllocationsRequest, sentinel.subscription.v2.Querier.QueryAllocationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryAllocations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QueryAllocationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QueryAllocationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryAllocations"))
              .build();
        }
      }
    }
    return getQueryAllocationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryAllocationRequest,
      sentinel.subscription.v2.Querier.QueryAllocationResponse> getQueryAllocationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryAllocation",
      requestType = sentinel.subscription.v2.Querier.QueryAllocationRequest.class,
      responseType = sentinel.subscription.v2.Querier.QueryAllocationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryAllocationRequest,
      sentinel.subscription.v2.Querier.QueryAllocationResponse> getQueryAllocationMethod() {
    io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryAllocationRequest, sentinel.subscription.v2.Querier.QueryAllocationResponse> getQueryAllocationMethod;
    if ((getQueryAllocationMethod = QueryServiceGrpc.getQueryAllocationMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryAllocationMethod = QueryServiceGrpc.getQueryAllocationMethod) == null) {
          QueryServiceGrpc.getQueryAllocationMethod = getQueryAllocationMethod =
              io.grpc.MethodDescriptor.<sentinel.subscription.v2.Querier.QueryAllocationRequest, sentinel.subscription.v2.Querier.QueryAllocationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryAllocation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QueryAllocationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QueryAllocationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryAllocation"))
              .build();
        }
      }
    }
    return getQueryAllocationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryPayoutsRequest,
      sentinel.subscription.v2.Querier.QueryPayoutsResponse> getQueryPayoutsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryPayouts",
      requestType = sentinel.subscription.v2.Querier.QueryPayoutsRequest.class,
      responseType = sentinel.subscription.v2.Querier.QueryPayoutsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryPayoutsRequest,
      sentinel.subscription.v2.Querier.QueryPayoutsResponse> getQueryPayoutsMethod() {
    io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryPayoutsRequest, sentinel.subscription.v2.Querier.QueryPayoutsResponse> getQueryPayoutsMethod;
    if ((getQueryPayoutsMethod = QueryServiceGrpc.getQueryPayoutsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryPayoutsMethod = QueryServiceGrpc.getQueryPayoutsMethod) == null) {
          QueryServiceGrpc.getQueryPayoutsMethod = getQueryPayoutsMethod =
              io.grpc.MethodDescriptor.<sentinel.subscription.v2.Querier.QueryPayoutsRequest, sentinel.subscription.v2.Querier.QueryPayoutsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryPayouts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QueryPayoutsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QueryPayoutsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryPayouts"))
              .build();
        }
      }
    }
    return getQueryPayoutsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryPayoutsForAccountRequest,
      sentinel.subscription.v2.Querier.QueryPayoutsForAccountResponse> getQueryPayoutsForAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryPayoutsForAccount",
      requestType = sentinel.subscription.v2.Querier.QueryPayoutsForAccountRequest.class,
      responseType = sentinel.subscription.v2.Querier.QueryPayoutsForAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryPayoutsForAccountRequest,
      sentinel.subscription.v2.Querier.QueryPayoutsForAccountResponse> getQueryPayoutsForAccountMethod() {
    io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryPayoutsForAccountRequest, sentinel.subscription.v2.Querier.QueryPayoutsForAccountResponse> getQueryPayoutsForAccountMethod;
    if ((getQueryPayoutsForAccountMethod = QueryServiceGrpc.getQueryPayoutsForAccountMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryPayoutsForAccountMethod = QueryServiceGrpc.getQueryPayoutsForAccountMethod) == null) {
          QueryServiceGrpc.getQueryPayoutsForAccountMethod = getQueryPayoutsForAccountMethod =
              io.grpc.MethodDescriptor.<sentinel.subscription.v2.Querier.QueryPayoutsForAccountRequest, sentinel.subscription.v2.Querier.QueryPayoutsForAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryPayoutsForAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QueryPayoutsForAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QueryPayoutsForAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryPayoutsForAccount"))
              .build();
        }
      }
    }
    return getQueryPayoutsForAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryPayoutsForNodeRequest,
      sentinel.subscription.v2.Querier.QueryPayoutsForNodeResponse> getQueryPayoutsForNodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryPayoutsForNode",
      requestType = sentinel.subscription.v2.Querier.QueryPayoutsForNodeRequest.class,
      responseType = sentinel.subscription.v2.Querier.QueryPayoutsForNodeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryPayoutsForNodeRequest,
      sentinel.subscription.v2.Querier.QueryPayoutsForNodeResponse> getQueryPayoutsForNodeMethod() {
    io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryPayoutsForNodeRequest, sentinel.subscription.v2.Querier.QueryPayoutsForNodeResponse> getQueryPayoutsForNodeMethod;
    if ((getQueryPayoutsForNodeMethod = QueryServiceGrpc.getQueryPayoutsForNodeMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryPayoutsForNodeMethod = QueryServiceGrpc.getQueryPayoutsForNodeMethod) == null) {
          QueryServiceGrpc.getQueryPayoutsForNodeMethod = getQueryPayoutsForNodeMethod =
              io.grpc.MethodDescriptor.<sentinel.subscription.v2.Querier.QueryPayoutsForNodeRequest, sentinel.subscription.v2.Querier.QueryPayoutsForNodeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryPayoutsForNode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QueryPayoutsForNodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QueryPayoutsForNodeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryPayoutsForNode"))
              .build();
        }
      }
    }
    return getQueryPayoutsForNodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryPayoutRequest,
      sentinel.subscription.v2.Querier.QueryPayoutResponse> getQueryPayoutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryPayout",
      requestType = sentinel.subscription.v2.Querier.QueryPayoutRequest.class,
      responseType = sentinel.subscription.v2.Querier.QueryPayoutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryPayoutRequest,
      sentinel.subscription.v2.Querier.QueryPayoutResponse> getQueryPayoutMethod() {
    io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryPayoutRequest, sentinel.subscription.v2.Querier.QueryPayoutResponse> getQueryPayoutMethod;
    if ((getQueryPayoutMethod = QueryServiceGrpc.getQueryPayoutMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryPayoutMethod = QueryServiceGrpc.getQueryPayoutMethod) == null) {
          QueryServiceGrpc.getQueryPayoutMethod = getQueryPayoutMethod =
              io.grpc.MethodDescriptor.<sentinel.subscription.v2.Querier.QueryPayoutRequest, sentinel.subscription.v2.Querier.QueryPayoutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryPayout"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QueryPayoutRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QueryPayoutResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryPayout"))
              .build();
        }
      }
    }
    return getQueryPayoutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryParamsRequest,
      sentinel.subscription.v2.Querier.QueryParamsResponse> getQueryParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryParams",
      requestType = sentinel.subscription.v2.Querier.QueryParamsRequest.class,
      responseType = sentinel.subscription.v2.Querier.QueryParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryParamsRequest,
      sentinel.subscription.v2.Querier.QueryParamsResponse> getQueryParamsMethod() {
    io.grpc.MethodDescriptor<sentinel.subscription.v2.Querier.QueryParamsRequest, sentinel.subscription.v2.Querier.QueryParamsResponse> getQueryParamsMethod;
    if ((getQueryParamsMethod = QueryServiceGrpc.getQueryParamsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryParamsMethod = QueryServiceGrpc.getQueryParamsMethod) == null) {
          QueryServiceGrpc.getQueryParamsMethod = getQueryParamsMethod =
              io.grpc.MethodDescriptor.<sentinel.subscription.v2.Querier.QueryParamsRequest, sentinel.subscription.v2.Querier.QueryParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QueryParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sentinel.subscription.v2.Querier.QueryParamsResponse.getDefaultInstance()))
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
    default void querySubscriptions(sentinel.subscription.v2.Querier.QuerySubscriptionsRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QuerySubscriptionsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQuerySubscriptionsMethod(), responseObserver);
    }

    /**
     */
    default void querySubscriptionsForAccount(sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQuerySubscriptionsForAccountMethod(), responseObserver);
    }

    /**
     */
    default void querySubscriptionsForNode(sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQuerySubscriptionsForNodeMethod(), responseObserver);
    }

    /**
     */
    default void querySubscriptionsForPlan(sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQuerySubscriptionsForPlanMethod(), responseObserver);
    }

    /**
     */
    default void querySubscription(sentinel.subscription.v2.Querier.QuerySubscriptionRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QuerySubscriptionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQuerySubscriptionMethod(), responseObserver);
    }

    /**
     */
    default void queryAllocations(sentinel.subscription.v2.Querier.QueryAllocationsRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryAllocationsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQueryAllocationsMethod(), responseObserver);
    }

    /**
     */
    default void queryAllocation(sentinel.subscription.v2.Querier.QueryAllocationRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryAllocationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQueryAllocationMethod(), responseObserver);
    }

    /**
     */
    default void queryPayouts(sentinel.subscription.v2.Querier.QueryPayoutsRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryPayoutsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQueryPayoutsMethod(), responseObserver);
    }

    /**
     */
    default void queryPayoutsForAccount(sentinel.subscription.v2.Querier.QueryPayoutsForAccountRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryPayoutsForAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQueryPayoutsForAccountMethod(), responseObserver);
    }

    /**
     */
    default void queryPayoutsForNode(sentinel.subscription.v2.Querier.QueryPayoutsForNodeRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryPayoutsForNodeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQueryPayoutsForNodeMethod(), responseObserver);
    }

    /**
     */
    default void queryPayout(sentinel.subscription.v2.Querier.QueryPayoutRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryPayoutResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQueryPayoutMethod(), responseObserver);
    }

    /**
     */
    default void queryParams(sentinel.subscription.v2.Querier.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryParamsResponse> responseObserver) {
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
    public void querySubscriptions(sentinel.subscription.v2.Querier.QuerySubscriptionsRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QuerySubscriptionsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQuerySubscriptionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void querySubscriptionsForAccount(sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQuerySubscriptionsForAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void querySubscriptionsForNode(sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQuerySubscriptionsForNodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void querySubscriptionsForPlan(sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQuerySubscriptionsForPlanMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void querySubscription(sentinel.subscription.v2.Querier.QuerySubscriptionRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QuerySubscriptionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQuerySubscriptionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryAllocations(sentinel.subscription.v2.Querier.QueryAllocationsRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryAllocationsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQueryAllocationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryAllocation(sentinel.subscription.v2.Querier.QueryAllocationRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryAllocationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQueryAllocationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryPayouts(sentinel.subscription.v2.Querier.QueryPayoutsRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryPayoutsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQueryPayoutsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryPayoutsForAccount(sentinel.subscription.v2.Querier.QueryPayoutsForAccountRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryPayoutsForAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQueryPayoutsForAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryPayoutsForNode(sentinel.subscription.v2.Querier.QueryPayoutsForNodeRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryPayoutsForNodeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQueryPayoutsForNodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryPayout(sentinel.subscription.v2.Querier.QueryPayoutRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryPayoutResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQueryPayoutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryParams(sentinel.subscription.v2.Querier.QueryParamsRequest request,
        io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryParamsResponse> responseObserver) {
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
    public sentinel.subscription.v2.Querier.QuerySubscriptionsResponse querySubscriptions(sentinel.subscription.v2.Querier.QuerySubscriptionsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQuerySubscriptionsMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountResponse querySubscriptionsForAccount(sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQuerySubscriptionsForAccountMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeResponse querySubscriptionsForNode(sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQuerySubscriptionsForNodeMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanResponse querySubscriptionsForPlan(sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQuerySubscriptionsForPlanMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.subscription.v2.Querier.QuerySubscriptionResponse querySubscription(sentinel.subscription.v2.Querier.QuerySubscriptionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQuerySubscriptionMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.subscription.v2.Querier.QueryAllocationsResponse queryAllocations(sentinel.subscription.v2.Querier.QueryAllocationsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQueryAllocationsMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.subscription.v2.Querier.QueryAllocationResponse queryAllocation(sentinel.subscription.v2.Querier.QueryAllocationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQueryAllocationMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.subscription.v2.Querier.QueryPayoutsResponse queryPayouts(sentinel.subscription.v2.Querier.QueryPayoutsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQueryPayoutsMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.subscription.v2.Querier.QueryPayoutsForAccountResponse queryPayoutsForAccount(sentinel.subscription.v2.Querier.QueryPayoutsForAccountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQueryPayoutsForAccountMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.subscription.v2.Querier.QueryPayoutsForNodeResponse queryPayoutsForNode(sentinel.subscription.v2.Querier.QueryPayoutsForNodeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQueryPayoutsForNodeMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.subscription.v2.Querier.QueryPayoutResponse queryPayout(sentinel.subscription.v2.Querier.QueryPayoutRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQueryPayoutMethod(), getCallOptions(), request);
    }

    /**
     */
    public sentinel.subscription.v2.Querier.QueryParamsResponse queryParams(sentinel.subscription.v2.Querier.QueryParamsRequest request) {
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
    public com.google.common.util.concurrent.ListenableFuture<sentinel.subscription.v2.Querier.QuerySubscriptionsResponse> querySubscriptions(
        sentinel.subscription.v2.Querier.QuerySubscriptionsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQuerySubscriptionsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountResponse> querySubscriptionsForAccount(
        sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQuerySubscriptionsForAccountMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeResponse> querySubscriptionsForNode(
        sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQuerySubscriptionsForNodeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanResponse> querySubscriptionsForPlan(
        sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQuerySubscriptionsForPlanMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.subscription.v2.Querier.QuerySubscriptionResponse> querySubscription(
        sentinel.subscription.v2.Querier.QuerySubscriptionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQuerySubscriptionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.subscription.v2.Querier.QueryAllocationsResponse> queryAllocations(
        sentinel.subscription.v2.Querier.QueryAllocationsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQueryAllocationsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.subscription.v2.Querier.QueryAllocationResponse> queryAllocation(
        sentinel.subscription.v2.Querier.QueryAllocationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQueryAllocationMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.subscription.v2.Querier.QueryPayoutsResponse> queryPayouts(
        sentinel.subscription.v2.Querier.QueryPayoutsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQueryPayoutsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.subscription.v2.Querier.QueryPayoutsForAccountResponse> queryPayoutsForAccount(
        sentinel.subscription.v2.Querier.QueryPayoutsForAccountRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQueryPayoutsForAccountMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.subscription.v2.Querier.QueryPayoutsForNodeResponse> queryPayoutsForNode(
        sentinel.subscription.v2.Querier.QueryPayoutsForNodeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQueryPayoutsForNodeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.subscription.v2.Querier.QueryPayoutResponse> queryPayout(
        sentinel.subscription.v2.Querier.QueryPayoutRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQueryPayoutMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sentinel.subscription.v2.Querier.QueryParamsResponse> queryParams(
        sentinel.subscription.v2.Querier.QueryParamsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQueryParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_SUBSCRIPTIONS = 0;
  private static final int METHODID_QUERY_SUBSCRIPTIONS_FOR_ACCOUNT = 1;
  private static final int METHODID_QUERY_SUBSCRIPTIONS_FOR_NODE = 2;
  private static final int METHODID_QUERY_SUBSCRIPTIONS_FOR_PLAN = 3;
  private static final int METHODID_QUERY_SUBSCRIPTION = 4;
  private static final int METHODID_QUERY_ALLOCATIONS = 5;
  private static final int METHODID_QUERY_ALLOCATION = 6;
  private static final int METHODID_QUERY_PAYOUTS = 7;
  private static final int METHODID_QUERY_PAYOUTS_FOR_ACCOUNT = 8;
  private static final int METHODID_QUERY_PAYOUTS_FOR_NODE = 9;
  private static final int METHODID_QUERY_PAYOUT = 10;
  private static final int METHODID_QUERY_PARAMS = 11;

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
        case METHODID_QUERY_SUBSCRIPTIONS:
          serviceImpl.querySubscriptions((sentinel.subscription.v2.Querier.QuerySubscriptionsRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QuerySubscriptionsResponse>) responseObserver);
          break;
        case METHODID_QUERY_SUBSCRIPTIONS_FOR_ACCOUNT:
          serviceImpl.querySubscriptionsForAccount((sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountResponse>) responseObserver);
          break;
        case METHODID_QUERY_SUBSCRIPTIONS_FOR_NODE:
          serviceImpl.querySubscriptionsForNode((sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeResponse>) responseObserver);
          break;
        case METHODID_QUERY_SUBSCRIPTIONS_FOR_PLAN:
          serviceImpl.querySubscriptionsForPlan((sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanResponse>) responseObserver);
          break;
        case METHODID_QUERY_SUBSCRIPTION:
          serviceImpl.querySubscription((sentinel.subscription.v2.Querier.QuerySubscriptionRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QuerySubscriptionResponse>) responseObserver);
          break;
        case METHODID_QUERY_ALLOCATIONS:
          serviceImpl.queryAllocations((sentinel.subscription.v2.Querier.QueryAllocationsRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryAllocationsResponse>) responseObserver);
          break;
        case METHODID_QUERY_ALLOCATION:
          serviceImpl.queryAllocation((sentinel.subscription.v2.Querier.QueryAllocationRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryAllocationResponse>) responseObserver);
          break;
        case METHODID_QUERY_PAYOUTS:
          serviceImpl.queryPayouts((sentinel.subscription.v2.Querier.QueryPayoutsRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryPayoutsResponse>) responseObserver);
          break;
        case METHODID_QUERY_PAYOUTS_FOR_ACCOUNT:
          serviceImpl.queryPayoutsForAccount((sentinel.subscription.v2.Querier.QueryPayoutsForAccountRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryPayoutsForAccountResponse>) responseObserver);
          break;
        case METHODID_QUERY_PAYOUTS_FOR_NODE:
          serviceImpl.queryPayoutsForNode((sentinel.subscription.v2.Querier.QueryPayoutsForNodeRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryPayoutsForNodeResponse>) responseObserver);
          break;
        case METHODID_QUERY_PAYOUT:
          serviceImpl.queryPayout((sentinel.subscription.v2.Querier.QueryPayoutRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryPayoutResponse>) responseObserver);
          break;
        case METHODID_QUERY_PARAMS:
          serviceImpl.queryParams((sentinel.subscription.v2.Querier.QueryParamsRequest) request,
              (io.grpc.stub.StreamObserver<sentinel.subscription.v2.Querier.QueryParamsResponse>) responseObserver);
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
          getQuerySubscriptionsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.subscription.v2.Querier.QuerySubscriptionsRequest,
              sentinel.subscription.v2.Querier.QuerySubscriptionsResponse>(
                service, METHODID_QUERY_SUBSCRIPTIONS)))
        .addMethod(
          getQuerySubscriptionsForAccountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountRequest,
              sentinel.subscription.v2.Querier.QuerySubscriptionsForAccountResponse>(
                service, METHODID_QUERY_SUBSCRIPTIONS_FOR_ACCOUNT)))
        .addMethod(
          getQuerySubscriptionsForNodeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeRequest,
              sentinel.subscription.v2.Querier.QuerySubscriptionsForNodeResponse>(
                service, METHODID_QUERY_SUBSCRIPTIONS_FOR_NODE)))
        .addMethod(
          getQuerySubscriptionsForPlanMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanRequest,
              sentinel.subscription.v2.Querier.QuerySubscriptionsForPlanResponse>(
                service, METHODID_QUERY_SUBSCRIPTIONS_FOR_PLAN)))
        .addMethod(
          getQuerySubscriptionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.subscription.v2.Querier.QuerySubscriptionRequest,
              sentinel.subscription.v2.Querier.QuerySubscriptionResponse>(
                service, METHODID_QUERY_SUBSCRIPTION)))
        .addMethod(
          getQueryAllocationsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.subscription.v2.Querier.QueryAllocationsRequest,
              sentinel.subscription.v2.Querier.QueryAllocationsResponse>(
                service, METHODID_QUERY_ALLOCATIONS)))
        .addMethod(
          getQueryAllocationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.subscription.v2.Querier.QueryAllocationRequest,
              sentinel.subscription.v2.Querier.QueryAllocationResponse>(
                service, METHODID_QUERY_ALLOCATION)))
        .addMethod(
          getQueryPayoutsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.subscription.v2.Querier.QueryPayoutsRequest,
              sentinel.subscription.v2.Querier.QueryPayoutsResponse>(
                service, METHODID_QUERY_PAYOUTS)))
        .addMethod(
          getQueryPayoutsForAccountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.subscription.v2.Querier.QueryPayoutsForAccountRequest,
              sentinel.subscription.v2.Querier.QueryPayoutsForAccountResponse>(
                service, METHODID_QUERY_PAYOUTS_FOR_ACCOUNT)))
        .addMethod(
          getQueryPayoutsForNodeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.subscription.v2.Querier.QueryPayoutsForNodeRequest,
              sentinel.subscription.v2.Querier.QueryPayoutsForNodeResponse>(
                service, METHODID_QUERY_PAYOUTS_FOR_NODE)))
        .addMethod(
          getQueryPayoutMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.subscription.v2.Querier.QueryPayoutRequest,
              sentinel.subscription.v2.Querier.QueryPayoutResponse>(
                service, METHODID_QUERY_PAYOUT)))
        .addMethod(
          getQueryParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sentinel.subscription.v2.Querier.QueryParamsRequest,
              sentinel.subscription.v2.Querier.QueryParamsResponse>(
                service, METHODID_QUERY_PARAMS)))
        .build();
  }

  private static abstract class QueryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sentinel.subscription.v2.Querier.getDescriptor();
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
              .addMethod(getQuerySubscriptionsMethod())
              .addMethod(getQuerySubscriptionsForAccountMethod())
              .addMethod(getQuerySubscriptionsForNodeMethod())
              .addMethod(getQuerySubscriptionsForPlanMethod())
              .addMethod(getQuerySubscriptionMethod())
              .addMethod(getQueryAllocationsMethod())
              .addMethod(getQueryAllocationMethod())
              .addMethod(getQueryPayoutsMethod())
              .addMethod(getQueryPayoutsForAccountMethod())
              .addMethod(getQueryPayoutsForNodeMethod())
              .addMethod(getQueryPayoutMethod())
              .addMethod(getQueryParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
