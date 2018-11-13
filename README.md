# cordova-plugin-janalytics

极光官方提供的[极光统计](https://www.jiguang.cn/analytics) Cordova 插件。

>注意：从 v1.2.0 开始支持 cordova-android 7.0.0，因 cordova-android 7.0.0 修改了 Android 项目结构，因此不兼容之前的版本，升级前请务必注意。
>
>如果需要在cordova-android 7.0.0之前版本集成最新插件，参照[这篇文章](https://www.jianshu.com/p/23b117ca27a6)
>
>如果需要安装之前版本的插件，请先自行安装 v1.2.0 以下版本（建议安装 v1.1.12，cordova-plugin-jcore 向下兼容）的 [cordova-plugin-jcore](https://github.com/jpush/cordova-plugin-jcore)，再安装插件，否则运行会报错。
>
>[Cordova Android版本与原生版本对应表](http://cordova.apache.org/docs/en/latest/guide/platforms/android/index.html#requirements-and-support)

## Install

- 通过 cordova plugin 安装，要求 Cordova CLI 5.0+：

  ```shell
  cordova plugin add cordova-plugin-janalytics --variable APP_KEY=极光appKey
  ```

- 或直接通过 url 安装：

  ```shell
  cordova plugin add https://github.com/jpush/cordova-plugin-janalytics --variable APP_KEY=极光appKey
  ```

- 或下载到本地安装：

  ```shell
  cordova plugin add <plugin_local_path> --variable APP_KEY=极光appKey
  ```

## API

可直接参考 [JAnalytics.js](/www/JAnalytics.js) 文件。

## 参考资料

[官方文档](https://docs.jiguang.cn/janalytics/guideline/intro/)

## Support

- [极光社区](http://community.jiguang.cn/)
- QQ 交流群：413602425 / 524248013

## License

MIT © [JiGuang](/license)
