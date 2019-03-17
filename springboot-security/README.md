1. 系统目录结构

├── README.md  帮助文档  
├── mvnw  
├── mvnw.cmd  
├── pom.xml  
├── springboot-security.iml  
└── src  
    ├── main  
    │   ├── java  
    │   │   └── com  
    │   │       └── li  
    │   │           └── springbootsecurity   
    │   │               ├── SpringbootSecurityApplication.java  
    │   │               ├── bo  
    │   │               │   ├── ResponseUserToken.java  
    │   │               │   ├── ResultCode.java  
    │   │               │   ├── ResultJson.java  
    │   │               │   └── ResultUtil.java  
    │   │               ├── code  
    │   │               │   └── MyBatisPlusGenerator.java  
    │   │               ├── config  
    │   │               │   ├── JwtAuthenticationEntryPoint.java  
    │   │               │   ├── JwtAuthenticationTokenFilter.java  
    │   │               │   ├── RestAuthenticationAccessDeniedHandler.java  
    │   │               │   └── WebSecurityConfig.java  
    │   │               ├── controller  
    │   │               │   ├── LoginController.java  
    │   │               │   ├── RoleController.java  
    │   │               │   └── UserController.java  
    │   │               ├── exception  
    │   │               │   ├── CustomException.java  
    │   │               │   └── DefaultExceptionHandler.java  
    │   │               ├── mapper  
    │   │               │   ├── RoleMapper.java  
    │   │               │   └── UserMapper.java  
    │   │               ├── model  
    │   │               │   ├── Role.java  
    │   │               │   └── User.java  
    │   │               ├── security  
    │   │               │   └── SecurityUser.java  
    │   │               ├── service  
    │   │               │   ├── IRoleService.java  
    │   │               │   ├── IUserService.java  
    │   │               │   └── impl  
    │   │               │       ├── RoleServiceImpl.java  
    │   │               │       └── UserServiceImpl.java  
    │   │               └── utils  
    │   │                   └── JwtTokenUtil.java  
    │   └── resources  
    │       ├── application.properties  
    │       ├── mapper  
    │       │   ├── RoleMapper.xml  
    │       │   └── UserMapper.xml  
    │       ├── static  
    │       └── templates  
    └── test  
        └── java  
            └── com  
                └── li  
                    └── springbootsecurity  
                        └── SpringbootSecurityApplicationTests.java  
