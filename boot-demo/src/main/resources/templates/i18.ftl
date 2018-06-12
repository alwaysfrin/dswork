<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
	<h1>国际化</h1>
	<h1>${msg}</h1>
    <h2><@spring.message "price"/></h2>
    <h2><@spring.message "pro.name"/></h2>
    <h2><@spring.message "pro.desc"/></h2>
    
    <form action="/changeintern" method="get">
       <input name="lang" type="hidden" value="zh"  />
       <button>切换为中文</button>
    </form>
    <form action="/changeintern" method="get">
       <input name="lang" type="hidden" value="en" />
       <button>切换为英文</button>
    </form>
</body>
</html>