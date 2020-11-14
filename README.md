# spring-boot-common

## セットアップ(Github Personal Access Token がある場合)

1. Github Packages(maven repo) を `%USERPROFILE%/.m2/settings.xml` に追記する

```settings.xml:xml
  <servers>
    <server>
      <id>github</id>
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
