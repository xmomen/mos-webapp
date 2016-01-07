# mos-webapp

## angularjs 与 jsp权限兼容集成解决方案

- 在angularjs所有异步请求中添加 XMLHttpRequest header属性
- 服务器端在权限拦截过滤器对请求类型做判断，若头部header中包含X-Requested-With getHeader("X-Requested-With")则为异步请求，返回json格式数据，若不包含则做页面跳转，跳转到登录页面或权限不足页面
- [参考博客](http://my.oschina.net/WMSstudio/blog/162594)