# LectureApplication
用Android Studio和Java开发的讲座信息查询预约系统APP

### 简介
在实际实现时，本系统包含管理员和普通用户两种角色：
1. 管理员可以对讲座信息进行发布、删除（取消发布）、编辑和查询等操作，还可以对用户预约讲座的权限进行设置，帮助用户重置密码等。
2. 对于普通用户，在第一次进入系统时需要进行注册，在注册时可以选择喜欢的讲座类型，这样在进入系统后系统会优先推送该类型的讲座。普通用户可以在首页中通过关键词、日期等查询讲座信息并进行预约，普通用户可以在历史记录中查询到自己预约过的讲座信息，可以取消预约。普通用户可以在“我的”功能中修改自己的个人信息，更新自己喜爱的讲座类型、电话等，数据库使用的时SQLite。


### 应用程序目录及文件清单
讲座信息查询预约系统主要构建和使用的目录由manifests、java 和 res，在 manifests 中主要对 AndroidManifest.xml 全局声明文件进行了编写，设定程序入口的活动。    
java目录下放置系统主要的java的代码，子目录有data和ui，data目录用于存放数据库相关操作类和封装数据的实体类，ui目录用于存放每个功能模块所用到的Activity、Fragment、ViewModel和UiAdapter类。在data和ui目录之外，还有为管理员和普通用户设置其底部导航栏和与之关联的导航视图的AdminActivity、MainActivity，以及存放全局上下文环境、对象、ViewModel的MyApplication。    
对于资源文件夹res，主要用到的目录有存放图片和图标资源的drawable、存放Fragment、Activity以及RecyclerView的item对应布局的layout、存放底部导航栏样式的menu、关联底部导航栏和Fragment页面的navigation，以及存放系统整体风格的values\themes。
