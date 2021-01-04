# Andrioid-utils-ktx

## 混淆
已在  `consumer-rules.pro` 自动添加
```
-keep  class * implements androidx.viewbinding.ViewBinding {
  public static *** bind(...);
  public static *** inflate(...);
}

```
