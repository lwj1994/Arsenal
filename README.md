# Andrioid-utils-ktx
[ ![Download](https://api.bintray.com/packages/wenchieh/maven/Android-utils-ktx/images/download.svg) ](https://bintray.com/wenchieh/maven/Android-utils-ktx/_latestVersion)
```
implementation 'com.lwjlol:utils-ktx:0.1.3'
```
## 使用
```
UtilsInitializer.init(applicationContext)
```

## 混淆
已在  `consumer-rules.pro` 自动添加
```
-keep  class * implements androidx.viewbinding.ViewBinding {
  public static *** bind(...);
  public static *** inflate(...);
}

```
