# Arsenal

[![](https://jitpack.io/v/lwj1994/Arsenal.svg)](https://jitpack.io/#lwj1994/Arsenal)

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}



	dependencies {
	        implementation 'com.github.lwj1994:Arsenal:Tag'
	}
```
## 使用
```
Arsenal.init(applicationContext)
```

## 混淆
已在  `consumer-rules.pro` 自动添加
```
-keep  class * implements androidx.viewbinding.ViewBinding {
  public static *** bind(...);
  public static *** inflate(...);
}

```
