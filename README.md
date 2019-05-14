# WEE
Android app for wee

# Lombok

### Make [lombok](https://projectlombok.org/setup/android) work with your Android Studio
- Go to File > Settings > Plugins
- Click on Browse repositories...
- Search for Lombok Plugin
- Click on Install plugin
- Restart Android Studio

### In order to remove "Lombok Requires Annotation Processing" Warning...
`Note that you need to do the following steps in Welcome screen`
- File->Other Settings->Default Settings
- Expand Build, Execution, Deployment
- Expand Compiler
- In Annotation Processors check Enable annotation processing
- You may need to re-open the project to get the settings to take effect.

Also create the file .idea/compiler.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project version="4">
    <component name="CompilerConfiguration">
        <annotationProcessing>
            <profile default="true" name="Default" enabled="true">                <!-- Used to remove lombok warning -->
                <processorPath useClasspath="true" />
            </profile>
        </annotationProcessing>
    </component>
</project>
```

# Butter Knife

Field and method binding for Android views which uses annotation processing to generate boilerplate
code for you.

 * Eliminate `findViewById` calls by using `@BindView` on fields.
 * Group multiple views in a list or array. Operate on all of them at once with actions,
   setters, or properties.
 * Eliminate anonymous inner-classes for listeners by annotating methods with `@OnClick` and others.
 * Eliminate resource lookups by using resource annotations on fields
```java
class ExampleActivity extends Activity {
  @BindView(R.id.user) EditText username;

  @BindString(R.string.login_error) String loginErrorMessage;

  @OnClick(R.id.submit) void submit() {
    // TODO call server...
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    ButterKnife.bind(this);
    // TODO Use fields...
  }
}
```

# Retrofit 2.x
Retrofit is a REST Client for Java and Android. It makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice. In Retrofit you configure which converter is used for the data serialization. Typically for JSON you use GSon, but you can add custom converters to process XML or other protocols. Retrofit uses the OkHttp library for HTTP requests.

[how-to retrofit](https://square.github.io/retrofit/)

# Migrated to AndroidX
AndroidX is the open-source project that the Android team uses to develop, test, package, version and release libraries within Jetpack.

[AndroidX Overview](https://developer.android.com/jetpack/androidx)