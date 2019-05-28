#客户端以授权码方式访问OAuth2服务器案例，使用rest template

##注意事项：
- 1.需要预先安装mysql数据库，再使用mysql workbench预先执行sql脚本src\main\resources\db\oauth_client.sql
- 2.该案例需先启动lab01中的支持授权码模式的OAuth2服务器，端口在8080
- 3.再运行本案例web应用，端口在9001，浏览器访问http://locahost:9001
- 4.该案例授权成功后，客户端会在数据库中缓存访问令牌，如果OAuth2服务器使用内存模式，
则重启OAuth2服务器后原访问令牌将失效，需要清除数据库令牌再重新授权。

## 流程：
- 1.浏览器访问<a target="_blank" href="http://locahost:9001">http://locahost:9001</a>
- 2.会进入到index.html页码，点击跳转到mainpage，这时会被Spring Security拦截，要进行security的登录，即要用客户端应用的账号密码登录
- 3.登录完后，进入后端接口/mainpage，第一次进来没有(授权服务器颁发的)access_token，会重定向到授权服务器的授权端点
- 4.因为本实验启用的是lab01的授权码模式的授权服务器，所以通过tokenService.getAuthorizationEndpoint()获取的授权端点我们指定为lab01服务器的地址
- 5.第一次授权时，授权服务器也需要进行security登录，账号密码配置在application.properties，授权服务器登录后会将用户引导至授权页码
- 6.用户授权后，授权服务器重定向至客户端注册的redirect_uri，这里进到客户端的/callback接口，
这时拿到授权码，调用token端点获取访问令牌access_token，将access_token存到数据库，并回到/mainpage
- 7.这时客户端拿到了access_token，在/mainpage中tryToGetUserInfo()尝试去资源服务器获取用户信息，就可以获取到了
