# Compose Pixel Perfect Design

A lightweight, powerful Jetpack Compose library designed to help developers achieve pixel-perfect, responsive UI across all Android screen sizes. By using percentage-based scaling and smart safe area utilities, you can ensure your design looks consistent on any device.

## About

In modern Android development, fragmentation is a major challenge. **Compose Pixel Perfect Design** simplifies this by providing extension properties that scale based on the actual device dimensions. Whether it's 10% of the screen height (`0.1.dh`) or 5% of the screen width (`0.05.dw`), your layout stays proportional.

## Features

- **Percentage-based Scaling**: Define sizes as a fraction of device width (`dw`), height (`dh`), or text scale (`sh`).
- **Safe Area Support**: Easily handle status bar and navigation bar insets with `Modifier.safeArea()`.
- **Dynamic Scale Factor**: Global `ScaleFactor` for easy accessibility adjustments or tablet optimization.
- **Window Metrics Integration**: Uses `androidx.window` for accurate screen dimension calculations.
- **Edge-to-Edge Ready**: Built to work seamlessly with Android's modern edge-to-edge display mode.

## Tech Stack

- **Language**: Kotlin 2.2.10+
- **UI Framework**: Jetpack Compose
- **Build System**: Gradle Kotlin DSL
- **Key Libraries**: AndroidX Window, Compose Material3, Core KTX

## Package Structure

```text
├── app                      # Sample application module
│   └── src/main/java        # Example usage of the library
└── pixelperfectdesign        # Core library module
    └── src/main/java
        └── com.compose.pixelperfectdesign
            └── utils.kt     # Core extensions (.dw, .dh, .sh, etc.)
```

## Dependency Setup

### 1. Add the module to your project
Include the `:pixelperfectdesign` module in your `settings.gradle.kts`:

```kotlin
include(":pixelperfectdesign")
```

### 2. Implement in your app's build.gradle.kts
```kotlin
dependencies {
    implementation(project(":pixelperfectdesign"))
}
```

## Usage

### 1. Initialize the Library
Call `initSize()` in your `MainActivity` before `setContent`:

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // Initialize device metrics
        initSize() 
        
        setContent {
            MyTheme {
                Scaffold { innerPadding ->
                    MyScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}
```

### 2. Use Extension Properties
Scale any dimension relative to the screen size:

```kotlin
@Composable
fun MyScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(0.8.dw)   // 80% of device width
            .height(0.2.dh)  // 20% of device height
            .safeArea()     // Automatic status/nav bar padding
    ) {
        Text(
            text = "Pixel Perfect",
            fontSize = 0.05.sh // Proportional font size
        )
    }
}
```

## Architecture

The library follows a **Utility-First** approach, leveraging Kotlin Extension Properties on `Number` and `Modifier` to provide a clean, declarative API.

- **Initialization**: `initSize()` captures the current window metrics using `WindowMetricsCalculator`.
- **Scaling Logic**: Uses `coerceAtMost` and `coerceAtLeast` to ensure consistent behavior across portrait and landscape modes.
- **Safe Area**: Wraps standard `WindowInsets` into easier-to-use modifiers and properties.

## Contribution

Contributions are welcome! If you find a bug or have a feature request, please open an issue or submit a pull request.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the Apache-2.0 License. See `LICENSE` for more information.