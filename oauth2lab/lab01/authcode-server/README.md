# 基于Spring Security OAuth2的授权服务器：授权码模式

## 操作

### 1. 获取授权码
浏览器请求：</br>
<a target="_Blank" href="http://localhost:8080/oauth/authorize?client_id=clientapp&redirect_uri=http://localhost:9001/callback&response_type=code&scope=read_userinfo">http://localhost:8080/oauth/authorize?client_id=clientapp&redirect_uri=http://localhost:9001/callback&response_type=code&scope=read_userinfo</a>

**注意**：

- 浏览器要输入application.properties中定义的：用户名 / 密码，在Postman中相当于设置Authorization
	- type:Basic Auth
	- 用户名 / 密码 
- state参数暂忽略

响应案例：

	#code就是授权码
	http://localhost:9001/callback?code=8uYpdo

### 2. 获取访问令牌
借用Postman工具：

POST --user clientapp:12345 <a target="_Blank" href="http://localhost:8080/oauth/">http://localhost:8080/oauth/token</a> -H "content-type: application/x-www-form-urlencoded" -d "code=8uYpdo&grant_type=authorization_code&redirect_uri=http%3A%2F%2Flocalh ost%3A9001%2Fcallback&scope=read_userinfo"

**注意**：

- Authorization中要设置客户应用凭证： 
	- type:Basic Auth
	- 用户名 / 密码：clientapp / 12345

案例响应：

	{
	    "access_token": "36cded80-b6f5-43b7-bdfc-594788a24530",
	    "token_type": "bearer",
	    "expires_in": 43199,
	    "scope": "read_userinfo"
	}

### 3. 调用API

GET <a target="_Blank" href="http://localhost:8080/api/userinfo">http://localhost:8080/api/userinfo</a> -H "authorization: Bearer 36cded80-b6f5-43b7-bdfc-594788a24530"

案例响应：

	{
	    "name": "junjun",
	    "email": "junjun@oauth2.com"
	}