# 基于Spring Security OAuth2的授权服务器：密码模式

## 操作

### 1. 获取访问令牌
curl -X POST --user clientapp:112233 <a target="_Blank" href="http://localhost:8080/oauth/token">http://localhost:8080/oauth/token</a> -H "accept: application/json" -H "content-type: application/x-www-form-urlencoded" -d "grant_type=password&username=bobo&password=xyz&scope=read_userinfo"

响应案例：

	{
	    "access_token": "58a02fd5-87f5-44ff-bbdd-d429cf6a2f60",
	    "token_type": "bearer",
	    "expires_in": 43199,
	    "scope": "read_userinfo"
	}
### 2. 调用API
curl -X GET <a target="_Blank" href="http://localhost:8080/api/userinfo">http://localhost:8080/api/userinfo</a> -H "authorization: Bearer 58a02fd5-87f5-44ff-bbdd-d429cf6a2f60"

案例响应：

	{
	    "name": "junjun",
	    "email": "junjun@oauth2.com"
	}