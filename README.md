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
