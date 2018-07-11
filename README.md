# kotlin_rest_api_demo



项目采用spring+jwt+jpa,使用gradle做依赖管理，数据库使用mysql。flyway管理数据库迁移。



### 演示

生成token：

```
curl -H "Content-Type: application/json" -d '{"username":"admin","password":"admin"}' http://localhost:8080/api/v1/auth

{"token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUzMTcwMjM2NiwiaWF0IjoxNTMxMDk3NTY2fQ.rE3FzDgBZYM1av5vFk2s6Wxh6LXAAxr0xRtJ_LMfxLWGjdugC53_iWlzZfUQhfHNIlBhVx84ixokNZbbI8DGcg"}
```

请求api

```
curl -H "Content-Type: application/json" -H "Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUzMTcwMjM2NiwiaWF0IjoxNTMxMDk3NTY2fQ.rE3FzDgBZYM1av5vFk2s6Wxh6LXAAxr0xRtJ_LMfxLWGjdugC53_iWlzZfUQhfHNIlBhVx84ixokNZbbI8DGcg"  http://localhost:8080/api/v1/get\?name\=tt

[{"id":1,"name":"tt"}]
```

分页

```
curl -H "Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUzMTc5ODgzNiwiaWF0IjoxNTMxMTk0MDM2fQ.9nGPdRF26HLODOB8QX3tDpX_gTlKnYjtWL2aUlZyENkccBfsN4DKjLF_RYpd46y4gE9P9ahiHfdMi7uZu2MZWA"  http://localhost:8080/api/v1/finAll\?size\=20\&page\=0\&sort\=id

{"content":[{"id":1,"name":"tt"},{"id":2,"name":"test"}],"pageable":{"sort":{"sorted":false,"unsorted":true},"offset":0,"pageSize":20,"pageNumber":0,"paged":true,"unpaged":false},"totalPages":1,"totalElements":2,"last":true,"size":20,"number":0,"sort":{"sorted":false,"unsorted":true},"numberOfElements":2,"first":true}

```
