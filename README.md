# AI Chatbot App

This is an AI-powered chatbot application for Android, built using Kotlin and XML. The app utilizes the Google Gemini model (`gemini-1.5-flash`) for intelligent conversational capabilities. It also includes speech-to-text and text-to-speech functionalities for seamless interaction. The application uses OkHttp for API communication and is developed in Android Studio.

## Features
- Intelligent chatbot powered by Google Gemini (`gemini-1.5-flash`).
- Speech-to-Text: Converts user speech into text for chatbot interaction.
- Text-to-Speech: Reads out chatbot responses for a hands-free experience.
- Simple and user-friendly interface designed with XML layouts.
- Network communication implemented with OkHttp for API integration.

## Getting Started

### Prerequisites
Before you begin, ensure you have the following:
- Android Studio installed on your system.
- Access to the Google Gemini API for obtaining API credentials.
- Internet connectivity for API communication.

### Setup Instructions
1. Clone this repository:
   ```bash
   git clone https://github.com/sujalpatel187/sujalGPT.git
   ```

2. Open the project in Android Studio.

3. Add your Gemini API credentials:
   - Sign up or log in to your Google Cloud account to access the Gemini model.
   - Obtain your API Key from the Google Cloud Console.
   - Add the API Key in your `strings.xml` file:
     ```xml
     <string name="gemini_api_key">YOUR_API_KEY</string>
     ```

4. Sync the project with Gradle to download all dependencies.

### Dependencies
Ensure the following dependencies are added to your `build.gradle` file:
```gradle
implementation 'com.squareup.okhttp3:okhttp:4.9.3'
implementation 'androidx.speech:speech:1.0.0' // For speech-to-text
implementation 'androidx.texttospeech:texttospeech:1.0.0' // For text-to-speech
```

## Usage
1. Launch the app on an Android device or emulator.
2. Use the microphone button to speak to the chatbot.
3. Listen to the chatbot's response through text-to-speech or view it as text on the screen.

## Project Structure
- **UI (XML layouts)**: Designed for intuitive interaction.
- **Kotlin**: Handles application logic, integrates Google Gemini API, and manages speech-to-text and text-to-speech functionalities.
- **OkHttp**: Used for API communication.

## Contributing
Contributions are welcome! Follow these steps to contribute:
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes and push them to your fork.
4. Submit a pull request.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact
For any queries or suggestions, feel free to reach out:
- GitHub: [Sujal Patel](https://github.com/sujalpatel187)

---

Enjoy building with AI!

