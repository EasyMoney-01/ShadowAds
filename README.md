# Shadow Ads 🎬

An Android app that automatically plays **Unity Rewarded Ads** continuously. Once an ad completes, the next ad plays automatically!

## Features ✨

- ✅ **Auto-play Rewarded Ads** - Ads start automatically when app launches
- ✅ **Continuous Loop** - One ad finishes → Next ad starts automatically
- ✅ **Unity Ads Integration** - Uses Unity Ads SDK (Game ID: 6066543)
- ✅ **Error Handling** - Automatic retry on ad failures
- ✅ **Beautiful UI** - Dark theme with real-time status updates
- ✅ **Kotlin** - Modern Android development with Kotlin

## Tech Stack 🛠️

- **Language**: Kotlin
- **Ads SDK**: Unity Ads 4.9.2
- **Min SDK**: Android 5.0 (API 21)
- **Target SDK**: Android 13 (API 33)
- **Build System**: Gradle

## Project Structure 📁

```
ShadowAds/
├── app/src/main/java/com/shadowads/
│   ├── MainActivity.kt          # Main activity with UI
│   └── AdManager.kt             # Unity Ads management
├── app/src/main/res/
│   ├── layout/
│   │   └── activity_main.xml    # Main UI layout
│   ├── values/
│   │   ├── strings.xml          # String resources
│   │   ├── colors.xml           # Color definitions
│   │   └── themes.xml           # App themes
├── app/src/main/AndroidManifest.xml
├── build.gradle                 # Build configuration
├── proguard-rules.pro          # ProGuard rules
└── README.md                    # This file
```

## Setup Instructions 🚀

### Prerequisites
- Android Studio (Latest)
- Android SDK 21+
- Gradle 7.4.2+

### Steps to Build

1. **Clone the repository**
   ```bash
   git clone https://github.com/EasyMoney-01/ShadowAds.git
   cd ShadowAds
   ```

2. **Open in Android Studio**
   - File → Open → Select ShadowAds folder
   - Wait for Gradle sync to complete

3. **Update Game ID** (Optional)
   - Open `app/src/main/java/com/shadowads/AdManager.kt`
   - Change `GAME_ID` to your Unity Ads Game ID
   - Current Game ID: `6066543`

4. **Build & Run**
   ```bash
   # Build
   ./gradlew build
   
   # Install on device/emulator
   ./gradlew installDebug
   ```

5. **Run on device**
   - Connect Android device via USB (with USB Debugging enabled)
   - Click "Run" button or use: `./gradlew installDebug`

## How It Works 🎯

### Ad Flow:
```
App Launch
    ↓
Initialize Unity Ads
    ↓
Show Rewarded Ad
    ↓
Ad Completes (2 sec delay)
    ↓
Show Next Ad
    ↓
(Loop continues...)
```

### Key Components:

**AdManager.kt**
- Initializes Unity Ads with Game ID
- Shows rewarded ads
- Handles ad lifecycle (start, complete, skip, error)
- Triggers next ad automatically on completion

**MainActivity.kt**
- UI Management
- Auto-play logic
- Status updates
- Error handling with retry

## Configuration 📝

### Auto-play Settings:
Edit delays in `MainActivity.kt`:
```kotlin
// After ad completes
delay(2000) // 2 second delay before next ad

// After error
delay(3000) // 3 second delay before retry
```

### Ad Placement:
- Placement ID: `Rewarded_Android`
- Type: Rewarded Ads
- Auto-loop: Enabled

## Permissions Required 🔐

- `android.permission.INTERNET` - Download ads
- `android.permission.ACCESS_NETWORK_STATE` - Check network

## Troubleshooting 🔧

### Ads not showing?
1. Check internet connection
2. Verify Game ID: `6066543`
3. Ensure ads are active in Unity Ads dashboard
4. Check logcat for error messages

### Emulator Issues?
- Use API 21+ for best compatibility
- Emulator may have network issues - use real device
- Check Play Services on emulator

### Build Errors?
```bash
# Clean and rebuild
./gradlew clean build
```

## Dependencies 📦

```gradle
// Core
androidx.core:core-ktx:1.10.1
androidx.appcompat:appcompat:1.6.1
androidx.constraintlayout:constraintlayout:2.1.4

// Ads
com.unity3d.ads:unity-ads:4.9.2

// Lifecycle
androidx.lifecycle:lifecycle-runtime-ktx:2.6.1

// Material Design
com.google.android.material:material:1.9.0
```

## Version Info 📌

- **App Version**: 1.0.0
- **Version Code**: 1
- **Unity Ads SDK**: 4.9.2
- **Kotlin**: 1.8.10

## License 📄

This project is open source and available under MIT License.

## Author 👤

**EasyMoney-01**
- GitHub: [@EasyMoney-01](https://github.com/EasyMoney-01)

## Support 💬

For issues and questions:
1. Check the [GitHub Issues](https://github.com/EasyMoney-01/ShadowAds/issues)
2. Review the troubleshooting section above
3. Create a new issue with details

---

**Happy Ad Watching! 🎉**
