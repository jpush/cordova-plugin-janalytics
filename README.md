# cordova-plugin-janalytics

极光官方提供的[极光统计](https://www.jiguang.cn/analytics) Cordova 插件。

## Install

本插件依赖 [cordova-plugin-jcore](https://github.com/wilhantian/cordova-plugin-jcore)，该插件随本插件安装时自动安装。

- 通过cordova plugin 安装，要求 Cordova CLI 5.0+：

  ```shell
  cordova plugin add cordova-plugin-janalytics --variable API_KEY=极光appKey --variable CHANNEL=渠道名
  ```

- 或直接通过 url 安装：

  ```shell
  cordova plugin add https://github.com/wilhantian/cordova-plugin-janalytics --variable API_KEY=极光appKey --variable CHANNEL=渠道名
  ```

- 或下载到本地安装：

  ```shell
  cordova plugin add Your_Plugin_Path  --variable API_KEY=极光KEY --variable CHANNEL=渠道名
  ```
  > 注意: CHANNEL不能为纯数字

## 参考资料

[官方文档](https://docs.jiguang.cn/janalytics/guideline/intro/)

## Support

- [极光社区](http://community.jiguang.cn/)
- QQ 交流群：413602425 / 524248013

## License

MIT © [JiGuang](/license)
