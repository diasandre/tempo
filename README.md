![Logo](https://github.com/diasandre/tempo/blob/main/Logo.png "by DALL-E")

# tempo
[![codebeat badge](https://codebeat.co/badges/62e72b08-44a4-4291-a763-4e5bdc345195)](https://codebeat.co/projects/github-com-diasandre-tempo-main)

This Kotlin project provides a convenient function for converting dates between different types, including String, LocalDate, and Instant.

## Installation
To use this library, you need to add the JitPack repository to your project's build file.

### Gradle
To install the library in your project using Gradle, add the following to your build.gradle file:

```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
Then, add the following dependency to your build.gradle file:

```
dependencies {
    implementation 'com.github.diasandre:tempo:0.1.1'
}
```
If you're using the Kotlin DSL for your Gradle build, add the following to your build.gradle.kts file:

```
repositories {
    maven("https://jitpack.io")
}
```

Then, add the following dependency to your build.gradle.kts file:

```
dependencies {
    implementation("com.github.diasandre:tempo:0.1.1")
}
```
### Maven
To install the library in your project using Maven, add the following to your pom.xml file:

```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Then, add the following dependency to your pom.xml file:

```
<dependency>
    <groupId>com.github.diasandre</groupId>
        <artifactId>tempo</artifactId>
    <version>0.1.1</version>
</dependency>
```
Please make sure to update the version number to the latest one available.

## Usage
To use the extension function, simply call handle on your input value (String, LocalDate and Instant) and provide a configuration object with the desired input and output configurations. For example, to convert a String in the format "yyyy-MM-dd" to a YearMonth object, you can use the following code:

```
val actual: YearMonth = "2022-01-01".handle {
            input {
                pattern = LOCAL_DATE_PATTERN
            }
            output {
                type = OUTPUT_TYPE.YEAR_MONTH
            }
        }
```

The handle function will automatically parse the input String using the provided pattern and convert it to the desired output type.

## Supported Types
The following types are currently supported for input and output:

- String
- LocalDate
- Instant
- YearMonth

If you need to convert to other types, please let us know and we will consider adding support for them.

## Customizing the Input Pattern
When using the handle function with a String input, you can provide a custom pattern for parsing the input. The default pattern is "yyyy-MM-dd", but you can specify any valid [SimpleDateFormat](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/text/SimpleDateFormat.html) pattern.

```
val actual: YearMonth = "2022/01/01".handle {
            input {
                pattern = DateTimeFormatter.ofPattern("yyyy/MM/dd")
            }
            output {
                type = OUTPUT_TYPE.YEAR_MONTH
            }
        }
```

## Updating the Output Value
The update configuration allows you to specify updates to be applied to the output value. The following updates are currently supported:

- `day`: Allows you to set the day of the output value.
- `month`: Allows you to set the month of the output value.
- `year`: Allows you to set the year of the output value.

You can also specify an operation type of PLUS or MINUS to add or subtract a certain number to the original value.

```
val actual: YearMonth = "2022-01-01".handle {
            input {
                pattern = LOCAL_DATE_PATTERN
            }
            output {
                type = OUTPUT_TYPE.YEAR_MONTH
                update {
                    day = Day(1)
                    month = Month(2, OPERATION_TYPE.PLUS)
                    year = Year(2020)
                }
            }
        }
```

## Contributing
If you find any bugs or have suggestions for improvements, please open an issue or submit a pull request. We welcome any contributions to make this project more useful for the Kotlin community.

The project logo was created by DALL-E.
