# WebViewBugDemo
Js和Java交互

参考：《Android WebView的Js对象注入漏洞解决方案》

文章链接：http://blog.csdn.net/leehong2005/article/details/11808557  
  
这个类解析了Android 4.0以下的WebView注入Javascript对象引发的安全漏洞。重载了addJavascriptInterface方法，如果版本>=4.2，则直接使用原有的方法。如果版本<4.2，则使用map缓存待注入对象（应用了java反射机制）

思路：

	（1）SDK 4.2(API 17)之后的版本，直接调用基类方法addJavascriptInterface进行JS注册
	
	（2）SDK 4.2(API 17)之前的版本，使用map缓存待注入对象，根据缓存的待注入java对象，生成映射的JavaScript代码，也就是桥梁
