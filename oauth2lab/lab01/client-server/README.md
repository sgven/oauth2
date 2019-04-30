# 基于Spring Security OAuth2的授权服务器：客户端模式

## 操作

### 1. 获取访问令牌
curl -X POST "<a target="_Blank" href="http://localhost:8080/oauth/token">http://localhost:8080/oauth/token</a>" --user clientdevops:123 -d "grant_type=client_credentials&scope=devops"

响应案例：

	{
	    "access_token": "776b162a-949e-4dcb-b16b-9985e8171dc0",
	    "token_type": "bearer",
	    "expires_in": 43188,
	    "scope": "devops"
	}
### 2. 调用API
curl -X GET <a target="_Blank" href="target="_Blank"">http://localhost:8080/api/userlist</a> -H "authorization: Bearer 776b162a-949e-4dcb-b16b-9985e8171dc0"

案例响应：

	[
	    {
	        "name": "xiejun",
	        "email": "xiejunb@yonyou.com"
	    },
	    {
	        "name": "cdr",
	        "email": "cdr@yonyou.com"
	    },
	    {
	        "name": "xx",
	        "email": "xx@yonyou.com"
	    }
	]