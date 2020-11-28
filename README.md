# spring-boot-common

## セットアップ(Github Personal Access Token がある場合)

1. Github Packages(maven repo) を `%USERPROFILE%/.m2/settings.xml` に追記する

```settings.xml:xml
  <servers>
    <server>
      <id>spring-boot-common</id>
      <username>gn5r</username>
      <password>Personal Access Token</password>
    </server>
  </servers>

  <profiles>
    <profile>
      <id>github</id>
      <repositories>
        <repository>
          <id>spring-boot-common</id>
          <spapshots>
            <enabled>true</enabled>
          </snapshots>
          <releases>
            <enabled>true</enabled>
          </releases>
          <url>https://maven.pkg.github.com/gn5r/spring-boot-common</url>
        </repository>
      </repositories>
    </profile>
  </profiles>

  <activeProfiles>
    <activeProfile>github</activeProfile>
  </activeProfiles>
```

2. 依存関係を `pom.xml` に追記する

```pom.xml:xml
<dependency>
  <groupId>com.github.gn5r</groupId>
  <artifactId>spring-boot-common<artifactId>
  <version>VERSION</version>
</dependency>
```

## セットアップ(Github Personal Access Token が無い場合)

1. `spring-boot-common.zip` を Github tag assets からダウンロードする

2. zip ファイルを展開し、`${basedir}/lib/` に jar ファイルをコピーする

3. 依存関係を `pom.xml` に追記する

```pom.xml:xml
<dependency>
  <groupId>com.github.gn5r</groupId>
  <artifactId>spring-boot-common<artifactId>
  <version>VERSION</version>
  <scope>system</scope>
  <systemPath>${basedir}/lib/spring-boot-common-VERSION.jar</systemcPath>
</dependency>
```

## ライブラリをインストールする

- 以下コマンドを実行して当該ライブラリをインストールする

  `mvn clean install`

## プロパティを読み込む設定

- `@SpringBootApplication` が付与されているクラスにて以下のように本パッケージを各 SpringBootApplication 全体で読み込むようにする
  - (プロパティを読み込むクラス毎にアノテーションを付与する方法でも可。但しあっちこっちで記述しないといけなくなる可能性もあるので Application クラスで読み込むようにした方が無難)

```java:SpringBootAppcication.java
/**
 * @ComponentScanアノテーションを付与し、本パッケージを記述して読み込むようにする
 * また本パッケージのみを記述すると、各アプリケーションのBeanなどを読み込まなくなってしまうので、続けて各アプリケーションのパッケージも記述すること
 */
@SpringBootApplication
@ComponentScan(nameGeneratir = FQCNBeanNameGenerator.class, value = {"com.github.gn5r.spring.boot.common", "各アプリケーションのパッケージ"})
public class SpringBootApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootApplication.class, args);
  }
}
```
