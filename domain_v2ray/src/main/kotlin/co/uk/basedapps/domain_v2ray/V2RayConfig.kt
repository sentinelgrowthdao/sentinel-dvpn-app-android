package co.uk.basedapps.domain_v2ray

internal object V2RayConfig {

  const val config = "{\n" +
    "\t\"dns\": {\n" +
    "\t\t\"hosts\": {\n" +
    "\t\t\t\"domain:googleapis.cn\": \"googleapis.com\"\n" +
    "\t\t},\n" +
    "\t\t\"servers\": [\n" +
    "\t\t\t\"1.1.1.1\"\n" +
    "\t\t]\n" +
    "\t},\n" +
    "\t\"inbounds\": [{\n" +
    "\t\t\t\"listen\": \"127.0.0.1\",\n" +
    "\t\t\t\"port\": 10808,\n" +
    "\t\t\t\"protocol\": \"socks\",\n" +
    "\t\t\t\"settings\": {\n" +
    "\t\t\t\t\"auth\": \"noauth\",\n" +
    "\t\t\t\t\"udp\": true,\n" +
    "\t\t\t\t\"userLevel\": 8\n" +
    "\t\t\t},\n" +
    "\t\t\t\"sniffing\": {\n" +
    "\t\t\t\t\"destOverride\": [\n" +
    "\t\t\t\t\t\"http\",\n" +
    "\t\t\t\t\t\"tls\"\n" +
    "\t\t\t\t],\n" +
    "\t\t\t\t\"metadataOnly\": false,\n" +
    "\t\t\t\t\"routeOnly\": false,\n" +
    "\t\t\t\t\"excludedDomains\": {},\n" +
    "\t\t\t\t\"enabled\": true\n" +
    "\t\t\t},\n" +
    "\t\t\t\"tag\": \"socks\"\n" +
    "\t\t}\n" +
    "\t],\n" +
    "\t\"log\": {\n" +
    "\t\t\"loglevel\": \"info\"\n" +
    "\t},\n" +
    "\t\"outbounds\": [{\n" +
    "\t\t\t\"mux\": {\n" +
    "\t\t\t\t\"concurrency\": 8,\n" +
    "\t\t\t\t\"enabled\": false\n" +
    "\t\t\t},\n" +
    "\t\t\t\"protocol\": \"vmess\",\n" +
    "\t\t\t\"settings\": {\n" +
    "\t\t\t\t\"vnext\": [{\n" +
    "\t\t\t\t\t\"address\": \"%s\",\n" +
    "\t\t\t\t\t\"port\": %1s,\n" +
    "\t\t\t\t\t\"users\": [{\n" +
    "\t\t\t\t\t\t\"alterId\": 0,\n" +
    "\t\t\t\t\t\t\"encryption\": \"\",\n" +
    "\t\t\t\t\t\t\"flow\": \"\",\n" +
    "\t\t\t\t\t\t\"id\": \"%2s\",\n" +
    "\t\t\t\t\t\t\"level\": 8,\n" +
    "\t\t\t\t\t\t\"security\": \"auto\"\n" +
    "\t\t\t\t\t}]\n" +
    "\t\t\t\t}]\n" +
    "\t\t\t},\n" +
    "\t\t\t\"streamSettings\": {\n" +
    "\t\t\t\t\"network\": \"grpc\",\n" +
    "\t\t\t\t\"grpcSettings\": {\n" +
    "\t\t\t\t\t\"serviceName\": \"\",\n" +
    "\t\t\t\t\t\"multiMode\": false\n" +
    "\t\t\t\t}\n" +
    "\t\t\t},\n" +
    "\t\t\t\"tag\": \"proxy\"\n" +
    "\t\t},\n" +
    "\t\t{\n" +
    "\t\t\t\"protocol\": \"freedom\",\n" +
    "\t\t\t\"settings\": {},\n" +
    "\t\t\t\"tag\": \"direct\"\n" +
    "\t\t},\n" +
    "\t\t{\n" +
    "\t\t\t\"protocol\": \"blackhole\",\n" +
    "\t\t\t\"settings\": {\n" +
    "\t\t\t\t\"response\": {\n" +
    "\t\t\t\t\t\"type\": \"http\"\n" +
    "\t\t\t\t}\n" +
    "\t\t\t},\n" +
    "\t\t\t\"tag\": \"block\"\n" +
    "\t\t}\n" +
    "\t],\n" +
    "\t\"routing\": {\n" +
    "\t\t\"domainStrategy\": \"IPIfNonMatch\",\n" +
    "\t\t\"rules\": [{\n" +
    "\t\t\t\"ip\": [\n" +
    "\t\t\t\t\"1.1.1.1\"\n" +
    "\t\t\t],\n" +
    "\t\t\t\"outboundTag\": \"proxy\",\n" +
    "\t\t\t\"port\": \"53\",\n" +
    "\t\t\t\"type\": \"field\"\n" +
    "\t\t}]\n" +
    "\t}\n" +
    "}"
}
