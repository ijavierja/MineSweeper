# MineSweeper

## Running MineSweeper

### Prerequisites

- **Java** must be installed (version 8 or higher).

### Step 1: Unzip the Project

1. **Unzip the project folder**:
    - **On macOS/Linux**:
      ```bash
      unzip MineSweeper.zip
      cd MineSweeper
      ```

    - **On Windows**:
        - Right-click the `MineSweeper.zip` file and select **Extract All**.
        - Navigate to the extracted folder in **Command Prompt**.

### Step 2: Build the Project

1. **Build the project** using the Gradle Wrapper (`gradlew`):
    - **On macOS/Linux**:
      ```bash
      ./gradlew build
      ```

    - **On Windows**:
      ```bash
      gradlew.bat build
      ```

### Step 3: Run the Application

1. **Run the application** after the build completes:
    - **On macOS/Linux**:
      ```bash
      java -cp build/classes/kotlin/main org.example.MainKt
      ```

    - **On Windows**:
      ```bash
      java -cp build/classes/kotlin/main org.example.MainKt
      ```
