### # https数字证书
* 生成
` keytool -genkey -alias tomcathttps -keyalg RSA -keysize 2048 -keystore sang.p12 -validity 365`
` keytool -importkeystore -srckeystore sang.p12 -destkeystore sang.p12 -deststoretype pkcs12`
* 复制sang.p12到项目的根目录下
* 配置
```properties
server.ssl.key-store=sang.p12
server.ssl.key-alias=tomcathttps
server.ssl.key-store-password=nobug666
```