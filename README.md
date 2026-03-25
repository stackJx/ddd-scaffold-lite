# DDD Scaffold Lite

轻量级 DDD（领域驱动设计）脚手架，基于 Spring Boot 2.7 + 六边形架构，一键生成标准化的 DDD 工程结构。

## 快速开始

### 方式一：IntelliJ IDEA 创建

**1. 配置 Maven**

在 `~/.m2/settings.xml` 中添加 JitPack 仓库：

```xml
<profiles>
    <profile>
        <id>jitpack</id>
        <repositories>
            <repository>
                <id>jitpack.io</id>
                <url>https://jitpack.io</url>
            </repository>
        </repositories>
    </profile>
</profiles>

<activeProfiles>
    <activeProfile>jitpack</activeProfile>
</activeProfiles>
```

**2. 新建项目**

`File → New → Project → Maven Archetype`

| 字段 | 值 |
|---|---|
| Catalog | 管理目录 → 添加 `https://jitpack.io` |
| GroupId | `com.github.stackJx` |
| ArtifactId | `ddd-scaffold-lite` |
| Version | `v1.0` |

填写你的项目 GroupId、ArtifactId 后点击 Create 即可。

### 方式二：命令行创建

```bash
mvn archetype:generate \
  -DarchetypeGroupId=com.github.stackJx \
  -DarchetypeArtifactId=ddd-scaffold-lite \
  -DarchetypeVersion=v1.0 \
  -DgroupId=com.example \
  -DartifactId=my-project \
  -Dversion=1.0-SNAPSHOT \
  -DinteractiveMode=false
```

## 工程结构

```
my-project
├── my-project-api              # API 契约层：接口定义、DTO、Response
├── my-project-app              # 应用启动层：Spring Boot 入口、配置
├── my-project-domain           # 领域层：聚合根、实体、值对象、领域服务
├── my-project-infrastructure   # 基础设施层：DAO、Repository 实现、Gateway
├── my-project-trigger          # 触发器层：HTTP 控制器、定时任务、消息监听
├── my-project-types            # 通用类型：枚举、异常、常量
├── docs                        # 运维文档：Docker、SQL、Shell
└── pom.xml
```

## 模块依赖关系

```
app → trigger → domain → types
         ↓         ↑
   infrastructure ─┘

api（独立，无依赖）
```

## 技术栈

| 组件 | 版本 |
|---|---|
| Java | 1.8+ |
| Spring Boot | 2.7.12 |
| MyBatis | 2.1.4 |
| MySQL | 8.x |
| Guava | 32.1.3-jre |
| Lombok | latest |

## 领域包结构

```
domain
├── xxx                         # 限界上下文 A
│   ├── adapter                 # 端口适配（依赖倒置接口）
│   │   └── repository          # 仓储接口
│   ├── model
│   │   ├── aggregate           # 聚合根
│   │   ├── entity              # 实体
│   │   └── valobj              # 值对象
│   └── service                 # 领域服务
└── yyy                         # 限界上下文 B（同上结构）
```

## License

Apache License 2.0
