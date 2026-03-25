# my-ddd-project - DDD 脚手架

## 1. 脚手架安装使用

### 1. 构建安装

```shell
mvn clean install
```

- 在 IntelliJ IDEA 执行 `mvn clean install` 这样会把脚手架安装到本地仓库中

### 2. 发布

```shell
mvn clean install net.ju-n.maven.plugins:checksum-maven-plugin:1.2:artifacts
```

```shell
mvn deploy
```

### 3. 使用

```shell
mvn archetype:generate \
  -DarchetypeGroupId=dev.stackbug \
  -DarchetypeArtifactId=ddd-scaffold-lite \
  -DarchetypeVersion=1.0 \
  -DgroupId=com.example \
  -DartifactId=my-project \
  -Dversion=1.0-SNAPSHOT
```

## 2. 工程结构介绍

```
.
├── README.md
├── docs
│   └── dev-ops
│       ├── app
│       │   └── docker-compose-1.0.yml
│       └── mysql
│           └── sql
│               └── my-ddd-project.sql
├── pom.xml
├── my-ddd-app
│   ├── Dockerfile
│   ├── build.sh
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java/dev/stackbug/
│       │   │   ├── Application.java
│       │   │   └── config/
│       │   └── resources/
│       └── test/
├── my-ddd-domain
├── my-ddd-infrastructure
├── my-ddd-trigger
├── my-ddd-types
└── my-ddd-api
```
