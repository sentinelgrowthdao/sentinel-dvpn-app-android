# Retrofit (https://github.com/square/retrofit/issues/3751#issuecomment-1192043644)
# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response
# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# Keep SerializedName
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}
-keep,allowobfuscation @interface com.google.gson.annotations.SerializedName

# Application
-keep class com.v2ray.ang.dto.** { *; }
-keep class co.uk.basedapps.ui_server.server.models.** { *; }
-keep class co.sentinel.cosmos.network.station.** { *; }
-keep class io.netty.** { *; }
-keep class kotlin.coroutines.intrinsics.IntrinsicsKt

# Proto
-keep class cosmos.** { *; }
-keep class sentinel.** { *; }
-keep class com.google.protobuf.** { *; }

# Sqlcipher
-keep class net.sqlcipher.** { *; }
-keep class net.sqlcipher.database.* { *; }

# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-dontwarn com.aayushatharva.brotli4j.Brotli4jLoader
-dontwarn com.aayushatharva.brotli4j.decoder.DecoderJNI$Status
-dontwarn com.aayushatharva.brotli4j.decoder.DecoderJNI$Wrapper
-dontwarn com.aayushatharva.brotli4j.encoder.BrotliEncoderChannel
-dontwarn com.aayushatharva.brotli4j.encoder.Encoder$Mode
-dontwarn com.aayushatharva.brotli4j.encoder.Encoder$Parameters
-dontwarn com.github.luben.zstd.Zstd
-dontwarn com.google.protobuf.nano.CodedOutputByteBufferNano
-dontwarn com.google.protobuf.nano.MessageNano
-dontwarn com.jcraft.jzlib.Deflater
-dontwarn com.jcraft.jzlib.Inflater
-dontwarn com.jcraft.jzlib.JZlib$WrapperType
-dontwarn com.jcraft.jzlib.JZlib
-dontwarn com.ning.compress.BufferRecycler
-dontwarn com.ning.compress.lzf.ChunkDecoder
-dontwarn com.ning.compress.lzf.ChunkEncoder
-dontwarn com.ning.compress.lzf.LZFChunk
-dontwarn com.ning.compress.lzf.LZFEncoder
-dontwarn com.ning.compress.lzf.util.ChunkDecoderFactory
-dontwarn com.ning.compress.lzf.util.ChunkEncoderFactory
-dontwarn com.oracle.svm.core.annotate.Alias
-dontwarn com.oracle.svm.core.annotate.InjectAccessors
-dontwarn com.oracle.svm.core.annotate.RecomputeFieldValue$Kind
-dontwarn com.oracle.svm.core.annotate.RecomputeFieldValue
-dontwarn com.oracle.svm.core.annotate.TargetClass
-dontwarn io.netty.internal.tcnative.AsyncSSLPrivateKeyMethod
-dontwarn io.netty.internal.tcnative.AsyncTask
-dontwarn io.netty.internal.tcnative.Buffer
-dontwarn io.netty.internal.tcnative.CertificateCallback
-dontwarn io.netty.internal.tcnative.CertificateCompressionAlgo
-dontwarn io.netty.internal.tcnative.CertificateVerifier
-dontwarn io.netty.internal.tcnative.Library
-dontwarn io.netty.internal.tcnative.ResultCallback
-dontwarn io.netty.internal.tcnative.SSL
-dontwarn io.netty.internal.tcnative.SSLContext
-dontwarn io.netty.internal.tcnative.SSLPrivateKeyMethod
-dontwarn io.netty.internal.tcnative.SSLSession
-dontwarn io.netty.internal.tcnative.SSLSessionCache
-dontwarn io.netty.internal.tcnative.SessionTicketKey
-dontwarn io.netty.internal.tcnative.SniHostNameMatcher
-dontwarn java.beans.ConstructorProperties
-dontwarn java.beans.Transient
-dontwarn java.lang.management.ManagementFactory
-dontwarn java.lang.management.RuntimeMXBean
-dontwarn lzma.sdk.ICodeProgress
-dontwarn lzma.sdk.lzma.Encoder
-dontwarn net.jpountz.lz4.LZ4Compressor
-dontwarn net.jpountz.lz4.LZ4Exception
-dontwarn net.jpountz.lz4.LZ4Factory
-dontwarn net.jpountz.lz4.LZ4FastDecompressor
-dontwarn net.jpountz.xxhash.XXHash32
-dontwarn net.jpountz.xxhash.XXHashFactory
-dontwarn org.apache.log4j.Level
-dontwarn org.apache.log4j.Logger
-dontwarn org.apache.log4j.Priority
-dontwarn org.apache.logging.log4j.Level
-dontwarn org.apache.logging.log4j.LogManager
-dontwarn org.apache.logging.log4j.Logger
-dontwarn org.apache.logging.log4j.message.MessageFactory
-dontwarn org.apache.logging.log4j.spi.ExtendedLogger
-dontwarn org.apache.logging.log4j.spi.ExtendedLoggerWrapper
-dontwarn org.eclipse.jetty.npn.NextProtoNego$ClientProvider
-dontwarn org.eclipse.jetty.npn.NextProtoNego$Provider
-dontwarn org.eclipse.jetty.npn.NextProtoNego$ServerProvider
-dontwarn org.eclipse.jetty.npn.NextProtoNego
-dontwarn org.jboss.marshalling.ByteInput
-dontwarn org.jboss.marshalling.ByteOutput
-dontwarn org.jboss.marshalling.Marshaller
-dontwarn org.jboss.marshalling.MarshallerFactory
-dontwarn org.jboss.marshalling.MarshallingConfiguration
-dontwarn org.jboss.marshalling.Unmarshaller
-dontwarn org.joda.convert.FromString
-dontwarn org.joda.convert.ToString
-dontwarn reactor.blockhound.BlockHound$Builder
-dontwarn reactor.blockhound.integration.BlockHoundIntegration
-dontwarn sun.security.x509.AlgorithmId
-dontwarn sun.security.x509.CertificateAlgorithmId
-dontwarn sun.security.x509.CertificateSerialNumber
-dontwarn sun.security.x509.CertificateSubjectName
-dontwarn sun.security.x509.CertificateValidity
-dontwarn sun.security.x509.CertificateVersion
-dontwarn sun.security.x509.CertificateX509Key
-dontwarn sun.security.x509.X500Name
-dontwarn sun.security.x509.X509CertImpl
-dontwarn sun.security.x509.X509CertInfo
